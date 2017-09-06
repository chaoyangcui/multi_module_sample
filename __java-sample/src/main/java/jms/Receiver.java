package jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * Created by Intellij IDEA.
 * Author: Eric Cui
 * Date  : 2017/9/4 22:45
 * Desc  : 描述信息
 */
@Component
public class Receiver {

    @JmsListener(destination = "mailbox", containerFactory = "jmsListenerContainerFactory")
    public void receiveMessage(Email email) {
        System.out.println("Received <" + email + ">");
    }

}
