/**
 * 
 */
package lib;

/**
 * @author tietokone
 * @version 5.7.2023
 *
 */
public class Relation implements Comparable<Relation>{
    private int recipeId;
    private int ingredientId;
    
    public int getRecipeId() {
        return recipeId;
    }

    public int getIngredientId() {
        return ingredientId;
    }

    /**
     * default constructor
     */
    public Relation() {
        recipeId = -1;
        ingredientId = -1;
    }
    
    /**
     * @param id1 id of recipe
     * @param id2 id of ingredient
     */
    public Relation(int id1, int id2) {
        recipeId = id1;
        ingredientId = id2;
    }
    
    /**
     * @param s string to be parsed
     * @return new Relation
     * @throws ParseException if error when parsing
     */
    public static Relation parse(String s) throws ParseException {
        String[] arr = s.split("\\|");
        if (arr.length != 2) throw new ParseException("Virhe relaation lukemisessa: Enemmän kuin yksi '|' rivissä");
        try {
            Integer.parseInt(arr[0].trim());
            Integer.parseInt(arr[1].trim());
        } catch (Exception e) {
            throw new ParseException("Virhe ainesosien lukemisessa: ID ei ole laillinen numero");
        }
        return new Relation(Integer.parseInt(arr[0].trim()), Integer.parseInt(arr[1].trim()));
    }
    
    @Override
    public String toString() {
        return recipeId + " | " + ingredientId;
    }

    @Override
    public int compareTo(Relation arg0) {
        return recipeId - arg0.recipeId;
    }
}
