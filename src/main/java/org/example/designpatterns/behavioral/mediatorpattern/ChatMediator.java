package org.example.designpatterns.behavioral.mediatorpattern;

public interface ChatMediator {
    void registerUser(User user);
    void sendMessage(String message, User sender);
}
