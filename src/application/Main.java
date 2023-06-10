package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.fxml.FXMLLoader;


/**
 * @author tietokone
 * @version 9.6.2023
 *
 */
public class Main extends Application {
    public static Stage stage;
	@Override
	public void start(Stage primaryStage) {
	    stage = primaryStage;
		try {
		    FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
		    loader.setController(new HomeController());
			VBox root = (VBox) loader.load();
			Scene scene = new Scene(root,1080,720);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
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
}
