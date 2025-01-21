package controllers;

import com.example.project.Person;
import com.example.project.ServiceProvider;
import com.example.project.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.EventObject;

public class RegisterController {

    public Button LoginButton;
    public ImageView imageView;
    public ImageView home_logo;
    public ImageView img3;
    @FXML
    private TextField PhoneBox;
    @FXML
    private TextField usernameBox;
    @FXML
    private TextField passwordBox;
    @FXML
    private TextField nameBox;
    @FXML
    private TextField emailBox;
    @FXML
    private javafx.scene.control.MenuButton MenuButton;
    @FXML
    private TextField companyNameField;
    @FXML
    private Label companyNameLabel;
    @FXML
    private MenuButton button;

    @FXML
    protected void onLoginButtonClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login-view.fxml"));
        Parent registerView = loader.load();

        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
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

    @FXML
    protected void onRegisterButtonClick()
    {
        // Get values from the TextFields
        String phone = PhoneBox.getText();
        String username = usernameBox.getText();
        String password = passwordBox.getText();
        String name = nameBox.getText();
        String email = emailBox.getText();
        String companyName = companyNameField.getText();

        String type = MenuButton.getText();
        Person p;

        if(phone.isEmpty() || username.isEmpty() || password.isEmpty()|| name.isEmpty() || email.isEmpty() )
            showErrorAlert("Information Missing", "Fill in the Empty Details");

        if(type.equals("User"))
        {
            p = new User();
            if(p.registerUser(username,password, name, email, phone, "") == -1)
                showErrorAlert("Username not available", "Please select a different username");
            else
                showInformationAlert("Successful", "Registration Completed");

        }
        else
        {
            p = new ServiceProvider();
            if(p.registerUser(username,password, name, email, phone, companyName) == -1)
                showErrorAlert("Username not available", "Please select a different username");
            else
                showInformationAlert("Successful", "Registration Completed");

        }

    }

    @FXML
    protected void handleUserSelection(ActionEvent event) {
        MenuButton.setText("User");
        companyNameLabel.setDisable(true);
        companyNameField.setDisable(true);
    }

    @FXML
    protected void handleServiceProviderSelection(ActionEvent event){
        MenuButton.setText("Service Provider");
        companyNameLabel.setDisable(false);
        companyNameField.setDisable(false);
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
