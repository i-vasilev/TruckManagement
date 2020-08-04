package ru.vasilev.webinnovations.truckManagement.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.vasilev.webinnovations.truckManagement.data.Unit;
import ru.vasilev.webinnovations.truckManagement.exceptions.FieldsIsAbsentException;
import ru.vasilev.webinnovations.truckManagement.service.UnitServiceImpl;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UnitController {
    private final UnitServiceImpl managementService;

    public UnitController(UnitServiceImpl managementService) {
        this.managementService = managementService;
    }

    @GetMapping("/unit")
    public Iterable<Unit> getAllUnits() {
        return managementService.getAllUnits();
    }

    @PostMapping(value = "/unit", produces = MediaType.APPLICATION_JSON_VALUE)
    public Unit addUnit(HttpServletRequest request) throws FieldsIsAbsentException {
        final String unitName = managementService.getParameter(request, "unit_name");

        return managementService.addUnit(unitName);
    }

    @GetMapping("/unit/{id}")
    public Unit getUnitById(@PathVariable("id") int id) {
        return managementService.getUnit(id);
    }

    @PutMapping("/unit/{id}")
    public Unit updateUnit(HttpServletRequest request, @PathVariable("id") int id) {
        final String unitName = managementService.getParameter(request, "unit_name");
        return managementService.updateUnit(id, unitName);
    }

}
