package gnam.queue.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

    // Il metodo ascolta i messaggi dalla coda 'test-queue'
    @RabbitListener(queues = "test-queue")
    public void receiveMessage(String message) {
        String transformedMessage = transformMessage(message);
        System.out.println("Messaggio ricevuto: " + transformedMessage);
    }
    private String transformMessage(String message) {
        // Converte la stringa in maiuscolo e aggiunge uno spazio tra ogni lettera
        return message.toUpperCase().replaceAll("", " ").trim();
    }

    
}
