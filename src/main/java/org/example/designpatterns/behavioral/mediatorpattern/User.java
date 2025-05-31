package org.example.designpatterns.behavioral.mediatorpattern;

public class User {

    private String name;
    private ChatMediator chatRoom;

    public User(String name, ChatMediator chatRoom) {
        this.name = name;
        this.chatRoom = chatRoom;
        this.chatRoom.registerUser(this); // auto-register
    }

    public void sendMessage(String msg){
        chatRoom.sendMessage(msg, this);
    }

    public void receiveMessage(String message, User sender){
        System.out.println("[" + this.name + "] received msg '" + message +
                "' from [" + sender.name +"]" );
    }
}
