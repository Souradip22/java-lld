# Combinator Pattern in Java for object validation

The Combinator Pattern is a design pattern used in **functional programming** to compose complex functions from simpler ones.
It's particularly useful for building validation logic, parsers, or any other kind of transformation pipeline.

#### The key benefits of the Combinator Pattern include:
- Modularity: Each validation rule is encapsulated in a separate function, making it easy to understand and maintain.
- Reusability: Validators can be reused across different validation scenarios.
- Composition: Complex validation rules can be built by combining simple validators using combinator functions.
- Type Safety: The use of function composition ensures type safety, as each validator has a well-defined input and output type.

##### Problem Statement:
We have a Customer class with fields (`name`, `email`, `phoneNumber`, `dob`). Our goal is to validate each field's data before proceeding with any operation,
such as storing it in a database. We aim to ensure data integrity by only storing valid customer information in the database.

##### Traditional Approach:
In the general approach, we typically create individual validation methods for each field of the Customer class. These methods contain specific validation logic for their respective fields.
Then, in the main processing logic, we sequentially call each validation method for every field, ensuring validity before proceeding with further operations.

we start with creating a `CustomerValidatorService` class responsible for validating customer data:
```java

import java.time.LocalDate;
import java.time.Period;

public class CustomerValidatorService {
    private boolean isEmailValid(String email){
        return email.contains("@");
    }
    private boolean isPhoneNumberValid(String phoneNumber){
        return phoneNumber.contains("091");
    }
    private boolean isAdult(LocalDate dob){
        return Period.between(dob, LocalDate.now()).getYears() > 16;
    }
    public boolean isValid(Customer customer){
        return isEmailValid(customer.email()) &&
                isPhoneNumberValid(customer.phoneNumber()) &&
                isAdult(customer.dob());
    }
}
```
Explanation:
- **isEmailValid(String email)**: This private method checks if the given email address contains the "@" symbol, indicating a valid email format.
- **isPhoneNumberValid(String phoneNumber)**: Another private method that checks if the given phone number contains the substring "091", assuming it's a valid format.
- **isAdult(LocalDate dob)**: This private method calculates the age of a person based on their date of birth (dob).
  It uses the `Period.between()` method to calculate the difference between the date of birth and the current date, then checks if the age is greater than 16 to determine if the person is an adult.
- **isValid(Customer customer)**: This method is public and serves as the entry point for validating a Customer object.
  It invokes the private validation methods for email, phone number, and age, and returns true if all validations pass, indicating that the customer data is valid.

Now inside main function we can use the validation logic like this:
```java
import java.time.LocalDate;
//other import statements
record Customer(String name, String email, String phoneNumber, LocalDate dob) {
}

public class Main {
    public static void main(String[] args) {
        Customer customer1 = new Customer(
                "John",
                "john@gmail.com",
                "091222",
                LocalDate.of(2018, 11, 22)
        );
        Customer customer2 = new Customer(
                "Anna",
                "anna@gmail.com",
                "091234",
                LocalDate.of(2002, 11, 22)
        );


        CustomerValidatorService validatorService = new CustomerValidatorService(); //with this we just get if valid or not without any proper message
        System.out.println(validatorService.isValid(customer1)); //false
        System.out.println(validatorService.isValid(customer2));//true

        //if valid we can store customer in DB
    }
}
```
This code still works. For `customer1`, the output is `false` because he is not an adult, and in the case of `customer2`, everything seems to be fine, so the output is `true`.
But, there are a few other problems too -
- This code is not readable
- Users are not able to identify which is the actual issue

Even though the code still might work, it is not that great because of the above two reasons. So, how can we improve this using the Combinator pattern.

