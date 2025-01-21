package controllers;

import com.example.project.Person;
import com.example.project.User;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class User2ViewController implements Initializable {
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
    private ComboBox searchComboBox;
    @FXML
    private Button providerSearchButton;


    @FXML
    private TableView<ResultModel> resultTableView;
    @FXML
    private TableColumn<ResultModel, String> nameColumn;
    @FXML
    private TableColumn<ResultModel, String> companyColumn;
    @FXML
    private TableColumn<ResultModel, String> col3;
    @FXML
    private TableColumn<ResultModel, String> col4;


    private class ResultModel{
        public String ServiceProviderName;
        public String ServiceName;
        public boolean accepted;
        public String companyName;


        public ResultModel(String s1, String s2, boolean s3, String s4)
        {
            ServiceProviderName = s1;
            ServiceName = s2;
            accepted = s3;
            companyName = s4;
        }
    }
    private Person user;

    public void setNameLabel(String name) {
        nameTag.setText(name);
    }

    public void setUserID(String ID) {
        userID = ID;
    }



    private void showInformationAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void initialize() {
        // Set default styles for all buttons
        setDefaultStyles();
        onGoingServiceButton.getStyleClass().add("button-selected");

        user = new User();
        user = user.load(userID);


        try {
            ResultSet resultSet = user.getOngoingServices(); //db.getOngoingServices(userID);
            int count = 0;
            while (resultSet.next()) {
                if (!resultSet.isClosed()) {
                    ResultModel data = new ResultModel(
                            resultSet.getString("Providername"),
                            resultSet.getString("ServiceName"),
                            resultSet.getBoolean("accepted"),
                            resultSet.getString("companyName")
                            );

                    resultTableView.getItems().add(data);
                    count ++;
                }
            }

            // Set cell value factories for columns
            nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().ServiceName));
            companyColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().ServiceProviderName));
            col4.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().companyName));

            // Use SimpleBooleanProperty directly for boolean columns
            col3.setCellValueFactory(cellData -> {
                boolean accepted = cellData.getValue().accepted;

                return new SimpleStringProperty(accepted ? "Yes" : "No");
            });

            if(count > 5)
                count = 5;

            resultTableView.setMaxHeight(count * (80 + 0.5)); // Adjust rowHeight as needed


        } catch (SQLException e) {
            e.printStackTrace();
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

}
