package org.example.projects.pubsubsystem;

public class EmailConsumer implements Consumer{
    private String name;

    public EmailConsumer(String name) {
        this.name = name;
    }

    @Override
    public void consume(Message message) {
        System.out.println("EmailConsumer ["+ name +"] consumed message: "
            + message.getPayload() + " by thread: " + Thread.currentThread().getName());
    }
}
