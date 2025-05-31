# 💬 Mediator Pattern - Chat Room Example

---

## 🧠 1. What is Mediator Pattern?

> The Mediator pattern centralizes communication between objects (colleagues), preventing them from referring to each other directly.

---

## 🧨 2. Problem Without Mediator

### ❌ Each user sends messages directly to every other user:

```java
public class User {
    private String name;
    private List<User> contacts;

    public User(String name) {
        this.name = name;
        this.contacts = new ArrayList<>();
    }

    public void addContact(User user) {
        contacts.add(user);
    }

    public void sendMessage(String message) {
        for (User u : contacts) {
            u.receiveMessage(name + ": " + message);
        }
    }

    public void receiveMessage(String message) {
        System.out.println(message);
    }
}
```

### 🔥 Issues:

* Users are **tightly coupled**
* You have to manually manage contacts
* Hard to scale or manage message routing

---

## 🪩 3. Solution: Use a **ChatRoom Mediator**

Let a **ChatRoom (mediator)** handle all communication between users.

---

## 🎯 4. Core Concepts

| Concept         | Role                                |
| --------------- | ----------------------------------- |
| `User`          | Colleague (participant)             |
| `ChatRoom`      | Mediator                            |
| `sendMessage()` | User asks ChatRoom to route message |

---

## 🛠️ 5. Java Implementation with Mediator Pattern

### Step 1: Mediator Interface

```java
public interface ChatRoomMediator {
    void registerUser(User user);
    void sendMessage(String message, User sender);
}
```

---

### Step 2: Concrete Mediator

```java
import java.util.ArrayList;
import java.util.List;

public class ChatRoom implements ChatRoomMediator {
    private List<User> users = new ArrayList<>();

    @Override
    public void registerUser(User user) {
        users.add(user);
    }

    @Override
    public void sendMessage(String message, User sender) {
        for (User user : users) {
            if (user != sender) {
                user.receive(message, sender.getName());
            }
        }
    }
}
```

---

### Step 3: Colleague Class (User)

```java
public class User {
    private String name;
    private ChatRoomMediator chatRoom;

    public User(String name, ChatRoomMediator chatRoom) {
        this.name = name;
        this.chatRoom = chatRoom;
        chatRoom.registerUser(this); // auto-register
    }

    public String getName() {
        return name;
    }

    public void send(String message) {
        chatRoom.sendMessage(message, this);
    }

    public void receive(String message, String from) {
        System.out.println("[" + name + "] received from [" + from + "]: " + message);
    }
}
```

---

### Step 4: Client Code

```java
public class Main {
    public static void main(String[] args) {
        ChatRoomMediator chatRoom = new ChatRoom();

        User alice = new User("Alice", chatRoom);
        User bob = new User("Bob", chatRoom);
        User charlie = new User("Charlie", chatRoom);

        alice.send("Hello everyone!");
        bob.send("Hi Alice!");
    }
}
```

---

## ✅ Output

```
[Bob] received from [Alice]: Hello everyone!
[Charlie] received from [Alice]: Hello everyone!
[Alice] received from [Bob]: Hi Alice!
[Charlie] received from [Bob]: Hi Alice!
```

---

## 🧠 Summary

* The `ChatRoom` acts as a **central hub**.
* Users are **decoupled** from each other.
* Chat logic (routing, filtering, logging, etc.) stays in **one place**.

---

## 🧪 6. Real-World Usage in Software Engineering

| Use Case                           | Description                                                    |
| ---------------------------------- | -------------------------------------------------------------- |
| **Chat Systems (Slack, WhatsApp)** | Server acts as a mediator between users                        |
| **Pub-Sub/Event Buses**            | Event broker routes messages between services                  |
| **Aircraft Communication (ATC)**   | Air Traffic Control (mediator) coordinates planes (colleagues) |
| **UI Form**                        | Form mediator updates component states                         |

---

## ✅ 7. Benefits

* Decouples users from one another
* Easy to manage message flow
* Add/remove users without changing others

---

## ⚠️ 8. Drawbacks

* Mediator can grow large (if not modularized)
* One failure point (if not designed well)

---

## 🧠 Final Tip

> Whenever **multiple objects need to talk**, and managing their connections becomes messy,
> **introduce a mediator** to clean things up.
