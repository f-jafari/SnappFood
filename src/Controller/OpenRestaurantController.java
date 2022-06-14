package Controller;

import Model.FoodCategory;
import Model.Restaurant;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class OpenRestaurantController implements Initializable {
    @FXML
    private TableView<FoodCategory> FoodCategoryTable;

    @FXML
    private Button backBTN;

    @FXML
    private Label nameLBL;

    @FXML
    private TableColumn<FoodCategory, String> FoodCategoryCol;

    @FXML
    private Label addressLBL;

    @FXML
    private Button addBTN;

    @FXML
    private Button OpenBTN;

    private Restaurant restaurant ;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //init cols and table
        FoodCategoryCol.setCellValueFactory(new PropertyValueFactory<FoodCategory,String>("name"));
        FoodCategoryTable.getItems().addAll(restaurant.getFoodCategories());
        //init labels
        nameLBL.setText(restaurant.getName());
        addressLBL.setText(restaurant.getAddress());

        backBTN.setOnAction( e -> {
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

    }

    public void initRestaurant(Restaurant restaurant)
    {
        this.restaurant = restaurant ;
    }
}
