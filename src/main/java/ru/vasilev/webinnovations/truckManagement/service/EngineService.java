package ru.vasilev.webinnovations.truckManagement.service;

import ru.vasilev.webinnovations.truckManagement.data.Engine;

/**
 * Interface for service that works with engine entity.
 */
public interface EngineService {
    /**
     * Executes adding into database.
     *
     * @param engine Entity for inserting into database..
     * @return new engine from db.
     */
    Engine addEngine(Engine engine);

    /**
     * Gets engine by its id.
     *
     * @param id engine's id.
     * @return engine with id.
     */
    Engine getEngine(int id);

    /**
     * Updates engine in database.
     *
     * @param engine  engine entity.
     * @return updated engine.
     */
    Engine updateEngine(Engine engine);

    /**
     * Gets iterable collection, that contains all engines in database.
     *
     * @return all engines in database.
     */
    Iterable<Engine> getAllEngines();
}
