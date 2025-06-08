package org.example.projects.parkinglotsystem;

public class ParkingSpot {

    private VehicleType spotType;
    private String spotNumber;
    private boolean isAvailable;
    private Vehicle parkedVehicle;

    public ParkingSpot(VehicleType spotType, String spotNumber) {
        this.spotType = spotType;
        this.spotNumber = spotNumber;
        this.isAvailable = true;
        this.parkedVehicle = null;
    }

    public VehicleType getSpotType() {
        return spotType;
    }

    public String getSpotNumber() {
        return spotNumber;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public Vehicle getParkedVehicle() {
        return parkedVehicle;
    }

    public String assignVehicle(Vehicle vehicle){
        this.parkedVehicle = vehicle;
        this.isAvailable = false;
        return this.spotNumber;
    }

    public void removeVehicle(){
        this.parkedVehicle = null;
        this.isAvailable = true;
    }
}
