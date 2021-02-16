package com.graduation.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sun.istack.NotNull;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;

@Entity
@Table(name = "dish")
public class Dish extends AbstractNamedEntity {
    @Column(name = "price", nullable = false)
    @Range(min = 1, max = 1_000_000)
    private int price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "menu_id", nullable = false)
    @JsonBackReference
    @NotNull
    private Menu menu;

    public Dish(Integer id, String name, int price) {
        super(id, name);
        this.price = price;
    }

    public Dish() {
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return name + " - " + price;
    }
}
