package Controller;
import Model.Food;
import Model.FoodCategory;
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
import java.util.regex.Pattern;

public class FoodCategoryMenuController implements Initializable {
    @FXML
    private TextField foodNameFLD;

    @FXML
    private TableView<Food> FoodsTable;

    @FXML
    private TableColumn<Food,String> PriceCol;

    @FXML
    private TableColumn<Food, String> nameCol;

    @FXML
    private Button backBTN;

    @FXML
    private Label errorLBL;

    @FXML
    private Button addBTN;

    @FXML
    private Label foodCategoryNameLBL;

    @FXML
    private TextField priceFLD;

    private FoodCategory foodCategory ;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        foodCategoryNameLBL.setText(foodCategory.getName());

        //init cols and table
        nameCol.setCellValueFactory(new PropertyValueFactory<Food,String>("name"));
        PriceCol.setCellValueFactory(new PropertyValueFactory<Food,String>("price"));
        FoodsTable.getItems().addAll(foodCategory.getFoods());


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

        addBTN.setOnAction( e  ->{
            if (!foodNameFLD.getText().isEmpty() && !priceFLD.getText().isEmpty())
            {
                if (Pattern.matches("^[0-9]+$",priceFLD.getText()))
                {
                    if (addedBefore())
                    {
                        errorLBL.setTextFill(Color.GREEN);
                        errorLBL.setText("Done");
                        errorLBL.setStyle("-fx-border-color: green");
                        Food food = new Food(foodNameFLD.getText(),priceFLD.getText());
                        foodCategory.getFoods().add(food);
                        refresh();
                    }
                }
                else
                {
                    errorLBL.setTextFill(Color.RED);
                    errorLBL.setText("invalid price!");
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

    public void initFoodCategory(FoodCategory foodCategory)
    {
        this.foodCategory = foodCategory;
    }
    public void refresh()
    {
        FoodsTable.getItems().clear();
        FoodsTable.getItems().addAll(foodCategory.getFoods());
    }

    public boolean addedBefore()
    {
        for (Food food : foodCategory.getFoods())
        {
            if (food.getName().equals(foodNameFLD.getText()))
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
