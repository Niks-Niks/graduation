DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS vote;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS restaurant CASCADE;
DROP TABLE IF EXISTS dish;
DROP TABLE IF EXISTS menu;
DROP SEQUENCE IF EXISTS global_seq CASCADE;

CREATE SEQUENCE global_seq START WITH 100;
SELECT v FROM Vote v WHERE v.id=:id AND v.user_id=:userId;
SELECT * FROM Vote v;
CREATE TABLE restaurant
(
    id   INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name VARCHAR(100) NOT NULL
);
CREATE
    UNIQUE INDEX restaurants_unique_name_idx ON restaurant (name);

CREATE TABLE users
(
    id         INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name       VARCHAR(50)                       NOT NULL,
    email      VARCHAR(50)                       NOT NULL,
    password   VARCHAR(50)                       NOT NULL,
    registered TIMESTAMP           DEFAULT now() NOT NULL,
    enabled    BOOLEAN             DEFAULT TRUE  NOT NULL
);
CREATE
    UNIQUE INDEX users_unique_email_idx ON users (email);

CREATE TABLE menu
(
    id              INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    restaurant_id   INTEGER      NOT NULL,
    date_added_menu DATE         NOT NULL,
    restaurant_name VARCHAR(100) NOT NULL,
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (id) ON DELETE CASCADE
);
CREATE
    UNIQUE INDEX menu_unique_rest_id_date_added ON menu (restaurant_id, date_added_menu);

CREATE TABLE dish
(
    id      INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name    VARCHAR(255) NOT NULL,
    price   INTEGER      NOT NULL,
    menu_id INTEGER      NOT NULL,
    FOREIGN KEY (menu_id) REFERENCES menu (id) ON DELETE CASCADE
);

CREATE TABLE vote
(
    id        INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    user_id   INTEGER                           NOT NULL,
    menu_id   INTEGER                           NOT NULL,
    date_vote DATE                DEFAULT now() NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (menu_id) REFERENCES menu (id) ON DELETE CASCADE
);
CREATE
    UNIQUE INDEX vote_unique_user_vote ON vote (user_id, date_vote);

CREATE TABLE user_roles
(
    user_id INTEGER NOT NULL,
    role    VARCHAR(30),
    CONSTRAINT user_roles_idx UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);