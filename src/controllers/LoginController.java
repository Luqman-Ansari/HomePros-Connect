package controllers;

import com.example.project.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML
    private ImageView imageView;
    @FXML
    private ImageView home_logo;
    @FXML
    private ImageView img3;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    protected void onLoginButtonClick(ActionEvent event) {

        if (usernameField.getText().isEmpty() || passwordField.getText().isEmpty()) {
            showErrorAlert("Information Missing", "Enter Username and Password");
            return;
        }

        Person authenticatedPerson = authenticatedPerson();
        String path = "user_Main-view.fxml";

        if(authenticatedPerson instanceof User)
        {
            path = "user_Main-view.fxml";
        }
        else if(authenticatedPerson instanceof ServiceProvider)
        {
            path = "serviceProviderMain-view.fxml";
        }

        if (authenticatedPerson != null) {
            try {
                showInformationAlert("Login Successful", "Welcome, " + authenticatedPerson.getName());
                // Load the user_Main-view.fxml
                FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
                Parent root = loader.load();

                if(authenticatedPerson instanceof User) {
                    // Access the controller of user_Main-view.fxml
                    user_MainController userMainController = loader.getController();

                    // Set the values in the controller
                    userMainController.setNameLabel(authenticatedPerson.getName());
                    userMainController.setUserID(authenticatedPerson.getID());
                    userMainController.initialize();
                }
                else if(authenticatedPerson instanceof ServiceProvider)
                {
                    // Access the controller of user_Main-view.fxml
                    serviceProviderMainController userMainController = loader.getController();

                    // Set the values in the controller
                    userMainController.setNameLabel(authenticatedPerson.getName());
                    userMainController.setUserID(authenticatedPerson.getID());
                    userMainController.initialize();
                }
                // Switch to the new scene
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace(); // Handle the exception appropriately
            }
        } else {
            // Authentication failed, show an error pop-up
            showErrorAlert("Login Failed", "Invalid credentials.");
        }
    }

    @FXML
    protected void onRegisterButtonClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("register-view.fxml"));
        Parent registerView = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(registerView);
        stage.setScene(scene);

    }

    public void initialize() {
        try {
            // Set the image path for Image 1
            String imagePath1 = "D:/Semester 5/SDA/Project/Final Project/Project Code/Project/src/main/resources/Img/27230.jpg";
            Image image1 = new Image(imagePath1);
            imageView.setImage(image1);

            // Set the image path for Image 2
            String imagePath2 = getClass().getResource("/Img/HomePros_log.png").toExternalForm();
            Image image2 = new Image(imagePath2);
            home_logo.setImage(image2);

            // Set the image path for Image 3
            String imagePath3 = getClass().getResource("/Img/future_byte_log.jpeg").toExternalForm();
            Image image3 = new Image(imagePath3);
            img3.setImage(image3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    private Person authenticatedPerson() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        DatabaseManager db = new DatabaseManager();
        // Call the authentication method with the entered username and password

        Person authenticatedPerson = null;
        String type = db.getType(username, password);

        if(type != null) {
            if (type.equals("User")) {
                authenticatedPerson = new User();
            } else if (type.equals("ServiceProvider")) {
                authenticatedPerson = new ServiceProvider();
            }
        }
        authenticatedPerson = authenticatedPerson.login(username, password);
        return authenticatedPerson;
    }
    private void showInformationAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    // Helper method to show an error alert
    private void showErrorAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}