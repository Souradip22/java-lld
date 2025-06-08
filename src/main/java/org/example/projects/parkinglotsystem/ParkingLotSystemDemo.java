package org.example.projects.parkinglotsystem;

public class ParkingLotSystemDemo {
    public static void main(String[] args) {

        Vehicle car1 = new Car("KA01-1234");
        Vehicle car2 = new Car("KA02-3259");
        Vehicle car3 = new Car("KA02-8888");
        Vehicle truck1 = new Truck("KA01-9898");
        Vehicle truck2 = new Truck("KA03-8976");
        Vehicle truck3 = new Truck("KA03-9999");
        Vehicle bike1 = new Bike("KA03-5679");
        Vehicle bike2 = new Bike("KA03-0978");
        Vehicle bike3 = new Bike("KA03-0011");


        ParkingSpot spot1 = new ParkingSpot(VehicleType.CAR, "101");
        ParkingSpot spot2 = new ParkingSpot(VehicleType.CAR, "102");
        ParkingSpot spot3 = new ParkingSpot(VehicleType.TRUCK, "103");
        ParkingSpot spot4 = new ParkingSpot(VehicleType.TRUCK, "104");
        ParkingSpot spot5 = new ParkingSpot(VehicleType.BIKE, "105");
        ParkingSpot spot6 = new ParkingSpot(VehicleType.BIKE, "106");

        ParkingFloor floor1 = new ParkingFloor("01");
        floor1.addParkingSpot(spot1);
        floor1.addParkingSpot(spot2);
        floor1.addParkingSpot(spot3);
        ParkingFloor floor2 = new ParkingFloor("02");
        floor2.addParkingSpot(spot4);
        floor2.addParkingSpot(spot5);
        floor2.addParkingSpot(spot6);


        PaymentProcessor creditCardProcessor = new CreditCardPayment();
        ParkingLotSystem parkingSystem =
                new ParkingLotSystem(creditCardProcessor);
        FeeStrategy fixedRateFeeStrategy = new FixedRateFeeStrategy();
        FeeStrategy variableRateFeeStrategy = new VariableRateFeeStrategy();
        parkingSystem.setFeeStrategy(variableRateFeeStrategy);
        parkingSystem.addFloor(floor1);
        parkingSystem.addFloor(floor2);


        Ticket ticket1 = parkingSystem.processEntry(car1);
        Ticket ticket2 = parkingSystem.processEntry(car2);
        Ticket ticket3 = parkingSystem.processEntry(car3);
        parkingSystem.processExit(ticket1.getTicketNumber());

        Ticket ticket4 = parkingSystem.processEntry(truck3);
        parkingSystem.processExit(ticket4.getTicketNumber());

        Ticket ticket5 = parkingSystem.processEntry(bike1);
        parkingSystem.processExit(ticket5.getTicketNumber());

    }
}
