
# 📖 1. What is the Command Pattern?

👉 **Definition**:
> **Command Pattern** turns a request into a standalone object that contains all information about the request.

✅ In simple words:
> "Encapsulate a request (method call) as an object."

---

# 🧠 2. Why is it useful?

- You can **queue**, **log**, **undo**, or **delay** a request.
- You **decouple** the object that sends a request from the object that performs it.
- **Flexible** — new commands = no big changes.

---

# 📦 3. Real Life Analogy (Simple)

Imagine a **remote control**:

- Buttons (like `on`, `off`, `volume up`) → **send commands**.
- TV, AC, Music System → **receive commands**.

Remote doesn't care **who** executes — it just **sends** the request!

✅ **Remote Control = Invoker**, **Devices = Receivers**, **Button Click = Command**.

---

# 🎯 4. Core Concepts:

| Term | Meaning |
|:---|:---|
| Command | Interface declaring an action (`execute()` method). |
| ConcreteCommand | Implements Command to perform real action. |
| Receiver | Knows how to perform the operation. |
| Invoker | Asks the command to carry out the request. |
| Client | Creates and configures commands and receivers. |

---

# 🔥 5. Basic Flow

1. Define a **Command interface** with an `execute()` method.
2. Create **Concrete Command** classes implementing `Command`.
3. Create a **Receiver** class that does real work.
4. Create an **Invoker** that triggers commands.
5. **Client** wires it all together.

---

# 🛠️ 6. Simple Example in Java

---
## Step 1: Command Interface

```java
public interface Command {
    void execute();
}
```

---
## Step 2: Receiver Class

```java
public class Light {
    public void turnOn() {
        System.out.println("Light is ON");
    }
    public void turnOff() {
        System.out.println("Light is OFF");
    }
}
```

---
## Step 3: Concrete Command Classes

```java
public class LightOnCommand implements Command {
    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOn();
    }
}
```

```java
public class LightOffCommand implements Command {
    private Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOff();
    }
}
```

---
## Step 4: Invoker Class

```java
public class RemoteControl {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void pressButton() {
        command.execute();
    }
}
```

---
## Step 5: Client (Main)

```java
public class Main {
    public static void main(String[] args) {
        Light light = new Light();

        Command lightOn = new LightOnCommand(light);
        Command lightOff = new LightOffCommand(light);

        RemoteControl remote = new RemoteControl();

        // Turn ON the light
        remote.setCommand(lightOn);
        remote.pressButton();

        // Turn OFF the light
        remote.setCommand(lightOff);
        remote.pressButton();
    }
}
```

---

# ✅ Output:

```
Light is ON
Light is OFF
```

---

# 🧠 7. Memory Trick

> "**Command Pattern** = Turn action into an object."

---

# ✨ 8. When to Use the Command Pattern?

- When you need **undo/redo** operations.
- When you need to **queue** or **log** requests.
- When you want to **parameterize** an object with operations.
- When you want to **decouple** the sender and receiver.

---

# ⚡ 9. Advanced Concepts

✅ **Undo functionality**:  
Commands can store previous state and implement an `undo()` method.

✅ **Macro Commands**:  
You can create a command that runs multiple commands together (like a "party mode" — turn on AC + Music + Lights).

✅ **Composite Commands**:  
Commands inside commands.

✅ **Delayed Execution**:  
You can store commands and run them later (Job Scheduling).

✅ **Transactional behavior**:  
If one command fails, you can rollback (undo previous ones).

---

# 🔥 Real-World Examples

| System | Command Usage |
|:---|:---|
| GUI Buttons | Clicking button triggers Command |
| Task Queues | Jobs as Commands |
| Text Editors | Undo/Redo Commands |
| Home Automation Systems | Remote sends commands to devices |

---

# 🚦 Visual Flow:

```
Client → creates → ConcreteCommand → calls → Receiver
Invoker → triggers → Command.execute()
```

---

# 🎯 One Line Summary:

> "**Command Pattern** says: wrap the action into an object, and run it whenever you want!"