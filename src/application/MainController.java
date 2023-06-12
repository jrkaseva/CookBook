package application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
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
