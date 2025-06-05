## 🧠 What is the Composite Pattern?

The **Composite Pattern** is a **structural design pattern** used to **treat individual objects and compositions of objects uniformly**.

> "Compose objects into tree structures to represent part-whole hierarchies."

---

## 🔴 Step 1: The Problem Without Composite Pattern

### 📘 Scenario:

You're building a **file explorer** like Windows Explorer or macOS Finder.

You have:

* **Files** (e.g., `.txt`, `.jpg`)
* **Folders** (that can contain files **and** other folders)

Each of them can:

* Show their name
* Show size (files have size, folders sum sizes of contents)

### ❌ Without Composite Pattern

You might create separate logic for file and folder:

```java
class File {
    private String name;
    private int size;

    // getters and display logic
}

class Folder {
    private String name;
    private List<Object> contents; // Object? Bad design

    // loops through contents and calculates size manually
}
```

### ❌ Problems:

* Different handling for files vs folders
* `Folder` needs to know **how to handle different types**
* Violates **Open/Closed Principle**
* No polymorphism → Hard to maintain

---

## ✅ Step 2: Solution With Composite Pattern

We’ll treat **File and Folder** as **composite objects** — both implement a common interface `FileSystemComponent`.

---

## 🔑 Core Concept

| Role          | Responsibility                                                 |
| ------------- | -------------------------------------------------------------- |
| **Component** | Common interface (e.g., `FileSystemComponent`)                 |
| **Leaf**      | Simple objects (e.g., `File`)                                  |
| **Composite** | Composite objects (e.g., `Folder` that contains Files/Folders) |

---

## 🛠️ Step 3: Implementing the Composite Pattern

### 1. Component Interface

```java
public interface FileSystemComponent {
    void showDetails();
    int getSize();
}
```

### 2. Leaf Class

```java
public class File implements FileSystemComponent {
    private String name;
    private int size;

    public File(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public void showDetails() {
        System.out.println("File: " + name + ", Size: " + size);
    }

    public int getSize() {
        return size;
    }
}
```

### 3. Composite Class

```java
public class Folder implements FileSystemComponent {
    private String name;
    private List<FileSystemComponent> children = new ArrayList<>();

    public Folder(String name) {
        this.name = name;
    }

    public void add(FileSystemComponent component) {
        children.add(component);
    }

    public void showDetails() {
        System.out.println("Folder: " + name);
        for (FileSystemComponent child : children) {
            child.showDetails();
        }
    }

    public int getSize() {
        int total = 0;
        for (FileSystemComponent child : children) {
            total += child.getSize();
        }
        return total;
    }
}
```

### 4. Client Code

```java
public class Explorer {
    public static void main(String[] args) {
        File file1 = new File("resume.pdf", 20);
        File file2 = new File("photo.jpg", 50);
        Folder images = new Folder("Images");
        images.add(file2);

        Folder root = new Folder("Documents");
        root.add(file1);
        root.add(images);

        root.showDetails();
        System.out.println("Total size: " + root.getSize() + " KB");
    }
}
```

---

## ✅ Output

```
Folder: Documents
File: resume.pdf, Size: 20
Folder: Images
File: photo.jpg, Size: 50
Total size: 70 KB
```

---

## 🧩 Why Composite is Better

| Without Composite     | With Composite              |
| --------------------- | --------------------------- |
| Manual type checking  | Uniform interface           |
| Duplicated logic      | Polymorphic behavior        |
| Harder to scale       | Easy to nest indefinitely   |
| Violation of SRP, OCP | Adheres to SOLID principles |

---

## 📚 Real-World Use Cases

| Use Case           | Composite Used                              |
| ------------------ | ------------------------------------------- |
| File system        | Folders and files as tree                   |
| HTML DOM           | Tags contain other tags/text                |
| Organization chart | Employees and managers                      |
| UI components      | A container has children and leaf widgets   |
| IDE Outline View   | Methods inside classes, classes in packages |

---

## 🔁 Advanced Features

### ✅ Composite supports:

* Recursive tree structures
* Depth-first traversal
* Dynamic addition/removal of children
* Any number of nested layers

### Example:

```java
Folder grandParent = new Folder("Root");
Folder parent = new Folder("SubFolder");
Folder child = new Folder("SubSubFolder");
child.add(new File("deep.txt", 5));
parent.add(child);
grandParent.add(parent);
grandParent.showDetails();
```

---

## 🧠 When to Use Composite Pattern

| ✅ Use When                                                       | ❌ Avoid When                             |
| ---------------------------------------------------------------- | ---------------------------------------- |
| You need a **tree-like structure**                               | You don’t need nesting                   |
| You want to **treat individual and composite objects uniformly** | Too few object types (only 1 level deep) |
| You want recursive operations                                    | Flat structures with simple logic        |

---

## 🧾 Summary

| Key              | Value                                        |
| ---------------- | -------------------------------------------- |
| Type             | Structural                                   |
| Intent           | Compose objects into tree structures         |
| Pros             | Uniform interface, scalability, elegance     |
| Cons             | May complicate if overused, adds indirection |
| Pattern Keywords | Tree, Part-Whole, Composite-Leaf             |

---

## 🧠 Tips to Remember

* Think: **Folder = Composite**, **File = Leaf**
* Works best for **recursive trees**
* If you’re writing:
  `if (object instanceof File) { ... } else if (object instanceof Folder)`
  → Consider using Composite Pattern!
