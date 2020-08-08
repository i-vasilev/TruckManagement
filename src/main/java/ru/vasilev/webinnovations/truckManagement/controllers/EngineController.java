package ru.vasilev.webinnovations.truckManagement.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vasilev.webinnovations.truckManagement.data.Engine;
import ru.vasilev.webinnovations.truckManagement.data.Unit;
import ru.vasilev.webinnovations.truckManagement.exceptions.EntityNotFoundException;
import ru.vasilev.webinnovations.truckManagement.exceptions.FieldsIsAbsentException;
import ru.vasilev.webinnovations.truckManagement.service.EngineService;
import ru.vasilev.webinnovations.truckManagement.service.UnitService;

import javax.servlet.http.HttpServletRequest;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/engine")
public class EngineController extends RestController {
    private final EngineService engineService;
    private final UnitService unitService;

    public EngineController(EngineService engineService, UnitService unitService) {
        this.engineService = engineService;
        this.unitService = unitService;
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
        final String name = getParameter(request, "engine_name");

        final String unitIdStr = getParameter(request, "unit_id");
        final int unitId = Integer.parseInt(unitIdStr);

        final String powerStr = getParameter(request, "power");
        final int power = Integer.parseInt(powerStr);

        final Unit unit = unitService.getUnit(unitId);
        if (unit == null) {
            throw new EntityNotFoundException("Unit is not found!");
        }

        final Engine engine = new Engine();
        engine.setName(name);
        engine.setUnit(unit);
        engine.setPower(power);
        engineService.addEngine(engine);

        return new ResponseEntity<>(engine, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Engine> updateEngine(HttpServletRequest request, @PathVariable int id) {
        Engine engine = engineService.getEngine(id);

        if (request.getParameterMap().isEmpty()) {
            throw new FieldsIsAbsentException("At least one of the parameters mustn't be empty!");
        }

        final String unitIdStr = request.getParameter("unit_id");
        if (unitIdStr != null) {
            final int unitId = Integer.parseInt(unitIdStr);
            final Unit unit = unitService.getUnit(unitId);
            engine.setUnit(unit);
        }

        final String powerStr = request.getParameter("power");
        if (powerStr != null) {
            final int power = Integer.parseInt(powerStr);
            engine.setPower(power);
        }

        engine = engineService.updateEngine(engine);
        return new ResponseEntity<>(engine, HttpStatus.ACCEPTED);
    }
}
