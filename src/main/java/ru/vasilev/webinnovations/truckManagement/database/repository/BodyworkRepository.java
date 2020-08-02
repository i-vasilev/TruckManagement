package ru.vasilev.webinnovations.truckManagement.database.repository;

import org.springframework.data.repository.CrudRepository;
import ru.vasilev.webinnovations.truckManagement.data.Bodywork;

public interface BodyworkRepository extends CrudRepository<Bodywork, Integer> {
    Bodywork getById(int id);
}
