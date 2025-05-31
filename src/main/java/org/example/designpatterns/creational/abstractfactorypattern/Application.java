package org.example.designpatterns.creational.abstractfactorypattern;

public class Application {
    GUIFactory guiFactory;
    Button button;
    CheckBox checkBox;

    public Application(GUIFactory guiFactory) {
        this.guiFactory = guiFactory;
        this.button = this.guiFactory.createButton();
        this.checkBox = this.guiFactory.createCheckBox();

    }

    public void renderUI(){
        button.addButton();
        checkBox.addCheckBox();
    }

}
