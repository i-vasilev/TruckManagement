package ru.vasilev.webinnovations.truckManagement.service;

import ru.vasilev.webinnovations.truckManagement.data.Bodywork;

import javax.servlet.http.HttpServletRequest;

/**
 * Interface for service that works with bodywork entity.
 */
public interface BodyworkService {

    /**
     * Executes adding bodywork into database.
     *
     * @param bodywork Entity for inserting into database.
     * @return new bodywork from db.
     */
    Bodywork addBodywork(Bodywork bodywork);

    /**
     * Gets bodywork by its id.
     *
     * @param id bodywork's id.
     * @return bodywork with id.
     */
    Bodywork getBodywork(int id);

    /**
     * Updates bodywork in db.
     *
     * @param bodywork {@link Bodywork} for updating.
     * @return Updated bodywork.
     */
    Bodywork updateBodywork(Bodywork bodywork);

    /**
     * Gets iterable collection, that contains all bodyworks in database.
     *
     * @return all bodyworks in database.
     */
    Iterable<Bodywork> getAllBodywork();
}
