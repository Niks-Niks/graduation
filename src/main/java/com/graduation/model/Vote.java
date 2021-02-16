package com.graduation.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "vote", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "date_vote"}, name = "vote_unique_user_vote")})
public class Vote extends AbstractBaseEntity {
    @Column(name = "date_vote", nullable = false)
    @NotNull
    private LocalDate date_vote;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    @NotNull
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id", referencedColumnName = "id", nullable = false)
    @NotNull
    private Menu menu;

    public Vote() {
    }

    public Vote(Integer id, LocalDate date_vote, User user, Menu menu) {
        super(id);
        this.date_vote = date_vote;
        this.user = user;
        this.menu = menu;
    }

    public Vote(Integer id, LocalDate date) {
        super(id);
        this.date_vote = date;
    }

    public LocalDate getDate_vote() {
        return date_vote;
    }

    public void setDateVote(LocalDate date_vote) {
        this.date_vote = date_vote;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "user=" + user +
                ", menu=" + menu +
                ", date=" + date_vote +
                '}';
    }
}
