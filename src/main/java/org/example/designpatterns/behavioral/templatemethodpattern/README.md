## 🧠 1. What is the Template Method Pattern?

**Definition**:
> Template Method Pattern defines the **skeleton of an algorithm** in a **base class**, but lets **subclasses override** specific steps **without changing the algorithm’s structure**.

### 🪄 Think of it like:
A recipe where the **steps stay the same**, but **ingredients or exact actions** can change.

---

## 🧩 2. Key Idea

- The **template method** is a method in the **abstract class** that defines **the flow** of the algorithm.
- The **steps** (some abstract, some concrete) are implemented in **subclasses**.

---

## 📦 3. Real-Life Analogy

**Example**: Making tea or coffee  
Steps:
1. Boil water ✅
2. Brew beverage ☕
3. Pour in cup ✅
4. Add condiments (sugar, milk, etc.) 🍯

🔁 You can reuse the flow, but change the **brew** and **addCondiments** part.

---

## 🛠️ 4. Java Example

### Step 1: Abstract Class with Template Method

```java
public abstract class Beverage {
    // Template method (final so subclasses can't override it)
    public final void prepareRecipe() {
        boilWater();
        brew();
        pourInCup();
        addCondiments();
    }

    void boilWater() {
        System.out.println("Boiling water");
    }

    void pourInCup() {
        System.out.println("Pouring into cup");
    }

    abstract void brew();           // Subclass will define
    abstract void addCondiments();  // Subclass will define
}
```

---

### Step 2: Concrete Subclass – Tea

```java
public class Tea extends Beverage {
    @Override
    void brew() {
        System.out.println("Steeping the tea");
    }

    @Override
    void addCondiments() {
        System.out.println("Adding lemon");
    }
}
```

---

### Step 3: Concrete Subclass – Coffee

```java
public class Coffee extends Beverage {
    @Override
    void brew() {
        System.out.println("Dripping coffee through filter");
    }

    @Override
    void addCondiments() {
        System.out.println("Adding sugar and milk");
    }
}
```

---

### Step 4: Client

```java
public class Main {
    public static void main(String[] args) {
        Beverage tea = new Tea();
        tea.prepareRecipe();

        System.out.println("----");

        Beverage coffee = new Coffee();
        coffee.prepareRecipe();
    }
}
```

---

## ✅ Output

```
Boiling water
Steeping the tea
Pouring into cup
Adding lemon
----
Boiling water
Dripping coffee through filter
Pouring into cup
Adding sugar and milk
```

---

## 🎯 5. When to Use Template Method Pattern?

- When you want to **reuse the common workflow**, but **allow steps to vary**.
- When multiple classes follow the **same general logic**, but have some differences in steps.
- In **frameworks** where base classes define the skeleton, and users customize via subclassing.

---

## 🧠 6. Benefits

✅ Promotes **code reuse**  
✅ Enforces **consistent structure**  
✅ Encourages **subclass customization**  
✅ Follows **Hollywood Principle**: "Don’t call us, we’ll call you"

---

## ⚠️ 7. Pitfalls

- Inheritance-based, so can become rigid with deep hierarchies.
- Modifying the algorithm may require changes in the base class.

---

## 🧪 8. Advanced: Hook Methods (Optional Steps)

You can define **"hook" methods** in the base class that **do nothing by default**, and subclasses can override them **if needed**.

```java
boolean customerWantsCondiments() {
    return true; // hook
}

public final void prepareRecipe() {
    boilWater();
    brew();
    pourInCup();
    if (customerWantsCondiments()) {
        addCondiments();
    }
}
```

Subclass can override this to skip condiments:

```java
@Override
boolean customerWantsCondiments() {
    return false; // e.g., black coffee
}
```

---

## 🎓 9. Template Method vs Strategy Pattern

| Template Method | Strategy |
|---|---|
| Uses **inheritance** | Uses **composition** |
| Fixed algorithm in base class | Algorithm varies completely |
| Subclasses override steps | Inject different strategy objects |

---

## 💡 10. Final Tip to Remember

> "Template = **Skeleton + Steps**  
Subclasses fill in the blanks, but **don’t change the outline.**"
