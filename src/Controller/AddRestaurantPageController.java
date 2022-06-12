package Controller;

import Model.Restaurant;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class AddRestaurantPageController implements Initializable {
    @FXML
    private TextField nameFLD;

    @FXML
    private TextField addressFLD;

    @FXML
    private Label errorLBL;

    @FXML
    private Button addBTN;

    @FXML
    private Button backBTN;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        backBTN.setOnAction(e -> {

            try {

                Parent root = FXMLLoader.load(getClass().getResource("/View/adminAccountPage.fxml"));
                Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            } catch(Exception ex) {
                ex.printStackTrace();
            }
        });

        addBTN.setOnAction( e -> {
            if (!nameFLD.getText().isEmpty() && !addressFLD.getText().isEmpty())
            {
                if (shouldAdd())
                {
                    Restaurant restaurant = new Restaurant();
                    restaurant.setName(nameFLD.getText());
                    restaurant.setAddress(addressFLD.getText());
                    Restaurant.restaurants.add(restaurant);

                    //load admin page
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
            }
            else
            {
                errorLBL.setTextFill(Color.RED);
                errorLBL.setText("You Have Empty Field");
                errorLBL.setStyle("-fx-border-color: red");
            }
        });
    }

    public Boolean shouldAdd()
    {
        for (Restaurant restaurant : Restaurant.restaurants)
        {
            if (restaurant.getAddress().equals(addressFLD.getText()) && restaurant.getName().equals(nameFLD.getText()))
            {
                errorLBL.setTextFill(Color.RED);
                errorLBL.setText("added before");
                errorLBL.setStyle("-fx-border-color: red");
                return false ;
            }
        }
        return true;
    }

}

