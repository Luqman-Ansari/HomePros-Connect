package com.example.project;

import controllers.serviceProviderMainController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class DatabaseManager {
    /*CHANGE LATER*/
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/homepros";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "123456";
/* ABOVE CODE*/
    private Connection connection;

    public DatabaseManager() {
        try {
            this.connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    String getNextID() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
            // Assuming your login table has a column named 'id'
            String sql = "SELECT MAX(id) + 1 FROM login";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                if (resultSet.next()) {
                    int maxID = resultSet.getInt(1);

                    if(maxID < 111111)
                        maxID = 111111;

                    return String.valueOf(maxID);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "111111";
    }

    int checkAvailability(String username)
    {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
            // Assuming your login table has a column named 'id'
            String sql = "SELECT * FROM login where username = '" + username+"'";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                if (resultSet.next()) {
                    return -1;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 1;
    }

    public ResultSet getServicesCompleted(String id)
    {
        String sql = "select s2.name as ServiceName, u.name as CustomerName, " +
                " (select amount from payment where id = b.paymentid) as amount, " +
                " (select status from payment where id = b.paymentid) as PaidStatus, " +
                " (select comment from feedback where id = b.feedbackid) as feedback " +
                "from booking b " +
                "inner join services s2 on s2.id = b.serviceid " +
                "inner join user u on u.id = b.userID " +
                "where providerID = ? and status = true";
        try {
            Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, Integer.parseInt(id));

            // Execute the query and return the ResultSet
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception according to your application's needs
            return null; // or throw an exception based on your error handling strategy
        }
    }

    public ResultSet getServicesinDatabase()
    {
        {
            String sql = "select name from services";
            try {
                Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
                PreparedStatement preparedStatement = connection.prepareStatement(sql);

                // Execute the query and return the ResultSet
                return preparedStatement.executeQuery();
            } catch (SQLException e) {
                e.printStackTrace(); // Handle the exception according to your application's needs
                return null; // or throw an exception based on your error handling strategy
            }
        }
    }

    public ResultSet getOngoingServicesUsers(String userID) throws SQLException {
        String sql = "select s2.name as ServiceName, s1.companyName, s1.name as Providername, accepted from booking b inner join serviceProvider s1 on s1.id = b.providerid inner join services s2 on s2.id = b.serviceid where userID = ? and status = false";
        try {
            Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, Integer.parseInt(userID));

            // Execute the query and return the ResultSet
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception according to your application's needs
            return null; // or throw an exception based on your error handling strategy
        }
    }

    public ResultSet getOngoingServicesProviders(String userID) throws SQLException {
        String sql = "select s2.name as ServiceName, u.name as CustomerName " +
                "from booking b  " +
                "inner join services s2 on s2.id = b.serviceid  " +
                "inner join user u on u.id = b.userID " +
                "where providerID = ? and status = false and accepted = true";
        try {
            Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, Integer.parseInt(userID));

            // Execute the query and return the ResultSet
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception according to your application's needs
            return null; // or throw an exception based on your error handling strategy
        }
    }

    public ResultSet getHistory(String id)throws SQLException {
        String sql = "select s2.name as a, s1.companyName as b, s2.name as c, " +
                "p.amount as d, f.comment as e " +
                "from booking h " +
                "inner join serviceProvider s1 on s1.id = h.providerid  " +
                "inner join services s2 on s2.id = h.serviceid  " +
                "inner join feedback f on f.id = h.feedbackid " +
                "inner join payment p on p.id = h.paymentid " +
                "where userID = ? and h.status = true and h.feedbackID is not null and h.paymentID is not null " +
                "and p.status = true";
        try {
            Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, Integer.parseInt(id));

            // Execute the query and return the ResultSet
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception according to your application's needs
            return null; // or throw an exception based on your error handling strategy
        }
    }

    public ResultSet getUserDetails(String id)
    {
        String sql = "select* from user u inner join login l on l.id = u.id and l.id = ?";
        try {
            Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, Integer.parseInt(id));

            // Execute the query and return the ResultSet
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception according to your application's needs
            return null; // or throw an exception based on your error handling strategy
        }
    }

    public ResultSet getProviderDetails(String id)
    {
        String sql = "select* from serviceProvider u inner join login l on l.id = u.id and l.id = ?";
        try {
            Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, Integer.parseInt(id));

            // Execute the query and return the ResultSet
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception according to your application's needs
            return null; // or throw an exception based on your error handling strategy
        }
    }


    public ObservableList<Services> getServicesForComboBox()
    {
        String query = "SELECT * FROM services";

        ObservableList<Services> services = FXCollections.observableArrayList();

        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                // Assuming Service has a constructor that takes the necessary fields
                Services service = new Services();
                service.setServiceID(String.valueOf(resultSet.getInt("id")));
                service.setServiceName(resultSet.getString("name"));
                services.add(service);
            }

            //return services;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return services;
    }

    public ObservableList<  serviceProviderMainController.object> getRequestsForComboBox(String providerID) {
        String query = "SELECT " +
                " ROW_NUMBER() OVER (ORDER BY b.BookingID) AS BookingNum, bookingID as ID, " +
                " (SELECT name FROM services s WHERE s.id = b.serviceID) AS ServiceName, " +
                " (SELECT name FROM user u WHERE u.id = b.userid) AS CustomerName " +
                " FROM " +
                " booking b " +
                " WHERE " +
                " b.providerID = ? AND b.status = false AND b.accepted = false;";

        ObservableList<  serviceProviderMainController.object> list = FXCollections.observableArrayList();

        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Set the providerID parameter
            preparedStatement.setString(1, providerID);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    // Assuming 'object' has a constructor that takes the necessary fields
                      serviceProviderMainController.object item = new serviceProviderMainController.object(
                            String.valueOf(resultSet.getInt("BookingNum")),
                            resultSet.getString("ServiceName"),
                            resultSet.getString("CustomerName"),
                              resultSet.getString("ID")
                              );
                    list.add(item);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }


    public Person loadPerson(String id) {
        String userType = getUserType(id);

        if ("User".equals(userType)) {
            return loadUser(id);
        } else if ("ServiceProvider".equals(userType)) {
            return loadServiceProvider(id);
        }

        // Handle other cases or return null if needed
        return null;
    }

    private String getUserType(String id) {
        String query = "SELECT type FROM login WHERE ID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, Integer.parseInt(id));
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("type");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Person loadUser(String id) {
        String query = "SELECT * FROM User WHERE ID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, Integer.parseInt(id));
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // Create and return a User object
                Person p = new User();
                p.setID(String.valueOf(resultSet.getInt("ID")));
                p.setName(resultSet.getString("name"));
                p.setEmail(resultSet.getString("email"));
                p.setPhoneNum(resultSet.getString("phone"));
                p.setBalance(resultSet.getFloat("bal"));
                return p;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Person loadServiceProvider(String id) {
        String query = "SELECT * FROM ServiceProvider WHERE ID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, Integer.parseInt(id));
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // Create and return a ServiceProvider object
                Person p = new ServiceProvider();
                p.setID(String.valueOf(resultSet.getInt("ID")));
                p.setName(resultSet.getString("name"));
                p.setEmail(resultSet.getString("email"));
                p.setPhoneNum(resultSet.getString("phone"));
                p.setBalance(resultSet.getFloat("bal"));
                p.setCompanyName(resultSet.getString("companyName"));
                p.setRating(resultSet.getInt("rating"));
                return p;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ObservableList<Person> getServiceProviderForComboBox(int id) throws SQLException {
        String query = "SELECT s2.ID, s2.name " +
                "FROM ServicesProvided s " +
                "INNER JOIN services s1 ON s.serviceID = s1.ID " +
                "INNER JOIN ServiceProvider s2 ON s2.ID = s.ServiceProviderID " +
                "WHERE s.serviceID = ?";

        ObservableList<Person> services = FXCollections.observableArrayList();

        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    // Assuming Service has a constructor that takes the necessary fields
                    Person sp = new ServiceProvider();
                    sp.setID(String.valueOf(resultSet.getInt("id")));
                    sp.setName(resultSet.getString("name"));
                    services.add(sp);
                }

                //return services;

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return services;
        }
    }
    public ResultSet searchForAvailableProviders(int serviceID) throws SQLException {
        try {
            Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
            String sql = "SELECT s2.name, s2.companyName, s2.rating " +
                    "FROM ServicesProvided s " +
                    "INNER JOIN services s1 ON s.serviceID = s1.ID " +
                    "INNER JOIN ServiceProvider s2 ON s2.ID = s.ServiceProviderID " +
                    "WHERE s.serviceID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, serviceID);

            // Execute the query and return the ResultSet
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception according to your application's needs
            return null; // or throw an exception based on your error handling strategy
        }
    }

    void registerUser(String ID, Person p) {
        if (p instanceof User || p instanceof ServiceProvider) {
            try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
                String tableName = (p instanceof User) ? "User" : "ServiceProvider";

                String sql;
                if(tableName.equals("User")) {
                    sql = "INSERT INTO User (ID, name, email, phone, bal) VALUES (?, ?, ?, ?, ?)";
                }
                else
                {
                    sql = "INSERT INTO ServiceProvider (ID, name, email, phone, bal, companyName, rating) VALUES (?, ?, ?, ?, ?, ?, ?)";
                }

                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    // Set parameters based on the Person object
                    preparedStatement.setString(1, ID);
                    preparedStatement.setString(2, p.getName());
                    preparedStatement.setString(3, p.email());
                    preparedStatement.setString(4, p.phoneNum());
                    preparedStatement.setFloat(5, p.balance());

                    if(tableName.equals("ServiceProvider"))
                    {
                        preparedStatement.setString(6, ((ServiceProvider) p).CompanyName());
                        preparedStatement.setInt(7, ((ServiceProvider) p).rating());
                    }

                    // Execute the update
                    preparedStatement.executeUpdate();
                }

                String sql2 = "INSERT INTO login (ID, username, password, type) VALUES (?, ?, ?, ?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql2)) {
                    // Set parameters based on the Person object
                    preparedStatement.setString(1, ID);
                    preparedStatement.setString(2, p.username());
                    preparedStatement.setString(3, p.password());
                    preparedStatement.setString(4, tableName);

                    // Execute the update
                    preparedStatement.executeUpdate();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Person is not an instance of User or ServiceProvider. Cannot register.");
        }
    }

    public void addBooking(int serviceName, String serviceProviderID, String userID) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {

            String sql = "INSERT INTO Booking (serviceID, providerID, userID, status, accepted, paymentID, feedbackid) VALUES (?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, serviceName);
                preparedStatement.setInt(2, Integer.parseInt(serviceProviderID));
                preparedStatement.setInt(3, Integer.parseInt(userID));
                preparedStatement.setBoolean(4, false);
                preparedStatement.setBoolean(5, false);
                preparedStatement.setInt(6, -1);
                preparedStatement.setInt(7, -1);



                // Execute the update
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception according to your application's needs
        }
    }
    public void addService(ServiceProvider provider, Services service) {
        try {
            // Check if the service exists in the Services table
            int serviceID = getServiceID(service.ServiceName());

            // If the service doesn't exist, add it to the Services table
            if (serviceID == -1) {
                serviceID = addServiceToTable(service);
            }

            // Add the relationship to the ServicesProvided table
            addServiceProvided(provider.getID(), String.valueOf(serviceID));

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately based on your application's needs
        }
    }

    public int getServiceID(String serviceName) throws SQLException {
        // Check if the service exists in the Services table
        String query = "SELECT ID FROM Services WHERE name = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, serviceName);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("ID");
                } else {
                    return -1; // Service not found
                }
            }
        }
    }

    public ResultSet showBookings(ServiceProvider serviceProvider) {
        try {
            Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
            String sql = "SELECT * FROM Booking WHERE providerID = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, serviceProvider.getID());

            // Execute the query and return the ResultSet
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception according to your application's needs
            return null; // or throw an exception based on your error handling strategy
        }
    }

    public void makePaymentRequest(Payment p, String bookingID) {
        try {
            // Step 1: Insert payment object and get paymentID
            int paymentID = insertPaymentAndGetID(p);

            // Step 2: Update Booking with paymentID
            updateBookingPaymentID(bookingID, paymentID);

        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }

    private int insertPaymentAndGetID(Payment p) throws SQLException {
        String insertPaymentQuery = "INSERT INTO payment (amount, status) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertPaymentQuery,
                PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setFloat(1, p.amount());
            preparedStatement.setBoolean(2, p.status());

            preparedStatement.executeUpdate();

            // Retrieve the generated paymentID
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                throw new SQLException("Failed to retrieve generated paymentID");
            }
        }
    }

    private void updateBookingPaymentID(String bookingID, int paymentID) throws SQLException {
        String updateBookingQuery = "UPDATE Booking SET paymentID = ? WHERE bookingID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateBookingQuery)) {
            preparedStatement.setInt(1, paymentID);
            preparedStatement.setString(2, bookingID);

            preparedStatement.executeUpdate();
        }
    }


    public int makePayment(String bookingID) {
        try {
            // Step 1: Find paymentID for the given bookingID
            int paymentID = findPaymentIDByBookingID(bookingID);

            // Step 2: Check payment status
            boolean paymentStatus = checkPaymentStatus(paymentID);
            if (paymentStatus) {
                return 2; // Payment already made
            }

            // Step 3: Update payment status to true and get the amount
            float amount = updatePaymentStatusAndGetAmount(paymentID);

            // Step 4: Find userID for the given bookingID
            int userID = findUserIDByBookingID(bookingID);

            // Step 5: Subtract amount from user's balance
            boolean paymentSuccessful = subtractAmountFromBalance(userID, amount);
            if (!paymentSuccessful) {
                return 3; // Insufficient balance
            }

            return 1; // Payment successful
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
            return 0; // Payment failed due to an error
        }
    }
    private int findPaymentIDByBookingID(String bookingID) throws SQLException {
        String findPaymentIDQuery = "SELECT paymentID FROM Booking WHERE bookingID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(findPaymentIDQuery)) {
            preparedStatement.setInt(1, Integer.parseInt(bookingID));

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("paymentID");
                } else {
                    throw new SQLException("Booking record not found for bookingID: " + bookingID);
                }
            }
        }
    }

    private boolean checkPaymentStatus(int paymentID) throws SQLException {
        String checkPaymentStatusQuery = "SELECT status FROM payment WHERE ID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(checkPaymentStatusQuery)) {
            preparedStatement.setInt(1, paymentID);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getBoolean("status");
                } else {
                    throw new SQLException("Payment record not found for paymentID: " + paymentID);
                }
            }
        }
    }

    private float updatePaymentStatusAndGetAmount(int paymentID) throws SQLException {
        String updatePaymentStatusQuery = "UPDATE payment SET status = true WHERE ID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(updatePaymentStatusQuery)) {
            preparedStatement.setInt(1, paymentID);
            preparedStatement.executeUpdate();
        }

        String getAmountQuery = "SELECT amount FROM payment WHERE ID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(getAmountQuery)) {
            preparedStatement.setInt(1, paymentID);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getFloat("amount");
                } else {
                    throw new SQLException("Payment record not found for paymentID: " + paymentID);
                }
            }
        }
    }

    private int findUserIDByBookingID(String bookingID) throws SQLException {
        String findUserIDQuery = "SELECT userID FROM Booking WHERE bookingID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(findUserIDQuery)) {
            preparedStatement.setInt(1, Integer.parseInt(bookingID));

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("userID");
                } else {
                    throw new SQLException("Booking record not found for bookingID: " + bookingID);
                }
            }
        }
    }

    private boolean subtractAmountFromBalance(int userID, float amount) throws SQLException {

        String AmountQuery = "select bal from user WHERE ID = ?";
        float balance =0;
        try (PreparedStatement preparedStatement = connection.prepareStatement(AmountQuery)) {
            preparedStatement.setInt(1, userID);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                     balance = resultSet.getFloat("bal");
                     if(balance < amount)
                         return false;
                }
            }
        }

        String subtractAmountQuery = "UPDATE User SET bal = bal - ? WHERE ID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(subtractAmountQuery)) {
            preparedStatement.setFloat(1, amount);
            preparedStatement.setInt(2, userID);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        }
    }
    public int updateBookingStatus(int option, String bookingID) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {

            String sql = null;

            if (option == 1)
                sql = "UPDATE Booking SET accepted = ? WHERE bookingID = ?";
            else if (option == 2)
                sql = "UPDATE Booking SET status = ? WHERE bookingID = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setBoolean(1, true);
                    preparedStatement.setString(2, bookingID);

                    // Execute the update
                    int rowsAffected = preparedStatement.executeUpdate();

                    if (rowsAffected > 0) {
                        return 1;
                    } else {
                        return 0;
                    }
                }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception according to your application's needs
        }
        return -1;
    }

    private int addServiceToTable(Services service) throws SQLException {
        // Add the service to the Services table
        String insertQuery = "INSERT INTO Services (name) VALUES (?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, service.ServiceName());
            preparedStatement.executeUpdate();
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Adding service failed, no ID obtained.");
                }
            }
        }
    }

    private void addServiceProvided(String serviceProviderID, String serviceID) throws SQLException {
        // Add the relationship to the ServicesProvided table
        String insertQuery = "INSERT INTO ServicesProvided (serviceID, ServiceProviderID) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setInt(1, Integer.parseInt(serviceID));
            preparedStatement.setInt(2, Integer.parseInt(serviceProviderID));
            preparedStatement.executeUpdate();
        }
    }

    public ResultSet getServiceRequests(String id)
    {
        String sql = "SELECT " +
                " ROW_NUMBER() OVER (ORDER BY b.BookingID) AS BookingNum, bookingid as ID, " +
                " (SELECT name FROM services s WHERE s.id = b.serviceID) AS ServiceName, " +
                " (SELECT name FROM user u WHERE u.id = b.userid) AS CustomerName " +
                " FROM " +
                " booking b " +
                " WHERE " +
                " b.providerID = ? AND b.status = false AND b.accepted = false;";
        try {
            Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, Integer.parseInt(id));

            // Execute the query and return the ResultSet
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception according to your application's needs
            return null; // or throw an exception based on your error handling strategy
        }
    }


    public String getType(String username, String password)
    {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {

            String sql = "SELECT * FROM login WHERE username = ? AND password = ?";
            String ID = null;
            String type = null;
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        type = resultSet.getString("type");
                        return type;
                    }
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public Person authenticateUser(String username, String password) {
        Person p = null;
        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {

            String sql = "SELECT * FROM login WHERE username = ? AND password = ?";
            String ID = null;
            String type = null;
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {

                        ID = resultSet.getString("ID");
                        type = resultSet.getString("type");
                    }
                }
            }

            String sql2 = "SELECT * FROM " + type + " WHERE ID = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql2)) {
                preparedStatement.setString(1, ID);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {

                        String name = resultSet.getString("name");
                        String email = resultSet.getString("email");
                        String phoneNum = resultSet.getString("phone");
                        float balance = resultSet.getFloat("bal");

                        if (type.equals("User")) {
                            p = new User();
                            p.setName(name);
                            p.setEmail(email);
                            p.setPhoneNum(phoneNum);
                            p.setBalance(balance);
                            p.setID(ID);
                        }
                        else
                        { // Set later
                            p = new ServiceProvider();
                            p.setName(name);
                            p.setEmail(email);
                            p.setPhoneNum(phoneNum);
                            p.setBalance(balance);
                            p.setCompanyName(resultSet.getString("companyName"));
                            p.setRating(resultSet.getInt("rating"));
                            p.setID(ID);
                        }

                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();


        }
        return p;
    }


//    public boolean executeUpdateQuery(String sql, Object... parameters) {
//        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//            setParameters(preparedStatement, parameters);
//            int affectedRows = preparedStatement.executeUpdate();
//            return affectedRows > 0;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            // Handle exception
//            return false;
//        }
//    }

    // Example method for executing queries that return results (e.g., SELECT)
    /*public ResultSet executeQueryWithResults(String sql, Object... parameters) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            setParameters(preparedStatement, parameters);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception
            return null;
        }
    }*/

    /*private void setParameters(PreparedStatement preparedStatement, Object... parameters) throws SQLException {
        for (int i = 0; i < parameters.length; i++) {
            preparedStatement.setObject(i + 1, parameters[i]);
        }
    }*/

      public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception
        }
    }
}
