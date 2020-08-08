package ru.vasilev.webinnovations.truckManagement.service;

import ru.vasilev.webinnovations.truckManagement.data.Brand;

/**
 * Interface for service that works with brand entity.
 */
public interface BrandService {
    /**
     * Executes adding into database.
     *
     * @param brandName brand's name.
     * @return new brand from db.
     */
    Brand addBrand(String brandName);

    /**
     * Gets brand by its id.
     *
     * @param id brand's id.
     * @return brand with id.
     */
    Brand getBrand(int id);

    /**
     * Updates brand in database.
     *
     * @param id        brand's id.
     * @param brandName brand's name.
     * @return updated brand.
     */
    Brand updateBrand(int id, String brandName);

    /**
     * Gets iterable collection, that contains all brands in database.
     *
     * @return all brands in database.
     */
    Iterable<Brand> getAllBrands();
}
