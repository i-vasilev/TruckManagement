package ru.vasilev.webinnovations.truckManagement.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vasilev.webinnovations.truckManagement.data.Unit;
import ru.vasilev.webinnovations.truckManagement.exceptions.FieldsIsAbsentException;
import ru.vasilev.webinnovations.truckManagement.service.HttpRequestServiceImpl;
import ru.vasilev.webinnovations.truckManagement.service.UnitService;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UnitController extends HttpRequestServiceImpl {
    private final UnitService managementService;

    public UnitController(UnitService managementService) {
        this.managementService = managementService;
    }

    @GetMapping(value = "/unit", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Unit>> getAllUnits() {
        final Iterable<Unit> allUnits = managementService.getAllUnits();
        return new ResponseEntity<>(allUnits, HttpStatus.OK);
    }

    @PostMapping(value = "/unit", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Unit> addUnit(HttpServletRequest request) throws FieldsIsAbsentException {
        final String unitName = getParameter(request, "unit_name");

        final Unit unit = managementService.addUnit(unitName);
        return new ResponseEntity<>(unit, HttpStatus.CREATED);
    }

    @GetMapping(value = "/unit/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Unit> getUnitById(@PathVariable("id") int id) {
        final Unit unit = managementService.getUnit(id);
        return new ResponseEntity<>(unit, HttpStatus.OK);
    }

    @PutMapping(value = "/unit/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Unit> updateUnit(HttpServletRequest request, @PathVariable("id") int id) {
        final String unitName = getParameter(request, "unit_name");
        return new ResponseEntity<>(managementService.updateUnit(id, unitName), HttpStatus.ACCEPTED);
    }

}
