/**
 * 
 */
package lib;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @author tietokone
 * @version 6.6.2023
 *
 */
public class Storage {
    private final String DIR = System.getProperty("user.dir").toString() + "\\src\\data\\";
    private final String RECIPES_FILE = DIR + "recipes.dat";
    private final String INGREDIENTS_FILE = DIR + "ingredients.dat";
    private final String RELATION_FILE = DIR + "relation.dat";
    private Scanner sc;
    
    private static Storage storage = null;
    private HashMap<Integer, Recipe> recipes;
    private HashMap<Integer, Ingredient> ingredients;
    
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
        if (recipes.containsValue(recipe)) {
            System.out.println("Recipe already exists");
            return;
        }
        recipes.put(recipe.getId(), recipe);
    }
    
    /**
     * @param id of recipe to be deleted
     */
    public void deleteRecipe(int id) {
        if (!recipes.containsKey(id)) {
            System.out.println("No recipe with id: " + id + " exists.");
            return;
        }
        recipes.remove(id);
    }
    
    /**
<<<<<<< Updated upstream
     * Loads data from data files.
     */
    public void loadData() {
        loadFile(RECIPES_FILE);
        loadFile(INGREDIENTS_FILE);
        loadFile(RELATION_FILE);
    }
    
    /**
     * @param file to be loaded from
     * 
     */
    public void loadFile(String file) {
        File f = new File(file);
        if (!f.exists()) {
            try {
                f.createNewFile();
                System.out.printf("New file created at: %s\n", f.getAbsoluteFile());
                return;
            } catch (IOException e) {
                System.out.println("Error creating new file");
                e.printStackTrace();
            }
        } else System.out.printf("File path: %s\n", f.getAbsolutePath());
        try {
            sc = new Scanner(f);
        } catch (FileNotFoundException e) {
            System.out.println("Error with \"new Scanner(File f)\"");
            e.printStackTrace();
            return;
        }
        int count = 0;
        while (sc.hasNextLine()) {
            count++;
            String line = sc.nextLine();
            System.out.println(line);
            try {                
                if (file.equals(RECIPES_FILE)) {
                    Recipe add = Recipe.parse(line);
                    recipes.put(add.getId(), add);
                }
                else if (file.equals(INGREDIENTS_FILE)) {
                    Ingredient add = Ingredient.parse(line);
                    ingredients.put(add.getId(), add);
                }
                else {
                    System.out.println("Relations not implemented yet");
                }
            }catch (ParseException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.printf("Loaded %d lines from %s\n", count, f.getName());
        sc.close();
    }
    
    /**
     * 
     */
    public void saveData() {
        //TODO: OVERWRITE EXISTING DATA IN FILE
        FileWriter writer = null;
        //File f = new File(RECIPES_FILE);
        try {
            writer = new FileWriter(RECIPES_FILE);
            for (int i : recipes.keySet()) {
                writer.write(recipes.get(i) + "\n");
            }
            writer = new FileWriter("ingredients.dat");
            for (int i : ingredients.keySet()) {
                writer.write(ingredients.get(i) + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
