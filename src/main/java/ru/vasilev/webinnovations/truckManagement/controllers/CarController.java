package ru.vasilev.webinnovations.truckManagement.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vasilev.webinnovations.truckManagement.data.Car;
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
    public ResponseEntity<Iterable<Car>> getAllCars() {
        final Iterable<Car> allCars = carService.getAllCars();
        return new ResponseEntity<>(allCars, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCar(@PathVariable int id) {
        final Car car = carService.getCar(id);
        return new ResponseEntity<>(car, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Car> addCar(HttpServletRequest request) {
        final Car car = carService.addCar(request);
        return new ResponseEntity<>(car, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable("id") int id, HttpServletRequest request) {
        final Car car = carService.updateCar(id, request);
        return new ResponseEntity<>(car, HttpStatus.ACCEPTED);
    }
}
