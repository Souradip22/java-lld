# Singleton Pattern

## Definition
A Singleton class is a class that allows only one instance of itself to be created and provides a global point of access to that instance.

## When to Use Singleton
- When you need exactly one instance of a class to control access to shared resources (e.g., configuration settings, logging, etc.).
- When the single instance should be extensible by subclassing and clients should be able to use an extended instance without modifying their code.

## Different Ways to Create a Singleton Class
1. [Eager Initialization](#eager-initialization)
2. [Lazy Initialization](#lazy-initialization)
3. [Synchronized Method](#synchronized-method)
4. [Double Check Locking](#double-check-locking)
5. [Bill Pugh Singleton](#bill-pugh-singleton)
6. [Enum Singleton](#enum-singleton)

### 1. Eager Initialization
<a name="eager-initialization"></a>
The instance of the class is created at the time of class loading.

**Pros**:
- Simple to implement.
- Thread-safe: The JVM handles the class loading and initialization in a thread-safe manner.

**Cons**:
- Instance is created even if it may not be used, which might lead to resource wastage.

**Example**:
```java
public class EagerSingleton {
    private static final EagerSingleton INSTANCE = new EagerSingleton();

    private EagerSingleton() {
        // private constructor to prevent instantiation
    }

    public static EagerSingleton getInstance() {
        return INSTANCE;
    }
}
```

### 2. Lazy Initialization
<a name="lazy-initialization"></a>
The instance of the class is created only when it is needed.

**Pros**:
- Instance is created only if needed, which can save resources.
- Simple to implement.

**Cons**:
- Not thread-safe: Multiple threads could create multiple instances if they access the `getInstance` method simultaneously.

**Example**:
```java
public class LazySingleton {
    private static LazySingleton instance;

    private LazySingleton() {
        // private constructor to prevent instantiation
    }

    public static LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }
}
```

### 3. Synchronized Method
<a name="synchronized-method"></a>
A thread-safe version of the lazy initialization where the `getInstance` method is synchronized.

**Pros**:
- Thread-safe: Ensures that only one instance is created even if multiple threads access the `getInstance` method simultaneously.

**Cons**:
- Reduced performance due to synchronization overhead, especially if the `getInstance` method is called frequently.

**Example**:
```java
public class SynchronizedSingleton {
    private static SynchronizedSingleton instance;

    private SynchronizedSingleton() {
        // private constructor to prevent instantiation
    }

    public static synchronized SynchronizedSingleton getInstance() {
        if (instance == null) {
            instance = new SynchronizedSingleton();
        }
        return instance;
    }
}
```

### 4. Double Check Locking (Mostly used)
<a name="double-check-locking"></a>
Reduces the overhead of acquiring a lock by first checking if the instance is already created before acquiring the lock. This ensures that synchronization is used only when necessary.

**Pros**:
- Thread-safe: Ensures that only one instance is created even if multiple threads access the `getInstance` method simultaneously.
- Better performance than the synchronized method because synchronization is used only when the instance is being created.

**Cons**:
- Complex implementation: More code and potential for errors compared to simpler methods.

**Why `volatile`?**
- The `volatile` keyword ensures that multiple threads handle the `instance` variable correctly when it is being initialized to the `DoubleCheckLockingSingleton` instance. Without `volatile`, it's possible for the `instance` variable to be in an inconsistent state, leading to subtle and hard-to-find bugs.
- In a multi-core CPU environment, each core has its own cache. Without `volatile`, there's a risk that one thread might update the `instance` variable in its local cache, while another thread reads it from main memory, resulting in inconsistent values between threads.
- `volatile` ensures that the `instance` variable is always read from main memory, bypassing the local cache of each core, thus guaranteeing that all threads see the most up-to-date value.

**Code Example**:
```java
public class DoubleCheckLockingSingleton {
    private static volatile DoubleCheckLockingSingleton instance;

    private DoubleCheckLockingSingleton() {
        // private constructor to prevent instantiation
    }

    public static DoubleCheckLockingSingleton getInstance() {
        if (instance == null) {
            synchronized (DoubleCheckLockingSingleton.class) {
                if (instance == null) {
                    instance = new DoubleCheckLockingSingleton();
                }
            }
        }
        return instance;
    }
}
```

### 5. Bill Pugh Singleton
<a name="bill-pugh-singleton"></a>
Utilizes a static inner helper class to create the instance of the Singleton class. This approach ensures lazy initialization with thread safety and without the need for explicit synchronization.

**Pros**:
- Thread-safe: The inner static class is not loaded until it is referenced, ensuring lazy initialization in a thread-safe manner.
- Lazy initialization: The singleton instance is created only when `getInstance` is called for the first time, avoiding unnecessary resource consumption.
- No synchronization overhead: The initialization-on-demand holder idiom ensures that synchronization is used only when necessary.

**Cons**:
- None significant.

**Example**:
```java
public class BillPughSingleton {
    private BillPughSingleton() {
        // private constructor to prevent instantiation
    }

    private static class SingletonHelper {
        private static final BillPughSingleton INSTANCE = new BillPughSingleton();
    }

    public static BillPughSingleton getInstance() {
        return SingletonHelper.INSTANCE;
    }
}
```
### 6. Enum Singleton
Implements the Singleton pattern using an enum type. Enums in Java are inherently singleton by design.
<a name="enum-singleton"></a>
**Pros**:
- Simple to implement: Enums in Java guarantee that only one instance of each enum constant exists.
- Thread-safe: Enums are inherently thread-safe due to the way they are implemented by the JVM.
- Serialization by default: Enums provide serialization out of the box, ensuring that the singleton instance is preserved across serialization and deserialization.

**Cons**:
- Not flexible for inheritance: Enums cannot be extended, which might be a limitation in certain scenarios.

**Example**:
```java
public enum EnumSingleton {
    INSTANCE;

    // Additional methods and fields can be added here

    // Example method
    public void doSomething() {
        // Your logic here
    }
}
```
