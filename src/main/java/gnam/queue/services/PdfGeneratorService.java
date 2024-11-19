package gnam.queue.services;

import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import gnam.queue.model.Order;

import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class PdfGeneratorService {

    public byte[] generateOrderPdf(Order order) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            PdfWriter writer = new PdfWriter(outputStream);
            Document document = new Document(new com.itextpdf.kernel.pdf.PdfDocument(writer));

            // Aggiungi contenuto al PDF
            document.add(new Paragraph("Dettagli Ordine"));
            document.add(new Paragraph("Cliente: " + order.getCustomerName()));
            document.add(new Paragraph("Indirizzo di consegna: " + order.getDeliveryAddress()));
            document.add(new Paragraph("Totale: €" + order.getTotalPrice()));

            document.add(new Paragraph("Articoli:"));
            order.getItems().forEach(item -> document.add(new Paragraph(
                    item.getItemName() + " - Quantità: " + item.getQuantity() + " - Prezzo: €" + item.getPrice()
            )));

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return outputStream.toByteArray();
    }
}
