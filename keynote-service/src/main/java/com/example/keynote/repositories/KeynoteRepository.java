package com.example.keynote.repositories;

import com.example.keynote.entities.Keynote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KeynoteRepository extends JpaRepository<Keynote, Long> {
}
