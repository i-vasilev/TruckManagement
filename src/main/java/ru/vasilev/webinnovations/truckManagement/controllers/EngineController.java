package ru.vasilev.webinnovations.truckManagement.controllers;

import org.springframework.web.bind.annotation.*;
import ru.vasilev.webinnovations.truckManagement.data.Engine;
import ru.vasilev.webinnovations.truckManagement.data.Unit;
import ru.vasilev.webinnovations.truckManagement.database.repository.EngineRepository;
import ru.vasilev.webinnovations.truckManagement.database.repository.UnitRepository;
import ru.vasilev.webinnovations.truckManagement.exceptions.EntityNotFoundException;
import ru.vasilev.webinnovations.truckManagement.exceptions.FieldsIsAbsentException;
import ru.vasilev.webinnovations.truckManagement.service.UnitService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/engine")
public class EngineController {
    private final UnitService unitService;
    private final EngineRepository engineRepository;
    private final UnitRepository unitRepository;

    public EngineController(EngineRepository engineRepository, UnitRepository unitRepository, UnitService unitService) {
        this.engineRepository = engineRepository;
        this.unitRepository = unitRepository;
        this.unitService = unitService;
    }

    @GetMapping
    public Iterable<Engine> getAllEngines() {
        return engineRepository.findAll();
    }

    @GetMapping("/{id}")
    public Engine getEngine(@PathVariable int id) {
        final Engine engine = engineRepository.getEngineById(id);
        if (engine == null) {
            throw new EntityNotFoundException("Engine not found");
        }
        return engine;
    }

    @PostMapping
    public Engine addEngine(HttpServletRequest request) {
        final String name = unitService.getParameter(request, "engine_name");
        final String unitIdStr = unitService.getParameter(request, "unit_id");
        final int unitId = Integer.parseInt(unitIdStr);
        final String powerStr = unitService.getParameter(request, "power");
        final int power = Integer.parseInt(powerStr);
        final Unit unit = unitRepository.findById(unitId);
        if (unit == null) {
            throw new EntityNotFoundException("Unit is not found!");
        }
        final Engine engine = new Engine();
        engine.setName(name);
        engine.setUnit(unit);
        engine.setPower(power);
        engineRepository.save(engine);
        return engine;
    }

    @PutMapping("/{id}")
    public Engine updateEngine(HttpServletRequest request, @PathVariable int id) {
        final Engine engine = engineRepository.getEngineById(id);
        if (engine == null) {
            throw new EntityNotFoundException("Engine isn't found in database!");
        }
        if (request.getParameterMap().isEmpty()) {
            throw new FieldsIsAbsentException("At least one of the parameters mustn't be empty!");
        }

        final String unitIdStr = request.getParameter("unit_id");
        if (unitIdStr != null) {
            final int unitId = Integer.parseInt(unitIdStr);
            final Unit unit = unitRepository.findById(unitId);
            if (unit == null) {
                throw new EntityNotFoundException("Unit not found!");
            }
            engine.setUnit(unit);
        }

        final String powerStr = request.getParameter("power");
        if (powerStr != null) {
            final int power = Integer.parseInt(powerStr);
            engine.setPower(power);
        }

        engineRepository.save(engine);
        return engine;
    }
}
