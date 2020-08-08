package ru.vasilev.webinnovations.truckManagement.service;

import ru.vasilev.webinnovations.truckManagement.data.Car;

/**
 * Interface for service that works with brand entity.
 */
public interface CarService {
    /**
     * Executes adding into database.
     *
     * @param car Entity for inserting into database.
     * @return new brand from db.
     */
    Car addCar(Car car);

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
     * @param car Entity that updates in database.
     * @return updated car.
     */
    Car updateCar(Car car);

    /**
     * Gets iterable collection, that contains all cars in database.
     *
     * @return all cars in database.
     */
    Iterable<Car> getAllCars();
}
