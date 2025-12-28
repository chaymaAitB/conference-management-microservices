package com.example.conference.clients;

import com.example.conference.models.Keynote;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@FeignClient(name = "keynote-service")
public interface KeynoteRestClient {
    
    @GetMapping("/api/keynotes/{id}")
    @CircuitBreaker(name = "keynoteService", fallbackMethod = "getDefaultKeynote")
    Keynote getKeynote(@PathVariable Long id);

    default Keynote getDefaultKeynote(Long id, Exception exception) {
        Keynote keynote = new Keynote();
        keynote.setId(id);
        keynote.setNom("Not Available");
        keynote.setPrenom("Not Available");
        keynote.setEmail("Not Available");
        return keynote;
    }
}
