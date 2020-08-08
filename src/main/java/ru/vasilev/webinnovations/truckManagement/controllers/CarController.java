package ru.vasilev.webinnovations.truckManagement.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vasilev.webinnovations.truckManagement.data.Bodywork;
import ru.vasilev.webinnovations.truckManagement.data.Car;
import ru.vasilev.webinnovations.truckManagement.data.Engine;
import ru.vasilev.webinnovations.truckManagement.exceptions.FieldsIsAbsentException;
import ru.vasilev.webinnovations.truckManagement.service.BodyworkService;
import ru.vasilev.webinnovations.truckManagement.service.CarService;
import ru.vasilev.webinnovations.truckManagement.service.EngineService;
import ru.vasilev.webinnovations.truckManagement.service.HttpRequestServiceImpl;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/car")
public class CarController extends HttpRequestServiceImpl {
    private final CarService carService;
    private final EngineService engineService;
    private final BodyworkService bodyworkService;

    public CarController(CarService carService, EngineService engineService, BodyworkService bodyworkService) {
        this.carService = carService;
        this.engineService = engineService;
        this.bodyworkService = bodyworkService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Car>> getAllCars() {
        final Iterable<Car> allCars = carService.getAllCars();
        return new ResponseEntity<>(allCars, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Car> getCar(@PathVariable int id) {
        final Car car = carService.getCar(id);
        return new ResponseEntity<>(car, HttpStatus.OK);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Car> addCar(HttpServletRequest request) {
        final String engineIdStr = getParameter(request, "engine_id");
        final int engineId = Integer.parseInt(engineIdStr);
        final Engine engine = engineService.getEngine(engineId);

        final String bodyworkIdStr = getParameter(request, "bodywork_id");
        final int bodyworkId = Integer.parseInt(bodyworkIdStr);
        final Bodywork bodywork = bodyworkService.getBodywork(bodyworkId);

        final Car car = new Car();
        car.setBodywork(bodywork);
        car.setEngine(engine);
        carService.addCar(car);

        return new ResponseEntity<>(car, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Car> updateCar(@PathVariable("id") int id, HttpServletRequest request) {
        final Car car = carService.getCar(id);
        if (request.getParameterMap().isEmpty()) {
            throw new FieldsIsAbsentException("At least one of the parameters mustn't be empty!");
        }

        final String engineIdStr = request.getParameter("engine_id");
        if (engineIdStr != null) {
            final int engineId = Integer.parseInt(engineIdStr);
            final Engine engine = engineService.getEngine(engineId);
            car.setEngine(engine);
        }

        final String bodyworkIdStr = request.getParameter("bodywork_id");
        if (bodyworkIdStr != null) {
            final int bodyworkId = Integer.parseInt(bodyworkIdStr);
            final Bodywork bodywork = bodyworkService.getBodywork(bodyworkId);
            car.setBodywork(bodywork);
        }

        carService.updateCar(car);
        return new ResponseEntity<>(car, HttpStatus.ACCEPTED);
    }
}
