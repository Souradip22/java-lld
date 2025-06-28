package org.example.projects.movieticketbooking;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

enum BookingStatus{
    PENDING, CONFIRMED, CANCELED
}

public class Booking {
    private String id;
    private User user;
    private List<Seat> seats;
    private Show show;
    private double totalPrice;
    private BookingStatus status;

    public Booking(User user, List<Seat> seats, Show show, BookingStatus status) {
        this.id = generateBookingId();
        this.user = user;
        this.seats = seats;
        this.show = show;
        this.totalPrice = calculateTotalPrice();
        this.status = status;
    }

    public static void printBookingDetails(Booking booking) {
        if( booking == null){
            System.out.println("Booking not present");
            return;
        }
        System.out.println("===== Booking Details =====");
        System.out.println("Booking ID   : " + booking.getId());
        System.out.println("User         : " + booking.getUser().getName() + " (" + booking.getUser().getEmail() + ")");
        System.out.println("Status       : " + booking.getStatus());
        System.out.println("Movie        : " + booking.getShow().getMovie().getName());
        System.out.println("Theater      : " + booking.getShow().getTheater().getName() + " - " + booking.getShow().getTheater().getLocation());
        System.out.println("Show Time    : " + booking.getShow().getStartTime() + " to " + booking.getShow().getEndTime());
        System.out.println("Seats        : " + booking.getSeats().stream()
                .map(Seat::getId)
                .collect(Collectors.joining(", ")));
        System.out.println("Total Price  : ₹" + booking.getTotalPrice());
        System.out.println("===========================\n");
    }

    private double calculateTotalPrice() {
        return seats.stream().mapToDouble(Seat::getPrice).sum();
    }

    private String generateBookingId() {
        return "BOOKING-" + UUID.randomUUID().toString().substring(0, 4);
    }

    public String getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public Show getShow() {
        return show;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }
}
