package gnam.queue.model;

public class OrderRequest {

    private Order order;
    private String email;

    // Costruttore
    public OrderRequest(Order order, String email) {
        this.order = order;
        this.email = email;
    }

    // Getter e Setter
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

