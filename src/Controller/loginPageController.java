package Controller;

import Model.Admin;
import Model.Person;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class loginPageController implements Initializable {

    @FXML
    private TextField usernameFLD;

    @FXML
    private Button loginBTN;

    @FXML
    private PasswordField passwordFLD;

    @FXML
    private Hyperlink signUPLink;

    @FXML
    private Label errorLBL;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        signUPLink.setOnAction( e -> {
            try {

                Parent root = FXMLLoader.load(getClass().getResource("/View/SignUpScene.fxml"));
                Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            } catch(Exception ex) {
                ex.printStackTrace();
            }
        });

        loginBTN.setOnAction( e -> {
            if(!usernameFLD.getText().isEmpty() && !passwordFLD.getText().isEmpty())
            {

                Boolean loginScale = false ;
                for (Person person : Person.people)
                {
                    if (person.getEmail().equals(usernameFLD.getText()) && person.getPassword().equals(passwordFLD.getText()))
                    {
                        loginScale = true ;
                        if (person instanceof Admin)
                        {
                            try {

                                Parent root = FXMLLoader.load(getClass().getResource("/View/adminAccountPage.fxml"));
                                Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
                                Scene scene = new Scene(root);
                                stage.setScene(scene);
                                stage.show();

                            } catch(Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                        break;
                    }
                }

                if (!loginScale)
                {
                    errorLBL.setTextFill(Color.RED);
                    errorLBL.setText("Your username does not exist or password is wrong!!\nHmm.. Forget Your Password?!");
                    errorLBL.setStyle("-fx-border-color: red");
                }
            }
            else
            {
                errorLBL.setTextFill(Color.RED);
                errorLBL.setText("You Have Empty Field");
                errorLBL.setStyle("-fx-border-color: red");
            }
        });

    }
}
