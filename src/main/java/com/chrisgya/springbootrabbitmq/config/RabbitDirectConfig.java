package com.chrisgya.springbootrabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitDirectConfig {

    public static final String DIRECT_EXCHANGE_NAME = "my-direct-exchange";
    public static final String DIRECT_MARKETING_QUEUE = "directMarketingQueue";
    public static final String DIRECT_FINANCE_QUEUE = "directFinanceQueue";
    public static final String DIRECT_ADMIN_QUEUE = "directAdminQueue";
    public static final String MARKETING_ROUTING_KEY = "marketing";
    public static final String FINANCE_ROUTING_KEY = "finance";
    public static final String ADMIN_ROUTING_KEY = "admin";

    @Bean
    Queue marketingQueue() {
        return new Queue(DIRECT_MARKETING_QUEUE, false);
    }

    @Bean
    Queue financeQueue() {
        return new Queue(DIRECT_FINANCE_QUEUE, false);
    }

    @Bean
    Queue adminQueue() {
        return new Queue(DIRECT_ADMIN_QUEUE, false);
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(DIRECT_EXCHANGE_NAME);
    }

    @Bean
    Binding marketingBinding(Queue marketingQueue, DirectExchange exchange) {
        return BindingBuilder.bind(marketingQueue).to(exchange).with(MARKETING_ROUTING_KEY);
    }

    @Bean
    Binding financeBinding(Queue financeQueue, DirectExchange exchange) {
        return BindingBuilder.bind(financeQueue).to(exchange).with(FINANCE_ROUTING_KEY);
    }

    @Bean
    Binding adminBinding(Queue adminQueue, DirectExchange exchange) {
        return BindingBuilder.bind(adminQueue).to(exchange).with(ADMIN_ROUTING_KEY);
    }

}
