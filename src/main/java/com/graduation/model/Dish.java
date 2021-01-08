package com.graduation.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;

@Entity
@Table(name = "dish")
public class Dish extends AbstractNamedEntity {
    @Column(name = "price", nullable = false)
    @Range(min = 1, max = 10000)
    private int price;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", referencedColumnName = "id")
    @JsonBackReference
    private Restaurant restaurant;

    public Dish(Integer id, String name, int price) {
        super(id, name);
        this.price = price;
    }

    public Dish() {
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return name + " - " + price;
    }
}
