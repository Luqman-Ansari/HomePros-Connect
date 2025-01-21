package com.example.project;

public class Services {
    private String ServiceID;
    private String ServiceName;

    public String ServiceID() {
        return ServiceID;
    }

    public void setServiceID(String serviceID) {
        ServiceID = serviceID;
    }
    public String ServiceName() {
        return ServiceName;
    }

    public void setServiceName(String serviceName) {
        ServiceName = serviceName;
    }

    @Override
    public String toString() {
        return ServiceName;
    }
}
