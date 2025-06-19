package org.example.streamapi;

import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Practice01 {
    public static void main(String[] args) {

        List<Course> courses = List.of(
                new Course("Spring", "Framework", 98, 20000),
                new Course("Spring Boot", "Framework", 95, 18000),
                new Course("API", "Microservices", 97, 22000),
                new Course("Microservices", "Microservices", 96, 25000),
                new Course("FullStack", "FullStack", 91, 14000),
                new Course("AWS", "Cloud", 92, 21000),
                new Course("Azure", "Cloud", 99, 21000),
                new Course("Docker", "Cloud", 92, 20000),
                new Course("Kubernetes", "Cloud", 91, 20000)
        );

        // 🔹 Filtering and Matching

        // 1. Filter courses with review score greater than 95 and print their details
        System.out.println("Courses with review score > 95:");
        courses.stream()
                .filter(course -> course.getReviewScore() > 95)
                .forEach(System.out::println);
        System.out.println();

        // 2. Check if any course has a review score less than 90
        boolean hasLowScore = courses.stream().anyMatch(course -> course.getReviewScore() < 90);
        System.out.println("Is there any course with review score less than 90? : " + hasLowScore);
        System.out.println();

        // 3. Find the first course with review score greater than 95
        Optional<Course> first = courses.stream()
                .filter(course -> course.getReviewScore() > 95)
                .findFirst();

        first.ifPresentOrElse(
                course -> System.out.println("First course with review score > 95: " + course),
                () -> System.out.println("No course found with review score > 95.")
        );
        System.out.println();

        // 🔹 Sorting

        // 4. Sort courses by increasing number of students
        System.out.println("Courses sorted by increasing number of students:");
        courses.stream()
                .sorted(Comparator.comparing(Course::getNoOfStudents))
                .forEach(System.out::println);
        System.out.println();

        // 5. Sort courses by decreasing review score, then by course name
        System.out.println("Courses sorted by decreasing review score and then by course name:");
        courses.stream()
                .sorted(Comparator.comparing(Course::getReviewScore).reversed()
                        .thenComparing(Course::getName))
                .forEach(System.out::println);
        System.out.println();

        // 6. Find the course with the maximum number of students
        Optional<Course> courseWithMaxNoOfStudents = courses.stream()
                .max(Comparator.comparing(Course::getNoOfStudents));

        System.out.println("Course with maximum number of students: " + courseWithMaxNoOfStudents.get());
        System.out.println();

        // 7. Find the course with the minimum review score
        Optional<Course> courseWithMinReviewScore = courses.stream()
                .min(Comparator.comparing(Course::getReviewScore));

        System.out.println("Course with minimum review score: " + courseWithMinReviewScore.get());
        System.out.println();

        // 🔹 Mapping and Grouping

        // 8. Create a list of all course names
        System.out.println("List of all course names:");
        System.out.println(courses.stream()
                .map(Course::getName)
                .collect(Collectors.toList()));
        System.out.println();

        // 9. Group courses by category and list their names
        System.out.println("Courses grouped by category:");
        System.out.println(courses.stream()
                .collect(Collectors.groupingBy(Course::getCategory,
                        Collectors.mapping(Course::getName, Collectors.toList()))));
        System.out.println();

        // 10. Count the number of courses in each category
        System.out.println("Number of courses in each category:");
        System.out.println(courses.stream()
                .collect(Collectors.groupingBy(Course::getCategory, Collectors.counting())));
        System.out.println();

        // 11. Find the average number of students per category
        System.out.println("Average number of students per category:");
        System.out.println(courses.stream()
                .collect(Collectors.groupingBy(Course::getCategory,
                        Collectors.averagingInt(Course::getNoOfStudents))));
        System.out.println();

        // 12. Get a distinct list of categories
        System.out.println("Distinct course categories:");
        System.out.println(courses.stream()
                .map(Course::getCategory)
                .distinct()
                .collect(Collectors.toList()));
        System.out.println();

        // 🔹 Advanced Stream Usage

        // 13. Create a summary of the number of students (max, min, avg, sum, count)
        System.out.println("Summary statistics of number of students:");
        IntSummaryStatistics statistics = courses.stream()
                .collect(Collectors.summarizingInt(Course::getNoOfStudents));

        System.out.println("Count: " + statistics.getCount());
        System.out.println("Sum: " + statistics.getSum());
        System.out.println("Average: " + statistics.getAverage());
        System.out.println("Min: " + statistics.getMin());
        System.out.println("Max: " + statistics.getMax());
        System.out.println();

        // 14. Find the top 3 courses by review score
        System.out.println("Top 3 courses by review score:");
        courses.stream()
                .sorted(Comparator.comparing(Course::getReviewScore).reversed())
                .limit(3)
                .forEach(System.out::println);
        System.out.println();

        // 15. Skip top 3 courses by review score and list the rest
        System.out.println("Courses excluding top 3 by review score:");
        courses.stream()
                .sorted(Comparator.comparing(Course::getReviewScore).reversed())
                .skip(3)
                .forEach(System.out::println);
        System.out.println();

        // 16. Calculate total number of students using mapToInt and sum
        int totalSum = courses.stream()
                .mapToInt(Course::getNoOfStudents)
                .sum();
        System.out.println("Total number of students (using sum): " + totalSum);
        System.out.println();

        // 17. Calculate total number of students using reduce
        int totalStudents = courses.stream()
                .reduce(0, (a, b) -> a + b.getNoOfStudents(), Integer::sum);
        System.out.println("Total number of students (using reduce): " + totalStudents);
        System.out.println();

        // 18. Chain filter, map, and sort to print names of courses with review score > 95, sorted by number of students
        System.out.println("Courses with review score > 95 sorted by course name:");
        courses.stream()
                .filter(course -> course.getReviewScore() > 95)
                .sorted(Comparator.comparing(Course::getName))
                .forEach(System.out::println);
        System.out.println();

        // 🔹 Functional Interfaces

        // 19. Predicate to check if a course has a review score greater than 95
        Predicate<Course> testScorePredicate = course -> course.getReviewScore() > 95;
        System.out.println("Predicate test on course 0: " + testScorePredicate.test(courses.get(0)));
        System.out.println("Predicate test on course 4: " + testScorePredicate.test(courses.get(4)));
        System.out.println();

        // 20. Comparator lambda to compare courses by number of students
        Comparator<Course> noOfStudentsComparator = Comparator.comparingInt(Course::getNoOfStudents);

        // 21. Function to map a course to its name in uppercase
        System.out.println("Course names in uppercase:");
        Function<Course, String> courseNameToUpperCase = course -> course.getName().toUpperCase();
        courses.stream()
                .map(courseNameToUpperCase)
                .forEach(System.out::println);
        System.out.println();

        // 22. Consumer lambda to print course details
        System.out.println("Course details using Consumer:");
        Consumer<Course> printCourseDetails = course -> System.out.println(
                course.getName() + " - " + course.getCategory() + " - " +
                        course.getReviewScore() + " - " + course.getNoOfStudents()
        );
        courses.forEach(printCourseDetails);
    }
}
