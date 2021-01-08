package com.graduation.web.user;

import com.graduation.model.Restaurant;
import com.graduation.model.User;
import com.graduation.model.Voice;
import com.graduation.repository.RestaurantRepository;
import com.graduation.repository.UserRepository;
import com.graduation.repository.VoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static com.graduation.AuthorizedUser.authUserId;

@RestController
@RequestMapping(value = RestUserController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestUserController {
    static final String REST_URL = "/rest";

    @Autowired
    private UserRepository repository;

    @Autowired
    private VoiceRepository voiceRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @GetMapping
    public User get() {
        return repository.findById(authUserId()).orElse(null);
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> register(@Valid @RequestBody User user) {
        User created = repository.save(user);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL).build().toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete() {
        repository.delete(authUserId());
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody User user) {
        repository.save(user);
    }

    @GetMapping(value = "/menu")
    public List<Restaurant> getMenu() {
        return restaurantRepository.findAll();
    }

    @PostMapping(value = "/voice/{id}")
    public ResponseEntity<Voice> choiceVoice(@RequestBody Voice voice, @PathVariable("id") int rest_id) {
        voice.setUser(repository.findById(authUserId()).orElse(null));
        voice.setRestaurant(restaurantRepository.findById(rest_id).orElse(null));
        Voice v = voiceRepository.save(voice);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/voice/{id}")
                .buildAndExpand(v.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(v);
    }
}