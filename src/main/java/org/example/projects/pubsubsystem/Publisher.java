package org.example.projects.pubsubsystem;

public interface Publisher {
    void publish(String topicName, Message message);
}
