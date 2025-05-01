package org.example.designpatterns.behavioral.commandpattern;

public class BoldTextCommand implements Command{

    private TextEditor textEditor;
    public BoldTextCommand(TextEditor textEditor) {
        this.textEditor = textEditor;
    }
    @Override
    public void execute() {
        textEditor.boldText();
    }
}
