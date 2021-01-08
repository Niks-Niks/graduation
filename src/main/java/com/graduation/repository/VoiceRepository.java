package com.graduation.repository;

import com.graduation.model.Voice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface VoiceRepository extends JpaRepository<Voice, Integer> {
    @Query("SELECT v FROM Voice v INNER JOIN v.user u on v.user.id=:userId")
    Voice getById(@Param("userId") int userId);

    @Query("SELECT v FROM Voice v WHERE v.user.id=:userId ORDER BY v.date DESC")
    List<Voice> getAll(@Param("userId") int userId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Voice v WHERE v.id=:id")
    int delete(@Param("id") Integer id);
}
