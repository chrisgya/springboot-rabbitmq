package com.chrisgya.springbootrabbitmq.service;

import com.chrisgya.springbootrabbitmq.model.Employee;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import static com.chrisgya.springbootrabbitmq.config.RabbitDirectConfig.*;
import static com.chrisgya.springbootrabbitmq.config.RabbitFanoutConfig.FANOUT_EXCHANGE_NAME;
import static com.chrisgya.springbootrabbitmq.config.RabbitHeaderConfig.*;
import static com.chrisgya.springbootrabbitmq.config.RabbitTopicConfig.*;

@Component
public class MyRunner implements CommandLineRunner {

    private final RabbitTemplate rabbitTemplate;

    public MyRunner(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void run(String... args) throws Exception {

        //Direct Exchange
        rabbitTemplate.convertAndSend(DIRECT_EXCHANGE_NAME, FINANCE_ROUTING_KEY, "Hello, direct world!");
        rabbitTemplate.convertAndSend(DIRECT_EXCHANGE_NAME, MARKETING_ROUTING_KEY, "Hello, direct world11111!");
        rabbitTemplate.convertAndSend(DIRECT_EXCHANGE_NAME, ADMIN_ROUTING_KEY, new Employee("Isaac", "Doe", 20));

        //one publisher, multiple subscribers
        rabbitTemplate.convertAndSend(FANOUT_EXCHANGE_NAME, "", new Employee("Chris", "mensah", 30));

        //TOPIC
        rabbitTemplate.convertAndSend(TOPIC_EXCHANGE_NAME, TOPIC_FINANCE_ROUTING_KEY, "Hello, direct world!");
        rabbitTemplate.convertAndSend(TOPIC_EXCHANGE_NAME, TOPIC_MARKETING_ROUTING_KEY, "Hello, direct world11111!");
        rabbitTemplate.convertAndSend(TOPIC_EXCHANGE_NAME, TOPIC_ADMIN_ROUTING_KEY, new Employee("Isaac", "Doe", 20));
        rabbitTemplate.convertAndSend(TOPIC_EXCHANGE_NAME, TOPIC_ALL_ROUTING_KEY, new Employee("Mary", "Ekua", 25));

        //HEADER
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setHeader(HEADER_KEY, HEADER_ADMIN_ROUTING_KEY);
        MessageConverter messageConverter = new SimpleMessageConverter();
        Message message = messageConverter.toMessage("Hello message from my header", messageProperties);
        rabbitTemplate.send(HEADER_EXCHANGE_NAME ,"", message);

// sendAndReceive()
    }
}
