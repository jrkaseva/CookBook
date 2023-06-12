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
public class test {
    /**
     * @param args not in use
     */
    public static void main(String[] args) {
        Recipe hernekeitto = new Recipe(-1, "Hernekeitto", "Joakim", "Suomi", "p‰‰ruoka",
                "Avaa purkki, kaada kattilaan ja lis‰‰ puolipurkkia vett‰. Kiehuta 4min ja nauti.", new ArrayList<Ingredient>());
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