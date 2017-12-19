package jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * Created by Intellij IDEA.
 * Date  : 2017/9/4 22:45
 * @author Eric Cui
 * Desc  : 描述信息
 */
@Component
public class Receiver {

    @JmsListener(destination = "mailbox", containerFactory = "jmsListenerContainerFactory")
    public void receiveMessage(Email email) {
        System.out.println("Received <" + email + ">");
    }

}
