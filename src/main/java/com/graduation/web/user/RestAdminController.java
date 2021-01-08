package com.graduation.web.user;

import com.graduation.model.User;
import com.graduation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static com.graduation.Util.ValidationUtil.assureIdConsistent;

@RestController
@RequestMapping(value = RestAdminController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestAdminController {
    static final String REST_URL = "/rest/admin/users";

    @Autowired
    private UserService service;

    @GetMapping
    public List<User> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public User get(@PathVariable int id) {
        return service.get(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> createWithLocation(@Valid @RequestBody User User) {
        User created = service.create(User);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        service.delete(id);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody User user, @PathVariable("id") int id) {
        assureIdConsistent(user, id);
        service.update(user);
    }

    @GetMapping("/voice/{id}")
    public List<User> getVoice(@PathVariable("id") int id) {
        return service.getByVoice(id);
    }
}