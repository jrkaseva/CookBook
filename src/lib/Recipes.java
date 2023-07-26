/**
 * 
 */
package lib;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * @author joakim kaseva
 * @version 4.7.2023
 *
 */
public class Recipes {
    private final File FILE = new File(System.getProperty("user.dir").toString() + "\\src\\data\\recipes.dat");
    private Scanner sc;
    private FileWriter writer;
    
    private ArrayList<Recipe> recipes = new ArrayList<>();
    
    /**
     * @return true if empty
     * @example
     * <pre name="test">
     * Recipes r = new Recipes();
     * r.isEmpty() === true;
     * r.put(new Recipe()); r.isEmpty() === false;
     * </pre>
     */
    public boolean isEmpty() {
        return recipes.isEmpty();
    }
    
    /**
     * comtest error -> first new Recipe() creates Recipe with id 2 in junit, but not in main
     * @param id of desired recipe
     * @return true if recipe exists
     * @example
     * <pre name="test">
     * Recipes r = new Recipes();
     * r.contains(1) === false;
     * r.put(new Recipe());
     * r.contains(1) === true;
     * r.contains(2) === false;
     * </pre>
     */
    public boolean contains(int id) {
        for (Recipe r : recipes) if (r.getId() == id) return true;
        return false;
    }
    
    /**
     * @param id of desired recipe
     * @return recipe with desired id
     */
    public Recipe get(int id) {
        for (Recipe r : recipes) if (r.getId() == id) return r;
        System.out.println("No recipe with ID " + id);
        return null;
    }
    
    /**
     * @param r recipe to be added
     */
    public void put(Recipe r) {
        recipes.add(r);
        sort();
    }
    
    /**
     * @param id of Ingredient to be removed
     */
    public void remove(int id) {
        recipes.remove(get(id));
    }
    
    /**
     * loads data from file
     */
    public void load() {
        try {
            int count = 0;
            sc = new Scanner(FILE);
            Recipe r = null;
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                r = Recipe.parse(line);
                put(r);
                count++;
            }
            sort();
            System.out.println("Read " + count + " lines of recipes.");
            if (r != null) Recipe.setNextId(r.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * saves data to file
     * @return true if successful
     */
    public boolean save() {
        sort();
        int count = 0;
        try {            
            writer = new FileWriter(FILE);             
            for (Recipe r : recipes) {
                writer.write(r + "\n");        
                count++;
            }
            writer.close();
            System.out.println("Successsfully saved " + count + " lines of recipe data.");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * @return ArrayList of recipes
     */
    public ArrayList<Recipe> getRecipes(){
        return recipes;
    }
    
    /**
     * sorts recipes by ID
     */
    public void sort() {
        Collections.sort(recipes);
    }

    /**
     * @param selectedObject string to get recipes by
     * @return array with recipes.
     */
    public ArrayList<Recipe> getByTrait(String selectedObject) {
        ArrayList<Recipe> arr = new ArrayList<>();
        if (selectedObject.equals("Kaikki")) return recipes;
        if (selectedObject.equals("Lempparit")) {
            for (Recipe r : recipes) {
                if (r.getStarred()) {
                    arr.add(r);
                }
            }
            return arr;
        }
        for (Recipe r : recipes) {
            if (r.getCourse().equals(selectedObject)) {
                arr.add(r);
            }
        }
        return arr;
    }

    /**
     * @return count of recipes
     */
    public int count() {
        return recipes.size();
    }
    
    /**
     * @param str to be searched with
     * @return arraylist with recipes containing str in name
     */
    public ArrayList<Recipe> search(String str){
        ArrayList<Recipe> arr = new ArrayList<>();
        for (Recipe r : recipes) {
            if (r.getName(false).contains(str)) arr.add(r);
        }
        return arr;
    }
}
