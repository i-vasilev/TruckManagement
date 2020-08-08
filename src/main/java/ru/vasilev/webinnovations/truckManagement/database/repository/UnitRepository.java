package ru.vasilev.webinnovations.truckManagement.database.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.vasilev.webinnovations.truckManagement.data.Unit;

/**
 * Repository for working with {@link Unit} entity
 */
@Repository
public interface UnitRepository extends CrudRepository<Unit, Integer> {
    /**
     * Finds unit by its id.
     *
     * @param id unit's id.
     * @return unit from DB.
     */
    Unit findById(int id);
}
