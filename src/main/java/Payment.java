package com.example.project;

public class Payment {
    private String ID;
    private float amount;
    private Boolean status;


    public String ID() {
        return ID;
    }

    public Payment setID(String ID) {
        this.ID = ID;
        return this;
    }

    public float amount() {
        return amount;
    }

    public Payment setAmount(float amount) {
        this.amount = amount;
        return this;
    }

    public Boolean status() {
        return status;
    }

    public Payment setStatus(Boolean status) {
        this.status = status;
        return this;
    }
}
