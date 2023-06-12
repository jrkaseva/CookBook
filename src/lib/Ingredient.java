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
    private int id;
    private String name;
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
}
