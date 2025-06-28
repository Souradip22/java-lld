package org.example.projects.movieticketbooking;

import java.time.LocalDateTime;
import java.util.*;

public class MovieBookingSystemDemo {
    public static void main(String[] args) {
        // add movies
        Movie movie1 = new Movie("Inception", "A mind-bending thriller about dreams within dreams.", "English", 2.5);
        Movie movie2 = new Movie("3 Idiots", "Three engineering students learn the importance of following their passion.", "Hindi", 2.8);
        Movie movie3 = new Movie("Spirited Away", "A girl enters a magical world ruled by spirits and gods.", "Japanese", 2.0);
        Movie movie4 = new Movie("Parasite", "A poor family infiltrates a wealthy household.", "Korean", 2.1);
        Movie movie5 = new Movie("The Dark Knight", "Batman faces off against the Joker in Gotham City.", "English", 2.3);

        // add theaters
        Theater theater1 = new Theater("PNR Cinemas", "HSR", new ArrayList<>());
        Theater theater2 = new Theater("INOX", "Varthur", new ArrayList<>());
        Theater theater3 = new Theater("Cinepolis", "Haralur",
                new ArrayList<>());

        // add shows
        Map<String, Seat> seats1 = generateSeats(3, 3);
        Map<String, Seat> seats2 = generateSeats(4, 4);
        Map<String, Seat> seats3 = generateSeats(5, 5);

        Show show1 = new Show(movie1, theater1,
                LocalDateTime.of(2025, 7, 1, 18, 0),
                LocalDateTime.of(2025, 7, 1, 20, 30),
                seats1);


        Show show2 = new Show(movie2, theater2,
                LocalDateTime.of(2025, 7, 1, 21, 0),
                LocalDateTime.of(2025, 7, 1, 23, 45),
                seats2);

        Show show3 = new Show(movie3, theater3,
                LocalDateTime.of(2025, 7, 2, 16, 0),
                LocalDateTime.of(2025, 7, 2, 18, 0),
                seats3);

        User user1 = new User("Amit", "amit@example.com");
        User user2 = new User("Sourav", "sourav@example.com");

        // Booking
        MovieBookingSystem bookingSystem = MovieBookingSystem.getInstance();
        List<Seat> availableSeats = bookingSystem.getAvailableSeats(show1);
        System.out.println("Available Seats:");
        printSeats(availableSeats);
        List<Seat> selectedSeats = Arrays.asList(show1.getSeats().get("1-3"),
                show1.getSeats().get("1-2"));
        System.out.println("Selected Seats:");
        printSeats(selectedSeats);
        Booking booking1 = bookingSystem.makeBooking(user1, selectedSeats,
                show1);
        Booking.printBookingDetails(booking1);
        bookingSystem.confirmBooking(booking1.getId());

        Booking booking2 = bookingSystem.makeBooking(user2, selectedSeats,
                show1); // Selected seats are not available for booking

        Booking.printBookingDetails(booking2);

        Booking booking3 = bookingSystem.makeBooking(user2,
                List.of(show2.getSeats().get("2-1"), show2.getSeats().get("2" +
                        "-2"), show2.getSeats().get("2-3")),
                show2); // Selected seats are not available for booking

        Booking.printBookingDetails(booking3);
        bookingSystem.cancelBooking(booking1.getId());
        System.out.println("Available seats for show2: ");
        printSeats(bookingSystem.getAvailableSeats(show1));
        System.out.println("Available seats for show3: ");
        printSeats(bookingSystem.getAvailableSeats(show2));



    }

    private static void printSeats(List<Seat> seats) {
        if (seats == null || seats.isEmpty()) {
            System.out.println("No available seats.");
            return;
        }

        System.out.println("----------------");
        seats.forEach(seat ->
                System.out.printf("Seat ID: %-5s | Type: %-8s | Status: %s%n",
                        seat.getId(),
                        seat.getType(),
                        seat.getStatus())
        );
        System.out.println();
    }

    private static Map<String, Seat> generateSeats(int row, int col) {
        Map<String, Seat> seats = new LinkedHashMap<>();
        for (int i = 1; i <= row; i++){
            for (int j = 1; j <= col; j++){
                SeatType type = i % 2 == 0? SeatType.PREMIUM: SeatType.REGULAR;
                double price = type == SeatType.PREMIUM? 150: 100;
                Seat seat = new Seat(i, j, type, price);
                seats.put(seat.getId(), seat);
            }
        }
        return seats;
    }

}
