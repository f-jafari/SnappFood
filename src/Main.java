import Model.Admin;
import Model.Person;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {
    public static void main(String[] args) {
        initAdmin();
        launch(args);
    }

    @Override
	public void start(Stage stage) {
		try {
			
			Parent root = FXMLLoader.load(getClass().getResource("/View/mainScene.fxml"));
			Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("View/Style/MainPage.css").toExternalForm());
			stage.setTitle("SnappFood");
			stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.initStyle(StageStyle.UNDECORATED);
			stage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

    public static void initAdmin()
    {
        Admin admin = new Admin();
        admin.setEmail("admin@gmail.com");
        admin.setPassword("12345678");
        Person.people.add( (Person) admin);
    }
}
