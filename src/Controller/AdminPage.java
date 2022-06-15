package Controller;

import Model.Cafe;
import Model.Place;
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

    @FXML
    private Button backToMainBTN;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //--in restaurant tab--
        //init cols
        restaurantNameCol.setCellValueFactory(new PropertyValueFactory<Restaurant,String>("name"));
        restaurantAddressCol.setCellValueFactory(new PropertyValueFactory<Restaurant,String>("address"));
        //restaurantTableView.getItems().addAll(Restaurant.restaurants);
        for (Place place : Place.places)
        {
            if (place instanceof Restaurant)
                restaurantTableView.getItems().add((Restaurant) place);
        }

        backToMainBTN.setOnAction( e -> {
            try {

                Parent root = FXMLLoader.load(getClass().getResource("/View/mainScene.fxml"));
                Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            } catch(Exception ex) {
                ex.printStackTrace();
            }
        });

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
            Restaurant chosenRestaurant = restaurantTableView.getSelectionModel().getSelectedItem();
            if (chosenRestaurant != null)
            {
                try {

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/openRestaurantOrCafePage.fxml"));
                    OpenRestaurantController controller = new OpenRestaurantController();
                    controller.initRestaurant(chosenRestaurant);
                    loader.setController(controller);
                    loader.load();
                    Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
                    Scene scene = new Scene(loader.getRoot());
                    stage.setScene(scene);
                    stage.show();

                } catch(Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });

        //--in cafe tab--
        //init table
        cafeNameCol.setCellValueFactory(new PropertyValueFactory<Cafe,String>("name"));
        cafeAddressCol.setCellValueFactory(new PropertyValueFactory<Cafe,String>("Address"));
        //cafeTable.getItems().addAll(Cafe.cafes);
        for (Place place : Place.places)
        {
            if (place instanceof Cafe)
                cafeTable.getItems().add((Cafe) place);
        }

        addCafeBTN.setOnAction( e -> {
            try {

                Parent root = FXMLLoader.load(getClass().getResource("/View/addCafePage.fxml"));
                Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            } catch(Exception ex) {
                ex.printStackTrace();
            }
        });

        openCafeBTN.setOnAction( e -> {
            Cafe chosenCafe = cafeTable.getSelectionModel().getSelectedItem();

            if (chosenCafe != null)
            {
                try {

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/openRestaurantOrCafePage.fxml"));
                    OpenCafeController controller = new OpenCafeController();
                    controller.initCafe(chosenCafe);
                    loader.setController(controller);
                    loader.load();
                    Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
                    Scene scene = new Scene(loader.getRoot());
                    stage.setScene(scene);
                    stage.show();

                } catch(Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
    }
}
