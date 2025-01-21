package controllers;

import com.example.project.Person;
import com.example.project.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class User4ViewController implements Initializable {
    @FXML
    private Button bookServiceButton;
    @FXML
    private Button onGoingServiceButton;
    @FXML
    private Button historyButton;
    @FXML
    private Button profileDetailsButton;
    @FXML
    private Button logoutButton;
    @FXML
    private Button feedbackButton;
    private String userID;
    @FXML
    private Label nameTag;
    @FXML
    private Label name;
    @FXML
    private Label username;
    @FXML
    private Label Password;
    @FXML
    private Label phone;
    @FXML
    private Label email;
    @FXML
    private Label bal;


    private Person user;

    public void setNameLabel(String name) {
        nameTag.setText(name);
    }

    public void setUserID(String ID) {
        userID = ID;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void initialize() {
        // Set default styles for all buttons
        setDefaultStyles();
        profileDetailsButton.getStyleClass().add("button-selected");

        if(userID != null) {
            user = new User();
            user = user.load(userID);

            ResultSet resultSet = user.getDeatils();

            try {
                if (resultSet.next()) {
                    // Assuming these columns are present in the ResultSet
                    String nameValue = resultSet.getString("name");
                    String usernameValue = resultSet.getString("username");
                    String passwordValue = resultSet.getString("password");
                    String phoneValue = resultSet.getString("phone");
                    String emailValue = resultSet.getString("email");
                    double balValue = resultSet.getDouble("bal");

                    // Assign values to labels
                    name.setText(nameValue);
                    username.setText(usernameValue);
                    Password.setText(passwordValue);
                    phone.setText(phoneValue);
                    email.setText(emailValue);
                    bal.setText(Double.toString(balValue)); // Convert double to String for Label
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    private void setDefaultStyles() {
        bookServiceButton.getStyleClass().clear();
        bookServiceButton.getStyleClass().add("button-default");

        onGoingServiceButton.getStyleClass().clear();
        onGoingServiceButton.getStyleClass().add("button-default");

        historyButton.getStyleClass().clear();
        historyButton.getStyleClass().add("button-default");

        profileDetailsButton.getStyleClass().clear();
        profileDetailsButton.getStyleClass().add("button-default");

        logoutButton.getStyleClass().clear();
        logoutButton.getStyleClass().add("button-default");

        feedbackButton.getStyleClass().clear();
        feedbackButton.getStyleClass().add("button-default");
    }

    @FXML
    public void bookServiceButtonPressed(ActionEvent e) throws IOException {
        // Set default styles for all buttons
        setDefaultStyles();

        // Set selected style for the clicked button
        bookServiceButton.getStyleClass().add("button-selected");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("user_Main-view.fxml"));
        Parent root = loader.load();

        // Access the controller of user_Main-view.fxml
        user_MainController userMainController = loader.getController();

        // Set the values in the controller
        userMainController.setNameLabel(nameTag.getText());
        userMainController.setUserID(userID);
        userMainController.initialize();

        // Switch to the new scene
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void onGoingServiceButtonPressed(ActionEvent e) throws IOException {
        // Set default styles for all buttons
        setDefaultStyles();

        // Set selected style for the clicked button
        onGoingServiceButton.getStyleClass().add("button-selected");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("user2-view.fxml"));
        Parent root = loader.load();

        // Access the controller of user_Main-view.fxml
        User2ViewController userMainController = loader.getController();

        // Set the values in the controller
        userMainController.setNameLabel(nameTag.getText());
        userMainController.setUserID(userID);
        userMainController.initialize();

        // Switch to the new scene
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void historyButtonPressed(ActionEvent e) throws IOException {
        // Set default styles for all buttons
        setDefaultStyles();

        // Set selected style for the clicked button
        historyButton.getStyleClass().add("button-selected");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("user3-view.fxml"));
        Parent root = loader.load();

        // Access the controller of user_Main-view.fxml
        User3ViewController userMainController = loader.getController();

        // Set the values in the controller
        userMainController.setNameLabel(nameTag.getText());
        userMainController.setUserID(userID);
        userMainController.initialize();

        // Switch to the new scene
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    public void profileDetailsButtonPressed(ActionEvent e) throws IOException {
        // Set default styles for all buttons
        setDefaultStyles();

        // Set selected style for the clicked button
        profileDetailsButton.getStyleClass().add("button-selected");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("user4-view.fxml"));
        Parent root = loader.load();

        // Access the controller of user_Main-view.fxml
        User4ViewController userMainController = loader.getController();

        // Set the values in the controller
        userMainController.setNameLabel(nameTag.getText());
        userMainController.setUserID(userID);
        userMainController.initialize();

        // Switch to the new scene
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void logoutButtonPressed(ActionEvent e) throws IOException {
        // Set default styles for all buttons
        setDefaultStyles();

        // Set selected style for the clicked button
        logoutButton.getStyleClass().add("button-selected");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("login-view.fxml"));
        Parent root = loader.load();

        // Access the controller of user_Main-view.fxml
        LoginController userMainController = loader.getController();

        userMainController.initialize();

        // Switch to the new scene
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    public void feedbackButtonPressed(ActionEvent e) throws IOException {
        // Set default styles for all buttons
        setDefaultStyles();

        // Set selected style for the clicked button
        feedbackButton.getStyleClass().add("button-selected");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("user5-view.fxml"));
        Parent root = loader.load();

        // Access the controller of user_Main-view.fxml
        User5ViewController userMainController = loader.getController();

        // Set the values in the controller
        userMainController.setNameLabel(nameTag.getText());
        userMainController.setUserID(userID);
        userMainController.initialize();

        // Switch to the new scene
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

}
