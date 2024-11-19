/*
 * implements Serializable
In Java, la keyword implements Serializable viene utilizzata per indicare che una classe può essere convertita in un formato di byte per essere:

Memorizzata su disco
Inviata attraverso una rete (ad esempio, come messaggio RabbitMQ)
Il processo di conversione in byte è chiamato serializzazione, e la conversione inversa è chiamata deserializzazione.

Perché Serializable?
Spring AMQP richiede che gli oggetti scambiati attraverso RabbitMQ siano serializzabili quando si utilizza un convertitore predefinito come il SimpleMessageConverter. Anche quando utilizziamo Jackson2JsonMessageConverter, rendere un oggetto Serializable è una buona pratica per garantire che possa essere correttamente processato in ambienti distribuiti.
 */
package gnam.queue.model;


import java.io.Serializable;
import java.util.List;

public class Order implements Serializable{

    private String customerName;
    private String deliveryAddress;
    private List<OrderItem> items;
    private double totalPrice;

    // Costruttore
    public Order(String customerName, String deliveryAddress, List<OrderItem> items) {
        this.customerName = customerName;
        this.deliveryAddress = deliveryAddress;
        this.items = items;
        this.totalPrice = calculateTotalPrice(items);
    }

    // Getters e Setters
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    private double calculateTotalPrice(List<OrderItem> items) {
        double total = 0;
        for (OrderItem item : items) {
            total += item.getPrice() * item.getQuantity();
        }
        return total;
    }

    @Override
    public String toString() {
        return "Order{" +
                "customerName='" + customerName + '\'' +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", items=" + items +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
