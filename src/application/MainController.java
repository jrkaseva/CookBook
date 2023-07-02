package application;
import java.io.IOException;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ListChooser;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import lib.Storage;
import lib.Recipe;

/**
 * @author tietokone
 * @version 7.6.2023
 * Controller for main scene
 */
public class MainController extends MenuController{
    
    private static Stage stage;
    
    private static Storage storage;
    
    @FXML
    private Button btnEditRecipe;
    
    @FXML
    private Button btnNewRecipe;
    
    @FXML
    private Button mainBtnDelete;
    
    @FXML
    private Button mainBtnFav;
    
    @FXML
    private Button mainBtnInfo;
    
    @FXML
    private ComboBox<?> mainComboBox;

    @FXML
    private Label mainComboLabel;

    @FXML
    private ListChooser<Recipe> mainListChooser;

    @FXML
    private TextArea mainTextArea;
    
    /**
     * 
     */
    public MainController() {
         System.out.println("Main");
         initialize();
         /* DIALOG USAGE
         Dialogs.showMessageDialog("Test dialog");
         Boolean choice = Dialogs.showQuestionDialog("TEST", "Test dialog", "Yes", "No");
         String input = Dialogs.showInputDialog("TEST", "Test dialog");
         System.out.println(choice + " : " + input);
         */
     }
    
    @FXML 
    private void initialize() {
        Platform.runLater(() -> {
            setData();
        });    
    }
    
    @FXML
    void btnThing3(ActionEvent event) {
        Dialogs.showMessageDialog("BtnThing3");
    }
    
    @FXML
    void btnThing4(ActionEvent event) {
        Dialogs.showMessageDialog("BtnThing4");
    }
    
    @Override @FXML
    void editRecipe(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/recipe.fxml"));
            RecipeController controller = new RecipeController(mainListChooser.getSelectedObject());
            loader.setController(controller);
            stage.setScene(new Scene(loader.load()));
            System.out.println("Loaded Recipe (edit)");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    @FXML
    void showInfo(ActionEvent event) {
        Dialogs.showMessageDialog("Tässä näkymässä voit valita reseptin ja nähdä tämän ohjeen. "
                + "Lisäksi voit poistaa, muokata ja asettaa reseptejä lemppariksi. "
                + "Voit myös rajata, mitä reseptejä haluat nähdä.");
    }
    
    private void setData() {
        mainListChooser.setRivit(new String[0]);
        stage = (Stage) mainBtnInfo.getScene().getWindow();
        storage = (Storage) stage.getUserData();
        for (int i : storage.getRecipes().keySet()) {
            mainListChooser.add(i + "", storage.getRecipes().get(i));
        }
        mainListChooser.addSelectionListener(v -> {
            mainTextArea.setText(mainListChooser.getSelectedObject().getGuide());
        });
    }
}
