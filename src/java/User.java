/**
 * 
 */
package java;

import java.util.ArrayList;

/**
 * @author tietokone
 * @version 6.6.2023
 *
 */
public class User {
    private static int next_id = 1;
    private int id;
    private String name;
    private String username;
    private String password;
    private String email;
    private ArrayList<String> diet;
    private ArrayList<String> allergies;
    private boolean subscribed;
    
    /**
     * Default constructor
     */
    public User() {}
    
    public User(String username, String firstname, String lastname, String email, ArrayList<String> diet, ArrayList<String> allergies) {
        try {
            username = checkUsername(username);
            email = checkEmail(email);
        } catch (Exception e){
            e.printStackTrace();
            return;
        }
        this.username = username;
        this.email = email;
        name = firstname.trim() + " " + lastname.trim();
        this.diet = diet;
        this.allergies = allergies;
        
    }
    
}
