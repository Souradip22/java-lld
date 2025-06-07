package org.example.projects.carrentalsystem;

import java.time.LocalDate;
import java.util.List;

import static org.example.projects.carrentalsystem.CarType.*;

public class CarRentalSystemDemo {
    public static void main(String[] args) {

        Car car1 = new Car("KA01-2335", "Honda", 1000, SEDAN);
        Car car2 = new Car("KA02-6992", "TATA", 1200, HATCHBACK);
        Car car3 = new Car("KA02-1902", "Mahindra", 1400, SUV);
        Car car4 = new Car("KA03-5643", "Mahindra", 1300, SUV);
        Car car5 = new Car("KA06-7808", "BMW", 1500, SUV);

        Customer customer1 = new Customer("Anil", "KA014322", "98763421");
        Customer customer2 = new Customer("Ravi", "KA09864", "743218953");
        Customer customer3 = new Customer("Prakash", "KA7342", "8987654312");

        PaymentProcessor creditCard = new CreditCardPaymentProcessor();

        CarRentalSystem rentalSystem = CarRentalSystem.getInstance(creditCard);

        rentalSystem.addCar(car1);
        rentalSystem.addCar(car2);
        rentalSystem.addCar(car3);
        rentalSystem.addCar(car4);
        rentalSystem.addCar(car5);

        // Make reservations
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(3);
        List<Car> availableCars = rentalSystem.searchCars(SUV, "Mahindra",
                startDate,
                endDate);
        System.out.println("Available cars with the criteria :" + availableCars.size());

        if (!availableCars.isEmpty()) {
            Car selectedCar = availableCars.getFirst(); // car3
            Reservation reservation =
                    rentalSystem.makeReservation(selectedCar, customer1
                    , startDate, endDate);
            if (reservation != null) {
                boolean paymentSuccess =
                        rentalSystem.processPayment(reservation.getTotalPrice());
                if (paymentSuccess) {
                    System.out.println("Reservation successful - [Reservation" +
                            " " +
                            "ID: " + reservation.getReservationId() + " " +
                            "Amount Paid: " + reservation.getTotalPrice() +
                            "]");
                } else {
                    System.out.println("Payment failed. Reservation canceled.");
                    rentalSystem.cancelReservation(reservation.getReservationId());
                }
            } else {
                System.out.println("Selected car is not available for the given dates.");
            }
        } else {
            System.out.println("No available cars found for the given criteria.");
        }
        // customer2 now tries to book the same car with in this period and
        // get error
        rentalSystem.makeReservation(car3,
                customer2, LocalDate.of(2025, 5,
                        27), LocalDate.of(2025, 6, 30));

    }
}
