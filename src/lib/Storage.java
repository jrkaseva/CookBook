/**
 * 
 */
package lib;

import java.util.ArrayList;

/**
 * @author joakim kaseva
 * @version 6.6.2023
 *
 */
public class Storage {
    private int maxRecipes = 100;
    
    private static Storage storage = null;
    private Recipes recipes = new Recipes();
    private Ingredients ingredients = new Ingredients();
    private Relations relations = new Relations();
    
    /**
     * @return Storage instance
     */
    public static Storage getInstance() {
        if (storage == null) storage = new Storage();
        return storage;
    }
    
    /**
     * @param id of recipe to be deleted
     */
    public void deleteRecipe(int id) {
        if (!recipes.contains(id)) {
            System.out.println("No recipe with id: " + id + " exists.");
            return;
        }
        recipes.remove(id);
    }
    
    /**
     * @return HashMap of recipes
     */
    public Recipes getRecipes(){
        return recipes;
    }
    
    /**
     * @return ingredients
     */
    public Ingredients getIngredients() {
        return ingredients;
    }
    
    /**
     * @param recipe to be added
     */
    public void addRecipe(Recipe recipe) {
        if (recipes.contains(recipe.getId())) {
            System.out.println("Recipe already exists (ID " + recipe.getId() + ")");
            return;
        }
        if (recipes.getRecipes().size() == maxRecipes) {
            System.out.println("Maximum amount of Recipes");
            return;
        }
        recipes.put(recipe);
    }

    /**
     * @param ingredient to be added
     * @return true if successfully added
     */
    public boolean addIngredient(Ingredient ingredient) {
        if (ingredient == null) return false;
        if (ingredients.containsValue(ingredient)) {
            System.out.println("Ingredient '" + ingredient + "' already exists (by ID or name)");
            return false;
        }
        if (!ingredients.hasNull()) {
            if (!ingredients.canExpand()) {
                System.out.println("Maximum amount of ingredients");
                return false;
            }
            ingredients = new Ingredients(ingredients);
        }
        System.out.println("Adding new ingredient: " + ingredient);
        ingredients.put(ingredient);
        return true;
    }
    
    /**
     * @param id of ingredient to be deleted
     */
    public void deleteIngredient(int id) {
        if (!ingredients.contains(id)) {
            System.out.println("No ingredient with id: " + id + " exists.");
            return;
        }
        ingredients.remove(id);
    }
    
    /**
     * @param str to be searched with
     * @return arraylist with recipes containing str
     */
    public ArrayList<Recipe> search(String str) {
        return recipes.search(str);
    }
    
    /**
     * Loads data from data files.
     */
    public void loadData() {
        ingredients.load();
        recipes.load();
        relations.load();
        setRelations();
    }
    
    /**
     * Sets the ingredients for recipes
     */
    private void setRelations() {
        for (Recipe r : recipes.getRecipes()) {
            for(Relation rel : relations.getRelations()) {
                if (rel.getRecipeId() == r.getId()) {
                    if (ingredients.get(rel.getIngredientId()) != null) r.addIngredient(ingredients.get(rel.getIngredientId()));
                }
            }
        }
    }
    
    private void getRelations() {
        relations = new Relations();
        for (Recipe r : recipes.getRecipes()) {
            for (Ingredient i : r.getIngredients()) {
                relations.addRelation(new Relation(r.getId(), i.getId()));
            }
        }
    }
    
    /**
     * @return true if can store ingredients
     */
    public boolean canAddIngredient() {
        if (ingredients.count() < 30) return true;
        return false;
    }
    
    /**
     * @return true if can store recipes
     */
    public boolean canAddRecipe() {
        if (recipes.getRecipes().size() < 100) return true;
        return false;
    }
    
    /**
     * Saves data to files
     * @return true if successful
     */
    public boolean saveData() {
        getRelations();
        return (ingredients.save() && recipes.save() && relations.save());
    }
}
