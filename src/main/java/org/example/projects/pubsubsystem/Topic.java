package org.example.projects.pubsubsystem;

import java.util.ArrayList;
import java.util.List;

public class Topic {
    private String name;
    private List<Consumer> consumers;

    public Topic(String name) {
        this.name = name;
        this.consumers = new ArrayList<>();
    }
    public void addConsumer(Consumer consumer){
        consumers.add(consumer);
    }
    public void removeConsumer(Consumer consumer){
        consumers.remove(consumer);
    }

    public String getName() {
        return name;
    }

    public List<Consumer> getConsumers() {
        return consumers;
    }
}
