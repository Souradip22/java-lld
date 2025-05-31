package org.example.designpatterns.creational.abstractfactorypattern;

public interface CheckBox {
    void addCheckBox();
}

class WindowsCheckBox implements CheckBox{
    @Override
    public void addCheckBox() {
        System.out.println("Windows CheckBox added");
    }
}

class MacCheckBox implements CheckBox{
    @Override
    public void addCheckBox() {
        System.out.println("MacOs CheckBox added");
    }
}
