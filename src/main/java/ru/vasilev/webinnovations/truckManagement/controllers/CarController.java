package ru.vasilev.webinnovations.truckManagement.controllers;

import org.springframework.web.bind.annotation.*;
import ru.vasilev.webinnovations.truckManagement.data.Bodywork;
import ru.vasilev.webinnovations.truckManagement.data.Car;
import ru.vasilev.webinnovations.truckManagement.data.Engine;
import ru.vasilev.webinnovations.truckManagement.database.repository.BodyworkRepository;
import ru.vasilev.webinnovations.truckManagement.database.repository.CarRepository;
import ru.vasilev.webinnovations.truckManagement.database.repository.EngineRepository;
import ru.vasilev.webinnovations.truckManagement.exceptions.EntityNotFoundException;
import ru.vasilev.webinnovations.truckManagement.exceptions.FieldsIsAbsentException;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
@RequestMapping("/car")
public class CarController {
    private final CarRepository carRepository;
    private final EngineRepository engineRepository;
    private final BodyworkRepository bodyworkRepository;

    public CarController(CarRepository carRepository, EngineRepository engineRepository, BodyworkRepository bodyworkRepository) {
        this.carRepository = carRepository;
        this.engineRepository = engineRepository;
        this.bodyworkRepository = bodyworkRepository;
    }

    @GetMapping
    public Iterable<Car> getAllCars() {
        return carRepository.findAll();
    }

    @GetMapping("/{id}")
    public Car getCar(@PathVariable int id) {
        return carRepository.findCarById(id);
    }

    @PostMapping
    public Car addCar(HttpServletRequest request) {
        final String engineIdStr = getParameter(request, "engine_id");
        final int engineId = Integer.parseInt(engineIdStr);
        final Engine engine = engineRepository.getEngineById(engineId);
        if (engine == null) {
            throw new EntityNotFoundException("Engine is not found!");
        }
        final String bodyworkIdStr = getParameter(request, "bodywork_id");
        final int bodyworkId = Integer.parseInt(bodyworkIdStr);
        final Bodywork bodywork = bodyworkRepository.getById(bodyworkId);
        if (bodywork == null) {
            throw new EntityNotFoundException("Bodywork is not found!");
        }
        final Car car = new Car();
        car.setBodywork(bodywork);
        car.setEngine(engine);
        carRepository.save(car);
        return car;
    }

    @PutMapping("/{id}")
    public Car updateCar(@PathVariable("id") int id, HttpServletRequest request) {
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
            final Engine engine = engineRepository.getEngineById(engineId);
            if (engine == null) {
                throw new EntityNotFoundException("Engine is not found!");
            }
            car.setEngine(engine);
        }
        final String bodyworkIdStr = request.getParameter("bodywork_id");
        if (bodyworkIdStr != null) {
            final int bodyworkId = Integer.parseInt(bodyworkIdStr);
            final Bodywork bodywork = bodyworkRepository.getById(bodyworkId);
            if (bodywork == null) {
                throw new EntityNotFoundException("Bodywork is not found!");
            }
            car.setBodywork(bodywork);
        }
        carRepository.save(car);
        return car;
    }

    private String getParameter(HttpServletRequest request, String parameterName) {
        final String parameter = request.getParameter(parameterName);
        if (parameter == null) {
            throw new FieldsIsAbsentException(String.format("Mandatory field \"%s\" is absent", parameter));
        }
        return parameter;
    }
}
