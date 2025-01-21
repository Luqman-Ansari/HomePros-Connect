package controllers;

import com.example.project.Person;
import com.example.project.ServiceProvider;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class service5ViewController  {
    @FXML
    private Button serviceRequestButton;
    @FXML
    private Button ongoingServiceButton;
    @FXML
    private Button completedServicesButton;
    @FXML
    private Button addServicesButton;
    @FXML
    private Button profileDetailsButton;
    @FXML
    private Button logoutButton;
    @FXML
    private Label nameTag;

    private Person provider;
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
    @FXML
    private Label company;
    @FXML
    private Label rating;


    private String userID;

    public void setNameLabel(String name) {
        nameTag.setText(name);
    }

    public void setUserID(String ID) {
        userID = ID;
    }

    @FXML
    public void serviceRequestButtonPressed(ActionEvent e) throws IOException {

        // Set selected style for the clicked button
        serviceRequestButton.getStyleClass().add("button-selected");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("serviceProviderMain-view.fxml"));
        Parent root = loader.load();

        // Access the controller of user_Main-view.fxml
        serviceProviderMainController userMainController = loader.getController();

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
    public void ongoingServiceButtonPressed(ActionEvent e) throws IOException {

        // Set selected style for the clicked button
        ongoingServiceButton.getStyleClass().add("button-selected");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("service2-view.fxml"));
        Parent root = loader.load();

        // Access the controller of user_Main-view.fxml
        service2ViewController userMainController = loader.getController();

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
    public void completedServicesButtonPressed(ActionEvent e) throws IOException {

        // Set selected style for the clicked button
        completedServicesButton.getStyleClass().add("button-selected");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("service3-view.fxml"));
        Parent root = loader.load();

        // Access the controller of user_Main-view.fxml
        service3ViewController userMainController = loader.getController();

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
    public void addServicesButtonPressed(ActionEvent e) throws IOException {

        // Set selected style for the clicked button
        addServicesButton.getStyleClass().add("button-selected");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("service4-view.fxml"));
        Parent root = loader.load();

        // Access the controller of user_Main-view.fxml
        service4ViewController userMainController = loader.getController();

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

        // Set selected style for the clicked button
        profileDetailsButton.getStyleClass().add("button-selected");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("service5-view.fxml"));
        Parent root = loader.load();

        // Access the controller of user_Main-view.fxml
        service5ViewController userMainController = loader.getController();

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
    public void initialize() {
        // Set default styles for all buttons
        setDefaultStyles();
        profileDetailsButton.getStyleClass().add("button-selected");

        if(userID != null) {
            provider = new ServiceProvider();
            provider = provider.load(userID);


            ResultSet resultSet = provider.getDeatils();

            try {
                if (resultSet.next()) {
                    // Assuming these columns are present in the ResultSet
                    String nameValue = resultSet.getString("name");
                    String usernameValue = resultSet.getString("username");
                    String passwordValue = resultSet.getString("password");
                    String phoneValue = resultSet.getString("phone");
                    String emailValue = resultSet.getString("email");
                    double balValue = resultSet.getDouble("bal");
                    String companyN = resultSet.getString("companyName");
                    int rate = resultSet.getInt("rating");


                    // Assign values to labels
                    name.setText(nameValue);
                    username.setText(usernameValue);
                    Password.setText(passwordValue);
                    phone.setText(phoneValue);
                    email.setText(emailValue);
                    bal.setText(Double.toString(balValue)); // Convert double to String for Label
                    company.setText(companyN);
                    rating.setText(Integer.toString(rate));

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
    private void setDefaultStyles() {
        serviceRequestButton.getStyleClass().clear();
        serviceRequestButton.getStyleClass().add("button-default");

        ongoingServiceButton.getStyleClass().clear();
        ongoingServiceButton.getStyleClass().add("button-default");

        completedServicesButton.getStyleClass().clear();
        completedServicesButton.getStyleClass().add("button-default");

        profileDetailsButton.getStyleClass().clear();
        profileDetailsButton.getStyleClass().add("button-default");

        logoutButton.getStyleClass().clear();
        logoutButton.getStyleClass().add("button-default");

        addServicesButton.getStyleClass().clear();
        addServicesButton.getStyleClass().add("button-default");
    }

}

