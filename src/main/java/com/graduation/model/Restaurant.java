package com.graduation.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "restaurants")
public class Restaurant extends AbstractNamedEntity {
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "restaurant")
    @JsonManagedReference
    private Set<Dish> menu;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "restaurant")
    @JsonManagedReference
    private Set<Voice> voice;

//TODO  => time to update menu!!!
    @Column(name = "menu", nullable = false, columnDefinition = "timestamp default now()")
    @NotNull
    private Date updateMenu = new Date();

    public Restaurant() {
    }

    public Restaurant(Integer id, String name) {
        super(id, name);
    }

    public Restaurant(Integer id, String name, Set<Dish> menu, Set<Voice> voice, Date updateMenu) {
        super(id, name);
        this.menu = menu;
        this.voice = voice;
        this.updateMenu = updateMenu;
    }

    public Set<Dish> getMenu() {
        return menu;
    }

    public void setMenu(Set<Dish> menu) {
        this.menu = menu;
    }

    public Set<Voice> getVoice() {
        return voice;
    }

    public void setVoice(Set<Voice> voice) {
        this.voice = voice;
    }

    public Date getupdateMenu() {
        return updateMenu;
    }

    public void setupdateMenu(Date updateMenu) {
        this.updateMenu = updateMenu;
    }

    @Override
    public String toString() {
        return "Ресторан: " +
                "Название - " + name;
    }

}
