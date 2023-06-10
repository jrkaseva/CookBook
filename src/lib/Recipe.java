/**
 * 
 */
package lib;

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
    private int course;
    private String guide;
    
    /**
     * default constructor
     */
    public Recipe() {}
    
    /**
     * @param id -1 if new, else already assigned id
     * @param name of course
     * @param creator of course
     * @param origin country
     * @param course 0 = appetizer, 1 = main, 2 = dessert
     * @param guide to follow
     */
    public Recipe(int id, String name, String creator, String origin, int course, String guide) {
        if (id == -1) {            
            this.id = next_id;
            next_id++;
        } else {
            this.id = id;
        }
        this.name = name.trim();
        this.creator = creator.trim();
        this.origin = origin.trim();
        this.course = course;
        this.guide = guide.trim();
    }
    
    @Override
    public String toString() {
        // TODO: format as id|name|creator|origin|course|guide
        return id + " | " + name;
    }
    
    /**
     * Prints the guide to console
     */
    public void printRecipe() {
        System.out.println(guide);
    }

    /**
     * @return the creator
     */
    public String getCreator() {
        return creator;
    }

    /**
     * @return the origin
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * @return the course
     */
    public int getCourse() {
        return course;
    }

    /**
     * @return the guide
     */
    public String getGuide() {
        return guide;
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
    
    
}
