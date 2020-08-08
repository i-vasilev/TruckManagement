package ru.vasilev.webinnovations.truckManagement.service;

import ru.vasilev.webinnovations.truckManagement.data.Engine;
import ru.vasilev.webinnovations.truckManagement.database.repository.EngineRepository;
import ru.vasilev.webinnovations.truckManagement.exceptions.EntityNotFoundException;

/**
 * Service that works with engine entity.
 */
public class EngineServiceImpl implements EngineService {
    private final EngineRepository engineRepository;
    private final UnitService unitService;

    public EngineServiceImpl(EngineRepository engineRepository, UnitService unitService) {
        this.engineRepository = engineRepository;
        this.unitService = unitService;
    }

    /**
     * Executes adding into database.
     *
     * @param engine Entity for inserting into database..
     * @return new engine from db.
     */
    @Override
    public Engine addEngine(Engine engine) {
        engineRepository.save(engine);
        return engine;
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
     * @return updated engine.
     */
    @Override
    public Engine updateEngine(Engine engine) {
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
