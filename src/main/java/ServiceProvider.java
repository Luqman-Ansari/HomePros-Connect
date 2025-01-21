package com.example.project;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiceProvider extends Person{
    private String providerID;
    private String CompanyName;
    private int rating;

    public ServiceProvider() {
        super();
        providerID = null;
        CompanyName = null;
        rating = 0;
    }

    @Override
    public ResultSet getDeatils() {
        return database.getProviderDetails(getID());
    }

    public ResultSet getServicesCompleted()
    {
        return database.getServicesCompleted(getID());
    }

    public ResultSet getOngoingServices() throws SQLException {
        return database.getOngoingServicesProviders(getID());
    }

    @Override
    public Person load(String id) {
        return database.loadPerson(id);
    }

    public void addService(String name)
    {
        Services s = new Services();
        s.setServiceName(name);
        database.addService(this, s);
    }

    public ResultSet getServiceRequests()
    {
        return database.getServiceRequests(getID());
    }


    public ResultSet showBookingRequests()
    {
        return database.showBookings(this);
    }

    public void acceptService(String BookingID)
    {
        Booking book = new Booking();
        book.updateBookingStatus(1, BookingID);
    }

    public void completeService(String BookingID, float amount)
    {
        Booking book = new Booking();
        book.updateBookingStatus(2, BookingID);

        Payment pay = new Payment();
        pay.setAmount(amount);
        pay.setStatus(false);

        book.requestPayment(pay, BookingID);
    }

    @Override
    public void setID(String id) {
        providerID = id;
    }

    public String getID() {
        return providerID;
    }

    public void setProviderID(String providerID) {
        this.providerID = providerID;
    }

    public String CompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public int rating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public int registerUser(String username, String password, String name, String email, String PhoneNum, String companyName) {
        providerID = database.getNextID();
        this.setName(name);
        this.setUsername(username);
        this.setPassword(password);
        this.setEmail(email);
        this.setPhoneNum(PhoneNum);
        this.setCompanyName(companyName);

        if(database.checkAvailability(username) == -1)
            return -1;

        database.registerUser(providerID, this);
        return 1;
    }



}
