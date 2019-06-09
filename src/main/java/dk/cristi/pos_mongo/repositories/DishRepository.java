package dk.cristi.pos_mongo.repositories;

import dk.cristi.pos_mongo.entities.Dish;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishRepository extends MongoRepository<Dish, String> {

}
