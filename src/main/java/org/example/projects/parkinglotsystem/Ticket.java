package org.example.projects.parkinglotsystem;

import java.time.LocalDateTime;
import java.util.UUID;

public class Ticket {
    private String ticketNumber;
    private Vehicle vehicle;
    private ParkingFloor parkingFloor;
    private ParkingSpot  parkingSpot;

    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private double totalCost;

    public Ticket(Vehicle vehicle, ParkingFloor parkingFloor, ParkingSpot parkingSpot) {
        this.ticketNumber = generateTicketNumber();
        this.vehicle = vehicle;
        this.parkingFloor = parkingFloor;
        this.parkingSpot = parkingSpot;
        this.entryTime = LocalDateTime.now();
    }

    private String generateTicketNumber(){
        return "TICKET-" + UUID.randomUUID().toString().substring(0,4);
    }
    public String getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public ParkingFloor getParkingFloor() {
        return parkingFloor;
    }

    public void setParkingFloor(ParkingFloor parkingFloor) {
        this.parkingFloor = parkingFloor;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public void setParkingSpot(ParkingSpot parkingSpot) {
        this.parkingSpot = parkingSpot;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(LocalDateTime entryTime) {
        this.entryTime = entryTime;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    public void setExitTime(LocalDateTime exitTime) {
        this.exitTime = exitTime;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public void showEntryTicketDetails() {
        System.out.println("✅ ENTRY {" +
                "ticketNumber='" + ticketNumber + '\'' +
                ", vehicle=" + vehicle.getNumberPlate()  +
                "(" + vehicle.getType() + ")"+
                ", parkingFloor=" + parkingFloor.getFloorNo() +
                ", parkingSpot=" + parkingSpot.getSpotNumber() +
                '}');
    }

    public void showExitTicketDetails(){

        System.out.println("❌ EXIT {" +
                "ticketNumber='" + ticketNumber + '\'' +
                ", vehicle=" + vehicle.getNumberPlate() +
                "(" + vehicle.getType() + ")"+
                ", exitTime=" + exitTime +
                ", totalFee=" + totalCost +
                '}');
    }

}
