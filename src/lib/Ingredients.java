/**
 * 
 */
package lib;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

/**
 * @author joakim kaseva
 * @version 4.7.2023
 *
 */
public class Ingredients {
    private final File FILE = new File(System.getProperty("user.dir").toString() + "\\src\\data\\ingredients.dat");
    private static Scanner sc;
    private FileWriter writer;
    
    private Ingredient[][] ingredients;
    
    private int cols = 3;
    private int rows = 0;
    private int maxRows = 10;
    
    /**
     * Default constructor
     */
    public Ingredients() {
        try {            
            sc = new Scanner(FILE);
            int count = 0;
            while (sc.hasNextLine()) {
                sc.nextLine();
                count++;
            }
            sc.close();
            if(count / 3 <= 8) rows = count / 3 + 2;
            else if (count / 3 > 10) return;
            else rows = 10;
            ingredients = new Ingredient[cols][rows];
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * @param col to be set
     * @param row to be set
     */
    public Ingredients(int col, int row) {
        cols = col; rows = row; ingredients = new Ingredient[col][row];
        
    }
    
    /**
     * @param old Ingredients to be expanded
     */
    public Ingredients(Ingredients old) {
        this.cols = old.cols;
        if (old.rows + 2 <= maxRows) this.rows = old.rows + 2;
        else this.rows = maxRows;
        this.ingredients = new Ingredient[cols][rows];
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < old.ingredients[0].length; j++) {
                this.ingredients[i][j] = old.ingredients[i][j];
            }
        }
    }
    
    /**
     * @return rows
     * @example
     * <pre name="test">
     * Ingredients i = new Ingredients(3,3);
     * i.getRows() === 3;
     * </pre>
     */
    public int getRows() {
        return rows;
    }
    
    /**
     * @return true if first value is null after sorting
     */
    public boolean isEmpty() {
        sort();
        return (ingredients[0][0] == null);
    }
    
    /**
     * @return matrix as an Array
     * @example
     * <pre name="test">
     * #import java.util.Arrays;
     * Ingredients i = new Ingredients(3, 1);
     * i.put(new Ingredient(1, "peruna"));
     * Arrays.toString(i.toArray()) === "[1 | peruna, null, null]";
     * </pre>
     */
    public Ingredient[] toArray() {
        Ingredient [] arr = new Ingredient[cols * rows];
        int count = 0;
        for(int j = 0; j < ingredients[0].length; j++) {
            for (int i = 0; i < cols; i ++) {
                arr[count] = ingredients[i][j];
                count++;
            }
        }
        return arr;
    }
    
    /**
     * @return the matrix
     */
    public Ingredient[][] getMatrix(){
        return ingredients;
    }
    
