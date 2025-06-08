package org.example.projects.parkinglotsystem;

import java.util.ArrayList;
import java.util.List;

public class ParkingFloor {
    private List<ParkingSpot> parkingSpots;
    private String floorNo;

    public ParkingFloor(String floorNo) {
        this.parkingSpots = new ArrayList<>();
        this.floorNo = floorNo;
    }

    public void addParkingSpot(ParkingSpot parkingSpot){
        this.parkingSpots.add(parkingSpot);
    }
    public void removeParkingSpot(ParkingSpot parkingSpot){
        this.parkingSpots.remove(parkingSpot);
    }

    public ParkingSpot getAvailableParkingSpotByType(VehicleType vehicleType){
        for (ParkingSpot spot: parkingSpots){
            if (spot.isAvailable() && spot.getSpotType().equals(vehicleType)){
                return spot;
            }
        }
        return null;
    }

    public List<ParkingSpot> getParkingSpots() {
        return parkingSpots;
    }

    public String getFloorNo() {
        return floorNo;
    }
}
