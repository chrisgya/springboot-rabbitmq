package com.chrisgya.springbootrabbitmq.service;

import com.chrisgya.springbootrabbitmq.model.Employee;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.chrisgya.springbootrabbitmq.config.RabbitDirectConfig.*;

@Component
public class DirectListerners {

    @RabbitListener(queues = DIRECT_FINANCE_QUEUE)
    public void listen(String in) {
        System.out.println("Message read from DIRECT financeQueue : " + in);
    }

    @RabbitListener(queues = DIRECT_MARKETING_QUEUE)
    public void listen1(String in) {
        System.out.println("Message read from DIRECT marketingQueue : " + in);
    }

    @RabbitListener(queues = DIRECT_ADMIN_QUEUE)
    public void listen2(Employee e) {
        System.out.println(String.format("Message read from DIRECT adminQueue : %s, %s, %d", e.getFirstName(), e.getLastName(), e.getAge()));
    }

}
