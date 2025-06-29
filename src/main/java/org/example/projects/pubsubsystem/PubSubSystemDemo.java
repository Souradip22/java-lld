package org.example.projects.pubsubsystem;

public class PubSubSystemDemo {

    public static void main(String[] args) {
            Broker broker = new Broker();

            Topic topicA = new Topic("TopicA");
            broker.addTopic(topicA);
            Consumer consumer1 = new EmailConsumer("EmailConsumer1");
            Consumer consumer2 = new EmailConsumer("EmailConsumer2");

            broker.addConsumer("TopicA", consumer1);
            broker.addConsumer("TopicA", consumer2);

            Publisher emailPublisher = new EmailPublisher(broker);
            emailPublisher.publish("TopicA", new Message("{EmailPublisher " +
                    "published message in " + topicA.getName() +
                    "}"));

            Topic topicB = new Topic("TopicB");
            broker.addTopic(topicB);

            Consumer consumer3 = new SMSConsumer("SMSConsumer1");
            Consumer consumer4 = new SMSConsumer("SMSConsumer2");
            Consumer consumer5 = new SMSConsumer("SMSConsumer3");

            broker.addConsumer("TopicB", consumer3);
            broker.addConsumer("TopicB", consumer4);
            broker.addConsumer("TopicB", consumer5);

            Publisher smsPublisher = new SMSPublisher(broker);
            smsPublisher.publish("TopicB", new Message("{SmsPublisher " +
                "published message in " + topicB.getName() +
                "}"));
            broker.removeConsumer("TopicB", consumer4);

//            broker.removeTopic("TopicB");
            smsPublisher.publish("TopicB", new Message("{SmsPublisher " +
                    "published message in " + topicB.getName() +
                    "}"));


            Dispatcher.shutdown();



    }
}
