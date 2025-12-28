package com.example.conference.services;

import com.example.conference.clients.KeynoteRestClient;
import com.example.conference.dtos.ConferenceDTO;
import com.example.conference.dtos.ReviewDTO;
import com.example.conference.entities.Conference;
import com.example.conference.entities.Review;
import com.example.conference.mappers.ConferenceMapper;
import com.example.conference.models.Keynote;
import com.example.conference.repositories.ConferenceRepository;
import com.example.conference.repositories.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ConferenceServiceImpl implements ConferenceService {
    private final ConferenceRepository conferenceRepository;
    private final ReviewRepository reviewRepository;
    private final ConferenceMapper conferenceMapper;
    private final KeynoteRestClient keynoteRestClient;

    @Override
    public ConferenceDTO saveConference(ConferenceDTO conferenceDTO) {
        Conference conference = conferenceMapper.fromConferenceDTO(conferenceDTO);
        Conference savedConference = conferenceRepository.save(conference);
        return conferenceMapper.fromConference(savedConference);
    }

    @Override
    public ConferenceDTO getConference(Long id) {
        Conference conference = conferenceRepository.findById(id).orElseThrow(() -> new RuntimeException("Conference not found"));
        ConferenceDTO conferenceDTO = conferenceMapper.fromConference(conference);
        if(conferenceDTO.getKeynoteId() != null) {
            // Fetch Keynote details if needed, for now just verifying the call works or enriching logic could be added
            // Keynote keynote = keynoteRestClient.getKeynote(conferenceDTO.getKeynoteId());
            // In a real app we might enrich the DTO with keynote details
        }
        return conferenceDTO;
    }

    @Override
    public List<ConferenceDTO> getAllConferences() {
        return conferenceRepository.findAll().stream()
                .map(conferenceMapper::fromConference)
                .collect(Collectors.toList());
    }

    @Override
    public ReviewDTO addReview(Long conferenceId, ReviewDTO reviewDTO) {
        Conference conference = conferenceRepository.findById(conferenceId).orElseThrow(() -> new RuntimeException("Conference not found"));
        Review review = conferenceMapper.fromReviewDTO(reviewDTO);
        review.setConference(conference);
        Review savedReview = reviewRepository.save(review);
        return conferenceMapper.fromReview(savedReview);
    }
}
