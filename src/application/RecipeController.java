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
import lib.*;

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
    private Button btnNewIngredient;
    
    @FXML
    private Button btnDeleteIngredients;

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
     * default constructor, no recipe selected -> new recipe
     */
    public RecipeController() {
    }
    
    /**
     * @param recipe to be modified -> editing recipe
     */
    public RecipeController(Recipe recipe) {
        this.recipe = recipe;
    }
    
    @FXML
    void cancel(@SuppressWarnings("unused") ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/main.fxml"));
            loader.setController(new MainController());
            Stage s = (Stage) btnReturn.getScene().getWindow();
            s.setScene(new Scene(loader.load()));
            s.setTitle("Keittokirja");
            System.out.println("Loaded Main");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML 
    private void initialize() {
        storage = Main.storage;
        Platform.runLater(() -> {
            recipeTextArea.setWrapText(true);
            recipeCB1.clear();
            setIngredients();
            if (recipe != null) setData();
            storage = Main.storage;
        });    
    }
    
    /**
     * Sets the ingredients when initializing
     */
    public void setIngredients() {
        recipeCB1.clear();
        recipeCB2.clear();
        recipeCB3.clear();
        Ingredients ingredients = storage.getIngredients();
        ingredients.sortAlpha();
        for (int j = 0; j < storage.getIngredients().getRows(); j++) {
            for (int i = 0; i < 3; i++) {
                if (ingredients.getMatrix()[i][j] == null) continue;
                CheckBoxChooser<Ingredient> cbc;
                switch (i) {
                case 0:
                    cbc = recipeCB1;
                    break;
                case 1:
                    cbc = recipeCB2;
                    break;
                default:
                    cbc = recipeCB3;
                    break;
                }
                cbc.add(ingredients.getMatrix()[i][j].getName(), ingredients.getMatrix()[i][j]);
            }
        }
    }
    
    /**
     * Sets the fields to match recipes data.
     * If recipe has Ingredients that aren't in checkBoxes, adds those Ingredients if possible.
     */
    public void setData() {
        recipeTextName.setText(recipe.getName());
        recipeTextOrigin.setText(recipe.getOrigin());
        recipeTextCreator.setText(recipe.getCreator());
        recipeTextArea.setText(recipe.getGuide());      
        for (int j = 0; j < recipe.getIngredients().size(); j++) {
            Ingredient i = recipe.getIngredients().get(j);
            if (recipeCB1.getObjects().contains(i)) {
                recipeCB1.setSelectedIndex(recipeCB1.getObjects().indexOf(i));
            } else if (recipeCB2.getObjects().contains(i)) {
                recipeCB2.setSelectedIndex(recipeCB2.getObjects().indexOf(i));
            } else if (recipeCB3.getObjects().contains(i)) {
                recipeCB3.setSelectedIndex(recipeCB3.getObjects().indexOf(i));
            } else {
                if (!storage.addIngredient(i)) {
                    if (storage.getIngredients().containsValue(i)) {
                        Ingredient temp = storage.getIngredients().get(i.getName());
                        if(Dialogs.showQuestionDialog("Sama ainesosa", "Vaihdetaanko ainesosa " + i.getName() + " vastaamaan ainesosaa samalla nimellä/id:llä?", "Kyllä", "Ei")) {
                            recipe.replaceIngredient(i, temp);
                        }
                    }
                    System.out.println("Ingredient '" + i + "' not enough space.");
                }
                else {
                    setIngredients();
                    setData();
                }
            }
        }
    }
    
    /**
     * Prompts Dialogs to get data for a new ingredient
     */
    public void newIngredient() {
        String input = Dialogs.showInputDialog("Anna uuden ainesosan nimi", "");
        if (input == null) return;
        input = input.trim().toLowerCase();
        if (input.isEmpty() || input.contains("|")) {
            Dialogs.showMessageDialog("Virhe annetussa ainesosan nimessä");
            return;
        }
        Ingredient ingredient = new Ingredient(-1, input);
        if(!storage.addIngredient(ingredient)) {
            Ingredient.setNextId(Ingredient.getNextId() - 1);
            Dialogs.showMessageDialog("Virhe lisätessä ainesosaa (ei tilaa tai olemassa jo samanniminen ainesosa)");
        }
        for (int j = 0; j < storage.getIngredients().getRows(); j++) {
            for (int i = 0; i < 3; i++) {
                if (storage.getIngredients().getMatrix()[i][j] != ingredient) continue;
                CheckBoxChooser<Ingredient> cbc;
                switch (i) {
                case 0:
                    cbc = recipeCB1;
                    break;
                case 1:
                    cbc = recipeCB2;
                    break;
                default:
                    cbc = recipeCB3;
                    break;
                }
                cbc.add(ingredient.getName(), ingredient);
            }
        }
    }
    
    /**
     * Deletes all currently selected ingredients
     */
    public void deleteIngredients() {
        if (!Dialogs.showQuestionDialog("Poistetaanko", "Haluatko varmasti poistaa kaikki valitut ainesosat?", "Kyllä", "Ei")) return;
        ArrayList<Ingredient> selected = (ArrayList<Ingredient>) recipeCB1.getSelectedObjects();
        for (Ingredient i : (ArrayList<Ingredient>) recipeCB2.getSelectedObjects()) {
            selected.add(i);
        }
        for (Ingredient i : (ArrayList<Ingredient>) recipeCB3.getSelectedObjects()) {
            selected.add(i);
        }
        for (int j = 0; j < storage.getIngredients().getRows(); j++) {
            for (int i = 0; i < 3; i++) {
                for (Ingredient ingredient : selected) {                    
                    if (storage.getIngredients().getMatrix()[i][j] == ingredient) {
                        storage.deleteIngredient(ingredient.getId());
                        i--;
                        break;
                    }
                }
            }
        }
        setIngredients();
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
        ArrayList<Ingredient> selected = (ArrayList<Ingredient>) recipeCB1.getSelectedObjects();
        for (Ingredient i : (ArrayList<Ingredient>) recipeCB2.getSelectedObjects()) {
            selected.add(i);
        }
        for (Ingredient i : (ArrayList<Ingredient>) recipeCB3.getSelectedObjects()) {
            selected.add(i);
        }
        recipe = new Recipe(id, recipeTextName.getText().trim(), recipeTextCreator.getText().trim(),
                recipeTextOrigin.getText().trim(), "not implemented course", 
                recipeTextArea.getText().trim(), selected);
        storage.addRecipe(recipe);
        text = text.replace("nimi", recipe.getName());
        Dialogs.showMessageDialog(text);
        resetFields();
    }
    
    /**
     * Checks if any textField is empty
     * */
    private boolean emptyFields() {
        if(recipeTextName.getText().trim().isEmpty()) return true;
        if(recipeTextCreator.getText().trim().isEmpty()) return true;
        if(recipeTextOrigin.getText().trim().isEmpty()) return true;
        if(recipeTextArea.getText().trim().isEmpty()) return true;
        return false;
    }
    
    /**
     * Checks if any textField contains '|' character
     */
    private boolean containsIllegalChar() {
        if(recipeTextName.getText().contains("|")) return true;
        if(recipeTextCreator.getText().contains("|")) return true;
        if(recipeTextOrigin.getText().contains("|")) return true;
        if(recipeTextArea.getText().contains("|")) return true;
        return false;
    }
    
    /**
     * empties textFields, removes recipe from being modified and resets other fields
     * */
    private void resetFields() {
        recipeTextName.setText("");
        recipeTextArea.setText("");
        recipeTextOrigin.setText("");
        recipeTextCreator.setText("");
        recipe = null;
        recipeComboCourse.setValue(null);
        // TODO: reset checkBoxes
    }
}
