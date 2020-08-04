package ru.vasilev.webinnovations.truckManagement.service;

import ru.vasilev.webinnovations.truckManagement.data.Engine;
import ru.vasilev.webinnovations.truckManagement.data.Unit;
import ru.vasilev.webinnovations.truckManagement.database.repository.EngineRepository;
import ru.vasilev.webinnovations.truckManagement.exceptions.EntityNotFoundException;
import ru.vasilev.webinnovations.truckManagement.exceptions.FieldsIsAbsentException;

import javax.servlet.http.HttpServletRequest;

/**
 * Service that works with engine entity.
 */
public class EngineServiceImpl extends HttpRequestServiceImpl implements EngineService {
    private final EngineRepository engineRepository;
    private final UnitService unitService;

    public EngineServiceImpl(EngineRepository engineRepository, UnitService unitService) {
        this.engineRepository = engineRepository;
        this.unitService = unitService;
    }

    /**
     * Executes adding into database.
     *
     * @param request user's request.
     * @return new engine from db.
     */
    @Override
    public Engine addEngine(HttpServletRequest request) {
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
        engineRepository.save(engine);
        return null;
    }

    /**
     * Gets engine by its id.
     *
     * @param id engine's id.
     * @return engine with id.
     */
    @Override
    public Engine getEngine(int id) {
        final Engine engine = engineRepository.getEngineById(id);
        if (engine == null) {
            throw new EntityNotFoundException("Engine not found");
        }
        return engine;
    }

    /**
     * Updates engine in database.
     *
     * @param engine  engine entity.
     * @param request user's request.
     * @return updated engine.
     */
    @Override
    public Engine updateEngine(Engine engine, HttpServletRequest request) {
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

        engineRepository.save(engine);
        return engine;
    }

    /**
     * Gets iterable collection, that contains all engines in database.
     *
     * @return all engines in database.
     */
    @Override
    public Iterable<Engine> getAllEngines() {
        return engineRepository.findAll();
    }
}
