package org.example.projects.movieticketbooking;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MovieBookingSystem {
    private static MovieBookingSystem instance;
    private Map<String, Booking> bookings;
    private Map<String, Theater> theaters;
    private List<Movie> movies;
    private Map<String, Show> shows;

    public MovieBookingSystem() {
        this.bookings = new ConcurrentHashMap<>();
        this.theaters = new ConcurrentHashMap<>();
        this.shows = new ConcurrentHashMap<>();
    }

    public static MovieBookingSystem getInstance(){
        if (instance == null) {
            instance = new MovieBookingSystem();
        }
        return instance;
    }
    public void addMovie(Movie movie){
        this.movies.add(movie);
    }
    public void removeMovie(Movie movie){
        this.movies.remove(movie);
    }
    public void addTheater(Theater theater){
        this.theaters.put(theater.getId(), theater);
    }
    public void removeTheater(String id){
        if (theaters.containsKey(id)){
            this.theaters.remove(id);
        } else {
            System.out.println("Invalid theater id, cannot remove: " + id);
        }
    }
    public synchronized Booking makeBooking(User user,
                                             List<Seat> selectedSeats,
                                 Show show){
        if (areSeatsAvailable(show, selectedSeats)){
            changeSeatsStatus(show, selectedSeats, SeatStatus.BOOKED);
            Booking booking = new Booking(user, selectedSeats, show,
                    BookingStatus.PENDING);
            bookings.put(booking.getId(), booking);
            return booking;
        }
        System.out.println("Selected seats are not available for booking");
        return null;
    }
    public synchronized void confirmBooking(String bookingId){
        if (bookings.containsKey(bookingId)){
            if (bookings.get(bookingId).getStatus() != BookingStatus.CONFIRMED){
                if (processPayment(bookings.get(bookingId).getTotalPrice())){
                    System.out.println("Your booking is confirmed with " +
                            "booking id: " + bookingId);
                } else {
                    //retry payment
                    // else cancel the booking
                    cancelBooking(bookingId);
                }
            }
        } else {
            System.out.println("Booking is not present with booking id:" + bookingId);
        }
    }
    public synchronized  void cancelBooking(String bookingId){
        if (bookings.containsKey(bookingId)){
            Booking booking = bookings.get(bookingId);
            booking.setStatus(BookingStatus.CANCELED);
            bookings.remove(bookingId);
            changeSeatsStatus(booking.getShow(), booking.getSeats(), SeatStatus.AVAILABLE);
            System.out.println("Booking with id: " + bookingId + " cancelled.");
            //process refund
        } else {
            System.out.println("Cannot cancel the booking. Invalid booking " +
                    "id: " + bookingId);
        }
    }

    private boolean processPayment(double totalPrice) {
        System.out.println("PAYMENT is successful for amount: " + totalPrice);
        return true;
    }

    private void changeSeatsStatus(Show show, List<Seat> selectedSeats, SeatStatus seatStatus) {
         selectedSeats.stream()
                .map(seat -> show.getSeats().get(seat.getId()))
                .forEach(seat -> seat.setStatus(seatStatus));
    }

    private boolean areSeatsAvailable(Show show, List<Seat> selectedSeats) {
        return selectedSeats.stream()
                .map(seat -> show.getSeats().get(seat.getId()))
                .allMatch(seat -> seat != null && seat.getStatus() == SeatStatus.AVAILABLE);
    }

    public List<Seat> getAvailableSeats(Show show) {
        return show.getSeats().values().stream()
                .filter(seat -> seat.getStatus() == SeatStatus.AVAILABLE).toList();
    }
}
