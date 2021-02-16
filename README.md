Task:

Build a voting system for deciding where to have lunch.

2 types of users: admin and regular users Admin can input a restaurant and it's lunch menu of the day (2-5 items
usually, just a dish name and price)
Menu changes each day (admins do the updates)
Users can vote on which restaurant they want to have lunch at Only one vote counted per user If user votes again the
same day:
If it is before 11:00 we assume that he changed his mind. If it is after 11:00 then it is too late, vote can't be
changed Each restaurant provides a new menu each day.

As a result, provide a link to github repository. It should contain the code, README.md with API documentation and
couple curl commands to test it.

curl -> https://github.com/Niks-Niks/graduation/blob/main/curl/curl

___________________________________________________________________

get all menus for 2021-02-12

curl -u admin@gmail.com:admin http://localhost:8080/graduation_war/rest/admin/menu/filter?date=2021-02-12

get all users

curl -u admin@gmail.com:admin http://localhost:8080/graduation_war/rest/admin/users

get voice -> id 127

curl -u admin@gmail.com:admin http://localhost:8080/graduation_war/rest/admin/vote/127