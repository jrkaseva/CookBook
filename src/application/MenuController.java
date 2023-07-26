package application;
import java.io.IOException;

import fi.jyu.mit.fxgui.Dialogs;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

/**
 * @author tietokone
 * @version 7.6.2023
 *
 */
public class MenuController {
    
    /**
     * Prepares to create a new recipe
     */
    @FXML
    protected MenuItem menuAddR;

    
    /**
     * Prepares to delete the selected recipe
     */
    @FXML
    protected MenuItem menuDelR;

    /**
     * Contains MenuItems for interacting with recipes
     */
    @FXML
    protected Menu menuEdit;

    /**
     * Sets a recipe as favourite
     */

    @FXML
    protected MenuItem menuFavR;

    /**
     * Contains MenuItems for the application
     */
    @FXML
    protected Menu menuFile;

    /**
     * Contains MenuItems for information/help
     */
    @FXML
    protected Menu menuHelp;

    /**
     * Shows the info of application
     */

    @FXML
    protected MenuItem menuInfo;

    /**
     * Prepares to edit the selected recipe
     */
    @FXML
    protected MenuItem menuModR;

    /**
     * Opens the current recipe
     */
    @FXML
    protected MenuItem menuOpen;

    /**
     * Prepares to print the selected recipe
     */
    @FXML
    protected MenuItem menuPrint;


    /**
     * Quits the application after confirmation of saving
     */
    @FXML
    protected MenuItem menuQuit;


    /**
     * Saves the current data in the application
     */
    @FXML
    protected MenuItem menuSave;


    /**
     * Shows the info of the current scene
     */
    @FXML
    protected MenuItem menuShowHelp;


    /**
     * Contains the Menus with MenuItems
     */
    @FXML
    protected MenuBar menuValikko;

    @FXML
    void addRecipe(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/recipe.fxml"));
            loader.setController(new RecipeController());
            Stage s = (Stage) menuValikko.getScene().getWindow();
            s.setScene(new Scene(loader.load()));
            s.setTitle("Keittokirja - lis‰‰ resepti");
            System.out.println("Loaded Recipe (new)");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void info(ActionEvent event) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setContentText("Keittokirja\nJoakim Kaseva\nIT-JYU\nOhjelmointi 2");
        alert.setHeaderText(null);
        alert.getDialogPane().setPrefSize(200, -1);
        alert.showAndWait();
    }

    @FXML
    void openRecipe(ActionEvent event) {
        System.out.println("Ei toiminnossa (Avaa resepti)");
    }

    @FXML
    void printInstruction(ActionEvent event) {
        System.out.println("Ei toiminnossa (Tulosta ohje)");
    }

    @FXML
    void quit(ActionEvent event) {
        if (Dialogs.showQuestionDialog("Lopetetaan", "Haluatko tallentaa ennen lopettamista?", "Kyll‰", "Ei")) save(event);
        System.exit(0);
    }
    @FXML
    void save(ActionEvent event) {
        if (Main.storage.saveData()) {            
            Dialogs.showMessageDialog("Tiedot tallennettu");
        } else {
            Dialogs.showMessageDialog("Virhe tallentaessa");
        }
    }
    
    /**
     * Disables menu buttons when editing/adding recipe
     */
    protected void disableMenuButtons() {
        menuEdit.setDisable(true);
        menuOpen.setDisable(true);
        menuPrint.setDisable(true);
    }
    
    
    /**
     * Enables disabled buttons
     */
    protected void enableMenuButtons() {
        menuEdit.setDisable(false);
        menuOpen.setDisable(false);
        menuPrint.setDisable(false);
    }

}
