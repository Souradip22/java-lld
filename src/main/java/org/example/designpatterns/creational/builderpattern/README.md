
## 🚩 1. **The Problem Without Builder Pattern**

Imagine you're building a `Meal` object with multiple optional fields:

```java
public class Meal {
    private String mainDish;
    private String sideDish;
    private String drink;
    private String dessert;
    private String appetizer;

    public Meal(String mainDish, String sideDish, String drink, String dessert, String appetizer) {
        this.mainDish = mainDish;
        this.sideDish = sideDish;
        this.drink = drink;
        this.dessert = dessert;
        this.appetizer = appetizer;
    }
}
```

### ❌ Problems:

* What if some fields are optional?
* How to remember the order of 5 parameters?
* Hard to read, error-prone, not scalable.

```java
Meal m1 = new Meal("Burger", "Fries", "Coke", null, null);
Meal m2 = new Meal("Burger", "Fries", "Coke", "Cake", "Soup");
```

---

## ✅ 2. **Core Concept of Builder Pattern**

> **Builder Pattern** helps you construct complex objects step-by-step using a separate builder class.

### ⭐ Advantages:

* Handles optional parameters clearly
* Improves code readability and maintainability
* Supports immutability
* Promotes separation of concerns (object creation logic is separated)

---

## 🧱 3. **Solution Using Builder Pattern**

```java
public class Meal {
    private final String mainDish;
    private final String sideDish;
    private final String drink;
    private final String dessert;
    private final String appetizer;

    private Meal(MealBuilder builder) {
        this.mainDish = builder.mainDish;
        this.sideDish = builder.sideDish;
        this.drink = builder.drink;
        this.dessert = builder.dessert;
        this.appetizer = builder.appetizer;
    }

    public void printMealSummary() {
        System.out.println("Meal{" +
                "mainDish='" + mainDish + '\'' +
                ", sideDish='" + sideDish + '\'' +
                ", drink='" + drink + '\'' +
                ", dessert='" + dessert + '\'' +
                ", appetizer='" + appetizer + '\'' +
                '}');
    }

    public static class MealBuilder {
        private final String mainDish;
        private final String sideDish;
        private final String drink;
        private String dessert;
        private String appetizer;

        public MealBuilder(String mainDish, String sideDish, String drink) {
            this.mainDish = mainDish;
            this.sideDish = sideDish;
            this.drink = drink;
        }

        public MealBuilder setDessert(String dessert) {
            this.dessert = dessert;
            return this;
        }

        public MealBuilder setAppetizer(String appetizer) {
            this.appetizer = appetizer;
            return this;
        }

        public Meal build() {
            return new Meal(this);
        }
    }
}
```

### 💡 Usage:

```java
Meal meal = new Meal.MealBuilder("Burger", "Fries", "Coke")
    .setDessert("Cake")
    .setAppetizer("Soup")
    .build();

meal.printMealSummary();
```

✅ Output:

```
Meal{mainDish='Burger', sideDish='Fries', drink='Coke', dessert='Cake', appetizer='Soup'}
```

---

## 🔍 4. **Advanced Concepts**

### a) **Immutable Objects**

All fields are `final` and only initialized through builder. No setters in the main class.

### b) **Method Chaining (Fluent Interface)**

Each method returns the builder object → allows chained calls.

### c) **Director Class (optional)**

Used to abstract and reuse specific builder steps:

```java
public class MealDirector {
    public Meal createVegMeal() {
        return new Meal.MealBuilder("Paneer", "Salad", "Juice")
                .setDessert("Gulab Jamun")
                .build();
    }
}
```

---

## 💼 5. **Real-Life Usage in Software Engineering**

| Use Case                        | Where You'll Find It            |
| ------------------------------- | ------------------------------- |
| **REST Client Setup**           | OkHttp, Retrofit, WebClient     |
| **UI Component Setup**          | JavaFX / Swing builders         |
| **Complex Test Data Setup**     | In JUnit/Mockito                |
| **URI Construction**            | `UriComponentsBuilder` (Spring) |
| **Database Query Construction** | JPA’s `CriteriaBuilder`         |
| **Lombok**                      | `@Builder` annotation           |
| **AWS SDK Clients**             | `AmazonS3ClientBuilder`         |

---

## 🎓 6. When to Use the Builder Pattern

* You have **many optional fields**
* Constructors are getting **too long**
* You want **readable object creation**
* You want **immutable objects**
* You want to avoid **telescoping constructors**

---

## 🧠 7. How to Remember

> Use the **Builder Pattern** when:
>
> * "The constructor takes too many arguments"
> * "You want object creation to be flexible and readable"
