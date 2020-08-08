package ru.vasilev.webinnovations.truckManagement.service;

import ru.vasilev.webinnovations.truckManagement.data.Bodywork;
import ru.vasilev.webinnovations.truckManagement.data.Car;
import ru.vasilev.webinnovations.truckManagement.data.Engine;
import ru.vasilev.webinnovations.truckManagement.database.repository.CarRepository;
import ru.vasilev.webinnovations.truckManagement.exceptions.FieldsIsAbsentException;

import javax.servlet.http.HttpServletRequest;

public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final EngineService engineService;
    private final BodyworkService bodyworkService;

    public CarServiceImpl(CarRepository carRepository, EngineService engineService, BodyworkService bodyworkService) {
        this.carRepository = carRepository;
        this.engineService = engineService;
        this.bodyworkService = bodyworkService;
    }

    @Override
    public Car addCar(Car car) {
        carRepository.save(car);
        return car;
    }

    @Override
    public Car getCar(int id) {
        return carRepository.findCarById(id);
    }

    @Override
    public Car updateCar(Car car) {

        carRepository.save(car);
        return car;
    }

    @Override
    public Iterable<Car> getAllCars() {
        return carRepository.findAll();
    }
}
