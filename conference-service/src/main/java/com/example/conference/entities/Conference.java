package com.example.conference.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Conference {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;
    private String type;
    private LocalDate date;
    private long duree;
    private int inscrits;
    private double score;
    private Long keynoteId;
    @OneToMany(mappedBy = "conference")
    private List<Review> reviews;
}
