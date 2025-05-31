package org.example.designpatterns.behavioral.mediatorpattern;

public class Main {
    public static void main(String[] args) {
        ChatMediator chatRoom = new ChatRoom();
        User john = new User("John", chatRoom);
        User alice = new User("Alice", chatRoom);
        User bob = new User("Bob", chatRoom);

        alice.sendMessage("Hello everyone!");
        bob.sendMessage("Hi Alice!");
    }
}
