package application;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

/**
 * @author tietokone
 * @version 7.6.2023
 *
 */
public class RecipeController extends MenuController{

    @FXML
    private Button btnReturn;
    
    /**
     * 
     */
    public RecipeController() {
        System.out.println("Recipe");
    }
    
    @FXML
    void cancel(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/main.fxml"));
            loader.setController(new MainController());
            Stage s = (Stage) btnReturn.getScene().getWindow();
            s.setScene(new Scene(loader.load()));
            s.setTitle("Keittokirja");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
