package com.graduation.controller;

import com.graduation.util.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.graduation.TestUserData.admin;
import static com.graduation.TestUtil.userHttpBasic;
import static com.graduation.TestVoteData.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("datajpa")
public class VoteRepositoryTest extends AbstractRepositoryTest {
    private static final String REST_URL = VoteRepositoryTest.REST_URL + '/';

    @Autowired
    private VoteRepository repository;

//    @Test
//    void get() throws Exception {
//        perform(MockMvcRequestBuilders.get(REST_URL + VOTE_ID)
//                .with(userHttpBasic(admin)))
//                .andExpect(status().isOk())
//                .andDo(print())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//                .andExpect(VOICE_MATCHER.contentJson(repository));
//    }

    @Test
    void getNotFound() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + NOT_FOUND)
                .with(userHttpBasic(admin)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + VOTE_ID)
                .with(userHttpBasic(admin)))
                .andExpect(status().isNoContent());
        assertThrows(NotFoundException.class, () -> repository.findById(VOTE_ID));
    }

    @Test
    void deleteNotFound() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + NOT_FOUND)
                .with(userHttpBasic(admin)))
                .andExpect(status().isUnprocessableEntity());
    }

//    @Test
//    void update() throws Exception {
//        Vote updated = getUpdated();
//        perform(MockMvcRequestBuilders.put(REST_URL + VOTE_ID).contentType(MediaType.APPLICATION_JSON)
//                .content(JsonUtil.writeValue(updated))
//                .with(userHttpBasic(admin)))
//                .andExpect(status().isNoContent());
//
//        VOICE_MATCHER.assertMatch(repository.findById(VOTE_ID).get(), updated);
//    }

    @Test
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL)
                .with(userHttpBasic(admin)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VOICE_MATCHER.contentJson(repository.findAll()));
    }
}
