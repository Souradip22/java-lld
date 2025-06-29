package org.example.projects.pubsubsystem;

public class EmailPublisher implements Publisher{
    private Broker broker;

    public EmailPublisher(Broker broker) {
        this.broker = broker;
    }
    @Override
    public void publish(String topicName, Message message) {
        broker.publishMessage(topicName, message);
    }
}
