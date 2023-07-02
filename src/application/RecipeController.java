package application;
import java.io.IOException;

import fi.jyu.mit.fxgui.CheckBoxChooser;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lib.Recipe;
import lib.Ingredient;

/**
 * @author tietokone
 * @version 7.6.2023
 *
 */
public class RecipeController extends MenuController{
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
        System.out.println("New recipe");
    }
    
    /**
     * @param recipe to be modified
     */
    public RecipeController(Recipe recipe) {
        System.out.println("Edit Recipe");
        this.recipe = recipe;
        initialize();
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
    
    @FXML 
    private void initialize() {
        Platform.runLater(() -> {
            setData();
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
    
    @FXML
    void test(ActionEvent event) {
        // Not in use
    }
}
