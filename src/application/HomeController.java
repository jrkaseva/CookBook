package application;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * @author tietokone
 * @version 7.6.2023
 *
 */
public class HomeController {

    @FXML
    private Button btnHome;
    
    /**
     * 
     */
    public HomeController() {
        System.out.println("Home");
    }
    
    @FXML
    void openMain(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/main.fxml"));
            loader.setController(new MainController());
            Stage s = (Stage) btnHome.getScene().getWindow();
            s.setScene(new Scene(loader.load()));
            System.out.println("Loaded main");
        } catch (IOException e) {
            e.printStackTrace();
        }
    } 
}
