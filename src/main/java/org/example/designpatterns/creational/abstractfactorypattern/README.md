## 🧠 1. What is the Abstract Factory Pattern?

> **Abstract Factory Pattern** provides an **interface to create families of related or dependent objects** without specifying their concrete classes.

---

## 🪨 2. The Problem Without the Pattern

Suppose you're building a **cross-platform UI toolkit** that should support **Windows** and **macOS**. You want to render buttons and checkboxes for each OS.

### ❌ Naive Approach:

```java
public class Button {
    public void paint() {
        System.out.println("Render Windows Button");
    }
}

public class Checkbox {
    public void paint() {
        System.out.println("Render Windows Checkbox");
    }
}
```

Now for macOS:

```java
public class MacButton {
    public void paint() {
        System.out.println("Render Mac Button");
    }
}

public class MacCheckbox {
    public void paint() {
        System.out.println("Render Mac Checkbox");
    }
}
```

Then in UI code:

```java
public class Application {
    public void renderUI(String os) {
        if (os.equals("Windows")) {
            Button button = new Button();
            Checkbox checkbox = new Checkbox();
            button.paint();
            checkbox.paint();
        } else if (os.equals("Mac")) {
            MacButton button = new MacButton();
            MacCheckbox checkbox = new MacCheckbox();
            button.paint();
            checkbox.paint();
        }
    }
}
```

### 🔴 Problems:

* Tight coupling between UI code and product classes.
* Violates **Open/Closed Principle** — adding a new OS requires modifying core logic.
* Difficult to test and extend.

---

## 🧩 3. Core Concepts

* **Product Families**: Related objects (e.g., buttons and checkboxes) meant to work together.
* **Abstract Factory**: Interface to create each type of product.
* **Concrete Factories**: Implement the creation for each platform.
* **Client**: Uses the factory, never instantiates concrete classes directly.

---

## ✅ 4. Fix With Abstract Factory Pattern

---

### Step 1: Define Abstract Product Interfaces

```java
public interface Button {
    void paint();
}

public interface Checkbox {
    void paint();
}
```

---

### Step 2: Create Concrete Products

```java
public class WindowsButton implements Button {
    public void paint() {
        System.out.println("Render Windows Button");
    }
}

public class WindowsCheckbox implements Checkbox {
    public void paint() {
        System.out.println("Render Windows Checkbox");
    }
}

public class MacButton implements Button {
    public void paint() {
        System.out.println("Render Mac Button");
    }
}

public class MacCheckbox implements Checkbox {
    public void paint() {
        System.out.println("Render Mac Checkbox");
    }
}
```

---

### Step 3: Create the Abstract Factory Interface

```java
public interface GUIFactory {
    Button createButton();
    Checkbox createCheckbox();
}
```

---

### Step 4: Implement Concrete Factories

```java
public class WindowsFactory implements GUIFactory {
    public Button createButton() {
        return new WindowsButton();
    }

    public Checkbox createCheckbox() {
        return new WindowsCheckbox();
    }
}

public class MacFactory implements GUIFactory {
    public Button createButton() {
        return new MacButton();
    }

    public Checkbox createCheckbox() {
        return new MacCheckbox();
    }
}
```

---

### Step 5: Client Code Uses Factory

```java
public class Application {
    private Button button;
    private Checkbox checkbox;

    public Application(GUIFactory factory) {
        button = factory.createButton();
        checkbox = factory.createCheckbox();
    }

    public void renderUI() {
        button.paint();
        checkbox.paint();
    }
}
```

---

### Step 6: Bootstrapper Code

```java
public class Main {
    public static void main(String[] args) {
        String os = "Mac"; // Could be read from config
        GUIFactory factory;

        if (os.equals("Windows")) {
            factory = new WindowsFactory();
        } else {
            factory = new MacFactory();
        }

        Application app = new Application(factory);
        app.renderUI();
    }
}
```

---

## 🧪 Output

```text
Render Mac Button
Render Mac Checkbox
```

---

## 📦 5. Real-World Use Cases

| Use Case                 | Description                                                  |
| ------------------------ | ------------------------------------------------------------ |
| **UI Toolkits**          | Swing, JavaFX, Qt, React Native themes                       |
| **Database Drivers**     | MySQL vs Oracle vs Postgres factories                        |
| **Cloud SDKs**           | Factories for AWS, Azure, GCP clients                        |
| **Dependency Injection** | Factories provide related dependencies (e.g., service + DAO) |
| **Game Development**     | Families of related game objects (units, terrain, etc.)      |

---

## 🎯 6. When to Use

Use **Abstract Factory** when:

* You need to create **families of related objects**.
* You want to **decouple** creation from usage.
* You want to make your code **easily extensible** and follow **Open/Closed Principle**.

---

## 🧠 7. Remember Like This:

> *"Abstract Factory = Factory for families of factories."*
> You give it an environment (e.g., OS), and it gives you all the right objects.
