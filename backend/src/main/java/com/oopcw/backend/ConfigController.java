package com.oopcw.backend;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/config")
public class ConfigController {

    private final TicketingService ticketingService;

    public ConfigController(TicketingService ticketingService) {
        this.ticketingService = ticketingService;
    }

    @PostMapping
    public ResponseEntity<String> configureSystem(@RequestBody TicketingConfig config) {
        ObjectMapper objectMapper = new ObjectMapper();

        File configFile = new File("config.json");
        try {
            objectMapper.writeValue(configFile, config);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ticketingService.configureSystem(config);
        return ResponseEntity.ok("System configured successfully.");
    }

    @PostMapping("/start")
    public ResponseEntity<String> startSystem() {
        ticketingService.startSystem();
        return ResponseEntity.ok("System started successfully.");
    }

    @PostMapping("/stop")
    public ResponseEntity<String> stopSystem() {
        ticketingService.stopSystem();
        return ResponseEntity.ok("System stopped.");
    }
}

