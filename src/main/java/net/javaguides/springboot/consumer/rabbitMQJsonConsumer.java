package net.javaguides.springboot.consumer;

import net.javaguides.springboot.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class rabbitMQJsonConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(rabbitMQJsonConsumer.class);

    @RabbitListener(queues={"${rabbitmq.queue_json.name}"})
    public void consumeJsonMessage(User user){
        LOGGER.info(String.format("Received json message -> %s", user.toString()));
    }
}
