package com.chrisgya.springbootrabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitTopicConfig {

    public static final String TOPIC_EXCHANGE_NAME = "my-topic-exchange";
    public static final String TOPIC_MARKETING_QUEUE = "topicMarketingQueue";
    public static final String TOPIC_FINANCE_QUEUE = "topicFinanceQueue";
    public static final String TOPIC_ADMIN_QUEUE = "topicAdminQueue";
    public static final String TOPIC_ALL_QUEUE = "topicAllQueue";
    public static final String TOPIC_MARKETING_ROUTING_KEY = "topic-queue.marketing";
    public static final String TOPIC_FINANCE_ROUTING_KEY = "topic-queue.finance";
    public static final String TOPIC_ADMIN_ROUTING_KEY = "topic-queue.admin";
    public static final String TOPIC_ALL_ROUTING_KEY = "topic-queue.*";

    @Bean
    Queue topicMarketingQueue() {
        return new Queue(TOPIC_MARKETING_QUEUE, false);
    }

    @Bean
    Queue topicFinanceQueue() {
        return new Queue(TOPIC_FINANCE_QUEUE, false);
    }

    @Bean
    Queue topicAdminQueue() {
        return new Queue(TOPIC_ADMIN_QUEUE, false);
    }

    @Bean
    Queue topicAllQueue() {
        return new Queue(TOPIC_ALL_QUEUE, false);
    }

    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange(TOPIC_EXCHANGE_NAME);
    }

    @Bean
    Binding topicMarketingBinding(Queue topicMarketingQueue, TopicExchange topicExchange) {
        return BindingBuilder.bind(topicMarketingQueue).to(topicExchange).with(TOPIC_MARKETING_ROUTING_KEY);
    }

    @Bean
    Binding topicFinanceBinding(Queue topicFinanceQueue,  TopicExchange topicExchange) {
        return BindingBuilder.bind(topicFinanceQueue).to(topicExchange).with(TOPIC_FINANCE_ROUTING_KEY);
    }

    @Bean
    Binding topicAdminBinding(Queue topicAdminQueue, TopicExchange topicExchange) {
        return BindingBuilder.bind(topicAdminQueue).to(topicExchange).with(TOPIC_ADMIN_ROUTING_KEY);
    }

    @Bean
    Binding topicAllBinding(Queue topicAllQueue, TopicExchange topicExchange) {
        return BindingBuilder.bind(topicAllQueue).to(topicExchange).with(TOPIC_ALL_ROUTING_KEY);
    }

}
