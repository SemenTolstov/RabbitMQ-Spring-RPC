package com.dataService.dataService.config;

import com.dataService.dataService.service.ToAddListener;
import com.dataService.dataService.service.ToGetListener;
import org.apache.log4j.Logger;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableRabbit
@Configuration
public class RabbitConfiguration {

    Logger logger = Logger.getLogger(RabbitConfiguration.class);

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory =
                new CachingConnectionFactory("localhost");
        return connectionFactory;
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory());
    }

    @Bean
    public Queue queueToAdd() {
        return new Queue("adding");
    }

    @Bean
    public Queue queueToGet() {
        return new Queue("getting");
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("exchange");
    }

    @Bean
    public Binding addingBinding() {
        return BindingBuilder.bind(queueToAdd()).to(directExchange()).with("adding");
    }

    @Bean
    public Binding gettingBinding() {
        return BindingBuilder.bind(queueToGet()).to(directExchange()).with("getting");
    }

    @Bean
    MessageListenerAdapter toAddAdapter(ToAddListener listener) {
        return new MessageListenerAdapter(listener, "messageAddListener");
    }

    @Bean
    public SimpleMessageListenerContainer toAddContainer(@Qualifier("toAddAdapter") MessageListenerAdapter messageListenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory());
        container.setQueueNames("adding");
        container.setMessageListener(messageListenerAdapter);
        return container;
    }

    @Bean
    MessageListenerAdapter toGetAdapter(ToGetListener listener) {
        return new MessageListenerAdapter(listener, "messageGetListener");
    }

    @Bean
    public SimpleMessageListenerContainer toGetContainer(@Qualifier("toGetAdapter") MessageListenerAdapter messageListenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory());
        container.setQueueNames("getting");
        container.setMessageListener(messageListenerAdapter);
        return container;
    }

}
