package com.sssarm.service;

import com.sssarm.service.domain.Order;
import com.sssarm.service.domain.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by cuiguiyang on 2017/7/2 14:17.
 * Desc:
 */
public class JMSService {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    JmsTemplate jmsTemplate;

    @JmsListener(destination = "order:new", containerFactory = "orderFactory")
    @Transactional
    public void create(OrderDTO orderDTO) {
        Order order = new Order();//new Order(orderDTO);
        order.setStatus("PENDING");
        orderRepository.save(order);
        jmsTemplate.convertAndSend("order:need_to_pay", order);
    }
}
