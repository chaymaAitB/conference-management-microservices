package com.example.conference.services;

import com.example.conference.dtos.ConferenceDTO;
import com.example.conference.dtos.ReviewDTO;
import java.util.List;

public interface ConferenceService {
    ConferenceDTO saveConference(ConferenceDTO conferenceDTO);
    ConferenceDTO getConference(Long id);
    List<ConferenceDTO> getAllConferences();
    ReviewDTO addReview(Long conferenceId, ReviewDTO reviewDTO);
}
