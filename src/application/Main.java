package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import lib.Ingredient;
import lib.Recipe;
import lib.Storage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.fxml.FXMLLoader;


/**
 * @author tietokone
 * @version 9.6.2023
 *
 */
public class Main extends Application {
    /**
     * Scene stage
     */
    public static Stage stage;
    
    /**
     * Storage instance
     */
    public static Storage storage;
    
	@Override
	public void start(Stage primaryStage) {
	    stage = primaryStage;
	    storage = Storage.getInstance();
	    storage.loadData();
	    // createTestObjects();
		try {
		    FXMLLoader loader = new FXMLLoader();
		    loader.setLocation(getClass().getResource("/fxml/home.fxml"));
		    loader.setController(new HomeController());
			VBox root = (VBox) loader.load();
			Scene scene = new Scene(root,1080,720);
			scene.getStylesheets().add(getClass().getResource("/styles/application.css").toExternalForm());
			stage.setScene(scene);
			stage.setResizable(false);
			stage.setTitle("Keittokirja");
			stage.show();
			stage.setUserData(storage);
			stage.setOnCloseRequest(v -> {
			   System.out.println("Saving data (not in use)");
			});
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * @param args not in use
	 */
	public static void main(String[] args) {
		launch(args);
	}
	
	private void createTestObjects() {
	    for (int i = 0; i < 15; i++) {	        
	        storage.addRecipe(new Recipe());
	        storage.addIngredient(new Ingredient());
	    }
	}
}
