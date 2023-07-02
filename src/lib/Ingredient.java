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
     * 
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
     */
    public int getId() {
        return id;
    }
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    
    @Override
    public String toString() {
        return id + " | " + name;
    }
    
    /**
     * @param s String to be parsed
     * @return new Ingredient
     * @throws ParseException if error with parsing
     */
    public static Ingredient parse(String s) throws ParseException {
        String[] arr = s.split("|");
        if (arr.length != 2) throw new ParseException("Virhe ainesosien lukemisessa: Enemmän kuin yksi '|' rivissä");
        try {
            Integer.parseInt(arr[0].trim());
        } catch (Exception e) {
            throw new ParseException("Virhe ainesosien lukemisessa: ID ei ole laillinen numero");
        }
        return new Ingredient(Integer.parseInt(arr[0].trim()), arr[1].trim());
    }
}
