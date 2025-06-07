package org.example.projects.carrentalsystem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class CarRentalSystem {
    private static CarRentalSystem instance;
    private Map<String, Car> cars;
    private Map<String, Reservation> reservations;
    private PaymentProcessor paymentProcessor;

    private CarRentalSystem(PaymentProcessor paymentProcessor) {
        this.cars = new ConcurrentHashMap<>();
        this.reservations = new ConcurrentHashMap<>();
        this.paymentProcessor = paymentProcessor;
    }
    public static synchronized CarRentalSystem getInstance(PaymentProcessor paymentProcessor) {
        if (instance == null) {
            instance = new CarRentalSystem(paymentProcessor);
        }
        return instance;
    }

    public void addCar(Car car){
        this.cars.put(car.getLicensePlateNumber(), car);
    }
    public void removeCar(Car car){
        this.cars.remove(car.getLicensePlateNumber());
    }

    // search available cars first with CarType and make
    // then based on the dates
    public List<Car> searchCars(CarType carType, String make,
                               LocalDate startDate, LocalDate endDate){
        List<Car> availableCars = new ArrayList<>();

        for (Car car: cars.values()){
            if (car.getMake().equalsIgnoreCase(make) && car.getCarType().equals(carType) && car.isAvailable()){
                if (isCarAvailable(car, startDate, endDate)){
                    availableCars.add(car);
                }
            }
        }
        return availableCars;
    }

    // loop over the reservation list and if the car is present
    // if present then get the reservation startDate and endDate and compare
    // with input dates
    private boolean isCarAvailable(Car car, LocalDate newStartDate,
                                   LocalDate newEndDate) {
        for (Reservation reservation: reservations.values()){
            if (reservation.getCar().getLicensePlateNumber().equalsIgnoreCase(car.getLicensePlateNumber())){
                LocalDate prevStartDate = reservation.getStartDate();
                LocalDate prevEndDate = reservation.getEndDate();
                // new booking ends before the previous one even starts.
                // new booking starts after the previous one has already ended.
                if (newEndDate.isBefore(prevStartDate) || newStartDate.isAfter(prevEndDate)){
                    return true;
                } else {
                    System.out.println("Car " + car.getLicensePlateNumber() +
                            " is not available in the given date range :( " + newStartDate + " : " + newEndDate + " )");
                    return false;
                }
            }
        }
        return true;
    }

    public synchronized Reservation makeReservation(Car car, Customer customer,
                                        LocalDate startDate,
                                LocalDate endDate){
        if (isCarAvailable(car, startDate, endDate)){
            String newReservationId = getReservationId();
            Reservation reservation = new Reservation(newReservationId,
                    car, customer, startDate, endDate);
            reservations.put(newReservationId, reservation);
            car.setAvailable(false);
            System.out.println(" -- Reservation IN_PROGRESS with " +
                    "following details --");
            reservation.showDetails();
            return reservation;
        } else {
            return null;
        }
    }

    private String getReservationId(){
        return "RES-" + UUID.randomUUID().toString().substring(0,6);
    }


    public boolean processPayment(double totalAmount) {
        return paymentProcessor.processPayment(totalAmount);
    }

    public void cancelReservation(String reservationId) {
        Reservation reservation = reservations.remove(reservationId);
        if (reservation != null){
            reservation.getCar().setAvailable(true);
        }
    }
}
