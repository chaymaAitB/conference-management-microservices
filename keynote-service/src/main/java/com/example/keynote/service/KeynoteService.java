package com.example.keynote.service;

import com.example.keynote.dtos.KeynoteDTO;
import java.util.List;

public interface KeynoteService {
    KeynoteDTO saveKeynote(KeynoteDTO keynoteDTO);
    KeynoteDTO getKeynote(Long id);
    List<KeynoteDTO> getAllKeynotes();
    void deleteKeynote(Long id);
    KeynoteDTO updateKeynote(Long id, KeynoteDTO keynoteDTO);
}
