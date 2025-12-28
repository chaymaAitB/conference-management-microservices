package com.example.keynote.web;

import com.example.keynote.dtos.KeynoteDTO;
import com.example.keynote.service.KeynoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/keynotes")
@RequiredArgsConstructor
@CrossOrigin("*")
public class KeynoteRestController {
    private final KeynoteService keynoteService;

    @GetMapping
    public List<KeynoteDTO> getAllKeynotes() {
        return keynoteService.getAllKeynotes();
    }

    @GetMapping("/{id}")
    public KeynoteDTO getKeynote(@PathVariable Long id) {
        return keynoteService.getKeynote(id);
    }

    @PostMapping
    public KeynoteDTO saveKeynote(@RequestBody KeynoteDTO keynoteDTO) {
        return keynoteService.saveKeynote(keynoteDTO);
    }

    @PutMapping("/{id}")
    public KeynoteDTO updateKeynote(@PathVariable Long id, @RequestBody KeynoteDTO keynoteDTO) {
        return keynoteService.updateKeynote(id, keynoteDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteKeynote(@PathVariable Long id) {
        keynoteService.deleteKeynote(id);
    }
}
