/**
 * 
 */
package lib;

import java.util.ArrayList;

/**
 * @author tietokone
 * @version 6.6.2023
 *
 */
public class Recipe {
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
     */
    public Recipe() {
        id = next_id;
        next_id++;
        name = "Recipe " + id;
        creator = "Creator " + id;
        origin = "Origin " + id;
        course = "Course " + id;
        guide = "Ohje: " + name;
        ingredients.add(new Ingredient());
    }
    
    /**
     * @param id -1 if new, else already assigned id
     * @param name of course
     * @param creator of course
     * @param origin country
     * @param course 0 = appetizer, 1 = main, 2 = dessert
     * @param guide to follow
     * @param ingredients of course
     */
    public Recipe(int id, String name, String creator, String origin, String course, String guide, ArrayList<Ingredient> ingredients) {

        if (id == -1) {            
            this.id = next_id;
            next_id++;
        } else {
            this.id = id;
        }
        this.name = name.trim();
        this.setCreator(creator.trim());
        this.setOrigin(origin.trim());
        this.setCourse(course);
        this.guide = guide.trim();
        this.setIngredients(ingredients);

    }
    
    @Override
    public String toString() {
        // TODO: format as id|name|creator|origin|course|guide
        return String.format("%d | %s | %s | %s | %s | %s", id, name, creator, origin, course, guide);
    }
    
    /**
     * Prints the guide to console
     */
    public void printRecipe() {
        System.out.printf("%s: (%s, %s) [%s]\n\n", name, creator, origin, course);
        System.out.printf("Ainesosat:\n");
        for (Ingredient i : ingredients) {
            System.out.printf("\t%s\n", i.toString());
        }
        System.out.println("\n" + guide);

    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }
    
    /**
     * @param previous_id highest existing id
     */
    public void setNextId(int previous_id) {
        next_id = previous_id + 1;
    }
    
    /**
     * @return name of Recipe
     */
    public String getName() {
        return name;
    }

    /**
     * @param name for Recipe
     */
    public void setName(String name) {
        this.name = name;
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
        this.ingredients = ingredients;
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
        if (arr.length != 6) throw new ParseException("Virhe reseptien lukemisessa: tarkista rivin '|' merkkien m‰‰r‰. Oli " + arr.length + " alkiota. Piti olla 6.");
        try {
            Integer.parseInt(arr[0].trim());
        } catch (Exception e) {
            throw new ParseException("Virhe ainesosien lukemisessa: ID ei ole laillinen numero");
        }
        return new Recipe(Integer.parseInt(arr[0].trim()), arr[1].trim(), arr[2].trim(), arr[3].trim(), arr[4].trim(), arr[5].trim(), null);
    }
}
