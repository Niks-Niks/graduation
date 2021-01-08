package com.graduation;

import com.graduation.model.Voice;

import java.time.LocalDate;

import static com.graduation.model.AbstractBaseEntity.ID;

public class TestVoiceData {
    public static final TestMatcher<Voice> VOICE_MATCHER = TestMatcher.usingIgnoringFieldsComparator(Voice.class, "registered");

    public static final int VOICE_ID = ID + 115;
    public static final int NOT_FOUND = 10;

    public static final Voice voice = new Voice(VOICE_ID, LocalDate.of(2021, 1, 10));

    public static Voice getNew() {
        return new Voice(null, LocalDate.of(2020, 12, 10));
    }

    public static Voice getUpdated() {
        return new Voice(VOICE_ID, LocalDate.of(2021, 1, 10));
    }
}
