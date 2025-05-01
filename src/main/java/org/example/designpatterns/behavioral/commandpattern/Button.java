package org.example.designpatterns.behavioral.commandpattern;

public class Button {
    private Command command;
    public void setCommand(Command command) {
        this.command = command;
    }
    public void click(){
        command.execute();
    }
}
