package ru.vasilev.webinnovations.truckManagement.service;

import ru.vasilev.webinnovations.truckManagement.data.Engine;

import javax.servlet.http.HttpServletRequest;

/**
 * Interface for service that works with engine entity.
 */
public interface EngineService extends HttpRequestService {
    /**
     * Executes adding into database.
     *
     * @param request user's request.
     * @return new engine from db.
     */
    Engine addEngine(HttpServletRequest request);

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
     * @param request user's request.
     * @return updated engine.
     */
    Engine updateEngine(Engine engine, HttpServletRequest request);

    /**
     * Gets iterable collection, that contains all engines in database.
     *
     * @return all engines in database.
     */
    Iterable<Engine> getAllEngines();
}
