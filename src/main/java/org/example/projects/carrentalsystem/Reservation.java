package org.example.projects.carrentalsystem;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Reservation {
    private String reservationId;
    private Car car;
    private Customer customer;
    private LocalDate startDate;
    private LocalDate endDate;
    private double totalPrice;

    public Reservation(String reservationId, Car car, Customer customer, LocalDate startDate, LocalDate endDate) {
        this.reservationId = reservationId;
        this.car = car;
        this.customer = customer;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalPrice = calTotalPrice();
    }

    private double calTotalPrice() {
        long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);
        System.out.println("Calculating total price for " + daysBetween + " " +
                "days");
        return car.getRentalPricePerDay() * daysBetween;
    }

    public String getReservationId() {
        return reservationId;
    }

    public Car getCar() {
        return car;
    }

    public Customer getCustomer() {
        return customer;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void showDetails(){
        System.out.println(
         "{" +
                "reservationId='" + reservationId + '\'' +
                ", car=" + car.getLicensePlateNumber() +
                ", customer=" + customer.getName() +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", totalPrice=" + totalPrice +
                '}');
    }
}
