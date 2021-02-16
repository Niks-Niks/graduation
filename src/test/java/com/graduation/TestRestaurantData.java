package com.graduation;

import com.graduation.model.Restaurant;

import static com.graduation.model.AbstractBaseEntity.ID;

public class TestRestaurantData {
    public static final TestMatcher<Restaurant> RESTAURANT_TEST_MATCHER = TestMatcher.usingIgnoringFieldsComparator(Restaurant.class);

    public static final int RESTAURANT_ID = ID;
    public static final int NOT_FOUND = 10;

    public static final Restaurant restaurant = new Restaurant(RESTAURANT_ID, "restUpdate");

    public static Restaurant getNew() {
        return new Restaurant(null, "restNew");
    }

    public static Restaurant getUpdated() {
        return new Restaurant(null, "restUpdate");
    }
}
