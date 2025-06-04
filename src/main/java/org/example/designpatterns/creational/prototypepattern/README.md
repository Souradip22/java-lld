## 🧠 **What is the Prototype Pattern?**

The **Prototype Pattern** is a **creational design pattern** that allows you to **clone** existing objects **without coupling** your code to their class.

> It helps when object creation is expensive or complex, and we want to **duplicate** objects instead of creating new ones from scratch.

---

## 🔴 **Step 1: The Problem (Without Prototype Pattern)**

### 🔧 Scenario:

Suppose you're creating a `Document` object in a design software, where creating a new document involves setting up a lot of metadata, loading templates, setting permissions, etc.

Each time a user clicks **"New from Template"**, you create a new instance from scratch.

```java
class Document {
    String type;
    String content;
    String metadata;

    public Document(String type) {
        this.type = type;
        this.content = "Default content for " + type;
        this.metadata = loadMetadataFromServer(type); // Expensive
    }

    private String loadMetadataFromServer(String type) {
        // Simulate costly operation
        try { Thread.sleep(2000); } catch (InterruptedException e) {}
        return "Metadata for " + type;
    }
}
```

```java
public class DocumentEditor {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Document doc1 = new Document("Invoice");
        Document doc2 = new Document("Invoice");
        long end = System.currentTimeMillis();
        System.out.println("Time: " + (end - start) + "ms");
    }
}
```

### ❌ **Issues:**

* Each time you create a new document, it performs **heavy setup**.
* Wastes resources and time.
* Difficult to maintain when config is complex.

---

## ✅ **Step 2: Solution with Prototype Pattern**

Let’s **clone** an existing object instead of building it from scratch.

---

## 🔑 **Core Concept**

1. Use a `clone()` method (via Java’s `Cloneable` or custom).
2. Store "prototypes" of common objects.
3. Create new objects by copying (not instantiating).

---

## 🛠️ **Step 3: Implementing the Prototype Pattern**

### 🧱 1. Make your class implement `Cloneable`

```java
class Document implements Cloneable {
    String type;
    String content;
    String metadata;

    public Document(String type) {
        this.type = type;
        this.content = "Default content for " + type;
        this.metadata = loadMetadataFromServer(type);
    }

    private String loadMetadataFromServer(String type) {
        try { Thread.sleep(2000); } catch (InterruptedException e) {}
        return "Metadata for " + type;
    }

    public Document clone() {
        try {
            return (Document) super.clone(); // shallow copy
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
```

### 🧱 2. Create a Registry to Store Prototypes

```java
class DocumentRegistry {
    private static Map<String, Document> prototypes = new HashMap<>();

    public static void loadPrototypes() {
        Document invoice = new Document("Invoice");
        prototypes.put("Invoice", invoice);
    }

    public static Document getClonedDocument(String type) {
        return prototypes.get(type).clone();
    }
}
```

### 🧱 3. Use it in Client Code

```java
public class DocumentEditor {
    public static void main(String[] args) {
        DocumentRegistry.loadPrototypes();

        long start = System.currentTimeMillis();
        Document doc1 = DocumentRegistry.getClonedDocument("Invoice");
        Document doc2 = DocumentRegistry.getClonedDocument("Invoice");
        long end = System.currentTimeMillis();
        System.out.println("Time: " + (end - start) + "ms");
    }
}
```

### ✅ Output

Fast cloning since setup happens only once.

---

## ⚙️ **Deep Copy vs Shallow Copy**

* **Shallow Copy**: Default clone, references same sub-objects.
* **Deep Copy**: Create new copies of nested objects.

### 🧪 Example: Deep Cloning Manually

```java
@Override
public Document clone() {
    Document clone = new Document();
    clone.type = this.type;
    clone.content = new String(this.content); // new object
    clone.metadata = new String(this.metadata); // simulate deep copy
    return clone;
}
```

---

## 💼 **Real-Life Use Cases**

| Use Case                  | Why Prototype Helps                                        |
| ------------------------- | ---------------------------------------------------------- |
| UI Builders               | Duplicate buttons, panels, and widgets from templates      |
| Game Dev                  | Clone enemies, characters, and levels efficiently          |
| Cloud Config              | Create server config objects without rebuilding every time |
| Document/Template Editing | Clone letterheads, invoices, resumes                       |

---

## 🧠 **When to Use Prototype Pattern**

✅ Use when:

* Object creation is **expensive or slow**.
* You want to **decouple** code from object construction logic.
* You need **many similar objects** with minor variations.

❌ Avoid when:

* Objects are **simple** to construct.
* Objects have **complex references** and deep copy is too costly.

---

## 🧾 Summary

| Feature    | Prototype Pattern                     |
| ---------- | ------------------------------------- |
| Type       | Creational                            |
| Intent     | Clone existing objects                |
| Benefit    | Faster, avoids complex instantiation  |
| Core Idea  | `.clone()` instead of `new`           |
| Real World | Templates, editors, cloud deployments |
