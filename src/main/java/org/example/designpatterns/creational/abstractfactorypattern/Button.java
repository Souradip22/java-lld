package org.example.designpatterns.creational.abstractfactorypattern;

interface Button {
    void addButton();
}

class WindowsButton implements Button{
    @Override
    public void addButton() {
        System.out.println("Windows button added");
    }
}

class MacButton implements Button{
    @Override
    public void addButton() {
        System.out.println("MacOS button added");
    }
}
