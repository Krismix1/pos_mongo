package dk.cristi.pos_mongo.controllers;

import dk.cristi.pos_mongo.entities.Order;
import dk.cristi.pos_mongo.models.OrderItem;
import dk.cristi.pos_mongo.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("orders")
public class OrderController {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @PutMapping("{id}")
    public void addItem(@PathVariable("id") String orderId, @RequestBody @Valid OrderItem[] items) {
        if (StringUtils.isEmpty(orderId)) {
            orderId = "";
        }
        Order order = this.orderRepository.findById(orderId).orElseGet(Order::new);

        Map<String, OrderItem> huh = new HashMap<>();
        for (OrderItem item : items) {
            OrderItem previous = huh.putIfAbsent(item.getDishId(), item);
            if (previous != null) {
                item.addCount(previous.getCount());
            }
        }

        // Search for already existing items in the saved order
        // So that instead of adding more elements, we increment the count
        for (OrderItem item : order.getItems()) {
            OrderItem newItem = huh.get(item.getDishId());
            if (newItem != null) {
                item.addCount(newItem.getCount());
                huh.remove(item.getDishId());
            }
        }
        // add the new items
        order.getItems().addAll(huh.values());

        this.orderRepository.save(order);
    }

    @GetMapping
    public Flux<Order> getAllOrders() {
        return Flux.fromIterable(this.orderRepository.findAll());
    }
}
