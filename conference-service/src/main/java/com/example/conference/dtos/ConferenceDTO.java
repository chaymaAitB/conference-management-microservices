package com.example.conference.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ConferenceDTO {
    private Long id;
    private String titre;
    private String type;
    private LocalDate date;
    private long duree;
    private int inscrits;
    private double score;
    private Long keynoteId;
    private List<ReviewDTO> reviews;
}
