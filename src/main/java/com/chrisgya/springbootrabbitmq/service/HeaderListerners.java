package com.chrisgya.springbootrabbitmq.service;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.chrisgya.springbootrabbitmq.config.RabbitHeaderConfig.HEADER_ADMIN_QUEUE;

@Component
public class HeaderListerners {

    @RabbitListener(queues = HEADER_ADMIN_QUEUE)
    public void headerListen(Message message) {
        System.out.println("Received from header: " + message.getBody());
    }


}
