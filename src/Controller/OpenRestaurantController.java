package Controller;

import Model.FoodCategory;
import Model.FoodType;
import Model.Restaurant;
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

    @FXML
    private TextField foodCategoryFLD;

    @FXML
    private Label errorLBL;


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

        addBTN.setOnAction( e -> {
            if(!foodCategoryFLD.getText().isEmpty())
            {
                if (!addedBefore())
                {
                    errorLBL.setTextFill(Color.RED);
                    errorLBL.setText("added before");
                    errorLBL.setStyle("-fx-border-color: red");
                }
                else {
                    errorLBL.setTextFill(Color.GREEN);
                    errorLBL.setText("Done");
                    errorLBL.setStyle("-fx-border-color: green");
                    FoodCategory foodCategory = new FoodCategory();
                    foodCategory.setName(foodCategoryFLD.getText());
                    foodCategory.setFoodType(FoodType.RESTAURANT_FOOD);
                    restaurant.getFoodCategories().add(foodCategory);
                    refresh();
                }
            }
            else {

                errorLBL.setTextFill(Color.RED);
                errorLBL.setText("You Have Empty Field");
                errorLBL.setStyle("-fx-border-color: red");
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

    public void initRestaurant(Restaurant restaurant)
    {
        this.restaurant = restaurant ;
    }

    public boolean addedBefore()
    {
        for (FoodCategory foodCategory: restaurant.getFoodCategories())
        {
            if (foodCategory.getName().equals(foodCategoryFLD.getText()))
                return false ;
        }
        return true ;
    }

    public void refresh()
    {
        FoodCategoryTable.getItems().clear();
        FoodCategoryTable.getItems().addAll(restaurant.getFoodCategories());
    }
}
