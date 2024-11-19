package gnam.queue.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
@Configuration
public class RabbitMQConfig {

    public static final String TEST_QUEUE_NAME = "test-queue";
    public static final String ORDER_QUEUE_NAME = "order-queue";

    @Bean
    public Queue testQueue() {
        return new Queue(TEST_QUEUE_NAME, true); // La coda è persistente
    }

    @Bean
    public Queue orderQueue() {
        return new Queue(ORDER_QUEUE_NAME, true); // La coda è persistente
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {  //ConnectionFactory necessario per connettersi a RabbitMQ. RabbitTemplate Oggetto principale per inviare messaggi a RabbitMQ.
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter(); //Convertitore che trasforma oggetti in JSON
    }
}

