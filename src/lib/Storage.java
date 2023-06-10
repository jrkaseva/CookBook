/**
 * 
 */
package lib;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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
    public static Storage getInstance() {
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
    
    /**
     * 
     */
    public void loadData() {
        File f = new File(RECIPES_FILE);
        if (!f.exists())
            try {
                f.createNewFile();
                System.out.printf("File created at: %s\n", f.getAbsoluteFile());
                return;
            } catch (IOException e) {
                System.out.println("Error creating new file");
                e.printStackTrace();
            }
        try {
            sc = new Scanner(f);
        } catch (FileNotFoundException e) {
            System.out.println("Error with \"new Scanner(File f)\"");
            e.printStackTrace();
            return;
        }
        System.out.println("Loading recipes from file...");
        int count = 0;
        while (sc.hasNextLine()) {
            count++;
            String line = sc.nextLine();
            System.out.println(line);
            // TODO: get data from .dat-files and if necessary, create .dat-files.
        }
        System.out.printf("Loaded %d lines\n", count);
    }
    
    public void saveData() {
        // TODO: save data to .dat-file. Object.toString should suffice.
    }
}
