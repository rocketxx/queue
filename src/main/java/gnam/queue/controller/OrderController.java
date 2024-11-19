package gnam.queue.controller;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import gnam.queue.model.Order;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final RabbitTemplate rabbitTemplate;

    // Iniezione di RabbitTemplate per inviare il messaggio alla coda RabbitMQ
    @Autowired
    public OrderController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping("/create")
    public String createOrder(@RequestBody Order order) {
        // Invia l'oggetto Order alla coda 'order-queue'
        rabbitTemplate.convertAndSend("order-queue", order);
        return "Ordine inviato con successo!";
    }
}
