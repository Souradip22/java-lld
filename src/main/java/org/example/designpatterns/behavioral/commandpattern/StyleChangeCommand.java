package org.example.designpatterns.behavioral.commandpattern;

public class StyleChangeCommand implements Command{
    private TextEditor textEditor;
    public StyleChangeCommand(TextEditor textEditor) {
        this.textEditor = textEditor;
    }
    @Override
    public void execute() {
        textEditor.changeStyle();
    }
}
