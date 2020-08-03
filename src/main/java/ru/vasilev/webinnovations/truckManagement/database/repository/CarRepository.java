package ru.vasilev.webinnovations.truckManagement.database.repository;

import org.springframework.data.repository.CrudRepository;
import ru.vasilev.webinnovations.truckManagement.data.Car;

public interface CarRepository extends CrudRepository<Car, Integer> {
    Car findCarById(int id);
}
