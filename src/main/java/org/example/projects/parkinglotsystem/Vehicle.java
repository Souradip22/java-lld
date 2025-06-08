package org.example.projects.parkinglotsystem;

abstract class Vehicle {
    private String numberPlate;
    private VehicleType type;

    public Vehicle(String numberPlate, VehicleType type) {
        this.numberPlate = numberPlate;
        this.type = type;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public VehicleType getType() {
        return type;
    }
}

class Truck extends Vehicle{
    public Truck(String numberPlate) {
        super(numberPlate, VehicleType.TRUCK);
    }
}

class Car extends Vehicle{
    public Car(String numberPlate){
        super(numberPlate, VehicleType.CAR);
    }
}

class Bike extends Vehicle{
    public Bike(String numberPlate) {
        super(numberPlate, VehicleType.BIKE);
    }
}