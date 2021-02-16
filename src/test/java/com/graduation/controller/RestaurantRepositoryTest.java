package com.graduation.controller;

import com.graduation.model.Restaurant;
import com.graduation.web.json.JsonUtil;
import com.graduation.web.restaurant.RestaurantRestController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.graduation.TestRestaurantData.*;
import static com.graduation.TestUserData.admin;
import static com.graduation.TestUtil.readFromJson;
import static com.graduation.TestUtil.userHttpBasic;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("datajpa")
public class RestaurantRepositoryTest extends AbstractRepositoryTest {
    private static final String REST_URL = RestaurantRestController.REST_URL + "/";

    @Autowired
    private RestaurantRepository repository;

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + RESTAURANT_ID)
                .with(userHttpBasic(admin)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_TEST_MATCHER.contentJson(restaurant));
    }

    @Test
    void createWithLocation() throws Exception {
        Restaurant newRestaurant = getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newRestaurant))
                .with(userHttpBasic(admin)));

        Restaurant created = readFromJson(action, Restaurant.class);
        int newId = created.id();
        newRestaurant.setId(newId);
        RESTAURANT_TEST_MATCHER.assertMatch(created, newRestaurant);
        RESTAURANT_TEST_MATCHER.assertMatch(repository.findById(newId).get(), newRestaurant);
    }

    @Test
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL)
                .with(userHttpBasic(admin)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_TEST_MATCHER.contentJson(repository.findAll()));
    }
}
