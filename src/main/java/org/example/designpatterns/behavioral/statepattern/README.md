## 🧠 1. What is the State Pattern?

**Definition**:

> The **State Pattern** allows an object to **change its behavior when its internal state changes**, appearing as if it changed its class.

---

## 💥 2. The Problem (Without State Pattern)

Let’s say you’re building a **Media Player** that can be in three states:

* Playing
* Paused
* Stopped

You might write code like this:

```java
public class MediaPlayer {
    private String state = "STOPPED";

    public void pressPlay() {
        if (state.equals("STOPPED")) {
            System.out.println("Starting playback...");
            state = "PLAYING";
        } else if (state.equals("PLAYING")) {
            System.out.println("Already playing");
        } else if (state.equals("PAUSED")) {
            System.out.println("Resuming playback...");
            state = "PLAYING";
        }
    }

    public void pressPause() {
        if (state.equals("PLAYING")) {
            System.out.println("Pausing playback...");
            state = "PAUSED";
        } else {
            System.out.println("Can't pause right now.");
        }
    }

    public void pressStop() {
        if (!state.equals("STOPPED")) {
            System.out.println("Stopping playback...");
            state = "STOPPED";
        } else {
            System.out.println("Already stopped.");
        }
    }
}
```

**❌ Issues:**

* Long if-else/switch chains.
* Adding new states = more complexity.
* Hard to test & maintain.

---

## ✅ 3. Solution: Using the State Pattern

We’ll move each **state-specific behavior** into its own class.

---

## 📦 4. Core Concepts

| Role                | Responsibility                                                       |
| ------------------- | -------------------------------------------------------------------- |
| **Context**         | Maintains an instance of a `State` that defines current behavior     |
| **State**           | Interface declaring methods like `pressPlay()`, `pressPause()`, etc. |
| **Concrete States** | Implement behavior specific to a state                               |
| **Transitions**     | Handled inside state objects, not in `Context`                       |

---

## 🛠️ 5. Java Example

### Step 1: Define the State Interface

```java
public interface State {
    void pressPlay(MediaPlayer context);
    void pressPause(MediaPlayer context);
    void pressStop(MediaPlayer context);
}
```

---

### Step 2: Concrete States

```java
public class PlayingState implements State {
    public void pressPlay(MediaPlayer context) {
        System.out.println("Already playing.");
    }

    public void pressPause(MediaPlayer context) {
        System.out.println("Pausing playback...");
        context.setState(new PausedState());
    }

    public void pressStop(MediaPlayer context) {
        System.out.println("Stopping playback...");
        context.setState(new StoppedState());
    }
}

public class PausedState implements State {
    public void pressPlay(MediaPlayer context) {
        System.out.println("Resuming playback...");
        context.setState(new PlayingState());
    }

    public void pressPause(MediaPlayer context) {
        System.out.println("Already paused.");
    }

    public void pressStop(MediaPlayer context) {
        System.out.println("Stopping playback...");
        context.setState(new StoppedState());
    }
}

public class StoppedState implements State {
    public void pressPlay(MediaPlayer context) {
        System.out.println("Starting playback...");
        context.setState(new PlayingState());
    }

    public void pressPause(MediaPlayer context) {
        System.out.println("Can't pause. Not playing.");
    }

    public void pressStop(MediaPlayer context) {
        System.out.println("Already stopped.");
    }
}
```

---

### Step 3: Context (MediaPlayer)

```java
public class MediaPlayer {
    private State currentState;

    public MediaPlayer() {
        this.currentState = new StoppedState(); // default
    }

    public void setState(State state) {
        this.currentState = state;
    }

    public void pressPlay() {
        currentState.pressPlay(this);
    }

    public void pressPause() {
        currentState.pressPause(this);
    }

    public void pressStop() {
        currentState.pressStop(this);
    }
}
```

---

### Step 4: Client

```java
public class Main {
    public static void main(String[] args) {
        MediaPlayer player = new MediaPlayer();

        player.pressPlay();
        player.pressPause();
        player.pressPlay();
        player.pressStop();
    }
}
```

---

## 📤 Output

```
Starting playback...
Pausing playback...
Resuming playback...
Stopping playback...
```

---

## 💼 6. Real-Life Software Usage

| System               | Usage of State Pattern                                      |
| -------------------- | ----------------------------------------------------------- |
| **TCP Connection**   | Different states: `LISTEN`, `SYN_SENT`, `ESTABLISHED`, etc. |
| **UI Components**    | Button: `Enabled`, `Hovered`, `Pressed`, `Disabled`         |
| **Workflow Engines** | `Draft` → `Submitted` → `Approved` → `Archived`             |
| **Game Development** | Player state: `Idle`, `Running`, `Jumping`, `Dead`          |
| **ATMs**             | `NoCard`, `HasCard`, `Authenticated`, `OutOfService` states |

---

## 🎯 7. When to Use State Pattern?

* When object behavior depends on its state.
* When state transitions are complex.
* To eliminate large if-else or switch blocks based on state.
* When states change dynamically at runtime.

---

## ✅ 8. Benefits

* Removes cluttered conditional logic.
* Easier to extend with new states.
* State transitions are encapsulated.
* Each state is testable & isolated.

---

## ⚠️ 9. Pitfalls

* More classes to manage.
* Can be overkill for simple state machines.
* Transitions must be carefully designed to avoid cycles or dead-ends.

---

## 🧪 10. Advanced: Dynamic State Injection

You can inject new states at runtime from config or external sources for plugin-like behavior.

---

## 🧠 Final Tip to Remember

> “**State Pattern** makes an object’s behavior vary as its state changes.
> Each state is a separate class that encapsulates the logic for that particular condition.”
