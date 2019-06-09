package dk.cristi.pos_mongo.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("dishes")
public class Dish {

    @Id
    private String id;
    private String title;
    private float price;

    public Dish() {}

    public Dish(String id, String title, float price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public float getPrice() {
        return price;
    }
}
