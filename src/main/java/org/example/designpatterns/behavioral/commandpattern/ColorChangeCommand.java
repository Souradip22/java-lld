package org.example.designpatterns.behavioral.commandpattern;

public class ColorChangeCommand implements Command{
    private TextEditor textEditor;
    public ColorChangeCommand(TextEditor textEditor) {
        this.textEditor = textEditor;
    }
    @Override
    public void execute() {
        textEditor.changeColor();
    }
}
