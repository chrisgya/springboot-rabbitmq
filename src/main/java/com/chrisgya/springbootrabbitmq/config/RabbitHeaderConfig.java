package com.chrisgya.springbootrabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitHeaderConfig {

    public static final String HEADER_EXCHANGE_NAME = "my-header-exchange";
    public static final String HEADER_MARKETING_QUEUE = "headerMarketingQueue";
    public static final String HEADER_FINANCE_QUEUE = "headerFinanceQueue";
    public static final String HEADER_ADMIN_QUEUE = "headerAdminQueue";
    public static final String HEADER_MARKETING_ROUTING_KEY = "headerMarketing";
    public static final String HEADER_KEY = "department";
    public static final String HEADER_FINANCE_ROUTING_KEY = "headerFinance";
    public static final String HEADER_ADMIN_ROUTING_KEY = "headerAdmin";

    @Bean
    Queue headerMarketingQueue() {
        return new Queue(HEADER_MARKETING_QUEUE, false);
    }

    @Bean
    Queue headerFinanceQueue() {
        return new Queue(HEADER_FINANCE_QUEUE, false);
    }

    @Bean
    Queue headerAdminQueue() {
        return new Queue(HEADER_ADMIN_QUEUE, false);
    }

    @Bean
    HeadersExchange headerExchange() {
        return new HeadersExchange(HEADER_EXCHANGE_NAME);
    }

    @Bean
    Binding headerMarketingBinding(Queue headerMarketingQueue, HeadersExchange headerExchange) {
        return BindingBuilder.bind(headerMarketingQueue).to(headerExchange).where(HEADER_KEY).matches(HEADER_MARKETING_ROUTING_KEY);
    }

    @Bean
    Binding headerFinanceBinding(Queue headerFinanceQueue, HeadersExchange headerExchange) {
        return BindingBuilder.bind(headerFinanceQueue).to(headerExchange).where(HEADER_KEY).matches(HEADER_FINANCE_ROUTING_KEY);
    }

    @Bean
    Binding headerAdminBinding(Queue headerAdminQueue, HeadersExchange headerExchange) {
        return BindingBuilder.bind(headerAdminQueue).to(headerExchange).where(HEADER_KEY).matches(HEADER_ADMIN_ROUTING_KEY);
    }

}
