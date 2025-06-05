## 🧠 What is the Decorator Pattern?

**Decorator Pattern** is a **structural design pattern** that allows you to **dynamically add behavior or responsibilities** to objects **without modifying their code**.

> It's like wrapping a gift box with additional layers (wrappers) without changing the actual gift.

---

## 🔴 Step 1: The Problem Without Decorator Pattern

### 🧩 Scenario:

You’re designing a coffee ordering system. Each coffee can have optional features: milk, sugar, whipped cream, etc.

### ✅ Basic Interface:

```java
public interface Coffee {
    String getDescription();
    double getCost();
}
```

### ❌ Naive Implementation:

```java
class SimpleCoffee implements Coffee {
    public String getDescription() {
        return "Simple Coffee";
    }

    public double getCost() {
        return 5.0;
    }
}

class MilkCoffee extends SimpleCoffee {
    public String getDescription() {
        return super.getDescription() + ", Milk";
    }

    public double getCost() {
        return super.getCost() + 1.5;
    }
}
```

### ❌ Problem:

* What if we want combinations like:

    * Milk + Sugar
    * Milk + Cream + Sugar
* We'd need to **create a new subclass for every combination**.
* This leads to a **class explosion** 💥 and violates **Open/Closed Principle**.

---

## ✅ Step 2: Solution with Decorator Pattern

Let’s wrap objects with additional behavior **dynamically** without changing the existing class.

---

## 🔑 Core Concept

| Role                  | Description                                             |
| --------------------- | ------------------------------------------------------- |
| **Component**         | The base interface (e.g., `Coffee`)                     |
| **ConcreteComponent** | The original object to decorate (e.g., `SimpleCoffee`)  |
| **Decorator**         | Abstract wrapper that implements the same interface     |
| **ConcreteDecorator** | Adds behavior (e.g., `MilkDecorator`, `SugarDecorator`) |

---

## 🛠️ Step 3: Implementing the Decorator Pattern

### 1. Component

```java
public interface Coffee {
    String getDescription();
    double getCost();
}
```

### 2. ConcreteComponent

```java
class SimpleCoffee implements Coffee {
    public String getDescription() {
        return "Simple Coffee";
    }

    public double getCost() {
        return 5.0;
    }
}
```

### 3. Abstract Decorator

```java
abstract class CoffeeDecorator implements Coffee {
    protected Coffee coffee;

    public CoffeeDecorator(Coffee coffee) {
        this.coffee = coffee;
    }

    public String getDescription() {
        return coffee.getDescription();
    }

    public double getCost() {
        return coffee.getCost();
    }
}
```

### 4. Concrete Decorators

```java
class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }

    public String getDescription() {
        return super.getDescription() + ", Milk";
    }

    public double getCost() {
        return super.getCost() + 1.5;
    }
}

class SugarDecorator extends CoffeeDecorator {
    public SugarDecorator(Coffee coffee) {
        super(coffee);
    }

    public String getDescription() {
        return super.getDescription() + ", Sugar";
    }

    public double getCost() {
        return super.getCost() + 0.5;
    }
}
```

### 5. Client Usage

```java
public class CoffeeShop {
    public static void main(String[] args) {
        Coffee coffee = new SimpleCoffee();
        coffee = new MilkDecorator(coffee);
        coffee = new SugarDecorator(coffee);

        System.out.println(coffee.getDescription());
        System.out.println("Total Cost: $" + coffee.getCost());
    }
}
```

### ✅ Output:

```
Simple Coffee, Milk, Sugar
Total Cost: $7.0
```

---

## 🔁 How It Works (Step-by-Step)

### Suppose this is the call chain:

```java
Coffee order = new SugarDecorator(new MilkDecorator(new SimpleCoffee()));
```

Visually:

```
SugarDecorator
   ⬇️
MilkDecorator
   ⬇️
SimpleCoffee
```

---

### Now `order.getCost()` executes like this:

1. **SugarDecorator** calls `super.getCost()` → goes to `MilkDecorator`.
2. **MilkDecorator** calls `super.getCost()` → goes to `SimpleCoffee`.
3. **SimpleCoffee** returns `5.0`
4. MilkDecorator adds `+1.5` → returns `6.5`
5. SugarDecorator adds `+0.5` → returns `7.0`

---

### Similarly, `getDescription()` bubbles up:

* `"Simple Coffee"` → `"Simple Coffee, Milk"` → `"Simple Coffee, Milk, Sugar"`

---

## 📚 Real Software Engineering Examples

| Example                   | Usage                                                               |
| ------------------------- | ------------------------------------------------------------------- |
| **Java I/O Streams**      | `BufferedInputStream`, `DataInputStream` wrapping `FileInputStream` |
| **Logging Frameworks**    | Adding context info (timestamp, thread, etc.) to logs               |
| **Web Filters in Spring** | Servlet filters wrap requests/responses                             |
| **React Components**      | Higher-Order Components (HOCs) act as decorators                    |
| **Security**              | Add role or permission validation dynamically using decorators      |

---

## 🎓 Advanced: Recursive Wrapping

Decorators can be **nested indefinitely**:

```java
coffee = new MilkDecorator(new SugarDecorator(new MilkDecorator(new SimpleCoffee())));
```

---

## ✅ When to Use Decorator Pattern

| ✅ Use When                                       | ❌ Avoid When                                               |
| ------------------------------------------------ | ---------------------------------------------------------- |
| You want to **add features dynamically**         | You need **deep object mutation**                          |
| You want to follow the **Open/Closed Principle** | Behavior is better handled via **inheritance or strategy** |
| You want to **wrap** and **reuse** existing code | Too many decorators may confuse readability                |

---

## 🧾 Summary

| Key     | Value                                                    |
| ------- | -------------------------------------------------------- |
| Type    | Structural Pattern                                       |
| Purpose | Dynamically attach new behavior                          |
| Pros    | Extensible, Open/Closed Principle, No subclass explosion |
| Cons    | Can lead to many small classes                           |
| Analogy | Wrapping gifts with layers of decoration                 |

---

## 🧠 Tips to Remember

* Think of **"wrapping" functionality** like middleware or layers.
* It’s **runtime composition** of behavior.
* Great when you **can’t or shouldn’t modify base classes**.
