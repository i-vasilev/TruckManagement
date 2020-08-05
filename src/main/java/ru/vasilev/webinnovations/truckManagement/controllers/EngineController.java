package ru.vasilev.webinnovations.truckManagement.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vasilev.webinnovations.truckManagement.data.Engine;
import ru.vasilev.webinnovations.truckManagement.service.EngineService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/engine")
public class EngineController {
    private final EngineService engineService;

    public EngineController(EngineService engineService) {
        this.engineService = engineService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Engine>> getAllEngines() {
        final Iterable<Engine> allEngines = engineService.getAllEngines();
        return new ResponseEntity<>(allEngines, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Engine> getEngine(@PathVariable int id) {
        final Engine engine = engineService.getEngine(id);
        return new ResponseEntity<>(engine, HttpStatus.OK);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Engine> addEngine(HttpServletRequest request) {
        final Engine engine = engineService.addEngine(request);
        return new ResponseEntity<>(engine, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Engine> updateEngine(HttpServletRequest request, @PathVariable int id) {
        Engine engine = engineService.getEngine(id);
        engine = engineService.updateEngine(engine, request);
        return new ResponseEntity<>(engine, HttpStatus.ACCEPTED);
    }
}
