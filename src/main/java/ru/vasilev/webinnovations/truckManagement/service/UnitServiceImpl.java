package ru.vasilev.webinnovations.truckManagement.service;

import org.springframework.stereotype.Service;
import ru.vasilev.webinnovations.truckManagement.data.Unit;
import ru.vasilev.webinnovations.truckManagement.database.repository.UnitRepository;
import ru.vasilev.webinnovations.truckManagement.exceptions.EntityNotFoundException;

@Service
public class UnitServiceImpl extends HttpRequestServiceImpl implements UnitService {
    private final UnitRepository unitRepository;

    public UnitServiceImpl(UnitRepository unitRepository) {
        this.unitRepository = unitRepository;
    }

    @Override
    public Unit addUnit(String unitName) {
        final Unit unit = new Unit();
        unit.setUnitName(unitName);
        return unitRepository.save(unit);
    }

    @Override
    public Unit getUnit(int id) {
        final Unit unit = unitRepository.findById(id);
        if (unit == null) {
            throw new EntityNotFoundException("Unit is not found");
        }
        return unit;
    }

    @Override
    public Unit updateUnit(int id, String unitName) {
        final Unit unit = getUnit(id);
        unit.setUnitName(unitName);
        return unitRepository.save(unit);
    }

    @Override
    public Iterable<Unit> getAllUnits() {
        return unitRepository.findAll();
    }
}
