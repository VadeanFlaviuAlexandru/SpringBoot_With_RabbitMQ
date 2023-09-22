package net.javaguides.springboot.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.queue.name}")
    private String queue;
    @Value("${rabbitmq.exchange.name}")
    private String exchange;
    @Value("${rabbitmq.routing_key.name}")
    private String routing_key;
    @Value("${rabbitmq.queue_json.name}")
    private String queue_json;
    @Value("${rabbitmq.routing_key_json.name}")
    private String routing_key_json;

    //------------------------------------------spring bean for rabbitMQ queue
    @Bean
    public Queue queue() {
        return new Queue(queue);
    }

    @Bean
    public Queue jsonQueue() {
        return new Queue(queue_json);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(exchange);
    }

    //------------------------------------------binding between queue and exchange using routing key
    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(exchange()).with(routing_key);
    }

    @Bean
    public Binding binding_json() {
        return BindingBuilder.bind(jsonQueue()).to(exchange()).with(routing_key_json);
    }

    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
    //to remember, springboot will automatically configure these three:
    //ConnectionFactory
    //RabbitTemplate
    //RabbitAdmin
}
