package ru.vasilev.webinnovations.truckManagement.controllers;

import org.springframework.web.bind.annotation.*;
import ru.vasilev.webinnovations.truckManagement.data.Car;
import ru.vasilev.webinnovations.truckManagement.database.repository.BodyworkRepository;
import ru.vasilev.webinnovations.truckManagement.database.repository.CarRepository;
import ru.vasilev.webinnovations.truckManagement.database.repository.EngineRepository;
import ru.vasilev.webinnovations.truckManagement.service.CarService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/car")
public class CarController {
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public Iterable<Car> getAllCars() {
        return carService.getAllCars();
    }

    @GetMapping("/{id}")
    public Car getCar(@PathVariable int id) {
        return carService.getCar(id);
    }

    @PostMapping
    public Car addCar(HttpServletRequest request) {
        return carService.addCar(request);
    }

    @PutMapping("/{id}")
    public Car updateCar(@PathVariable("id") int id, HttpServletRequest request) {
        return carService.updateCar(id, request);
    }
}
