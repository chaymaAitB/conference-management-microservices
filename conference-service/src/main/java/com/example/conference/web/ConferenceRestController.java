package com.example.conference.web;

import com.example.conference.dtos.ConferenceDTO;
import com.example.conference.dtos.ReviewDTO;
import com.example.conference.services.ConferenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/conferences")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ConferenceRestController {
    private final ConferenceService conferenceService;

    @GetMapping
    public List<ConferenceDTO> getAllConferences() {
        return conferenceService.getAllConferences();
    }

    @GetMapping("/{id}")
    public ConferenceDTO getConference(@PathVariable Long id) {
        return conferenceService.getConference(id);
    }

    @PostMapping
    public ConferenceDTO saveConference(@RequestBody ConferenceDTO conferenceDTO) {
        return conferenceService.saveConference(conferenceDTO);
    }

    @PostMapping("/{id}/reviews")
    public ReviewDTO addReview(@PathVariable Long id, @RequestBody ReviewDTO reviewDTO) {
        return conferenceService.addReview(id, reviewDTO);
    }
}
