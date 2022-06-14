package Controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.ResourceBundle;

public class MainPageController implements Initializable {

    @FXML
    private Button loginBTN;

    @FXML
    private Button signUpBTN;

    @FXML
    private Button exitBTN;

    @FXML
    private AnchorPane scenePane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        loginBTN.setOnAction( e -> {
            try {

                Parent root = FXMLLoader.load(getClass().getResource("/View/loginScene.fxml"));
                Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.initStyle(StageStyle.UTILITY);
                stage.initStyle(StageStyle.UNDECORATED);
                stage.show();

            } catch(Exception ex) {
                ex.printStackTrace();
            }
        });

        signUpBTN.setOnAction( e -> {
            try {

                Parent root = FXMLLoader.load(getClass().getResource("/View/SignUpScene.fxml"));
                Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.initStyle(StageStyle.UTILITY);
                stage.initStyle(StageStyle.UNDECORATED);
                stage.show();

            } catch(Exception ex) {
                System.out.println(ex.getMessage());
            }
        });


        exitBTN.setOnAction( e -> {
            Stage stage ;
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Exit");
            alert.setHeaderText("You're about to exit!");
            if (alert.showAndWait().get() == ButtonType.OK)
            {
                stage = (Stage) scenePane.getScene().getWindow();
                stage.close();
            }
            //Platform.exit();
        });
    }
}
