package com.example.project;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User extends Person {
    private String UserID;
    //private List<Booking> bookings;

    public User() {
        super();
        UserID = null;
    }

    @Override
    public ResultSet getDeatils() {
        return database.getUserDetails(getID());
    }

    @Override
    public Person load(String id) {
        return database.loadPerson(id);
    }

    public ResultSet getOngoingServices() throws SQLException {
        return database.getOngoingServicesUsers(getID());
    }

    public ResultSet getServicesCompleted() throws SQLException {
        return database.getHistory(getID());
    }

    public int registerUser(String username, String password, String name, String email, String PhoneNum, String temp )
    {
        UserID = database.getNextID();

        this.setUsername(username);
        this.setPassword(password);
        this.setName(name);
        this.setEmail(email);
        this.setPhoneNum(PhoneNum);

        if(database.checkAvailability(username) == -1)
            return -1;


        database.registerUser(UserID, this);

        return 1;
    }

    //1. booking Successful
    //2. Service Doesn't Exist
    //-1. Error Occured
    public int makeBooking(String serviceName, String serviceProviderID)
    {
        try {

            if(database.getServiceID(serviceName) == -1 )
                return 2;

            Booking book = new Booking();
            book.createBooking(database.getServiceID(serviceName), serviceProviderID, this.getID());

            return 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // 1. Payment Completed
    // 2. Payment already made
    public int makePayment(String BookingID)
    {
       return database.makePayment(BookingID);
    }
    public String getID() {
        return UserID;
    }

    public void setID(String userID) {
        UserID = userID;
    }

}
