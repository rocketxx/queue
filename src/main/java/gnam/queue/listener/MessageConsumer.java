package gnam.queue.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

    // Il metodo ascolta i messaggi dalla coda 'test-queue'
    @RabbitListener(queues = "test-queue")
    public void receiveMessage(String message) {
        // Stampa il messaggio con la frase "Messaggio ricevuto:"
        System.out.println("Messaggio ricevuto: " + message);
    }
}
