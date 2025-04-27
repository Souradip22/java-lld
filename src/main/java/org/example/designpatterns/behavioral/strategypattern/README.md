# 📖 1. What is the Strategy Pattern?

👉 **Definition**:
> **Strategy Pattern** defines a family of algorithms (strategies), encapsulates each one, and makes them interchangeable.

✅ In short:
> "Choose behavior/algorithm at runtime."

---

# 🧠 2. Real Life Example (Simple)

- You go to a **payment screen**.
- You can **pay by Credit Card**, **UPI**, or **PayPal**.
- **Payment Method** = Strategy.
- Based on your choice, different **algorithms** run.

✅ Same problem ➔ different strategies!

---

# 🎯 3. Core Concepts:

| Term | Meaning |
|:---|:---|
| Context | The class that uses a Strategy. |
| Strategy | The interface for the algorithms. |
| Concrete Strategies | Different implementations of the Strategy. |

---

# 🔥 4. Basic Flow

1. Define a **Strategy interface** (example: `PaymentStrategy`).
2. Create **multiple Strategy implementations** (CreditCard, UPI, PayPal).
3. The **Context class** (like PaymentProcessor) **uses any Strategy**.
4. **Change Strategy at runtime** easily!

---

# 🛠️ 5. Simple Example in Java

---

## Step 1: Strategy Interface

```java
public interface PaymentStrategy {
    void pay(int amount);
}
```

---

## Step 2: Concrete Strategies

```java
public class CreditCardPayment implements PaymentStrategy {
    @Override
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using Credit Card.");
    }
}
```

```java
public class UPIPayment implements PaymentStrategy {
    @Override
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using UPI.");
    }
}
```

```java
public class PayPalPayment implements PaymentStrategy {
    @Override
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using PayPal.");
    }
}
```

---

## Step 3: Context Class

```java
public class PaymentProcessor {
    private PaymentStrategy paymentStrategy;

    // Inject strategy through constructor or setter
    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void processPayment(int amount) {
        paymentStrategy.pay(amount);
    }
}
```

---

## Step 4: Main

```java
public class Main {
    public static void main(String[] args) {
        PaymentProcessor processor = new PaymentProcessor();

        // User chooses Credit Card
        processor.setPaymentStrategy(new CreditCardPayment());
        processor.processPayment(500);

        // Later user chooses UPI
        processor.setPaymentStrategy(new UPIPayment());
        processor.processPayment(300);

        // Later user chooses PayPal
        processor.setPaymentStrategy(new PayPalPayment());
        processor.processPayment(700);
    }
}
```

---

# ✅ Output:

```
Paid 500 using Credit Card.
Paid 300 using UPI.
Paid 700 using PayPal.
```

---

# ✨ 6. When to Use Strategy Pattern?

- When you have **multiple algorithms** for a task.
- When **behavior changes based on user input, configuration, or environment**.
- When you want to **avoid many if-else** or **switch statements**.
- When you want **easy extensibility** (add new strategies without touching existing code).

---

# 🧠 7. Memory Trick:

> **"Different behavior, Same interface."**  
> (Strategy = Plug and Play Behavior.)

---

# ⚡ 8. Advanced Notes (for interviews)

- **Open/Closed Principle**:  
  Strategy pattern follows this.  
  (Open for extension ➔ new strategies. Closed for modification ➔ no changes to context.)

- **Dependency Injection** friendly:  
  You can inject Strategy at runtime.

- **Composition over Inheritance**:  
  Instead of subclassing Context for each behavior, you **compose** with different strategies.

---

# ✨ Real-World Examples:

| System | Strategy Used |
|:---|:---|
| Navigation App | Different routes (fastest, shortest, scenic) |
| Compression Tools | ZIP, RAR, 7ZIP Compression |
| Games | Different attack strategies (aggressive, defensive) |

---

# 📦 Mini Summary in One Line:

> "Strategy Pattern lets you **change algorithms like changing clothes 👕**, without changing the code that uses them."

