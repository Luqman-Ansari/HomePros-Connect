package com.example.project;

public class Booking {
    private String bookingID;
    private String userID;
    private String providerID;
    private String serviceID;
    private Boolean status;
    private Boolean accepted;

    private DatabaseManager database;

    public Booking() {
        bookingID = null;
        userID = null;
        providerID = null;
        serviceID = null;
        status = false;
        accepted = false;
        database = new DatabaseManager();
    }

    public void createBooking(int serviceID, String serviceProviderID, String UserID)
    {
        database.addBooking(serviceID, serviceProviderID, UserID);
    }

    public void updateBookingStatus(int option, String bookingID)
    {
        database.updateBookingStatus(option, bookingID);
    }

    public void requestPayment(Payment p, String BookingID)
    {
        database.makePaymentRequest(p, BookingID);
    }
    public String bookingID() {
        return bookingID;
    }

    public Booking setBookingID(String bookingID) {
        this.bookingID = bookingID;
        return this;
    }

    public String userID() {
        return userID;
    }

    public Booking setUserID(String userID) {
        this.userID = userID;
        return this;
    }

    public String providerID() {
        return providerID;
    }

    public Booking setProviderID(String providerID) {
        this.providerID = providerID;
        return this;
    }

    public String serviceID() {
        return serviceID;
    }

    public Booking setServiceID(String serviceID) {
        this.serviceID = serviceID;
        return this;
    }

    public Boolean status() {
        return status;
    }

    public Booking setStatus(Boolean status) {
        this.status = status;
        return this;
    }

    public Boolean accepted() {
        return accepted;
    }

    public Booking setAccepted(Boolean accepted) {
        this.accepted = accepted;
        return this;
    }
}
