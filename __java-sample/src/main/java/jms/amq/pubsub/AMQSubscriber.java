package jms.amq.pubsub;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Intellij IDEA.
 * Author: Eric Cui
 * Date  : 2017/9/7 22:50
 * Desc  : 描述信息
 */
public class AMQSubscriber {

    private static ExecutorService executor = Executors.newSingleThreadExecutor();

    public static void main(String[] args) {
        executor.submit(new Subscriber());
        new Thread(new Subscriber(), "subscriber-1").start();
    }

    static class Subscriber implements Runnable {
        @Override
        public void run() {
            ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
            TopicConnection connection = null;
            TopicSession session = null;
            final String threadName = Thread.currentThread().getName();
            try {
                connection = factory.createTopicConnection();
                connection.start();
                session = connection.createTopicSession(Boolean.FALSE, DeliveryMode.NON_PERSISTENT);

                Topic topic = session.createTopic("topic_1");
                TopicSubscriber subscriber = session.createSubscriber(topic);
                subscriber.setMessageListener(message -> {
                    if (message instanceof TextMessage) {
                        try {
                            System.out.println(
                                    threadName +
                                            " receive: " +
                                            ((TextMessage) message).getText());
                        } catch (JMSException e) {
                            e.printStackTrace();
                        }
                    }
                });
                // subscriber.close();
                // session.close();
                // connection.stop();
            } catch (JMSException e) {
                e.printStackTrace();
            // } finally {
                // try {
                //     if (session != null) {
                //         session.close();
                //         connection.close();
                //     }
                // } catch (JMSException e) {
                //     e.printStackTrace();
                // }
            }
        }
    }
}
