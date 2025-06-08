package org.example.projects.parkinglotsystem;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ParkingLotSystem {
    private List<ParkingFloor> floors;
    private Map<String, Ticket> ticketMap;
    private PaymentProcessor paymentProcessor;
    private FeeStrategy feeStrategy;

    public ParkingLotSystem(PaymentProcessor paymentProcessor) {
        this.floors = new ArrayList<>();
        this.ticketMap = new ConcurrentHashMap<>();
        this.paymentProcessor = paymentProcessor;
    }

    public void addFloor(ParkingFloor parkingFloor){
        this.floors.add(parkingFloor);
    }
    public void removeFloor(ParkingFloor parkingFloor){
        this.floors.remove(parkingFloor);
    }

    public Ticket processEntry(Vehicle vehicle){
        for (ParkingFloor floor: floors){
            ParkingSpot availableSpot =
                    floor.getAvailableParkingSpotByType(vehicle.getType());
            if (availableSpot!= null && availableSpot.isAvailable()){
                Ticket newTicket = new Ticket(vehicle, floor, availableSpot);
                availableSpot.assignVehicle(vehicle);
                ticketMap.put(newTicket.getTicketNumber(), newTicket);
                newTicket.showEntryTicketDetails();
                return newTicket;
            }
        }
        System.out.println("⛔NO parking spot available for vehicle number: "
                + vehicle.getNumberPlate() + " type: "+ vehicle.getType());
        return null;
    }

    public void processExit(String ticketNumber){
        if (ticketMap.containsKey(ticketNumber)){
            Ticket ticket = ticketMap.get(ticketNumber);
            ticket.setExitTime(LocalDateTime.now().plusHours(2));
            double totalCost = feeStrategy.calculateTotalPrice(ticket);
            ticket.setTotalCost(totalCost);
            ticket.showExitTicketDetails();
            if (paymentProcessor.processPayment(totalCost)){
                ticketMap.remove(ticketNumber);
                ticket.getParkingSpot().removeVehicle();
                System.out.println("Payment of amount: " + totalCost + " is " +
                        "successful");
            } else {
                System.out.println("Payment is FAILED... Please " +
                        "try again");
            }

        } else {
            System.out.println("Ticket number is not valid: " + ticketNumber);
        }
    }

    public void setFeeStrategy(FeeStrategy feeStrategy) {
        this.feeStrategy = feeStrategy;
    }
}
