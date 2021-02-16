package com.graduation.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "menu", uniqueConstraints = {@UniqueConstraint(columnNames = {"date_added_menu", "restaurant_id"}, name = "menu_unique_rest_id_date_added")})
public class Menu extends AbstractBaseEntity {

    @Column(name = "date_added_menu", nullable = false)
    @NotNull
    private LocalDate date_added_menu;

    @Column(name = "restaurant_name")
    private String restaurant_name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @JsonBackReference
    @NotNull
    private Restaurant restaurant;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "menu")
    @OrderBy("price DESC")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonManagedReference
    private List<Dish> dishes;

    public Menu() {
    }

    public Menu(Integer id, LocalDate date_added_menu, String restaurant_name, Restaurant restaurant, List<Dish> dishes) {
        super(id);
        this.date_added_menu = date_added_menu;
        this.restaurant_name = restaurant_name;
        this.restaurant = restaurant;
        this.dishes = dishes;
    }

    public Menu(Integer id, LocalDate date) {
        super(id);
        this.date_added_menu = date_added_menu;
    }

    public LocalDate getDateAddedMenu() {
        return date_added_menu;
    }

    public void setDateAddedMenu(LocalDate dateAddedMenu) {
        this.date_added_menu = dateAddedMenu;
    }

    public String getRestaurantName() {
        return restaurant_name;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurant_name = restaurantName;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }
}
