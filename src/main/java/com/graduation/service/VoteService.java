package com.graduation.service;

import com.graduation.model.Menu;
import com.graduation.model.User;
import com.graduation.model.Vote;
import com.graduation.controller.MenuRepository;
import com.graduation.controller.UserRepository;
import com.graduation.controller.VoteRepository;
import com.graduation.util.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static com.graduation.util.ValidationUtil.checkNotFoundWithId;

@Service
public class VoteService {
    private final VoteRepository voteRepository;
    private final UserRepository userRepository;
    private final MenuRepository menuRepository;

    public VoteService(VoteRepository voteRepository, UserRepository userRepository, MenuRepository menuRepository) {
        this.voteRepository = voteRepository;
        this.userRepository = userRepository;
        this.menuRepository = menuRepository;
    }

    private final String time_vote = "11:00:00";

    @Transactional
    public Vote create(Vote vote, int menu_id, int user_id) {
        if (!vote.isNew()) {
            return null;
        }
        User user = userRepository.getOne(user_id);
        Menu menu = menuRepository.findById(menu_id).orElseThrow();
        vote.setUser(user);
        vote.setMenu(menu);
        vote.setDateVote(LocalDate.now());
        return voteRepository.save(vote);
    }

    public Vote findById(int id, int user_id) {
        return voteRepository.getById(id, user_id);
    }

    public Vote findByDate(LocalDate date, int user_id) {
        return voteRepository.getByDate(date, user_id);
    }

    @Transactional
    public Vote update(int menu_id, int user_id) {
        LocalTime time = LocalTime.parse(time_vote);
        if (LocalTime.now().isAfter(time)) {
            throw new NotFoundException("It's too late to update vote for today.");
        }
        Vote vote = findByDate(LocalDate.now(), user_id);
        Menu menu = menuRepository.findById(menu_id).orElseThrow();
        vote.setMenu(menu);
        return vote;
    }

    public List<Vote> findAll(int user_id) {
        return voteRepository.getAll(user_id);
    }

    public void delete(int id, int user_id) {
        checkNotFoundWithId(voteRepository.delete(id, user_id), id);
    }
}
