# 🧠 Step 1: What is the Memento Pattern?

- **Memento Pattern** is about **saving and restoring** an object's **state**.
- Think of it like "**Undo**" in Word or any Editor.

You can **save** the current work (state) and **restore** it later if needed.

---

# ✏️ Step 2: Real-Life Example

Imagine:

- You are writing a document.
- You save it after typing **"Hello"**.
- Then you type **"World"**.
- Now you want to **undo** and go back to only **"Hello"**.

👉 Saving the document = **Creating a Memento**.  
👉 Undoing = **Restoring from a Memento**.

---

# 🏗️ Step 3: Structure of Memento Pattern

There are **three players**:

| Component | Role |
|:---|:---|
| Originator | The main object whose state needs to be saved. |
| Memento | A snapshot of the Originator's internal state. |
| Caretaker | Manages the saved Mementos (but cannot change them). |

---

# 📦 Step 4: Basic Code Example (Real quick)

Let's say we have a **TextEditor**:

### 1. Memento Class (only stores state)

```java
package org.example.designpatterns.behavioral.mementopattern;

//Memento Class (only stores state)
public class TextEditorMemento {
    private final String content;

    public TextEditorMemento(String content) {
        this.content = content;
    }
    public String getContent(){
        return content;
    }

}

```

---

### 2. Originator Class (creates and restores Memento)

```java
package org.example.designpatterns.behavioral.mementopattern;

//Originator Class (creates and restores Memento)
public class TextEditor {
    private String content = "";
    public void type(String word) {
        content += word;
    }
    public String getContent(){
        return content;
    }
    public TextEditorMemento save(){
        return new TextEditorMemento(content);
    }
    public void restore(TextEditorMemento textEditorMemento){
        content = textEditorMemento.getContent();
    }
}
```

---

### 3. Caretaker Class (manages Mementos)

```java
package org.example.designpatterns.behavioral.mementopattern;

import java.util.Stack;

//Caretaker Class (manages Mementos)
public class TextEditorMementoManager {
    private Stack<TextEditorMemento> history = new Stack<>();
    public void save(TextEditor textEditor){
        history.push(textEditor.save());
    }
    public void undo(TextEditor textEditor){
        if (!history.isEmpty()){
            history.pop();
            if (!history.isEmpty()){
                textEditor.restore(history.peek());
            }
        }
    }
}

```

---

# 👨‍💻 Step 5: Using the Memento Pattern

```java
package org.example.designpatterns.behavioral.mementopattern;

public class MementoTest {
    public static void main(String[] args) {
        TextEditor editor = new TextEditor();
        TextEditorMementoManager manager = new TextEditorMementoManager();

        manager.save(editor); // Save ""

        editor.type("Hello ");
        manager.save(editor);  // Save after typing Hello

        editor.type("World!");
        manager.save(editor); // save after typing Hello World!


        manager.undo(editor);  // Undo World!, back to Hello
        System.out.println("OUTPUT: "+ editor.getContent());

        editor.type(" Happy");
        manager.save(editor); //save after Hello Happy

        editor.type(" Weekend");
        manager.save(editor); //save after Hello Happy Weekend

        manager.undo(editor);  // Undo Weekend, back to "Hello Happy Hello"
        System.out.println("OUTPUT: "+ editor.getContent());


    }
}

```

---

# 🎯 Step 6: When to Use Memento Pattern?

✅ You want to **implement Undo/Redo** functionality.  
✅ You want to **backup and restore** object's state without exposing internal details.  
✅ You want to **save states periodically** (like game save points).

---

# 🔥 Step 7: Advanced Concept — Multiple Mementos

In real-world apps (like MS Word), you don't have just **one** undo.  
You have a **history** of many undo levels.

Instead of one memento, **store a list**:

```java
Stack<TextEditorMemento> history = new Stack<>();
```

Every time you `save()`, push to the stack.  
Every time you `undo()`, pop from the stack.

✅ Simple but powerful.

---

# 🧹 Step 8: Important Tips for Interview Memory

- **Originator** knows **how** to save and restore.
- **Memento** holds the saved **state** safely.
- **Caretaker** just **stores** and **provides** Mementos (no touching inside).

✅ *Save and Restore without breaking encapsulation.*  
✅ *Caretaker cannot modify the Memento.*

---

# 📝 Final 10-second Summary

> **Memento Pattern = Save + Undo mechanism by capturing object's internal state without exposing its details.**
