package Controller;

import Model.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class OpenCafeForOrderController implements Initializable {

    private Place place ;
    private Client client ;
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

    @FXML
    private TextField foodCategoryFLD;

    @FXML
    private Label errorLBL;

    public void initPlace(Place place , Client client)
    {
        this.place = place ;
        this.client = client;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addBTN.setVisible(false);
        foodCategoryFLD.setVisible(false);
        //init cols and table
        FoodCategoryCol.setCellValueFactory(new PropertyValueFactory<FoodCategory,String>("name"));
        if (place instanceof Cafe )
        {
            Cafe cafe = (Cafe) place;
            FoodCategoryTable.getItems().addAll(cafe.getFoodCategories());
        }
        else
        {
            Restaurant restaurant = (Restaurant) place ;
            FoodCategoryTable.getItems().addAll(restaurant.getFoodCategories());
        }

        //init labels
        nameLBL.setText(place.getName());
        addressLBL.setText(place.getAddress());

        backBTN.setOnAction( e -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/clientAccountPage.fxml"));
                ClientAccountController controller = new ClientAccountController();
                controller.initLoggedUser(client);
                loader.setController(controller);
                loader.load();
                Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
                Scene scene = new Scene(loader.getRoot());
                stage.setScene(scene);
                stage.show();

            } catch(Exception ex) {
                ex.printStackTrace();
            }
        });


        OpenBTN.setOnAction( e -> {
            FoodCategory chosenFoodCategory = FoodCategoryTable.getSelectionModel().getSelectedItem();
            if (chosenFoodCategory != null)
            {
                try {

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/foodCategoryFoodsMenu.fxml"));
                    FoodCategoryMenuController controller = new FoodCategoryMenuController();
                    controller.initFoodCategory(chosenFoodCategory);
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
