package com.graduation.controller;

import com.graduation.TestUserData;
import com.graduation.util.NotFoundException;
import com.graduation.model.Role;
import com.graduation.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static com.graduation.TestUserData.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ActiveProfiles("datajpa")
public class UserRepositoryTest extends AbstractRepositoryTest {

    @Autowired
    private UserRepository repository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Test
    void create() {
        User created = repository.save(getNew());
        int newId = created.id();
        User newUser = getNew();
        newUser.setId(newId);
        USER_MATCHER.assertMatch(created, newUser);
        USER_MATCHER.assertMatch(repository.findById(newId).get(), newUser);
    }

    @Test
    void duplicateMailCreate() {
        assertThrows(DataAccessException.class, () ->
                repository.save(new User(null, "Duplicate", "user@yandex.ru", "newPass", Role.USER)));
    }

    @Test
    void delete() {
        repository.delete(USER_ID);
        assertThrows(NotFoundException.class, () -> repository.findById(USER_ID));
    }

    @Test
    void deletedNotFound() {
        assertThrows(NotFoundException.class, () -> repository.delete(NOT_FOUND));
    }

    @Test
    void get() {
        User user = repository.findById(ADMIN_ID).orElse(null);
        USER_MATCHER.assertMatch(user, TestUserData.admin);
    }

    @Test
    void getNotFound() {
        assertThrows(NotFoundException.class, () -> repository.findById(NOT_FOUND));
    }

    @Test
    void update() {
        User updated = getUpdated();
        repository.save(updated);
        USER_MATCHER.assertMatch(repository.findById(USER_ID).get(), getUpdated());
    }

    @Test
    void getAll() {
        List<User> all = repository.findAll();
        USER_MATCHER.assertMatch(all, admin, user);
    }
}
