/**
 * 
 */
package lib;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * @author joakim kaseva
 * @version 5.7.2023
 *
 */
public class Relations {
    private final File FILE = new File(System.getProperty("user.dir").toString() + "\\src\\data\\relation.dat");
    private Scanner sc;
    private FileWriter writer;
    
    private ArrayList<Relation> relations;
    
    /**
     * default contsructor
     */
    public Relations() {
        relations = new ArrayList<Relation>();
    }
    
    /**
     * @return ArrayList of relations
     */
    public ArrayList<Relation> getRelations(){
        return relations;
    }
    
    /**
     * @param relation to be added
     */
    public void addRelation(Relation relation) {
        relations.add(relation);
    }
    
    /**
     * loads Ingredient data from file
     */
    public void load() {
        try {
            sc = new Scanner(FILE);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                addRelation(Relation.parse(line));
            }
            Collections.sort(relations);
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Saves ingredient data to file
     * @return true if successful
     */
    public boolean save() {
        Collections.sort(relations);
        try {            
            writer = new FileWriter(FILE);
            for (Relation r : relations) writer.write(r + "\n");
            writer.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
