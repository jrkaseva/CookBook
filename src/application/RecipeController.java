package application;
import java.io.IOException;
import java.util.ArrayList;

import fi.jyu.mit.fxgui.CheckBoxChooser;
import fi.jyu.mit.fxgui.Dialogs;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lib.Recipe;
import lib.Storage;
import lib.Ingredient;

/**
 * @author tietokone
 * @version 7.6.2023
 *
 */
public class RecipeController extends MenuController{
    
    private Storage storage;
    
    private Recipe recipe;

    @FXML
    private Button btnReturn;

    @FXML
    private Button recipeBtnConfirm;

    @FXML
    private Button recipeBtnInfo;

    @FXML
    private CheckBoxChooser<Ingredient> recipeCB1;

    @FXML
    private CheckBoxChooser<Ingredient> recipeCB2;

    @FXML
    private CheckBoxChooser<Ingredient> recipeCB3;

    @FXML
    private ComboBox<String> recipeComboCourse;

    @FXML
    private TextArea recipeTextArea;

    @FXML
    private TextField recipeTextCreator;

    @FXML
    private TextField recipeTextName;

    @FXML
    private TextField recipeTextOrigin;
    
    /**
     * 
     */
    public RecipeController() {
    }
    
    /**
     * @param recipe to be modified
     */
    public RecipeController(Recipe recipe) {
        this.recipe = recipe;
    }
    
    @FXML
    void cancel(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/main.fxml"));
            loader.setController(new MainController());
            Stage s = (Stage) btnReturn.getScene().getWindow();
            s.setScene(new Scene(loader.load()));
            s.setTitle("Keittokirja");
            System.out.println("Loaded Main");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    @FXML 
    private void initialize() {
        Platform.runLater(() -> {
            if (recipe != null) setData();
            storage = Main.storage;
        });    
    }
    
    /**
     * 
     */
    public void setData() {
        recipeTextName.setText(recipe.getName());
        recipeTextOrigin.setText(recipe.getOrigin());
        recipeTextCreator.setText(recipe.getCreator());
        recipeTextArea.setText(recipe.getGuide());
    }
    
    /**
     * Creates/edits a recipe
     */
    public void createRecipe() {
        if (containsIllegalChar()) {
            Dialogs.showMessageDialog("Laiton merkki '\\|' tekstikentässä");
            return;
        }
        if (emptyFields()) {
            Dialogs.showMessageDialog("Virhe: tyhjä tekstikenttä");
            return;
        }
        int id = -1;
        String text = "Lisätty resepti nimi";
        if (recipe != null) {
            // Editing recipe
            id = recipe.getId();
            storage.deleteRecipe(id);
            text = text.replace("Lisätty", "Muokattu");
        }
        // TODO COMBOBOX FOR COURSE
        recipe = new Recipe(id, recipeTextName.getText().trim(), recipeTextCreator.getText().trim(),
                recipeTextOrigin.getText().trim(), "not implemented course", 
                recipeTextArea.getText().trim(), new ArrayList<Ingredient>());
        storage.addRecipe(recipe);
        text = text.replace("nimi", recipe.getName());
        Dialogs.showMessageDialog(text);
        resetFields();
    }
    
    private boolean emptyFields() {
        if(recipeTextName.getText().trim().isEmpty()) return true;
        if(recipeTextCreator.getText().trim().isEmpty()) return true;
        if(recipeTextOrigin.getText().trim().isEmpty()) return true;
        if(recipeTextArea.getText().trim().isEmpty()) return true;
        return false;
    }

    private boolean containsIllegalChar() {
        if(recipeTextName.getText().contains("|")) return true;
        if(recipeTextCreator.getText().contains("|")) return true;
        if(recipeTextOrigin.getText().contains("|")) return true;
        if(recipeTextArea.getText().contains("|")) return true;
        return false;
    }
    
    private void resetFields() {
        recipeTextName.setText("");
        recipeTextArea.setText("");
        recipeTextOrigin.setText("");
        recipeTextCreator.setText("");
        recipe = null;
        recipeComboCourse.setValue(null);
    }
}
