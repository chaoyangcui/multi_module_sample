package jms.amq.pubsub;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by Intellij IDEA.
 * Author: Eric Cui
 * Date  : 2017/9/7 22:42
 * Desc  : 描述信息
 */
public class AMQPublisher {

    public static void main(String[] args) {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
        TopicConnection connection = null;
        TopicSession session = null;
        try {
            connection = factory.createTopicConnection();
            connection.start();
            session = connection.createTopicSession(Boolean.TRUE, DeliveryMode.NON_PERSISTENT);

            Topic topic = session.createTopic("topic_1");
            TopicPublisher publisher = session.createPublisher(topic);
            publisher.publish(session.createTextMessage("a new message."));
            publisher.close();

            session.commit();
        } catch (JMSException e) {
            e.printStackTrace();
        } finally {
            try {
                assert session != null;
                session.close();
                connection.close();
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }

}
