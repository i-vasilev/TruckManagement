package ru.vasilev.webinnovations.truckManagement.service;

import ru.vasilev.webinnovations.truckManagement.data.Unit;

public interface UnitService extends HttpRequestService {

    Unit addUnit(String unitName);

    Unit getUnit(int id);

    Unit updateUnit(int id, String unitName);

    Iterable<Unit> getAllUnits();
}
