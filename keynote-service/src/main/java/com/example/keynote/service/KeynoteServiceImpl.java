package com.example.keynote.service;

import com.example.keynote.dtos.KeynoteDTO;
import com.example.keynote.entities.Keynote;
import com.example.keynote.mappers.KeynoteMapper;
import com.example.keynote.repositories.KeynoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class KeynoteServiceImpl implements KeynoteService {
    private final KeynoteRepository keynoteRepository;
    private final KeynoteMapper keynoteMapper;

    @Override
    public KeynoteDTO saveKeynote(KeynoteDTO keynoteDTO) {
        Keynote keynote = keynoteMapper.fromKeynoteDTO(keynoteDTO);
        Keynote savedKeynote = keynoteRepository.save(keynote);
        return keynoteMapper.fromKeynote(savedKeynote);
    }

    @Override
    public KeynoteDTO getKeynote(Long id) {
        Keynote keynote = keynoteRepository.findById(id).orElseThrow(() -> new RuntimeException("Keynote not found"));
        return keynoteMapper.fromKeynote(keynote);
    }

    @Override
    public List<KeynoteDTO> getAllKeynotes() {
        return keynoteRepository.findAll().stream()
                .map(keynoteMapper::fromKeynote)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteKeynote(Long id) {
        keynoteRepository.deleteById(id);
    }

    @Override
    public KeynoteDTO updateKeynote(Long id, KeynoteDTO keynoteDTO) {
        Keynote keynote = keynoteRepository.findById(id).orElseThrow(() -> new RuntimeException("Keynote not found"));
        keynote.setNom(keynoteDTO.getNom());
        keynote.setPrenom(keynoteDTO.getPrenom());
        keynote.setEmail(keynoteDTO.getEmail());
        keynote.setFonction(keynoteDTO.getFonction());
        Keynote updatedKeynote = keynoteRepository.save(keynote);
        return keynoteMapper.fromKeynote(updatedKeynote);
    }
}
