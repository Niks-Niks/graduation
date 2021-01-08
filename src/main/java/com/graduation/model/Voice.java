package com.graduation.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "voice", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "vote"}, name = "user_vote_idx")})
public class Voice extends AbstractBaseEntity {
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    @NotNull
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id", referencedColumnName = "id", nullable = false)
    @NotNull
    private Restaurant restaurant;

    @Column(name = "vote", nullable = false, columnDefinition = "timestamp default now()")
    @NotNull
    private LocalDate date;

    public Voice() {
    }

    public Voice(LocalDate date) {
        this.date = date;
    }

    public Voice(Integer id, LocalDate date) {
        super(id);
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Voice{" +
                "user=" + user +
                ", restaurant=" + restaurant +
                ", date=" + date +
                '}';
    }
}
