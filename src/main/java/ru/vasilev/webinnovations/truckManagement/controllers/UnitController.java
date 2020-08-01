package ru.vasilev.webinnovations.truckManagement.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.vasilev.webinnovations.truckManagement.data.Unit;
import ru.vasilev.webinnovations.truckManagement.database.repository.UnitRepository;
import ru.vasilev.webinnovations.truckManagement.exceptions.FieldsIsAbsentException;
import ru.vasilev.webinnovations.truckManagement.exceptions.EntityNotFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class UnitController {

    private final UnitRepository unitRepository;

    public UnitController(UnitRepository unitRepository) {
        this.unitRepository = unitRepository;
    }

    @GetMapping("/unit")
    public List<Unit> getAllUnits() {
        return unitRepository.findAll();
    }

    @PostMapping(value = "/unit", produces = MediaType.APPLICATION_JSON_VALUE)
    public Unit addUnit(HttpServletRequest request) throws FieldsIsAbsentException {
        final String unitName = getUnitName(request);
        final Unit unit = new Unit();
        unit.setUnitName(unitName);
        unitRepository.save(unit);
        return unit;
    }

    @GetMapping("/unit/{id}")
    public Unit getUnitById(@PathVariable("id") int id) {
        return getUnit(id);
    }

    @PutMapping("/unit/{id}")
    public Unit updateUnit(HttpServletRequest request, @PathVariable("id") int id) {
        final Unit unit = getUnit(id);
        final String unitName = getUnitName(request);
        unit.setUnitName(unitName);
        unitRepository.save(unit);
        return unit;
    }

    private Unit getUnit(int id) {
        final Unit unit = unitRepository.findById(id);
        if (unit == null) {
            throw new EntityNotFoundException("Unit is not found");
        }
        return unit;
    }

    private String getUnitName(HttpServletRequest request) {
        final String unitName = request.getParameter("unit_name");
        if (unitName == null) {
            throw new FieldsIsAbsentException("Mandatory field unit_name is absent");
        }
        return unitName;
    }
}
