package com.graduation.web.voice;

import com.graduation.model.Voice;
import com.graduation.repository.VoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = RestVoiceController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestVoiceController {
    public static final String REST_URL = "/rest/admin/voice";

    @Autowired
    private VoiceRepository voiceRepository;

    @GetMapping()
    public List<Voice> findAll() {
        return voiceRepository.findAll();
    }

    @GetMapping("/{id}")
    public Voice findById(@PathVariable("id") int id) {
        return voiceRepository.findById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        voiceRepository.delete(id);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody Voice voice) {
        voiceRepository.save(voice);
    }
}

