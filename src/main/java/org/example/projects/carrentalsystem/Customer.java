package org.example.projects.carrentalsystem;

public class Customer {
    private String name;
    private String drivingLicenseNumber;
    private String contactNo;

    public Customer(String name, String drivingLicenseNumber, String contactNo) {
        this.name = name;
        this.drivingLicenseNumber = drivingLicenseNumber;
        this.contactNo = contactNo;
    }

    public String getName() {
        return name;
    }

    public String getDrivingLicenseNumber() {
        return drivingLicenseNumber;
    }

    public String getContactNo() {
        return contactNo;
    }
}
