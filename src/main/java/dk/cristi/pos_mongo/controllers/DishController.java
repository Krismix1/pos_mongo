package dk.cristi.pos_mongo.controllers;


import dk.cristi.pos_mongo.entities.Dish;
import dk.cristi.pos_mongo.repositories.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@RestController
@RequestMapping("dishes")
public class DishController {

    private final DishRepository dishRepository;

    @Autowired
    public DishController(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    @GetMapping("/{id}")
    public Mono<Dish> getDishById(@PathVariable String id) {
        final Optional<Dish> optionalDish = this.dishRepository.findById(id);
        if (!optionalDish.isPresent()) {
            return Mono.empty();
        }
        return Mono.just(optionalDish.get());
    }

    @GetMapping
    public Flux<Dish> getAllDishes() {
        return Flux.fromIterable(this.dishRepository.findAll());
    }
}
