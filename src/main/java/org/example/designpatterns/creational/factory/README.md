## 🧠 1. What is the Factory Pattern?


> Factory Pattern provides an **interface for creating objects** in a **superclass**, but **allows subclasses to alter the type of objects** that will be created.

---

## 🪨 2. The Problem Without Factory

Suppose you’re building a **notification system** that sends either **Email** or **SMS**.

```java
public class NotificationService {
    public void sendNotification(String type) {
        if (type.equals("EMAIL")) {
            EmailNotification email = new EmailNotification();
            email.send("Hello via Email!");
        } else if (type.equals("SMS")) {
            SMSNotification sms = new SMSNotification();
            sms.send("Hello via SMS!");
        }
    }
}
```

### 🔴 Problems:

* ❌ Code violates **Open/Closed Principle** — adding a new type (e.g., Push) requires modifying existing code.
* ❌ Class knows about all **concrete classes** — tight coupling.
* ❌ Harder to maintain/test/extend.

---

## 🧩 3. Core Concepts of Factory Pattern

* Create **objects without exposing instantiation logic**.
* Let the **factory method decide** which object to return.
* Helps in managing **object creation logic** in one place.

---

## ✅ 4. Refactor With Factory Pattern

---

### Step 1: Create a Common Interface

```java
public interface Notification {
    void send(String message);
}
```

---

### Step 2: Concrete Implementations

```java
public class EmailNotification implements Notification {
    public void send(String message) {
        System.out.println("Email: " + message);
    }
}

public class SMSNotification implements Notification {
    public void send(String message) {
        System.out.println("SMS: " + message);
    }
}
```

---

### Step 3: Create the Factory Class

```java
public class NotificationFactory {
    public static Notification createNotification(String type) {
        if (type.equalsIgnoreCase("EMAIL")) {
            return new EmailNotification();
        } else if (type.equalsIgnoreCase("SMS")) {
            return new SMSNotification();
        }
        throw new IllegalArgumentException("Unknown notification type");
    }
}
```

---

### Step 4: Use the Factory in Your Code

```java
public class NotificationService {
    public void send(String type, String message) {
        Notification notification = NotificationFactory.createNotification(type);
        notification.send(message);
    }
}
```

---

### ✅ Output

```text
Email: Hello via Email!
SMS: Hello via SMS!
```

---

## 📦 5. Real-World Use Cases

| Use Case                 | Description                                                  |
| ------------------------ | ------------------------------------------------------------ |
| **Loggers**              | Create different loggers (file, console, DB) based on config |
| **UI Toolkits**          | Generate different UI components (Windows vs macOS)          |
| **ORMs**                 | Generate SQL dialects (MySQL, PostgreSQL)                    |
| **Notification Systems** | Email, SMS, Push from a single API                           |

---

## 🚦 6. Types of Factory Patterns

### 🔹 Simple Factory

* A single method to return object based on parameter.
* Not a formal GoF pattern but commonly used.

### 🔹 Factory Method Pattern (GoF)

* Uses **inheritance**: lets subclasses decide what to instantiate.

### 🔹 Abstract Factory Pattern

* Produces **families of related objects** (e.g., buttons, checkboxes).

---

## 🎯 7. When to Use Factory Pattern?

* When you have **complex object creation logic**.
* When your code needs to **work with multiple concrete classes**.
* To follow **Open/Closed Principle**.
* When the instantiation process may **change in the future**.

---

## ⚠️ 8. Pitfalls

* May introduce extra complexity.
* Too many factory classes can be overkill.
* Can hide dependencies if not documented properly.

---

## 💡 9. Final Tip to Remember

> "**Factory** is like a smart constructor.
> You ask it for a product, and it decides **which class to instantiate**, so you don’t have to."
