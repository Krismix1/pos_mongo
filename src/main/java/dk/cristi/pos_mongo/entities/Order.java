package dk.cristi.pos_mongo.entities;

import dk.cristi.pos_mongo.models.OrderItem;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.LinkedList;
import java.util.List;

@Document("orders")
public class Order {
    @Id
    private String id;
    private List<OrderItem> items;

    public Order() {
        items = new LinkedList<>();
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }
}
