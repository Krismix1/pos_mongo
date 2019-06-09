package dk.cristi.pos_mongo.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class OrderItem {
    @NotBlank
    private String dishId;
    @NotNull
    @Positive
    private int count;

    public String getDishId() {
        return dishId;
    }

    public int getCount() {
        return count;
    }

    public void addCount(int i) {
        if (i < 0) {
            throw new RuntimeException("Count should not be negative");
        }
        count += i;
    }
}
