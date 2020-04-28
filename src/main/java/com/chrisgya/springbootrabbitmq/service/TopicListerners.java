package com.chrisgya.springbootrabbitmq.service;

import com.chrisgya.springbootrabbitmq.model.Employee;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.chrisgya.springbootrabbitmq.config.RabbitTopicConfig.*;

@Component
public class TopicListerners {

    @RabbitListener(queues = TOPIC_FINANCE_QUEUE)
    public void listen(String in) {
        System.out.println("Message read from TOPIC financeQueue : " + in);
    }

    @RabbitListener(queues = TOPIC_MARKETING_QUEUE)
    public void listen1(String in) {
        System.out.println("Message read from TOPIC marketingQueue : " + in);
    }

    @RabbitListener(queues = TOPIC_ADMIN_QUEUE)
    public void listen2(Employee e) {
        System.out.println(String.format("Message read from TOPIC adminQueue : %s, %s, %d", e.getFirstName(), e.getLastName(), e.getAge()));
    }

    @RabbitListener(queues = TOPIC_ALL_QUEUE)
    public void listenAll(Object e) {
        if(e instanceof Employee){
            Employee emp = (Employee) e;
            System.out.println(String.format("Message read from TOPIC allQueue : %s, %s, %d", emp.getFirstName(), emp.getLastName(), emp.getAge()));
        } else {
            System.out.println("Message read from TOPIC allQueue : " + e);
        }
    }

}
