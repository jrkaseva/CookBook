/**
 * 
 */
package lib;

/**
 * @author tietokone
 * @version 6.6.2023
 *
 */
public class Ingredient {
    private static int next_id = 1;
    private int id;
    private String name;
    
    
    /**
     * default constructor (for testing class)
     */
    public Ingredient() {
        id = next_id;
        next_id++;
        name = "Ingredient " + id;
    }
    
    /**
     * @param id of Ingredient
     * @param name of Ingredient
     */
    public Ingredient(int id, String name) {
        if (id == -1) {            
            this.id = next_id;
            next_id++;
        } else {
            this.id = id;
        }
        this.name = name.trim().replace("|", " ");
    }
    
    /**
     * @return the id
     * @example
     * <pre name="test">
     * Ingredient i = new Ingredient(1, "peruna");
     * i.getId() === 1;
     * Ingredient j = new Ingredient(2, "maito");
     * j.getId() === 2;
     * </pre>
     */
    public int getId() {
        return id;
    }
    /**
     * @return the name
     * @example
     * <pre name="test">
     * Ingredient i = new Ingredient(1, "peruna");
     * i.getName() === "Peruna";
     * Ingredient j = new Ingredient(2, "maito");
     * j.getName() === "Maito";
     * </pre>
     */
    public String getName() {
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }
    
    @Override
    public String toString() {
        return id + " | " + name;
    }
    
    /**
     * @param s String to be parsed
     * @return new Ingredient
     * @throws ParseException if error with parsing
     * @example
     * <pre name="test">
     * try {
     * Ingredient i = Ingredient.parse("1 | peruna"); 
     * i.getId() === 1;
     * i = Ingredient.parse("100 | maitojauhe");
     * i.getId() === 100;
     * i = Ingredient.parse("");
     * i.getId() === 100;
     * } catch (ParseException e) {
     *      System.out.println("Caught ParseException");
     * }
     * </pre>
     */
    public static Ingredient parse(String s) throws ParseException {
        String[] arr = s.split("\\|");
        if (arr.length != 2) throw new ParseException("Virhe ainesosien lukemisessa: Enemmän kuin yksi '|' rivissä");
        try {
            Integer.parseInt(arr[0].trim());
        } catch (Exception e) {
            throw new ParseException("Virhe ainesosien lukemisessa: ID ei ole laillinen numero");
        }
        next_id = Integer.parseInt(arr[0].trim()) + 1;
        return new Ingredient(Integer.parseInt(arr[0].trim()), arr[1].trim());
    }
    
    /**
     * @param i integer to set next_id to
     */
    public static void setNextId(int i) {
        next_id = i;
    }
    
    /**
     * @return next_id
     * @example
     * <pre name="test">
     * Ingredient.getNextId() === 1;
     * Ingredient i = new Ingredient(5, "peruna");
     * Ingredient.getNextId() === 1;
     * i = new Ingredient();
     * Ingredient.getNextId() === 2;
     * i.getId() === 1;
     * </pre>
     */
    public static int getNextId() {
        return next_id;
    }
}
