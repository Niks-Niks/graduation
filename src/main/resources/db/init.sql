DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS voice;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS restaurants CASCADE;
DROP TABLE IF EXISTS dish;
DROP SEQUENCE IF EXISTS global_seq CASCADE;

CREATE SEQUENCE global_seq START WITH 100;

CREATE TABLE restaurants
(
    id   INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name VARCHAR(100)                      NOT NULL,
    menu TIMESTAMP           DEFAULT now() NOT NULL
);

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


CREATE TABLE dish
(
    id            INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name          VARCHAR(100) NOT NULL,
    price         INTEGER      NOT NULL,
    restaurant_id INTEGER      NOT NULL,
    FOREIGN KEY (restaurant_id) REFERENCES restaurants (id) ON DELETE CASCADE
);

CREATE TABLE voice
(
    id            INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    user_id       INTEGER                           NOT NULL,
    restaurant_id INTEGER                           NOT NULL,
    vote          TIMESTAMP           DEFAULT now() NOT NULL,
    CONSTRAINT user_vote_idx UNIQUE (user_id, vote),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (restaurant_id) REFERENCES restaurants (id) ON DELETE CASCADE
);

CREATE TABLE user_roles
(
    user_id INTEGER NOT NULL,
    role    VARCHAR(30),
    CONSTRAINT user_roles_idx UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);