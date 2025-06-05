## 🧠 What is the Proxy Pattern?

**Proxy Pattern** is a **structural design pattern** that provides a **placeholder or surrogate** for another object to **control access** to it.

> Think of it like a **security guard** or **front desk** — it controls who can access the real object.

---

## 🔴 Step 1: The Problem Without Proxy Pattern

### 📘 Scenario:

You're building an image viewer that loads high-resolution images. Loading takes time and resources.

You directly use the `RealImage` class to show the image.

```java
public interface Image {
    void display();
}

public class RealImage implements Image {
    private String filename;

    public RealImage(String filename) {
        this.filename = filename;
        loadFromDisk();  // costly operation
    }

    private void loadFromDisk() {
        System.out.println("Loading " + filename);
    }

    public void display() {
        System.out.println("Displaying " + filename);
    }
}
```

### ❌ Problem:

Every time you create `RealImage`, it immediately loads the image from disk, **even if you don’t display it**.

```java
Image img1 = new RealImage("photo1.png");  // Loads even if not shown
img1.display();
```

### Result:

* High memory usage
* Unnecessary disk I/O
* Slower UI loading

---

## ✅ Step 2: Solution with Proxy Pattern

We add a **Proxy** that defers loading the image **until it's needed** (i.e., **lazy loading**).

---

## 🔑 Core Concept

| Role            | Purpose                                      |
| --------------- | -------------------------------------------- |
| **Subject**     | Common interface (e.g., `Image`)             |
| **RealSubject** | Actual object with heavy logic (`RealImage`) |
| **Proxy**       | Wrapper that controls access to RealSubject  |

---

## 🛠️ Step 3: Implementing the Proxy Pattern

### 1. Subject Interface

```java
public interface Image {
    void display();
}
```

### 2. RealSubject

```java
public class RealImage implements Image {
    private String filename;

    public RealImage(String filename) {
        this.filename = filename;
        loadFromDisk(); // heavy
    }

    private void loadFromDisk() {
        System.out.println("Loading " + filename);
    }

    public void display() {
        System.out.println("Displaying " + filename);
    }
}
```

### 3. Proxy

```java
public class ProxyImage implements Image {
    private RealImage realImage;
    private String filename;

    public ProxyImage(String filename) {
        this.filename = filename;
    }

    public void display() {
        if (realImage == null) {
            realImage = new RealImage(filename); // lazy init
        }
        realImage.display();
    }
}
```

### 4. Client Code

```java
public class Main {
    public static void main(String[] args) {
        Image img1 = new ProxyImage("photo1.png");  // No loading yet
        System.out.println("Image created");

        img1.display(); // Loads and displays
        img1.display(); // Only displays
    }
}
```

### ✅ Output:

```
Image created
Loading photo1.png
Displaying photo1.png
Displaying photo1.png
```

---

## 💡 Real-World Use Cases

| Example              | Proxy Role                                                    |
| -------------------- | ------------------------------------------------------------- |
| **Virtual Proxy**    | Lazy load expensive resources (e.g., images, DB connections)  |
| **Protection Proxy** | Restrict access (e.g., user roles)                            |
| **Remote Proxy**     | Represent object in different address space (e.g., RMI, gRPC) |
| **Smart Proxy**      | Add logging, caching, reference counting, etc.                |

---

## 🛡️ Protection Proxy Example

```java
public interface Document {
    void view();
}

public class RealDocument implements Document {
    public void view() {
        System.out.println("Viewing document contents...");
    }
}

public class SecureDocumentProxy implements Document {
    private RealDocument doc;
    private String userRole;

    public SecureDocumentProxy(String userRole) {
        this.userRole = userRole;
    }

    public void view() {
        if ("ADMIN".equals(userRole)) {
            if (doc == null) doc = new RealDocument();
            doc.view();
        } else {
            System.out.println("Access denied.");
        }
    }
}
```

---

## 🧠 When to Use Proxy Pattern

| ✅ Use When                                       | ❌ Avoid When                                    |
| ------------------------------------------------ | ----------------------------------------------- |
| Object is resource-heavy to create or load       | You don't need access control or lazy loading   |
| You want to control or enhance access            | Simpler delegation or DI might be sufficient    |
| You want to add logging, access control, caching | You need direct access to real object internals |

---

## 🔁 Variants of Proxy Pattern

| Type             | Use Case                                    |
| ---------------- | ------------------------------------------- |
| Virtual Proxy    | Delay object creation                       |
| Protection Proxy | Access control                              |
| Remote Proxy     | Access remote object                        |
| Smart Proxy      | Add behavior like logging, locking, caching |

---

## 📚 Real Software Engineering Examples

| Use Case                      | Proxy Pattern                                        |
| ----------------------------- | ---------------------------------------------------- |
| Spring AOP / `@Transactional` | Behind the scenes uses dynamic proxies               |
| Hibernate Lazy Loading        | Returns proxies instead of real objects until needed |
| gRPC / RMI stubs              | Client-side proxy for remote service                 |
| Security Filters              | Role-based access via proxies                        |
| Guava / Caffeine Cache        | Wraps real service to add caching                    |

---

## ✅ Summary

| Key     | Value                                     |
| ------- | ----------------------------------------- |
| Type    | Structural                                |
| Purpose | Control access to real object             |
| Pros    | Lazy loading, access control, enhancement |
| Cons    | Adds indirection, complexity if overused  |

---

## 🧠 Tips to Remember

* “**Surrogate**” or “**gatekeeper**” — controls access to something expensive or sensitive.
* Use it for **lazy load**, **security**, **remote access**, or **logging**.
* Think: **Spring uses proxy for transactions and method interceptors**.