    /**
     * @param id of Ingredient to get
     * @return Ingredient with desired id
     * @example
     * <pre name="test"> 
     * Ingredients i = new Ingredients(3,1);
     * i.put(new Ingredient(1, "peruna"));
     * i.get(1).toString() === "1 | peruna";
     * </pre>
     */
    public Ingredient get(int id) {
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < ingredients[0].length; j++) {
                if (ingredients[i][j] != null && ingredients[i][j].getId() == id) return ingredients[i][j];
            }
        }
        System.out.println("No Ingredient with ID " + id);
        return null;
    }
    
    /**
     * @param name of desired Ingredient
     * @return Ingredient or null if not found
     * @example
     * <pre name="test"> 
     * Ingredients i = new Ingredients(3,1);
     * i.put(new Ingredient(1, "peruna"));
     * i.get("peruna").toString() === "1 | peruna";
     * </pre>
     */
    public Ingredient get(String name) {
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < ingredients[0].length; j++) {
                if (ingredients[i][j] != null && ingredients[i][j].getName().toLowerCase().equals(name.trim().toLowerCase())) return ingredients[i][j];
            }
        }
        System.out.println("No Ingredient with name " + name);
        return null;
    }
    
    /**
     * @param id of ingredient
     * @return true if ingredient found
     */
    public boolean contains(int id) {
        return get(id) != null;
    }
    
    /**
     * @param ingredient to be found in matrix
     * @return true if matrix contains the ingredient
     * @example
     * <pre name="test">
     * Ingredients i = new Ingredients(3,1);
     * Ingredient j = new Ingredient(1, "peruna");
     * i.containsValue(j) === false;
     * i.put(j); i.containsValue(j) === true;
     * </pre>
     */
    public boolean containsValue(Ingredient ingredient) {
        if (ingredient == null) return false;
        System.out.println("Checking if ingredient with ID " + ingredient.getId() + " exists.");
        return (get(ingredient.getId()) != null || get(ingredient.getName()) != null);
    }
    
    /**
     * @param ingredient to be put into matrix (new ingredients always have highest id)
     * @example
     * <pre name="test">
     * Ingredients i = new Ingredients(3, 1);
     * i.count() === 0;
     * i.put(new Ingredient()); i.count() === 1;
     * i.put(new Ingredient());
     * i.put(new Ingredient()); i.count() === 3;
     * i.put(new Ingredient()); i.count() === 3;
     * </pre>
     */
    public void put(Ingredient ingredient) {
        for (int j = 0; j < ingredients[0].length; j++) {
            for (int i = 0; i < cols; i++) {
                if (ingredients[i][j] == null) {
                    ingredients[i][j] = ingredient;
                    sort();
                    return;
                }
            }
        }
        System.out.println("Error: Maximum amount of ingredients (" + cols * rows + ")");
    }
    
    
    /**
     * 
     * 
     * @param id of Ingredient to be removed
     * @return removed Ingredient
     * @example
     * <pre name="test">
     *  Ingredients i = new Ingredients();
     *  i.put(new Ingredient(1, "Liha"));
     *  i.put(new Ingredient(2, "Peruna"));
     *  i.count() === 2;
     *  i.remove(3);    i.count() === 2;
     *  i.remove(2);    i.count() === 1;
     * </pre>
     */
    public Ingredient remove(int id) {
        System.out.println("Removing with ID " + id);
        Ingredient ingredient = null;
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < ingredients[0].length; j++) {
                if (ingredients[i][j] != null && ingredients[i][j].getId() == id) {
                    ingredient = ingredients[i][j];
                    ingredients[i][j] = null;
                }
            }
        }
        if (ingredient == null) System.out.println("No Ingredient with ID " + id);
        sort();
        return ingredient;
    }
    
    /**
     * @return count of Ingredients in matrix
     * @example
     * <pre name="test">
     * Ingredients i = new Ingredients();
     * i.put(new Ingredient()); i.count() === 1;
     * </pre>
     */
    public int count() {
        int count = 0;
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < ingredients[0].length; j++) {
                if (ingredients[i][j] != null) count++;
            }
        }
        return count;
    }
    
    /**
     * loads Ingredient data from file
     */
    public void load() {
        try {
            int count = 0;
            sc = new Scanner(FILE);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                put(Ingredient.parse(line));
                count++;
            }
            sort();
            System.out.println("Read " + count + " lines of ingredients.");
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Saves ingredient data to file
     * @return true if successful
     */
    public boolean save() {
        sort();
        try {         
            int count = 0;
            writer = new FileWriter(FILE);
            for (int j = 0; j < ingredients[0].length; j++) {
                for (int i = 0; i < cols; i++) {
                    if (ingredients[i][j] != null) {
                        writer.write(ingredients[i][j] + "\n");
                        count++;
                    }
                }
            }   
            writer.close();
            System.out.println("Successsfully saved " + count + " lines of ingredient data.");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public String toString() {
        StringBuilder bldr = new StringBuilder();
        for (int j = 0; j < ingredients[0].length; j++) {
            for (int i = 0; i < cols; i++) {
                if (ingredients[i][j] != null) bldr.append(ingredients[i][j].getId() + " | ");
                else bldr.append("- | ");
            }
            bldr.append("\n");
        }
        return bldr.toString();
    }
    
    /**
     * @param col to begin from
     * @param row to begin from
     * @return if has other values than null after certain point
     * */
    private boolean hasNonNulls(int col, int row) {
        int i = col;
        for (int j = row; j < ingredients[0].length; j++) {
            while (i < cols) {
                if (ingredients[i][j] != null) {
                    return true;
                }
                i++;
            }
            i = 0;
        }
        return false;
    }
    
    
    /**
     * Moves null values to the end of the matrix
     */
    public void removeNull() {
        for (int j = 0; j < ingredients[0].length; j++) {
            for (int i = 0; i < cols; i++) {
                if (ingredients[i][j] == null) {
                    if (hasNonNulls(i, j)) {
                        for (int l = ingredients[0].length - 1; l >= j; l--) {
                            for (int k = cols - 1; k >= 0; k--) {
                                if (l == j && k == i) break;
                                if (ingredients[k][l] != null) {
                                    swap(i,j,k,l);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
    /**
     * Sorts the matrix from smallest to largest by ID
     */
    public void sort() {
        removeNull();
        for (int j = 0; j < ingredients[0].length; j++) {
            for (int i = 0; i < cols; i++) {
                for (int l = ingredients[0].length - 1; l >= j; l--) {
                    for (int k = cols - 1; k >= 0; k--) {
                        if (l == j && k == i) break;
                        if (ingredients[i][j] == null) return;
                        if (ingredients[k][l] == null) continue;
                        if (ingredients[i][j].getId() > ingredients[k][l].getId()) {
                            swap(i,j,k,l);
                        }
                    }
                }
            }
        }
    }
    
    /**
     * Sorts the matrix from smallest to largest by ID
     */
    public void sortAlpha() {
        removeNull();
        for (int j = 0; j < ingredients[0].length; j++) {
            for (int i = 0; i < cols; i++) {
                for (int l = ingredients[0].length - 1; l >= j; l--) {
                    for (int k = cols - 1; k >= 0; k--) {
                        if (l == j && k == i) break;
                        if (ingredients[i][j] == null) return;
                        if (ingredients[k][l] == null) continue;
                        if (ingredients[i][j].getName().compareTo(ingredients[k][l].getName()) > 0) {
                            swap(i,j,k,l);
                        }
                    }
                }
            }
        }
    }
    
    /**
     * @param col1 first Ingredient's colum
     * @param row1 first Ingredient's row
     * @param col2 second Ingredient's colum
     * @param row2 second Ingredient's row
     * @example
     * <pre name="test">
     * Ingredients i = new Ingredients(3, 1);
     * i.put(new Ingredient());
     * i.getFromIndexes(0,0) == null === false;
     * i.getFromIndexes(1,0) == null === true;
     * i.swap(0,0,1,0); 
     * i.getFromIndexes(0,0) == null === true;
     * i.getFromIndexes(1,0) == null === false;
     * </pre>
     */
    public void swap(int col1, int row1, int col2, int row2) {
        Ingredient temp = ingredients[col1][row1];
        ingredients[col1][row1] = ingredients[col2][row2];
        ingredients[col2][row2] = temp;
    }
    
    /**
     * @return true if new rows can be added
     */
    public boolean canExpand() {
        return rows < maxRows;
    }
    
    /**
     * @return true if has null values
     * @example
     * <pre name="test">
     * Ingredients i = new Ingredients(3, 1);
     * i.hasNull() === true;
     * i.put(new Ingredient()); i.hasNull() === true;
     * i.put(new Ingredient()); i.put(new Ingredient());
     * i.hasNull() === false;
     * </pre>
     */
    public boolean hasNull() {
        for (int j = 0; j < ingredients[0].length; j++) {
            for (int i = 0; i < cols; i++) {
                if (ingredients[i][j] == null) return true;
            }
        }
        return false;
    }
    
    /**
     * @param col of matrix
     * @param row of matrix
     * @return Ingredient in matrix
     * @example
     * <pre name="test">
     * Ingredients i = new Ingredients(3,1);
     * Ingredient j = new Ingredient();
     * i.put(j); i.getFromIndexes(0,0) == null === false;
     * i.getFromIndexes(1,0) == null === true;
     * </pre>
     */
    public Ingredient getFromIndexes(int col, int row) {
        return ingredients[col][row];
    }
    
    /**
     * @param args not in use
     */
    public static void main(String[] args) {
        Ingredients i = new Ingredients(3, 1);
        i.put(new Ingredient(1, "peruna"));
        System.out.println(i.get("peruna"));
    }
}
