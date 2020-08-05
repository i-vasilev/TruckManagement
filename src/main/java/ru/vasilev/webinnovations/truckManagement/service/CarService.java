package ru.vasilev.webinnovations.truckManagement.service;

import org.springframework.http.ResponseEntity;
import ru.vasilev.webinnovations.truckManagement.data.Car;

import javax.servlet.http.HttpServletRequest;

/**
 * Interface for service that works with brand entity.
 */
public interface CarService extends HttpRequestService {
    /**
     * Executes adding into database.
     *
     * @param request User's request.
     * @return new brand from db.
     */
    Car addCar(HttpServletRequest request);

    /**
     * Gets car by its id.
     *
     * @param id car's id.
     * @return car with id.
     */
    Car getCar(int id);

    /**
     * Updates car in database.
     *
     * @param request user's HTTP request.
     * @return updated car.
     */
    Car updateCar(int id, HttpServletRequest request);

    /**
     * Gets iterable collection, that contains all cars in database.
     *
     * @return all cars in database.
     */
    Iterable<Car> getAllCars();
}
