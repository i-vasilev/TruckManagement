package ru.vasilev.webinnovations.truckManagement.service;

import ru.vasilev.webinnovations.truckManagement.data.Bodywork;
import ru.vasilev.webinnovations.truckManagement.data.Car;
import ru.vasilev.webinnovations.truckManagement.data.Engine;
import ru.vasilev.webinnovations.truckManagement.database.repository.CarRepository;
import ru.vasilev.webinnovations.truckManagement.exceptions.EntityNotFoundException;
import ru.vasilev.webinnovations.truckManagement.exceptions.FieldsIsAbsentException;

import javax.servlet.http.HttpServletRequest;

public class CarServiceImpl extends HttpRequestServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final EngineService engineService;
    private final BodyworkService bodyworkService;

    public CarServiceImpl(CarRepository carRepository, EngineService engineService, BodyworkService bodyworkService) {
        this.carRepository = carRepository;
        this.engineService = engineService;
        this.bodyworkService = bodyworkService;
    }

    @Override
    public Car addCar(HttpServletRequest request) {
        final String engineIdStr = getParameter(request, "engine_id");
        final int engineId = Integer.parseInt(engineIdStr);
        final Engine engine = engineService.getEngine(engineId);

        final String bodyworkIdStr = getParameter(request, "bodywork_id");
        final int bodyworkId = Integer.parseInt(bodyworkIdStr);
        final Bodywork bodywork = bodyworkService.getBodywork(bodyworkId);

        final Car car = new Car();
        car.setBodywork(bodywork);
        car.setEngine(engine);
        carRepository.save(car);
        return car;
    }

    @Override
    public Car getCar(int id) {
        return carRepository.findCarById(id);
    }

    @Override
    public Car updateCar(int id, HttpServletRequest request) {
        final Car car = carRepository.findCarById(id);
        if (car == null) {
            throw new EntityNotFoundException("Car is not found");
        }
        if (request.getParameterMap().isEmpty()) {
            throw new FieldsIsAbsentException("At least one of the parameters mustn't be empty!");
        }
        final String engineIdStr = request.getParameter("engine_id");
        if (engineIdStr != null) {
            final int engineId = Integer.parseInt(engineIdStr);
            final Engine engine = engineService.getEngine(engineId);
        }
        final String bodyworkIdStr = request.getParameter("bodywork_id");
        if (bodyworkIdStr != null) {
            final int bodyworkId = Integer.parseInt(bodyworkIdStr);
            final Bodywork bodywork = bodyworkService.getBodywork(bodyworkId);
            car.setBodywork(bodywork);
        }
        carRepository.save(car);
        return car;
    }

    @Override
    public Iterable<Car> getAllCars() {
        return carRepository.findAll();
    }
}
