package Controller;

import Model.Client;
import Model.Person;
import com.sun.javaws.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class SignUpController implements Initializable {


    @FXML
    private Button signupBTN;

    @FXML
    private TextField phoneFLD;

    @FXML
    private TextField passwordFLD;

    @FXML
    private TextField firstNameFLD;

    @FXML
    private TextField lastNameFLD;

    @FXML
    private TextField emailFLD;

    @FXML
    private Label errorLBL;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        signupBTN.setOnAction( e -> {
            if (!firstNameFLD.getText().isEmpty()  && !emailFLD.getText().isEmpty() && !passwordFLD.getText().isEmpty() && !lastNameFLD.getText().isEmpty() && !phoneFLD.getText().isEmpty())
            {
                if (Pattern.matches("^[a-zA-Z]+$",firstNameFLD.getText()) && Pattern.matches("^[a-zA-Z]+$",lastNameFLD.getText()))
                {

                    if (Pattern.matches("^[\\_ a-zA-z0-9 \\.]+[@][a-zA-Z0-9]+\\.[a-zA-Z]{3}$",emailFLD.getText()))
                    {
                        if (Pattern.matches("^[a-zA-Z0-9]{8,}$",passwordFLD.getText()))
                        {

                            if(Pattern.matches("^09[0-9]{9}$", phoneFLD.getText() ) || Pattern.matches("^00989[0-9]{9}$", phoneFLD.getText() ) || Pattern.matches("^\\+989[0-9]{9}$", phoneFLD.getText() ))
                            {
                                if (UserShouldBeHere())
                                {
                                    errorLBL.setTextFill(Color.GREEN);
                                    errorLBL.setText("Done");
                                    errorLBL.setStyle("-fx-border-color: green");
                                    Client client = new Client();
                                    client.setName(firstNameFLD.getText());
                                    client.setLastName(lastNameFLD.getText());
                                    client.setEmail(emailFLD.getText());
                                    client.setPhoneNumber(phoneFLD.getText());
                                    client.setPassword(passwordFLD.getText());
                                    client.setWallet(0);
                                    Person.people.add((Person) client);
                                }

                            }
                            else
                            {
                                errorLBL.setTextFill(Color.RED);
                                errorLBL.setText("inValid phone number");
                                errorLBL.setStyle("-fx-border-color: red");
                            }
                        }
                        else
                        {
                            errorLBL.setTextFill(Color.RED);
                            errorLBL.setText("inValid Password ,Point : Password must be equal \nto or greater than 8 characters");
                            errorLBL.setStyle("-fx-border-color: red");
                        }
                    }
                    else
                    {
                        errorLBL.setTextFill(Color.RED);
                        errorLBL.setText("inValid Email");
                        errorLBL.setStyle("-fx-border-color: red");
                    }
                }
                else
                {
                    errorLBL.setTextFill(Color.RED);
                    errorLBL.setText("inValid Name");
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

    public boolean UserShouldBeHere()
    {
        for (Person person : Person.people)
        {
            if (person.getEmail().equals(emailFLD.getText()))
            {
                errorLBL.setTextFill(Color.RED);
                errorLBL.setText("registered before");
                errorLBL.setStyle("-fx-border-color: red");
                return false;
            }
        }
        return true ;
    }
}
