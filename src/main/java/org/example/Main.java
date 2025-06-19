package org.example;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        //
        List<Integer> numbers = List.of(12, 9, 13, 4, 6, 2, 4, 12, 15);

        Predicate<Integer> integerPredicate = num -> num % 2 == 0;
        Predicate<Integer> integerPredicate2 = new Predicate<Integer>() {
            @Override
            public boolean test(Integer number) {
                return number % 2 == 0;
            }
        };
        Function<Integer, Integer> integerFunction = num -> num + 10;
        Consumer<Integer> integerConsumer = num -> System.out.println(num);

        numbers.stream()
                .filter(integerPredicate2)
                .map(integerFunction)
                .forEach(integerConsumer);
    }
}