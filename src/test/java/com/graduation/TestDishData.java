package com.graduation;

import com.graduation.model.Dish;

import static com.graduation.model.AbstractBaseEntity.ID;

public class TestDishData {
    public static final TestMatcher<Dish> DISH_MATCHER = TestMatcher.usingIgnoringFieldsComparator(Dish.class, "registered");

    public static final int DISH_ID = ID + 115;
    public static final int NOT_FOUND = 10;

    public static final Dish dish = new Dish(DISH_ID, "dish", 150);

    public static Dish getNew() {
        return new Dish(null, "New dish", 199);
    }

    public static Dish getUpdated() {
        return new Dish(DISH_ID + 10, "update dish", 10);
    }
}
