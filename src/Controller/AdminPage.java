package Controller;

import Model.Cafe;
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

public class AdminPage implements Initializable {

    @FXML
    private TableView<Restaurant> restaurantTableView;

    @FXML
    private TableColumn<Restaurant, String> restaurantAddressCol;

    @FXML
    private Button addRestaurantBTN;

    @FXML
    private TableColumn<Restaurant, String> restaurantNameCol;


    @FXML
    private TableColumn<Cafe,String> cafeNameCol;

    @FXML
    private TableColumn<Cafe, String> cafeAddressCol;


    @FXML
    private Button OpenRestaurantBTN;

    @FXML
    private Button openCafeBTN;

    @FXML
    private TableView<Cafe> cafeTable;

    @FXML
    private Button addCafeBTN;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //--in restaurant tab--
        //init cols
        restaurantNameCol.setCellValueFactory(new PropertyValueFactory<Restaurant,String>("name"));
        restaurantAddressCol.setCellValueFactory(new PropertyValueFactory<Restaurant,String>("address"));
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

        OpenRestaurantBTN.setOnAction( e -> {

        });

        //--in cafe tab--
        //init table
        cafeNameCol.setCellValueFactory(new PropertyValueFactory<Cafe,String>("name"));
        cafeAddressCol.setCellValueFactory(new PropertyValueFactory<Cafe,String>("Address"));
        cafeTable.getItems().addAll(Cafe.cafes);


    }
}
