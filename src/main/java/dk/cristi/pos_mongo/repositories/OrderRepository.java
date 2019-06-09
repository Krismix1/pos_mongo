package dk.cristi.pos_mongo.repositories;

import dk.cristi.pos_mongo.entities.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, String> {
}
