package Controller;

import Model.Client;
import Model.ReadAndWriteInFile;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class ClientAccountController implements Initializable {
    @FXML
    private TableView<?> codesTable;

    @FXML
    private Label lastNameLBL;

    @FXML
    private Label emailLBL;

    @FXML
    private Label nameLBL;

    @FXML
    private TableColumn<?, ?> codesCol;

    @FXML
    private Label phoneNumberLBL;

    @FXML
    private Label walletLBL;

    @FXML
    private Button exitBTN;

    @FXML
    private TabPane tabPane;

    @FXML
    private Label inviteErrorLBL;
    @FXML
    private Button inviteFriendBTN;

    @FXML
    private TextField inviteFriendFLD;

    private Client loggedClient ;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //--account tab
        nameLBL.setText("name : " +  loggedClient.getName());
        lastNameLBL.setText("lastname : " +  loggedClient.getLastName());
        emailLBL.setText("email : " +  loggedClient.getEmail());
        phoneNumberLBL.setText("phone number : " +  loggedClient.getPhoneNumber());
        walletLBL.setText("wallet : " +  loggedClient.getWallet());

        exitBTN.setOnAction( e -> {
            Stage stage ;
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Exit");
            alert.setHeaderText("You're about to exit!");
            if (alert.showAndWait().get() == ButtonType.OK)
            {
                ReadAndWriteInFile.WriteObjectToFile(); //save information
                stage = (Stage) tabPane.getScene().getWindow();
                stage.close();
            }
        });

        //--invite friend tab
        inviteFriendBTN.setOnAction( e -> {
            if(Pattern.matches("^[\\_ a-zA-z0-9 \\.]+[@][a-zA-Z0-9]+\\.[a-zA-Z]{3}$",inviteFriendFLD.getText()))
            {
                if (!invitedFriend())
                {
                    sendCodeToEmail(makeRandomCode());
                    loggedClient.getInvitedFriendEmails().add(inviteFriendFLD.getText());
                    inviteErrorLBL.setTextFill(Color.GREEN);
                    inviteErrorLBL.setText("done");

                }
            }
            else
            {
                inviteErrorLBL.setTextFill(Color.RED);
                inviteErrorLBL.setText("invalid email");
            }
        });

    }

    public void initLoggedUser(Client loggedClient)
    {
        this.loggedClient =  loggedClient;
    }

    public boolean invitedFriend()
    {
        for (String email : loggedClient.getInvitedFriendEmails())
        {
            if (email.equals(inviteFriendFLD.getText()))
            {
                inviteErrorLBL.setTextFill(Color.RED);
                inviteErrorLBL.setText("Already invited!");
                return true ;
            }
        }
        return false ;
    }

    public String makeRandomCode()
    {
        int max = 9999 ;
        int min = 1000 ;
        int randomNumber = (int) Math.floor( Math.random()*(max-min+1)+min );
        return String.valueOf(randomNumber) ;
    }

    public void sendCodeToEmail(String code )
    {
        // Recipient's email ID needs to be mentioned.
        String to = inviteFriendFLD.getText();

        // Sender's email ID needs to be mentioned
        String from = "sabihd0rd4b@gmail.com";

        // Assuming you are sending email from through gmails smtp
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("sabihd0rd4b@gmail.com", "ciaj icsf uaji vubi");

            }

        });

        // Used to debug SMTP issues
        session.setDebug(true);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("Messenger Code");

            // Now set the actual message
            message.setText("You have an invitation code from  " + loggedClient.getName() + " "+ loggedClient.getLastName() + " code : " +code);

            Transport.send(message);

        } catch (Exception ex) {

            System.out.println(ex.getMessage());
        }

    }

}
