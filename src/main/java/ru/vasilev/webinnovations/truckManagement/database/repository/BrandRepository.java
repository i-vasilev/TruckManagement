package ru.vasilev.webinnovations.truckManagement.database.repository;

import org.springframework.data.repository.CrudRepository;
import ru.vasilev.webinnovations.truckManagement.data.Brand;

public interface BrandRepository extends CrudRepository<Brand, Integer> {
    Brand findBrandById(int id);
}
