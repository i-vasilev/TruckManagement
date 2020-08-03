package ru.vasilev.webinnovations.truckManagement.database.repository;

import org.springframework.data.repository.CrudRepository;
import ru.vasilev.webinnovations.truckManagement.data.Engine;

public interface EngineRepository extends CrudRepository<Engine, Integer> {
    Engine getEngineById(int id);
}
