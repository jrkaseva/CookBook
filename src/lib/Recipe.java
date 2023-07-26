/**
 * 
 */
package lib;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * @author tietokone
 * @version 6.6.2023
 * error testing static attributes, should reset in every test
 */
public class Recipe implements Comparable<Recipe> {
    private static int next_id = 1;
    private int id;
    private String name;
    private String creator;
    private String origin;
    private String course;
    private String guide;
    private Boolean starred = false;
    private ArrayList<Ingredient> ingredients = new ArrayList<>();
    
    /**
     * default constructor
     * @example
     * <pre name="test">
     * Recipe r = new Recipe();
     * r.getId() === 1;
     * </pre>
     */
    public Recipe() {
        id = next_id;
        next_id++;
        name = "Recipe " + id;
        creator = "Creator " + id;
        origin = "Origin " + id;
        course = "Course " + id;
        guide = "Ohje: " + name;
    }
    
    /**
     * @param id -1 if new, else already assigned id
     * @param name of course
     * @param creator of course
     * @param origin country
     * @param course 0 = appetizer, 1 = main, 2 = dessert
     * @param starred true if starred
     * @param guide to follow
     * @param ingredients of course
     * @example
     * <pre name="test">
     * Recipe r = new Recipe(1, "perunasalaatti", "atria", "suomi", "p‰‰ruoka", "ohje", null);
     * r.toString() === "1 | perunasalaatti | atria | suomi | p‰‰ruoka | ohje";
     * </pre>
     */
    public Recipe(int id, String name, String creator, String origin, String course, boolean starred, String guide, ArrayList<Ingredient> ingredients) {

        if (id == -1) {            
            this.id = next_id;
            next_id++;
        } else {
            this.id = id;
        }
        this.starred = starred;
        this.name = name.trim().toLowerCase();
        this.setCreator(creator.trim());
        this.setOrigin(origin.trim());
        this.setCourse(course);
        this.guide = guide.trim();
        this.setIngredients(ingredients);

    }
    
    @Override
    public String toString() {
        return String.format("%d | %s | %s | %s | %s | %s | %s", id, name, creator, origin, course, starred, guide.replace("\n", "%NEWLINE%"));
    }
    
    /**
     * Prints the guide to console
     */
    public void printRecipe() {
        System.out.printf("%s: (%s, %s) [%s]\n\n", getName(true), creator, origin, course);
        System.out.printf("Ainesosat:\n");
        for (Ingredient i : ingredients) {
            System.out.printf("\t%s\n", i.toString());
        }
        System.out.println("\n" + guide);
    }

    /**
     * @return the id
     * @example
     * <pre name="test">
     * Recipe r = new Recipe(1, "perunasalaatti", "atria", "suomi", "p‰‰ruoka", "ohje", null);
     * r.getId() === 1;
     * </pre>
     */
    public int getId() {
        return id;
    }
    
    /**
     * comtest error -> first new Recipe() creates Recipe with id 2 in junit, but not in main
     * @param previous_id highest existing id
     * @example
     * <pre name="test">
     * Recipe.setNextId(6);
     * Recipe r = new Recipe();
     * r.getId() === 7;
     * </pre>
     */
    public static void setNextId(int previous_id) {
        next_id = previous_id + 1;
    }
    
    /**
     * @param capitalized if true
     * @return name of Recipe
     */
    public String getName(boolean capitalized) {
        if (capitalized) return name.substring(0,1).toUpperCase() + name.substring(1);
        return name;
    }

    /**
     * @param name for Recipe
     */
    public void setName(String name) {
        this.name = name.toLowerCase();
    }

    /**
     * @return the ingredients
     */
    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    /**
     * @param ingredients the ingredients to set
     */
    public void setIngredients(ArrayList<Ingredient> ingredients) {
        if (ingredients != null) this.ingredients = ingredients;
    }
    
    /**
     * @param ingredient to be added
     */
    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
    }
    
    
    /**
     * @param i1 Ingredient to be replaced
     * @param i2 Ingredient to replace
     * @example
     * <pre name="test">
     * Recipe r = new Recipe();
     * Ingredient i = new Ingredient(1, "peruna");
     * r.addIngredient(i); r.getIngredients().get(0).toString() === "1 | peruna";
     * r.replaceIngredient(i, new Ingredient(2, "maito"));
     * r.getIngredients().get(0).toString() === "2 | maito";
     * </pre>
     */
    public void replaceIngredient(Ingredient i1, Ingredient i2) {
        if (ingredients.remove(i1)) ingredients.add(i2);
        else System.out.println("Error replacing recipe's ingredients");
    }

    /**
     * @return the creator
     */
    public String getCreator() {
        return creator;
    }

    /**
     * @param creator the creator to set
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * @return the origin
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * @param origin the origin to set
     */
    public void setOrigin(String origin) {
        this.origin = origin;
    }
    
    /**
     * @return if recipe is starred
     */
    public Boolean getStarred() {
        return starred;
    }
    
    /**
     * @param b true if to be starred.
     */
    public void setStarred(Boolean b) {
        starred = b;
        System.out.printf("%s | starred: %b\n", name, starred);
    }

    /**
     * @return the course
     */
    public String getCourse() {
        return course;
    }

    /**
     * @param course the course to set
     */
    public void setCourse(String course) {
        this.course = course;
    }
    
    /**
     * @return guide
     */
    public String getGuide() {
        return guide;
    }
    
    /**
     * @param s new string for guide
     */
    public void setGuide(String s) {
        guide = s;
    }
    
    /**
     * @param s String to be parsed
     * @return new Ingredient
     * @throws ParseException if error with parsing
     */
    public static Recipe parse(String s) throws ParseException {
        String[] arr = s.split("\\|");
        if (arr.length != 7) throw new ParseException("Virhe reseptien lukemisessa: tarkista rivin '|' merkkien m‰‰r‰. Oli " + arr.length + " alkiota. Piti olla 7.");
        try {
            Integer.parseInt(arr[0].trim());
        } catch (Exception e) {
            throw new ParseException("Virhe ainesosien lukemisessa: ID ei ole laillinen numero");
        }
        boolean star = true;
        if (arr[5].trim().equals("false")) star = false;
        return new Recipe(Integer.parseInt(arr[0].trim()), arr[1].trim(), arr[2].trim(), arr[3].trim(), arr[4].trim(), star, arr[6].trim().replace("%NEWLINE%", "\n"), new ArrayList<Ingredient>());
    }
    
    /**
     * @param r recipe to be compared to
     * @return order of recipes
     */
    public int compareIdOld(Recipe r) {
        return id - r.id;
    }
    
    /**
     * @param r recipe to be compared to
     * @return order of recipes
     */
    public int compareIdNew(Recipe r) {
        return r.id - id;
    }
    
    /**
     * @param r recipe to be compared to
     * @return order of recipes
     */
    public int compareAlpha(Recipe r) {
        return name.compareTo(r.name);
    }
    
    /**
     * @param r recipe to be compared to
     * @return order of recipes
     */
    public int compareZulu(Recipe r) {
        return r.name.compareTo(name);
    }

    @Override
    public int compareTo(Recipe arg0) {
        return id - arg0.id;
    }
}
