package com.chrisgya.springbootrabbitmq.service;

import com.chrisgya.springbootrabbitmq.model.Employee;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.chrisgya.springbootrabbitmq.config.RabbitFanoutConfig.*;

@Component
public class FanoutListerners {

    @RabbitListener(queues = FANOUT_FINANCE_QUEUE)
    public void fanoutListenF(Employee e) {
        System.out.println(String.format("Message read from FANOUT_FINANCE_QUEUE : %s, %s, %d", e.getFirstName(), e.getLastName(), e.getAge()));
    }

    @RabbitListener(queues = FANOUT_MARKETING_QUEUE)
    public void fanoutListen(Employee e) {
        System.out.println(String.format("Message read from FANOUT_MARKETING_QUEUE : %s, %s, %d", e.getFirstName(), e.getLastName(), e.getAge()));
    }


    @RabbitListener(queues = FANOUT_ADMIN_QUEUE)
    public void fanoutListen2(Employee e) {
        System.out.println(String.format("Message read from FANOUT_ADMIN_QUEUE : %s, %s, %d", e.getFirstName(), e.getLastName(), e.getAge()));
    }
}
