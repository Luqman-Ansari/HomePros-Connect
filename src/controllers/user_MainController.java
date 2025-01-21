package controllers;

import com.example.project.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class user_MainController implements Initializable {
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

    private Person user;

    private class ResultModel{
        public String name;
        public String companyName;
        public String rating;

        public ResultModel(String s1, String s2, String s3)
        {
            name = s1;
            companyName = s2;
            rating = s3;
        }
    }

    @FXML
    private TableView<ResultModel> resultTableView;
    @FXML
    private TableColumn<ResultModel, String> nameColumn;
    @FXML
    private TableColumn<ResultModel, String> companyColumn;
    @FXML
    private TableColumn<ResultModel, String> ratingColumn;
    @FXML
    private Button sendRequestButton;
    @FXML
    private ComboBox searchServiceProviderBox;



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

    @FXML void providerSearchButtonPressed() throws SQLException {
        Services selectedService = (Services) searchComboBox.getValue();

        DatabaseManager database = new DatabaseManager();

        if (selectedService != null) {
            try {
                ResultSet resultSet = database.searchForAvailableProviders(Integer.parseInt(selectedService.ServiceID()));

                boolean check = false;

                while (resultSet.next()) {
                    ResultModel data = new ResultModel(
                            resultSet.getString("name"),
                            resultSet.getString("companyName"),
                            resultSet.getString("rating")
                    );
                    check = true;
                    resultTableView.getItems().add(data);
                }

                if(!check)
                    showErrorAlert("Error", "No Service Provider Available");


                // Set cell value factories for columns
                nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().name));
                companyColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().companyName));
                ratingColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().rating));

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else {
            showErrorAlert("Service not selected", "Select a Service from the list");
            return;
        }


        // Fetch data from the database and populate the ComboBox
        ObservableList<Person> service = fetchDataFromDatabase2(Integer.parseInt(selectedService.ServiceID()));
        searchServiceProviderBox.setItems(service);

        // Customize the appearance of the ComboBox items
        searchServiceProviderBox.setCellFactory(new Callback<ListView<Person>, ListCell<Person>>() {
            @Override
            public ListCell<Person> call(ListView<Person> param) {
                return new ListCell<Person>() {
                    @Override
                    protected void updateItem(Person service, boolean empty) {
                        super.updateItem(service, empty);
                        if (service != null && !empty) {
                            setText(service.name());
                        } else {
                            setText(null);
                        }
                    }
                };
            }
        });


    }
    private void showErrorAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private ObservableList<Services> fetchDataFromDatabase() {

        DatabaseManager db = new DatabaseManager();
        return db.getServicesForComboBox();
    }

    private ObservableList<Person> fetchDataFromDatabase2(int id) throws SQLException {

        DatabaseManager db = new DatabaseManager();
        return db.getServiceProviderForComboBox(id);
    }

    @FXML void providerRequestButtonPressed() {
        User u = new User();
        u.setID(userID);
        Services selectedService = (Services) searchComboBox.getValue();
        Person p = new ServiceProvider();
        p = (Person) searchServiceProviderBox.getValue();

        if (p == null) {
            showErrorAlert("Service Provider not selected", "Select a Service Provider from the list");
            return;
        }

        u.makeBooking(selectedService.ServiceName(), p.getID());
        showInformationAlert("Success", "Service is Requested!");
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
        bookServiceButton.getStyleClass().add("button-selected");

        DatabaseManager db = new DatabaseManager();
        user = new User();
        user = db.loadPerson(userID);

        // Fetch data from the database and populate the ComboBox
        ObservableList<Services> service = fetchDataFromDatabase();
        searchComboBox.setItems(service);

        // Customize the appearance of the ComboBox items
        searchComboBox.setCellFactory(new Callback<ListView<Services>, ListCell<Services>>() {
            @Override
            public ListCell<Services> call(ListView<Services> param) {
                return new ListCell<Services>() {
                    @Override
                    protected void updateItem(Services service, boolean empty) {
                        super.updateItem(service, empty);
                        if (service != null && !empty) {
                            setText(service.ServiceName());
                        } else {
                            setText(null);
                        }
                    }
                };
            }
        });
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
