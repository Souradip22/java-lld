## 🧠 What is the Adapter Pattern?

**Adapter Pattern** is a **structural design pattern** that allows **incompatible interfaces to work together**.

> It acts as a **bridge** between two incompatible interfaces.

---

## 🔴 Step 1: The Problem Without Adapter Pattern

### 📘 Scenario:

You’re building a payment system. You already have an interface `PaymentGateway` used by your app, but a new third-party service (e.g., Stripe) doesn’t match this interface.

### ✅ Your expected interface:

```java
public interface PaymentGateway {
    void pay(String amount);
}
```

### 🚫 The 3rd party library:

```java
// You can't change this
public class StripeAPI {
    public void makePayment(String currency, String amount) {
        System.out.println("Stripe payment of " + amount + " " + currency);
    }
}
```

### ❌ Problem:

You can't directly pass `StripeAPI` where `PaymentGateway` is expected. Their **interfaces don’t match**.

---

## ✅ Step 2: The Adapter Solution

We create an **Adapter** that implements your `PaymentGateway` interface but internally uses `StripeAPI`.

---

## 🔑 Core Concept

| Element | Role                           |
| ------- | ------------------------------ |
| Target  | Your expected interface        |
| Adaptee | Existing incompatible class    |
| Adapter | Converts Target → Adaptee call |

---

## 🛠️ Step 3: Implement the Adapter Pattern

### 1. Target Interface

```java
public interface PaymentGateway {
    void pay(String amount);
}
```

### 2. Adaptee (Incompatible class)

```java
public class StripeAPI {
    public void makePayment(String currency, String amount) {
        System.out.println("Stripe: Paid " + amount + " " + currency);
    }
}
```

### 3. Adapter

```java
public class StripeAdapter implements PaymentGateway {
    private StripeAPI stripe;

    public StripeAdapter(StripeAPI stripe) {
        this.stripe = stripe;
    }

    @Override
    public void pay(String amount) {
        stripe.makePayment("USD", amount); // Convert format
    }
}
```

### 4. Client Code (Now works!)

```java
public class PaymentService {
    public static void main(String[] args) {
        StripeAPI stripe = new StripeAPI();
        PaymentGateway adapter = new StripeAdapter(stripe);
        adapter.pay("100");
    }
}
```

### ✅ Output:

```
Stripe: Paid 100 USD
```

---

## 🔁 Real-World Examples in Software Engineering

| Context                     | Adapter Use Case                                             |
| --------------------------- | ------------------------------------------------------------ |
| **Legacy Code Integration** | Wrapping old class with new interface                        |
| **Cloud SDKs**              | Adapting vendor SDK to generic app interface                 |
| **UI Frameworks**           | Adapting legacy event listeners to modern ones               |
| **APIs**                    | Converting different API response formats into common DTOs   |
| **Logging**                 | Using an adapter to bridge `SLF4J` with `Log4J` or `Logback` |

---

## 🎓 Advanced: Object Adapter vs Class Adapter

### ✅ Object Adapter (Most Common in Java)

Uses **composition** (holds instance of adaptee).

```java
public class StripeAdapter implements PaymentGateway {
    private StripeAPI stripe;

    public StripeAdapter(StripeAPI stripe) {
        this.stripe = stripe;
    }

    public void pay(String amount) {
        stripe.makePayment("USD", amount);
    }
}
```

### ✅ Class Adapter (Less Common, Uses Inheritance)

Uses **inheritance**, can be done in languages that support multiple inheritance.

```java
public class StripeClassAdapter extends StripeAPI implements PaymentGateway {
    public void pay(String amount) {
        makePayment("USD", amount);
    }
}
```

> In Java, multiple inheritance is not allowed for classes, so class adapters are rarely used unless you inherit from abstract classes/interfaces.

---

## ✅ When to Use Adapter Pattern

| ✅ Use When                                                 | ❌ Avoid When                         |
| ---------------------------------------------------------- | ------------------------------------ |
| You want to reuse existing code but interfaces don’t match | The adaptee class changes frequently |
| You integrate 3rd party / legacy APIs                      | There’s only one place it's used     |
| You want to decouple client from implementation            | You can refactor adaptee directly    |

---

## 🧾 Summary

| Term        | Meaning                                                 |
| ----------- | ------------------------------------------------------- |
| Type        | Structural Pattern                                      |
| Purpose     | Make incompatible interfaces work together              |
| How         | Wrap adaptee in adapter that matches expected interface |
| Key Benefit | Reusability, integration without modification           |

---

## ✅ Tips to Remember

* **“Convert interface of a class into another interface”**.
* Think of **charging adapters**: socket mismatch → use plug adapter.
* Use when you **can’t modify existing class**, but must **use it** with your system.
