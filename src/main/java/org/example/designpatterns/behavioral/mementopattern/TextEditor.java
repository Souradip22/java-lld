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
