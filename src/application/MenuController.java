package application;
import java.io.IOException;

import fi.jyu.mit.fxgui.Dialogs;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
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

    @FXML
    protected MenuItem menuAddR;

    @FXML
    protected MenuItem menuDelR;

    @FXML
    protected Menu menuEdit;

    @FXML
    protected MenuItem menuFavR;

    @FXML
    protected Menu menuFile;

    @FXML
    protected Menu menuHelp;

    @FXML
    protected MenuItem menuInfo;

    @FXML
    protected MenuItem menuModR;

    @FXML
    protected MenuItem menuOpen;

    @FXML
    protected MenuItem menuPrint;

    @FXML
    protected MenuItem menuQuit;

    @FXML
    protected MenuItem menuSave;

    @FXML
    protected MenuItem menuShowHelp;

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
    void editRecipe(ActionEvent event) {
        Dialogs.showMessageDialog("Going to edit recipe");
        System.out.println("Ei toiminnossa (Muokkaa resepti‰)");
    }

    @FXML
    void help(ActionEvent event) {
        System.out.println("Ei toiminnossa (Apua)");
    }

    @FXML
    void info(ActionEvent event) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setContentText("Joakim Kaseva\nIT-JYU\nOhjelmointi 2");
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
        System.out.println("Ei toiminnossa (Lopeta)");
    }

    @FXML
    void removeRecipe(ActionEvent event) {
        System.out.println("Ei toiminnossa (Poista resepti)");
    }

    @FXML
    void save(ActionEvent event) {
        System.out.println("Ei toiminnossa (Tallenna)");
    }

    @FXML
    void starRecipe(ActionEvent event) {
        System.out.println("Ei toiminnossa (Lemppariksi resepti)");
    }
    
    @FXML
    void onClick(ActionEvent event) {
        Dialogs.showMessageDialog("Ei viel‰ toiminnassa");
    }

}
