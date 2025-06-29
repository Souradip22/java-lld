package org.example.projects.pubsubsystem;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Broker {
    private Map<String, Topic> topicMap;
    private final Dispatcher dispatcher;

    public Broker() {
        this.topicMap = new ConcurrentHashMap<>();
        this.dispatcher = new Dispatcher();
    }

    public void addTopic(Topic topic){
        topicMap.putIfAbsent(topic.getName(), topic);
    }
    public void removeTopic(String name){
        if (topicMap.containsKey(name)){
            topicMap.remove(name);
        } else {
            System.out.println("Invalid topic name, cannot remove: " + name);
        }
    }
    public void addConsumer(String topicName, Consumer consumer){
        if (topicMap.containsKey(topicName)){
            topicMap.get(topicName).addConsumer(consumer);
        } else {
            System.out.println("Topic name is not present, unable to ADD " +
                    "consumer from topic: " + topicName);
        }
    }
    public void removeConsumer(String topicName, Consumer consumer){
        if (topicMap.containsKey(topicName)){
            topicMap.get(topicName).removeConsumer(consumer);
        } else {
            System.out.println("Topic name is not present, unable to REMOVE " +
                    "consumer from topic: " + topicName);
        }
    }

    public void publishMessage(String topicName, Message message){
        if (topicMap.containsKey(topicName)){
            topicMap.get(topicName).getConsumers().stream()
                    .forEach(consumer -> {
                        Dispatcher.dispatch(consumer, message);
            });

        } else {
            System.out.println("Topic name is not present, unable to PUBLISH " +
                    "message from topic: " + topicName);
        }
    }
}
