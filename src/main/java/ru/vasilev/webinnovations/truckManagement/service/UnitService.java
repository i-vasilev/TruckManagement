package ru.vasilev.webinnovations.truckManagement.service;

import ru.vasilev.webinnovations.truckManagement.data.Unit;

/**
 * Interface for service that works with unit entity.
 */
public interface UnitService {
    /**
     * Executes adding into database.
     *
     * @param unitName New unit's name.
     * @return new unit from db.
     */
    Unit addUnit(String unitName);

    /**
     * Gets unit by its id.
     *
     * @param id unit's id.
     * @return unit with id.
     */
    Unit getUnit(int id);

    /**
     * Updates unit in database.
     *
     * @param id       unit's id.
     * @param unitName unit's name.
     * @return updated unit.
     */
    Unit updateUnit(int id, String unitName);

    /**
     * Gets iterable collection, that contains all units in database.
     *
     * @return all units in database.
     */
    Iterable<Unit> getAllUnits();
}
