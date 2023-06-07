/**
 * 
 */
package lib;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author tietokone
 * @version 6.6.2023
 *
 */
public class Storage {
    private final String RECIPES_FILE = "recipes.dat";
    private final String INGREDIENTS_FILE = "ingredients.dat";
    private final String RELATION_FILE = "relation.dat";
    private Scanner sc;
    
    private static Storage storage = null;
    private ArrayList<Recipe> recipes;
    private ArrayList<Ingredient> ingredients;
    
    /**
     * @return Storage instance
     */
    public Storage getInstance() {
        if (storage == null) storage = new Storage();
        return storage;
    }
    
    /**
     * @param recipe to be added
     */
    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);
    }
    
    /**
     * @param index of recipe to be deleted
     */
    public void deleteRecipe(int index) {
        recipes.remove(index);
    }
    
    public void loadData() {
        sc = new Scanner(RECIPES_FILE);
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            System.out.println(line);
            // TODO: get data from .dat-files and if necessary, create .dat-files.
        }
    }
    
    public void saveData() {
        // TODO: save data to .dat-file. Object.toString should suffice.
    }
}
