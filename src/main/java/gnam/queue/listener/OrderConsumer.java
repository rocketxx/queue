package gnam.queue.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import gnam.queue.model.Order;
import gnam.queue.model.OrderRequest;
import gnam.queue.services.EmailService;
import gnam.queue.services.PdfGeneratorService;

@Component
public class OrderConsumer {
    
    @Autowired
    private PdfGeneratorService pdfGeneratorService;

    @Autowired
    private EmailService emailService;

    @RabbitListener(queues = "order-queue")
    public void receiveOrder(OrderRequest  orderRequest) {
        try {
            Order order = orderRequest.getOrder();
            // 1. Genera il PDF dell'ordine
            byte[] pdfContent = pdfGeneratorService.generateOrderPdf(order);

            String email = orderRequest.getEmail();
            // 2. Crea e invia l'email con l'allegato PDF
            sendEmailWithPdf(email,order, pdfContent);

            // Log di conferma
            System.out.println("Ordine ricevuto e email inviata per: " + order.getCustomerName());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Errore durante l'elaborazione dell'ordine: " + e.getMessage());
        }
    }

    private void sendEmailWithPdf(String email,Order order, byte[] pdfContent) {
        // 3. Parametri per l'email
        String emailRecipient = email; 
        String subject = "Ordine Ricevuto - " + order.getCustomerName();
        String body = "In allegato trovi i dettagli dell'ordine.";

        // 4. Invio email
        emailService.sendOrderEmail(
            emailRecipient, 
            subject, 
            body, 
            pdfContent, 
            "ordine_" + order.getId() + ".pdf"
        );

        System.out.println("Email inviata con il PDF dell'ordine a: " + emailRecipient);
    }

}
