package ru.vasilev.webinnovations.truckManagement.service;

import ru.vasilev.webinnovations.truckManagement.data.Car;
import ru.vasilev.webinnovations.truckManagement.database.repository.CarRepository;

/**
 * Service that works with brand entity.
 */
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    /**
     * Executes adding into database.
     *
     * @param car Entity for inserting into database.
     * @return new brand from db.
     */
    @Override
    public Car addCar(Car car) {
        carRepository.save(car);
        return car;
    }

    /**
     * Gets car by its id.
     *
     * @param id car's id.
     * @return car with id.
     */
    @Override
    public Car getCar(int id) {
        return carRepository.findCarById(id);
    }

    /**
     * Updates car in database.
     *
     * @param car Entity that updates in database.
     * @return updated car.
     */
    @Override
    public Car updateCar(Car car) {

        carRepository.save(car);
        return car;
    }

    /**
     * Gets iterable collection, that contains all cars in database.
     *
     * @return all cars in database.
     */
    @Override
    public Iterable<Car> getAllCars() {
        return carRepository.findAll();
    }
}
