package ru.vasilev.webinnovations.truckManagement.database.repository;

import org.springframework.data.repository.CrudRepository;
import ru.vasilev.webinnovations.truckManagement.data.Brand;
import ru.vasilev.webinnovations.truckManagement.data.Model;

public interface ModelRepository extends CrudRepository<Model, Integer> {

    Model findModelById(int id);
}
