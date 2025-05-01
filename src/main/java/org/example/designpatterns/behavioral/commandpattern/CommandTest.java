package org.example.designpatterns.behavioral.commandpattern;

public class CommandTest {
    public static void main(String[] args) {

        TextEditor textEditor = new TextEditor();

        Button btn = new Button();
        btn.setCommand(new BoldTextCommand(textEditor));
        btn.click(); // same button can bold the text

        btn.setCommand(new ColorChangeCommand(textEditor));
        btn.click(); // same button can change color

        btn.setCommand(new StyleChangeCommand(textEditor));
        btn.click(); // same button can change style


    }
}
