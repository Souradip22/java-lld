package org.example.projects.parkinglotsystem;

import java.time.Duration;
import java.util.Map;

public interface FeeStrategy {
    double calculateTotalPrice(Ticket ticket);
}

class FixedRateFeeStrategy implements FeeStrategy{
    @Override
    public double calculateTotalPrice(Ticket ticket) {
        long totalMinutes =
                Duration.between(ticket.getEntryTime(), ticket.getExitTime()).toMinutes();
        long hours = (long) Math.ceil(totalMinutes / 60.0); // round up to next hour
        long ratePerHour = 30;
       return hours * ratePerHour;
    }
}

class VariableRateFeeStrategy implements FeeStrategy{
    private final Map<VehicleType, Double> HOURLY_RATE= Map.of(
            VehicleType.BIKE, 10.00,
            VehicleType.CAR, 20.00,
            VehicleType.TRUCK , 30.00
    );
    @Override
    public double calculateTotalPrice(Ticket ticket) {
        long totalMinutes =
                Duration.between(ticket.getEntryTime(), ticket.getExitTime()).toMinutes();
        long hours = (long) Math.ceil(totalMinutes / 60.0); // round up to next hour
        Double ratePerHour =  HOURLY_RATE.get(ticket.getVehicle().getType());
        return hours * ratePerHour;
    }
}
