package application;
import java.io.IOException;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ListChooser;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import lib.Storage;
import lib.Ingredient;
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
            mainTextArea.setWrapText(true);
            setData();
            stage.getScene().getStylesheets().add("styles/main.css");
            btnNewRecipe.getStyleClass().add("bot");
        });
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
            e.printStackTrace();
        }
    }
    
    @FXML
    void showInfo(ActionEvent event) {
        Dialogs.showMessageDialog("Tässä näkymässä voit valita reseptin ja nähdä tämän ohjeen. "
                + "Lisäksi voit poistaa, muokata ja asettaa reseptejä lemppariksi. "
                + "Voit myös rajata, mitä reseptejä haluat nähdä.");
    }
    
    /**
     * Sets the data for the scene
     */
    private void setData() {
        mainListChooser.setRivit(new String[0]);
        stage = (Stage) mainBtnInfo.getScene().getWindow();
        storage = Main.storage;
        for (Recipe r : storage.getRecipes().getRecipes()) {
            mainListChooser.add(r.getName(), r);
        }
        mainListChooser.addSelectionListener(v -> {
            StringBuilder bldr = new StringBuilder("Ainesosat:\n");
            if (!mainListChooser.getSelectedObject().getIngredients().isEmpty()) {      
                int count = 0;
                for (Ingredient i : mainListChooser.getSelectedObject().getIngredients()) {
                    if (i == null) count++;
                    else bldr.append(i.getName() + "\n");
                }
                if (count != 0) bldr.append(count + " missing ingredients. Try editing recipe to add missing ingredients" + "\n");
            } else {
                bldr.append("Ei asetettu ainesosia\n");
            }
            bldr.append("****************\n");
            if (mainListChooser.getSelectedObject().getGuide() != null) bldr.append(mainListChooser.getSelectedObject().getGuide());
            mainTextArea.setText(bldr.toString());
        });
        mainComboLabel.setText("0");
        mainListChooser.setSelectedIndex(0);
        
    }
    
    /**
     * Removes the current recipe selected by mainListChooser
     */
    public void removeSelectedRecipe() {
        if (!Dialogs.showQuestionDialog("Varmistus", "Haluatko varmasti poistaa valitun reseptin?",
                "Kyllä", "Ei")) return;
        storage.deleteRecipe(mainListChooser.getSelectedObject().getId());
        initialize();
    }
}
