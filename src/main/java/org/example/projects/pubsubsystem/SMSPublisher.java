package org.example.projects.pubsubsystem;

public class SMSPublisher implements Publisher{
    private Broker broker;

    public SMSPublisher(Broker broker) {
        this.broker = broker;
    }

    @Override
    public void publish(String topicName, Message message) {
        broker.publishMessage(topicName, message);
    }
}
