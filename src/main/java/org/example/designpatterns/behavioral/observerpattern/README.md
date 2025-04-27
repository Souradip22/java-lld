# 📖 1. What is the Observer Pattern?

👉 **Definition**:
> **Observer Pattern** is when **one object** (called **Subject**) maintains a list of **dependents** (called **Observers**) and **automatically notifies them** of any state change.

✅ In short:
> "If Subject changes ➔ all Observers should know!"

---

# 🧠 2. Where have you seen this in real life?

- **YouTube Channel** = Subject
- **You (Subscriber)** = Observer
- When the channel posts a video, **all subscribers are notified**!

Simple, right? 🔥

---

# 🎯 3. Core Concepts:

| Term | Meaning |
|:---|:---|
| Subject | The object that holds the main state (eg. YouTube Channel). |
| Observer | The object that wants to know if Subject changes (eg. You, the subscriber). |
| Notify | Subject tells all Observers "Hey! I changed!" |

---

# 🔥 4. Basic Flow

1. Observer subscribes (registers) with Subject.
2. Subject changes (state update).
3. Subject notifies all Observers.
4. Observers receive update and act accordingly.

---

# 🛠️ 5. Simple Example in Java

---

## Step 1: Create the Observer Interface

```java
public interface Observer {
    void update(String message);
}
```

---

## Step 2: Create the Subject Interface

```java
public interface Subject {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();
}
```

---

## Step 3: Implement Subject (YouTubeChannel)

```java
import java.util.ArrayList;
import java.util.List;

public class YouTubeChannel implements Subject {
    private List<Observer> subscribers = new ArrayList<>();
    private String latestVideo;

    public void uploadVideo(String videoTitle) {
        this.latestVideo = videoTitle;
        notifyObservers();
    }

    @Override
    public void registerObserver(Observer o) {
        subscribers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        subscribers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer subscriber : subscribers) {
            subscriber.update(latestVideo);
        }
    }
}
```

---

## Step 4: Implement Observer (User)

```java
public class User implements Observer {
    private String name;

    public User(String name) {
        this.name = name;
    }

    @Override
    public void update(String videoTitle) {
        System.out.println(name + " received notification: New Video - " + videoTitle);
    }
}
```

---

## Step 5: Main

```java
public class Main {
    public static void main(String[] args) {
        YouTubeChannel channel = new YouTubeChannel();

        User user1 = new User("Alice");
        User user2 = new User("Bob");

        channel.registerObserver(user1);
        channel.registerObserver(user2);

        channel.uploadVideo("Observer Pattern Explained!");

        channel.removeObserver(user1);

        channel.uploadVideo("Advanced Java Patterns!");
    }
}
```

---

# ✅ Output:

```
Alice received notification: New Video - Observer Pattern Explained!
Bob received notification: New Video - Observer Pattern Explained!
Bob received notification: New Video - Advanced Java Patterns!
```

---

# 🎯 6. When to Use Observer Pattern

- When multiple objects depend on **one** object and must stay updated automatically.
- Event systems (button click ➔ listeners).
- News feed, messaging apps.
- Stock price update systems.

---

# 🧠 7. Memory Trick:

> **"One Subject, Many Observers, Instant Update."**  
> (S.O.U - Subject, Observer, Update.)

---

# ⚡ 8. Advanced Notes (for interviews)

- **Push vs Pull**:
    - **Push**: Subject sends full data during `update()`.
    - **Pull**: Subject sends minimal data (like "updated") and Observer pulls details if needed.

- **Java built-in support**:
    - Java had `Observable` and `Observer` classes (deprecated in newer Java).
    - We manually implement it now, or use libraries like RxJava, EventBus.

- **Thread safety**:  
  If multiple threads modify Subject, you need synchronization.

---

# ✨ Real-world Systems using Observer:

| System | Subject | Observer |
|:---|:---|:---|
| Stock Market App | Stock Server | Investor Apps |
| Weather App | Weather Station | Weather Displays |
| Messaging App | Message Server | User Devices |

