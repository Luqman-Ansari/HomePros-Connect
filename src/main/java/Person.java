package com.example.project;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class Person {
    private String username;
    private String password;
    private String name;
    private String email;
    private String phoneNum;
    private float balance;
    protected DatabaseManager database;

    public Person()
    {
        username = null;
        password = null;
        name = null;
        email = null;
        phoneNum = null;
        balance = 1000000.0F;
        database = new DatabaseManager();
    }

    public abstract ResultSet getDeatils();

    public abstract Person load(String id);


    public abstract void setID(String id);

    public Person login(String user, String pass) {
        Person p = null;

        p = database.authenticateUser(user, pass);

        return p;
    }

    @Override
    public String toString() {
        return name; // Or whatever property you want to display in the ComboBox
    }

    public String getID(){return null;}

    public int registerUser(String username, String password, String name, String email, String PhoneNum, String temp ){
        return -1;
    };
    public String name() {
        return name;
    }

    public DatabaseManager database() {
        return database;
    }
    public void setDatabase(DatabaseManager database) {
        this.database = database;
    }

    public float balance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public String password() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String username() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String email() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String phoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public void setCompanyName(String companyName) {
    }

    public void setRating(int rating) {
    }

    public ResultSet getOngoingServices() throws SQLException {
        return null;
    }

    public ResultSet getServicesCompleted() throws SQLException {
        return null;
    }

    public ResultSet getServiceRequests()
    {
        return null;
    }
}
