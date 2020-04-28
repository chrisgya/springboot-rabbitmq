package com.chrisgya.springbootrabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitFanoutConfig {

    public static final String FANOUT_EXCHANGE_NAME = "my-fanout-exchange";
    public static final String FANOUT_MARKETING_QUEUE = "fanoutMarketingQueue";
    public static final String FANOUT_FINANCE_QUEUE = "fanoutFinanceQueue";
    public static final String FANOUT_ADMIN_QUEUE = "fanoutAdminQueue";

    @Bean
    Queue fanoutMarketingQueue() {
        return new Queue(FANOUT_MARKETING_QUEUE, false);
    }

    @Bean
    Queue fanoutFinanceQueue() {
        return new Queue(FANOUT_FINANCE_QUEUE, false);
    }

    @Bean
    Queue fanoutAdminQueue() {
        return new Queue(FANOUT_ADMIN_QUEUE, false);
    }

    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange (FANOUT_EXCHANGE_NAME);
    }

    @Bean
    Binding fanoutMarketingBinding(Queue fanoutMarketingQueue, FanoutExchange  fanoutExchange) {
        return BindingBuilder.bind(fanoutMarketingQueue).to(fanoutExchange);
    }

    @Bean
    Binding fanoutFinanceBinding(Queue fanoutFinanceQueue, FanoutExchange  fanoutExchange) {
        return BindingBuilder.bind(fanoutFinanceQueue).to(fanoutExchange);
    }

    @Bean
    Binding fanoutAdminBinding(Queue fanoutAdminQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutAdminQueue).to(fanoutExchange);
    }

}
