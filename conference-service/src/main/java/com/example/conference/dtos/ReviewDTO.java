package com.example.conference.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ReviewDTO {
    private Long id;
    private LocalDate date;
    private String text;
    private int stars;
}
