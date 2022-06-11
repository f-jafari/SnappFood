import Model.Admin;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        initAdmin();
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

    }

    public static void initAdmin()
    {
        Admin admin = new Admin();
        admin.setEmail("admin@gmail.com");
        admin.setPassword("12345678");
    }
}