### Using Combinator Pattern
We start by creating an interface called `CustomerRegistrationValidator`. Let me first show you the code, and then we will discuss what is happening here.
```java
import java.time.LocalDate;
import java.time.Period;
import java.util.function.Function;
//other necessary imports

public interface CustomerRegistrationValidator
        extends Function <Customer, ValidationResult>{

    static CustomerRegistrationValidator isEmailValid(){
        return customer -> customer.email().contains("@") ?
                SUCCESS : EMAIL_NOT_VALID;
    }
    static CustomerRegistrationValidator isPhoneNumberValid(){
        return customer -> customer.phoneNumber().contains("091") ?
                SUCCESS : PHONE_NUMBER_NOT_VALID;
    }
    static CustomerRegistrationValidator isAdult(){
        return customer ->  Period.between(customer.dob(), LocalDate.now()).getYears() > 16?
                SUCCESS : IS_NOT_AN_ADULT;
    }
    default CustomerRegistrationValidator and (CustomerRegistrationValidator other){
        return customer -> {
            ValidationResult result = this.apply(customer);
            return result.equals(SUCCESS) ? other.apply(customer) : result;
        };
    }
    enum ValidationResult{
        SUCCESS,
        PHONE_NUMBER_NOT_VALID,
        EMAIL_NOT_VALID,
        IS_NOT_AN_ADULT
    }
}
```

Explanation:

**Interface Definition:**
- `CustomerRegistrationValidator` is defined as a functional interface that takes a `Customer` object and returns a `ValidationResult`.
- It extends the `Function` interface, specifying that it takes a `Customer` and returns a `ValidationResult`.

**Static Factory Methods:**
- `isEmailValid()`, `isPhoneNumberValid()`, and `isAdult()` are static factory methods that return instances of `CustomerRegistrationValidator`. This is important for chaining (we will see it in action)
- Each method returns a `lambda` expression that takes a `Customer` object and performs specific validation checks (email format, phone number format, and age validation, respectively).
- If the validation passes, it returns `SUCCESS`, otherwise, it returns the appropriate `ValidationResult`.

**Combining Validators:** [<span className="text-red-500"> *Important </span>]
- The `and` method is defined as a `default` method in the interface. It takes another `CustomerRegistrationValidator` as an argument and returns a new validator that combines the functionality of both
  validators. It applies the validation of the current validator (`this`) first. If it succeeds (`SUCCESS`), it applies the validation of the other validator. Otherwise, it returns the result of the current validator.

**Enum ValidationResult:**
- Defines possible outcomes of the validation process: `SUCCESS`, `PHONE_NUMBER_NOT_VALID`, `EMAIL_NOT_VALID`, and `IS_NOT_AN_ADULT`.


With this pattern, you can compose different validation rules by chaining them using the and method, providing a flexible and modular way to validate customer registrations.
Let us also make the necessary changes in the Main Java file.
```java
public class Main {
    public static void main(String[] args) {
        Customer customer1 = new Customer(
                "John",
                "john@gmail.com",
                "091222",
                LocalDate.of(2018, 11, 22)
        );
        Customer customer2 = new Customer(
                "Anna",
                "anna@gmail.com",
                "091234",
                LocalDate.of(2002, 11, 22)
        );
        //Using Combinator pattern
        ValidationResult result = isEmailValid()
                .and(isAdult())
                .and(isPhoneNumberValid())
                .apply(customer1);

        System.out.println(result);
        if (result != ValidationResult.SUCCESS){
            throw new IllegalStateException(result.name());
        }
        //if valid we can store customer in DB

    }
}
```

This time we will get a proper error with a message like below:
```shell
IS_NOT_AN_ADULT
Exception in thread "main" java.lang.IllegalStateException: IS_NOT_AN_ADULT
	at functionalprogramming.combinatorpattern.Main.main(Main.java:34)
```

So, now you can add and chain more validation logic like this:
```java
ValidationResult result = isEmailValid()
        .and(isAdult())
        .and(isPhoneNumberValid())
        .and(anotherLogic())
        //.....other logics
        .apply(customer1);
```

#### Few points:

- **Advantages of the Combinator Pattern:**
    - Enhances code manageability and readability.
    - Facilitates chaining of validation logic.
    - Provides specific error messages in case of validation failure.

- **Improved Validation Process:**
    - Easily compose complex validation rules from simpler ones.
    - Encapsulates each validation rule as a separate function for modularity and reusability.

- **Enhanced Maintainability:**
    - Simplifies maintenance and updates to validation logic.
    - Changes to validation rules can be made without impacting other parts of the codebase.

- **Alignment with Functional Programming Paradigm:**
    - Aligns well with principles of functional programming.
    - Leverages higher-order functions and lambda expressions for flexible validation logic.

- **Overall Efficiency and Reliability:**
    - Ensures only valid data is processed further, enhancing efficiency and reliability.
    - Promotes structured and systematic validation, reducing errors in data processing.


