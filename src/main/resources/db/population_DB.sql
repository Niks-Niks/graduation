ALTER SEQUENCE global_seq RESTART WITH 100;

INSERT INTO restaurant (name)
VALUES ('rest1'),
       ('rest2'),
       ('rest3');

INSERT INTO users (name, email, password, registered, enabled)
VALUES ('User1', 'user1@yandex.ru', '{noop}user1', '2020-12-29', true),
       ('User2', 'user2@gmail.com', '{noop}user2', '2020-12-30', true),
       ('User3', 'user3@gmail.com', '{noop}user3', '2021-01-01', true),
       ('Admin', 'admin@gmail.com', '{noop}admin', '2021-02-13', true);


INSERT INTO user_roles (role, user_id)
VALUES ('USER', 103),
       ('USER', 104),
       ('USER', 105),
       ('ADMIN', 106);

INSERT INTO MENU (restaurant_id, date_added_menu, restaurant_name)
VALUES ('100', '2020-12-28', 'rest1'),
       ('100', '2020-12-29', 'rest1'),
       ('100', '2020-12-30', 'rest1'),
       ('101', '2020-12-31', 'rest2'),
       ('101', '2021-01-15', 'rest2'),
       ('101', '2021-01-18', 'rest2'),
       ('102', '2021-01-20', 'rest3'),
       ('102', '2021-01-30', 'rest3'),
       ('102', '2021-02-12', 'rest3');

INSERT INTO VOTE(date_vote, user_id, menu_id)
VALUES ('2020-12-28', '103', '107'),
       ('2020-12-29', '103', '108'),
       ('2020-12-30', '103', '109'),
       ('2020-12-31', '104', '111'),
       ('2021-01-01', '104', '111'),
       ('2021-01-15', '104', '112'),
       ('2021-01-18', '104', '110'),
       ('2021-01-19', '104', '110'),
       ('2021-01-20', '105', '114'),
       ('2021-01-30', '105', '114'),
       ('2021-02-11', '106', '112'),
       ('2021-02-12', '106', '115');

INSERT INTO dish (name, price, menu_id)
VALUES ('meal_1_1', '50', 107),
       ('meal_1_2', '150', 107),
       ('meal_2_1', '69', 107),
       ('meal_2_2', '79', 108),
       ('meal_3_1', '69', 108),
       ('meal_3_2"', '89', 108),
       ('meal_3_3', '59', 109),
       ('meal_4_1', '99', 109),
       ('meal_4_2"', '120', 110),
       ('meal_4_2"', '139', 110),
       ('meal_4_2"', '16', 111),
       ('meal_4_2"', '19', 111),
       ('meal_4_2"', '54', 112),
       ('meal_4_2"', '64', 112),
       ('meal_4_2"', '12', 113),
       ('meal_4_2"', '15', 113),
       ('meal_4_2"', '51', 114),
       ('meal_4_2"', '98', 114),
       ('meal_4_2"', '67', 115),
       ('meal_4_2"', '12', 115);

