package application;
import lib.Storage;

/**
 * @author tietokone
 * @version 7.6.2023
 * Controller for main scene
 */
public class MainController extends MenuController{
    
    private static Storage storage = Storage.getInstance();
    
    /**
     * 
     */
    public MainController() {
         storage.loadData();
         System.out.println("MainController main-method");
     }
    
    
}
