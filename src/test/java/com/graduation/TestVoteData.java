package com.graduation;

import com.graduation.model.Vote;

import java.time.LocalDate;

import static com.graduation.model.AbstractBaseEntity.ID;

public class TestVoteData {
    public static final TestMatcher<Vote> VOICE_MATCHER = TestMatcher.usingIgnoringFieldsComparator(Vote.class, "user", "menu");

    public static final int VOTE_ID = ID + 115;
    public static final int NOT_FOUND = 10;

    public static LocalDate dateVote = LocalDate.parse("2020-11-16");

    public static Vote getNew() {
        return new Vote(null, LocalDate.now());
    }

    public static Vote getWithId() {
        Vote vote = new Vote(VOTE_ID, LocalDate.of(2020, 11, 18));
        return vote;
    }
}
