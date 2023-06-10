/**
 * 
 */
package lib;

/**
 * @author tietokone
 * @version 6.6.2023
 *
 */
public class test {
    public static void main(String[] args) {
        Recipe hernekeitto = new Recipe(-1, "Hernekeitto", "Joakim", "Suomi", 1,
                "Avaa purkki, kaada kattilaan ja lisää puolipurkkia vettä. Kiehuta 4min ja nauti.");
        hernekeitto.printRecipe();
        System.out.println(hernekeitto);
    }
    
    /**
     * 
     */
    public static void initialize() {
        // TODO: Create .dat-files with existing data.
    }
}