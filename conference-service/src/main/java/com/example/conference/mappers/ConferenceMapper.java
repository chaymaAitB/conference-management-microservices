package com.example.conference.mappers;

import com.example.conference.dtos.ConferenceDTO;
import com.example.conference.dtos.ReviewDTO;
import com.example.conference.entities.Conference;
import com.example.conference.entities.Review;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;

@Service
public class ConferenceMapper {
    public ReviewDTO fromReview(Review review) {
        ReviewDTO reviewDTO = new ReviewDTO();
        BeanUtils.copyProperties(review, reviewDTO);
        return reviewDTO;
    }

    public Review fromReviewDTO(ReviewDTO reviewDTO) {
        Review review = new Review();
        BeanUtils.copyProperties(reviewDTO, review);
        return review;
    }

    public ConferenceDTO fromConference(Conference conference) {
        ConferenceDTO conferenceDTO = new ConferenceDTO();
        BeanUtils.copyProperties(conference, conferenceDTO);
        if (conference.getReviews() != null) {
            conferenceDTO.setReviews(conference.getReviews().stream()
                    .map(this::fromReview)
                    .collect(Collectors.toList()));
        }
        return conferenceDTO;
    }

    public Conference fromConferenceDTO(ConferenceDTO conferenceDTO) {
        Conference conference = new Conference();
        BeanUtils.copyProperties(conferenceDTO, conference);
        return conference;
    }
}
