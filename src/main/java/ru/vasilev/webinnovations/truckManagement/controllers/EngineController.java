package ru.vasilev.webinnovations.truckManagement.controllers;

import org.springframework.web.bind.annotation.*;
import ru.vasilev.webinnovations.truckManagement.data.Engine;
import ru.vasilev.webinnovations.truckManagement.database.repository.EngineRepository;
import ru.vasilev.webinnovations.truckManagement.database.repository.UnitRepository;
import ru.vasilev.webinnovations.truckManagement.service.EngineService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/engine")
public class EngineController {
    private final EngineService engineService;
    private final EngineRepository engineRepository;
    private final UnitRepository unitRepository;

    public EngineController(EngineRepository engineRepository, UnitRepository unitRepository, EngineService engineService) {
        this.engineRepository = engineRepository;
        this.unitRepository = unitRepository;
        this.engineService = engineService;
    }

    @GetMapping
    public Iterable<Engine> getAllEngines() {
        return engineService.getAllEngines();
    }

    @GetMapping("/{id}")
    public Engine getEngine(@PathVariable int id) {
        return engineService.getEngine(id);
    }

    @PostMapping
    public Engine addEngine(HttpServletRequest request) {
        return engineService.addEngine(request);
    }

    @PutMapping("/{id}")
    public Engine updateEngine(HttpServletRequest request, @PathVariable int id) {
        final Engine engine = engineService.getEngine(id);
        return engineService.updateEngine(engine, request);
    }
}
