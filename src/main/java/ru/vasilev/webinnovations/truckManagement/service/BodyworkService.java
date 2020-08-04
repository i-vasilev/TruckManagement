package ru.vasilev.webinnovations.truckManagement.service;

import ru.vasilev.webinnovations.truckManagement.data.Bodywork;
import ru.vasilev.webinnovations.truckManagement.data.Brand;

import javax.servlet.http.HttpServletRequest;

/**
 * Interface for service that works with bodywork entity.
 */
public interface BodyworkService extends HttpRequestService {

    /**
     * Executes adding bodywork into database.
     *
     * @param model bodywork's model.
     * @return new bodywork from db.
     */
    Bodywork addBodywork(String model, Brand brand);

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
     * @param request  User's request.
     * @return Updated bodywork.
     */
    public Bodywork updateBodywork(Bodywork bodywork, HttpServletRequest request);

    /**
     * Gets iterable collection, that contains all bodyworks in database.
     *
     * @return all bodyworks in database.
     */
    Iterable<Bodywork> getAllBodywork();
}
