package controllers;

import com.example.project.DatabaseManager;
import com.example.project.Person;
import com.example.project.ServiceProvider;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class service4ViewController  {
    public Button addServiceButton;
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
    @FXML
    private TextField addServiceBox;
    @FXML
    private TableView<service4ViewController.ResultModel> resultTableView;
    @FXML
    private TableColumn<service4ViewController.ResultModel, String> ServiceNameCol;
    private class ResultModel{
        public String serviceName;
         public ResultModel(String s1)
        {
            serviceName = s1;
        }
    }


    private String userID;

    public void setNameLabel(String name) {
        nameTag.setText(name);
    }

    public void setUserID(String ID) {
        userID = ID;
    }

    @FXML
    private void addServiceButtonDBPressed()
    {
        String enteredText = addServiceBox.getText();

        // Convert the string to lowercase
        String serviceStr = enteredText.toLowerCase();

        ServiceProvider p = new ServiceProvider();
        p.setID(userID);
        p.addService(serviceStr);
        showInformationAlert("Successful", "Service added Successfully!");
        initialize();
    }

    private void showInformationAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
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
        addServicesButton.getStyleClass().add("button-selected");

        try {
            DatabaseManager db = new DatabaseManager();

            resultTableView.getItems().clear();

            ResultSet resultSet = db.getServicesinDatabase();

            int count = 0;
            while (resultSet.next()) {
                if (!resultSet.isClosed()) {
                    service4ViewController.ResultModel data = new service4ViewController.ResultModel(
                            resultSet.getString("name")
                    );

                    resultTableView.getItems().add(data);
                    count++;
                }
            }


            // Set cell value factories for columns

            ServiceNameCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().serviceName));


            if (count > 5)
                count = 5;

            resultTableView.setMaxHeight(count * (80 + 0.5)); // Adjust rowHeight as needed


        } catch (SQLException e) {
            e.printStackTrace();
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

