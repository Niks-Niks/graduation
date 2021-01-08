package com.graduation;

import com.graduation.model.Role;
import com.graduation.model.User;

import java.util.Collections;

import static com.graduation.model.AbstractBaseEntity.ID;

public class TestUserData {
    public static final TestMatcher<User> USER_MATCHER = TestMatcher.usingIgnoringFieldsComparator(User.class, "registered", "password");

    public static final int USER_ID = ID + 3;
    public static final int ADMIN_ID = ID + 4;
    public static final int NOT_FOUND = 10;

    public static final User user = new User(USER_ID, "User", "user@yandex.ru", "{noop}password", Role.USER);
    public static final User admin = new User(ADMIN_ID, "Admin", "admin@gmail.com", "{noop}admin", Role.ADMIN);

    public static User getNew() {
        return new User(null, "New", "new@gmail.com", "newPass", Role.USER);
    }

    public static User getUpdated() {
        User updated = new User(user);
        updated.setEmail("update@gmail.com");
        updated.setName("UpdatedName");
        updated.setPassword("updatePass");
        updated.setRoles(Collections.singletonList(Role.ADMIN));
        return updated;
    }
}
