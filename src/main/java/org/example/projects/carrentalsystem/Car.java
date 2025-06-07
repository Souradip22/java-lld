package org.example.projects.carrentalsystem;

enum CarType{
    SUV, SEDAN, HATCHBACK
}
public class Car {
    private String licensePlateNumber;
    private String make;
    private double rentalPricePerDay;
    private CarType carType;
    private boolean isAvailable;

    public Car(String licensePlateNumber, String make, double rentalPricePerDay, CarType carType) {
        this.licensePlateNumber = licensePlateNumber;
        this.make = make;
        this.rentalPricePerDay = rentalPricePerDay;
        this.carType = carType;
        this.isAvailable = true;
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public String getMake() {
        return make;
    }

    public double getRentalPricePerDay() {
        return rentalPricePerDay;
    }

    public CarType getCarType() {
        return carType;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
