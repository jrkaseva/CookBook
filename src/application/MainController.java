package application;
import java.io.IOException;

import fi.jyu.mit.fxgui.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
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
    private ComboBoxChooser<String> mainComboBox;
    
    @FXML
    private ComboBoxChooser<String> mainComboOrder;

    @FXML
    private Label mainComboLabel;

    @FXML
    private ListChooser<Recipe> mainListChooser;

    @FXML
    private TextArea mainTextArea;
    
    @FXML 
    private TextField mainSearch;
    
    /**
     * 
     */
    public MainController() {}
    
    @FXML 
    private void initialize() {
        Platform.runLater(() -> {
            mainTextArea.setWrapText(true);
            setData();
            stage.getScene().getStylesheets().add("styles/main.css");
            enableMenuButtons();
        });
    }
    
    @FXML
    void starRecipe(ActionEvent event) {
        mainListChooser.getSelectedObject().setStarred(!mainListChooser.getSelectedObject().getStarred());
        listListener();
        if (mainComboBox.getSelectedObject().equals("Lempparit")) boxListener("");
    }
    
    @FXML
    void editRecipe(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/recipe.fxml"));
            RecipeController controller = new RecipeController(mainListChooser.getSelectedObject(), false);
            loader.setController(controller);
            stage.setScene(new Scene(loader.load()));
            System.out.println("Loaded Recipe (edit)");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    void help(ActionEvent event) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Help");
        alert.setContentText("Tässä näkymässä voit valita reseptin ja nähdä tämän ohjeen. "
                + "Lisäksi voit poistaa, muokata ja asettaa reseptejä lemppariksi. "
                + "Voit myös rajata, mitä reseptejä haluat nähdä.");
        alert.setHeaderText(null);
        alert.getDialogPane().setPrefSize(400, -1);
        alert.showAndWait();
    }
    
    /**
     * Sets the data for the scene
     */
    private void setData() {
        mainListChooser.setRivit(new String[0]);
        stage = (Stage) mainBtnInfo.getScene().getWindow();
        storage = Main.storage;
        mainListChooser.addSelectionListener( v -> {
            listListener();
        });
        mainComboLabel.setText(storage.getRecipes().count() + "");
        mainListChooser.setSelectedIndex(0);
        mainComboBox.clear();
        mainComboBox.add("Kaikki");
        mainComboBox.add("Lempparit");
        mainComboBox.add("Alkuruoka");
        mainComboBox.add("Pääruoka");
        mainComboBox.add("Jälkiruoka");
        mainComboBox.add("Muu");
        mainComboBox.addSelectionListener( v -> {
            boxListener(mainSearch.getText());
        });
        mainComboBox.setSelectedIndex(0);
        mainComboOrder.add("Vanhasta uuteen");
        mainComboOrder.add("A -> Ö");
        mainComboOrder.add("Uudesta vanhaan");
        mainComboOrder.add("Ö -> A");
        mainComboOrder.addSelectionListener(v -> {
            if (mainComboOrder.getSelectedIndex() == 0) {
                storage.getRecipes().getRecipes().sort(Recipe::compareIdOld);
            } if (mainComboOrder.getSelectedIndex() == 1) {
                storage.getRecipes().getRecipes().sort(Recipe::compareAlpha);
            } if (mainComboOrder.getSelectedIndex() == 2) {
                storage.getRecipes().getRecipes().sort(Recipe::compareIdNew);
            } if (mainComboOrder.getSelectedIndex() == 3) {
                storage.getRecipes().getRecipes().sort(Recipe::compareZulu);
            }
            boxListener(mainSearch.getText());
        });
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
    
    private void listListener() {
        StringBuilder bldr = new StringBuilder();
        if (mainListChooser.getSelectedObject().getStarred()) bldr.append("##########\n  LEMPPARI  \n##########\n");
        bldr.append("Ainesosat: \n");
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
    }
    
    private void boxListener(String str) {
        mainListChooser.clear();
        if (mainComboBox.getSelectedObject() == null) mainComboBox.setSelectedIndex(0);
        for (Recipe r : storage.getRecipes().getByTrait(mainComboBox.getSelectedObject())) {
            if (r.getName(false).contains(str)) mainListChooser.add(r.getName(true), r);
        }
        mainTextArea.setText("Ei valittua reseptiä");
        if (mainListChooser.getObjects().size() != 0) mainListChooser.setSelectedIndex(0);
        mainComboLabel.setText(mainListChooser.getObjects().size() + "");
    }
    
    @Override @FXML
    void openRecipe(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/recipe.fxml"));
            RecipeController controller = new RecipeController(mainListChooser.getSelectedObject(), true);
            loader.setController(controller);
            stage.setScene(new Scene(loader.load()));
            System.out.println("Loaded Recipe (non-editable)");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    void search() {
        boxListener(mainSearch.getText());
    }
}
