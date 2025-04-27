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
