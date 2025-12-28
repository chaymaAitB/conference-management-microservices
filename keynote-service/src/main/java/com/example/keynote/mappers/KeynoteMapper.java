package com.example.keynote.mappers;

import com.example.keynote.dtos.KeynoteDTO;
import com.example.keynote.entities.Keynote;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class KeynoteMapper {
    public KeynoteDTO fromKeynote(Keynote keynote) {
        KeynoteDTO keynoteDTO = new KeynoteDTO();
        BeanUtils.copyProperties(keynote, keynoteDTO);
        return keynoteDTO;
    }

    public Keynote fromKeynoteDTO(KeynoteDTO keynoteDTO) {
        Keynote keynote = new Keynote();
        BeanUtils.copyProperties(keynoteDTO, keynote);
        return keynote;
    }
}
