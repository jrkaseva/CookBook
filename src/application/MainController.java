package application;
import java.io.IOException;

import fi.jyu.mit.fxgui.Dialogs;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import lib.Storage;

/**
 * @author tietokone
 * @version 7.6.2023
 * Controller for main scene
 */
public class MainController extends MenuController{
    
    private static Storage storage;
    
    @FXML
    private Button mainBtnInfo;
    
    @FXML
    private Button mainBtnDelete;
    
    @FXML
    private Button btnEditRecipe;
    
    @FXML
    private Button mainBtnFav;
    
    @FXML
    private Button btnNewRecipe;
    
    /**
     * 
     */
    public MainController() {
         System.out.println("Main");
         /* DIALOG USAGE
         Dialogs.showMessageDialog("Test dialog");
         Boolean choice = Dialogs.showQuestionDialog("TEST", "Test dialog", "Yes", "No");
         String input = Dialogs.showInputDialog("TEST", "Test dialog");
         System.out.println(choice + " : " + input);
         */
     }
    
    @FXML
    void showInfo(ActionEvent event) {
        Dialogs.showMessageDialog("Tässä näkymässä voit valita reseptin ja nähdä tämän ohjeen. "
                + "Lisäksi voit poistaa, muokata ja asettaa reseptejä lemppariksi. "
                + "Voit myös rajata, mitä reseptejä haluat nähdä.");
    }
    
    @FXML
    void btnThing3(ActionEvent event) {
        Dialogs.showMessageDialog("BtnThing3");
    }
    
    @FXML
    void btnThing4(ActionEvent event) {
        Dialogs.showMessageDialog("BtnThing4");
    }
}
