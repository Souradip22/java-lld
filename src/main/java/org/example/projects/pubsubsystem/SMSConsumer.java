package org.example.projects.pubsubsystem;

public class SMSConsumer implements Consumer{
    private String name;

    public SMSConsumer(String name) {
        this.name = name;
    }

    @Override
    public void consume(Message message) {
        System.out.println("SMSConsumer ["+ name +"] consumed message: " + message.getPayload()+ " by thread: " + Thread.currentThread().getName());
    }
}
