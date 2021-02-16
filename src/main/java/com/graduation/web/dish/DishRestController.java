package com.graduation.web.dish;

import com.graduation.model.Dish;
import com.graduation.model.Menu;
import com.graduation.controller.DishRepository;
import com.graduation.controller.MenuRepository;
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

import static com.graduation.util.ValidationUtil.assureIdConsistent;
import static com.graduation.util.ValidationUtil.checkNotFoundWithId;

@RestController
@RequestMapping(value = DishRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class DishRestController {
    public static final String REST_URL = "/rest/admin/";

    @Autowired
    private DishRepository repository;

    @Autowired
    private MenuRepository menuRepository;

    @GetMapping("dishes")
    public List<Dish> getAll() {
        return repository.findAll();
    }

    @GetMapping("dish/{id}")
    public Dish get(@PathVariable int id) {
        return repository.findById(id).orElseThrow();
    }

    @PostMapping(value = "dish/{menu_id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> createWithLocation(@Valid @RequestBody Dish dish, @Param("menu_id") int menuId) {
        if (!dish.isNew()) {
            return null;
        }
        Menu menu = menuRepository.findById(menuId).orElseThrow();
        dish.setMenu(menu);
        Dish created = repository.save(dish);
        URI uriOfNewResorce = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/dish/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResorce).body(created);
    }

    @DeleteMapping("dish/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    @PutMapping(value = "dish/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody Dish dish, @Param("id") int id) {
        assureIdConsistent(dish, id);
        repository.save(dish);
    }
}