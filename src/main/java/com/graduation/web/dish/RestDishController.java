package com.graduation.web.dish;

import com.graduation.model.Dish;
import com.graduation.repository.DishRepository;
import com.graduation.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static com.graduation.Util.ValidationUtil.assureIdConsistent;
import static com.graduation.Util.ValidationUtil.checkNotFoundWithId;

@RestController
@RequestMapping(value = RestDishController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestDishController {
    public static final String REST_URL = "/rest/admin/dish";

    @Autowired
    private DishRepository repository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @GetMapping
    public List<Dish> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Dish get(@PathVariable int id) {
        return repository.findById(id).orElse(null);
    }

    @PostMapping(value = "/{rest_id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> createWithLocation(@Valid @RequestBody Dish dish, @Param("rest_id") int restId) {
        if (!dish.isNew()) {
            return null;
        }
        dish.setRestaurant(restaurantRepository.findById(restId).orElse(null));
        Dish created = repository.save(dish);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/rest/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody Dish dish, @Param("id") int id) {
        assureIdConsistent(dish, id);
        repository.save(dish);
    }
}