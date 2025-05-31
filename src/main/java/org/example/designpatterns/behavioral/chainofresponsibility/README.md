
## 🧠 1. What Is the Chain of Responsibility Pattern?

**Definition**:

> A behavioral pattern that lets you **pass a request along a chain of handlers**, where each handler decides either to process the request or pass it to the next handler.

---

## 💥 2. The Problem (Without the Pattern)

Suppose you are building a **user input validation system**. You want to validate:

1. Input is not empty
2. Email is valid
3. Password length ≥ 8

You might write something like:

```java
public class Validator {
    public boolean validate(User user) {
        if (user.getName().isEmpty()) {
            System.out.println("Name is empty");
            return false;
        }
        if (!user.getEmail().contains("@")) {
            System.out.println("Email is invalid");
            return false;
        }
        if (user.getPassword().length() < 8) {
            System.out.println("Password too short");
            return false;
        }
        System.out.println("Validation passed!");
        return true;
    }
}
```

**❌ Problems:**

* Validation logic is tightly coupled.
* Cannot add/remove/reorder validators dynamically.
* Difficult to reuse individual rules.

---

## ✅ 3. Solution: Using the Chain of Responsibility Pattern

Each validator becomes a **link in a chain**, responsible for:

* Checking a rule.
* Either **handling** it or **passing it to the next** handler.

---

## 📦 4. Core Concepts

| Concept                | Description                                          |
| ---------------------- | ---------------------------------------------------- |
| **Handler (Abstract)** | Declares interface and has reference to next handler |
| **Concrete Handler**   | Implements actual logic and passes to next if needed |
| **Client**             | Starts the chain by sending request to first handler |

---

## 🛠️ 5. Java Implementation – Step-by-Step

### Step 1: User Class

```java
public class User {
    private String name, email, password;
    public User(String name, String email, String password) {
        this.name = name; this.email = email; this.password = password;
    }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
}
```

---

### Step 2: Abstract Handler

```java
public abstract class Validator {
    protected Validator next;

    public Validator linkWith(Validator next) {
        this.next = next;
        return next;
    }

    public abstract boolean validate(User user);
    
    protected boolean validateNext(User user) {
        if (next == null) return true;
        return next.validate(user);
    }
}
```

---

### Step 3: Concrete Handlers

```java
public class NameValidator extends Validator {
    public boolean validate(User user) {
        if (user.getName().isEmpty()) {
            System.out.println("Name is empty");
            return false;
        }
        return validateNext(user);
    }
}

public class EmailValidator extends Validator {
    public boolean validate(User user) {
        if (!user.getEmail().contains("@")) {
            System.out.println("Invalid email");
            return false;
        }
        return validateNext(user);
    }
}

public class PasswordValidator extends Validator {
    public boolean validate(User user) {
        if (user.getPassword().length() < 8) {
            System.out.println("Password too short");
            return false;
        }
        return validateNext(user);
    }
}
```

---

### Step 4: Client Code

```java
public class Main {
    public static void main(String[] args) {
        User user = new User("John", "john@example.com", "pass1234");

        Validator validatorChain = new NameValidator();
        validatorChain.linkWith(new EmailValidator())
                      .linkWith(new PasswordValidator());

        if (validatorChain.validate(user)) {
            System.out.println("All validations passed.");
        } else {
            System.out.println("Validation failed.");
        }
    }
}
```

---

## 📤 Output

```
All validations passed.
```

---

## 💼 6. Real-World Software Engineering Use Cases

| System                        | Usage                                                       |
| ----------------------------- | ----------------------------------------------------------- |
| **Servlet Filters (Java EE)** | Request goes through multiple filters (Auth, Logging, CORS) |
| **Logging Frameworks**        | Logs passed through levels (Debug → Info → Error)           |
| **Event Handling Systems**    | Events passed through multiple handlers or interceptors     |
| **Middleware Pipelines**      | In Express.js or ASP.NET, middleware components form chains |
| **Access Control**            | Chain of checks for different permission levels             |

---

## 🎯 7. When to Use This Pattern?

Use Chain of Responsibility when:

* You have multiple objects that can handle a request.
* You want to avoid coupling sender and receiver.
* You want to build flexible, extensible validation or processing pipelines.

---

## ✅ 8. Benefits

* **Loose coupling** between sender and handlers.
* **Flexible chain composition** at runtime.
* **Single Responsibility**: each handler does one job.

---

## ⚠️ 9. Drawbacks

* No guarantee that request will be handled (unless enforced).
* Chain setup must be done correctly.
* Debugging can be harder with long chains.

---

## 🧪 10. Advanced Usage

* Handlers can store logs or side-effects.
* Chains can be dynamically loaded from config files or dependency injection.
* Handlers can be async (e.g., reactive programming, coroutines).

---

## 🧠 Final Mnemonic

> Think of a **security checkpoint**: each person (handler) checks a different thing. If one passes, they hand it over to the next.
> That’s **Chain of Responsibility**.
