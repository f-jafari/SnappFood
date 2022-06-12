package Controller;

import Model.Restaurant;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AddRestaurantPaneController implements Initializable {

    @FXML
    private TableView<Restaurant> restaurantTableView;

    @FXML
    private TableColumn<Restaurant, String> addressCol;

    @FXML
    private Button addRestaurantBTN;

    @FXML
    private TableColumn<Restaurant, String> nameCol;

    @FXML
    private Button addFoodCategoryBTN;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //--in restaurant tab--
        //init cols
        nameCol.setCellValueFactory(new PropertyValueFactory<Restaurant,String>("name"));
        addressCol.setCellValueFactory(new PropertyValueFactory<Restaurant,String>("address"));
        restaurantTableView.getItems().addAll(Restaurant.restaurants);

        addRestaurantBTN.setOnAction(e -> {
            try {

                Parent root = FXMLLoader.load(getClass().getResource("/View/addRestaurantPage.fxml"));
                Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            } catch(Exception ex) {
                ex.printStackTrace();
            }
        });

        addFoodCategoryBTN.setOnAction( e -> {

        });

        //--in cafe tab--
    }
}
