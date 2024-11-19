package gnam.queue.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import gnam.queue.model.Order;

@Component
public class OrderConsumer {
    
    @RabbitListener(queues = "order-queue")
    public void receiveOrder(Order order) {
        // Elabora l'ordine
        System.out.println("Ordine ricevuto: " + order);
    }
}
