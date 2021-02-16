package com.graduation.web.vote;

import com.graduation.model.Menu;
import com.graduation.model.User;
import com.graduation.model.Vote;
import com.graduation.controller.MenuRepository;
import com.graduation.controller.UserRepository;
import com.graduation.service.VoteService;
import com.graduation.util.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import static com.graduation.AuthorizedUser.authUserId;

@RestController
@RequestMapping(value = VoteRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteRestController {
    public static final String REST_URL = "/rest/admin/";

    @Autowired
    private VoteService service;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("votes")
    public List<Vote> findAll() {
        User user = userRepository.findById(authUserId()).orElseThrow();
        return service.findAll(user.getId());
    }

    @GetMapping("vote/{id}")
    public Vote findById(@PathVariable("id") int id) {
        User user = userRepository.findById(authUserId()).orElseThrow();
        Vote result = service.findById(id, user.getId());
        if (result == null) {
            throw new NotFoundException("Vote not found by user " + user.getName());
        }
        return result;
    }

    @GetMapping("/date")
    public Vote findByDate(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        User user = userRepository.findById(authUserId()).orElseThrow();
        return service.findByDate(date, user.getId());
    }

    @GetMapping("/menus")
    public List<Menu> todayMenus() {
        return menuRepository.getByDate(LocalDate.now());
    }

    @GetMapping("/menu/filter")
    public List<Menu> menuFor(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return menuRepository.getByDate(date);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vote> create(@RequestBody Vote vote, @RequestParam int menuId) {
        User user = userRepository.findById(authUserId()).orElseThrow();
        Vote created = service.create(vote, menuId, user.getId());
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/vote/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestParam int menu_id) {
        User user = userRepository.findById(authUserId()).orElseThrow();
        service.update(menu_id, user.getId());
    }
}

