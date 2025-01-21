package controllers;

import com.example.project.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class serviceProviderMainController {

    @FXML
    private TableView<serviceProviderMainController.ResultModel> resultTableView;
    @FXML
    private TableColumn<serviceProviderMainController.ResultModel, String> serviceNameCol;
    @FXML
    private TableColumn<serviceProviderMainController.ResultModel, String> customerNameCol;
    @FXML
    private TableColumn<serviceProviderMainController.ResultModel, String> bookingCol;

    private class ResultModel {
        public String serviceName;
        public String customerName;
        public int bookingNum;


        public ResultModel(String s1, String s2, int num) {

            serviceName = s1;
            customerName = s2;
            bookingNum = num;
        }
    }

    @FXML
    private ComboBox<serviceProviderMainController.object> serviceReqBox;
    @FXML
    private Button acceptButton;
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

    private String userID;

    public void setNameLabel(String name) {
        nameTag.setText(name);
    }

    public void setUserID(String ID) {
        userID = ID;
    }

    @FXML
    public void acceptButtonPressed(ActionEvent e) {
        // Assuming serviceReqBox is a ComboBox<service2.object>
        serviceProviderMainController.object selectedService = serviceReqBox.getValue();

        if (selectedService != null) {
            // Assuming ServiceProvider has a method acceptService(service2.object service)
            ServiceProvider p = new ServiceProvider();
            p.setID(userID);
            p.acceptService(selectedService.bookingID);
            showInformationAlert("Successful", "Request Accepted");
            initialize();
        } else {
            showErrorAlert("Failed", "No Request Selected");
        }
    }

    private void showErrorAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
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

    public static class object{
        private String num;
        private String CustomerName;
        private String ServiceName;
        private String bookingID;



        public object(String n1, String s1, String s2, String id)
        {
            num = n1;
            CustomerName = s1;
            ServiceName = s2;
            bookingID = id;
        }

        @Override
        public String toString() {
            return num + ". " + ServiceName + " - " + CustomerName;
        }

    }

    private ObservableList<object> fetchDataFromDatabase(String id) {

        DatabaseManager db = new DatabaseManager();
        return db.getRequestsForComboBox(id);
    }

    @FXML
    public void initialize() {
        // Set default styles for all buttons
        setDefaultStyles();
        serviceRequestButton.getStyleClass().add("button-selected");

        if (userID != null) {
            Person provider = new ServiceProvider();
            provider = provider.load(userID);


            ObservableList<object> items = fetchDataFromDatabase(userID);
            serviceReqBox.setItems(items);

            // Customize the appearance of the ComboBox items
            serviceReqBox.setCellFactory(new Callback<ListView<object>, ListCell<object>>() {
                @Override
                public ListCell<object> call(ListView<object> param) {
                    return new ListCell<object>() {
                        @Override
                        protected void updateItem(object service, boolean empty) {
                            super.updateItem(service, empty);
                            if (service != null && !empty) {
                                setText(service.num + ". " +  service.ServiceName + " - " + service.CustomerName );
                            } else {
                                setText(null);
                            }
                        }
                    };
                }
            });

            try {
                ResultSet resultSet = provider.getServiceRequests();
                int count = 0;
                while (resultSet.next()) {
                    if (!resultSet.isClosed()) {
                        serviceProviderMainController.ResultModel data = new serviceProviderMainController.ResultModel(
                                resultSet.getString("ServiceName"),
                                resultSet.getString("CustomerName"),
                                resultSet.getInt("BookingNum")
                        );

                        resultTableView.getItems().add(data);
                        count++;
                    }
                }


                // Set cell value factories for columns

                serviceNameCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().serviceName));
                customerNameCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().customerName));
                bookingCol.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf((cellData.getValue().bookingNum))));

                if (count > 5)
                    count = 5;

                resultTableView.setMaxHeight(count * (80 + 0.5)); // Adjust rowHeight as needed


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
