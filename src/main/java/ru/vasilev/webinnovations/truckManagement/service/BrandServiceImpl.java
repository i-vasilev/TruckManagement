package ru.vasilev.webinnovations.truckManagement.service;

import org.springframework.stereotype.Service;
import ru.vasilev.webinnovations.truckManagement.data.Brand;
import ru.vasilev.webinnovations.truckManagement.database.repository.BrandRepository;
import ru.vasilev.webinnovations.truckManagement.exceptions.EntityNotFoundException;

/**
 * Service that works with brand entity.
 */
@Service
public class BrandServiceImpl extends HttpRequestServiceImpl implements BrandService {
    private final BrandRepository brandRepository;

    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    /**
     * Executes adding into database.
     *
     * @param brandName brand's name.
     * @return new brand from db.
     */
    @Override
    public Brand addBrand(String brandName) {
        final Brand brand = new Brand();
        brand.setBrandName(brandName);
        return brandRepository.save(brand);
    }

    /**
     * Gets brand by its id.
     *
     * @param id brand's id.
     * @return brand with id.
     */
    @Override
    public Brand getBrand(int id) {
        final Brand brand = brandRepository.findBrandById(id);
        if (brand == null) {
            throw new EntityNotFoundException("Brand not found.");
        }
        return brand;
    }

    /**
     * Updates brand in database.
     *
     * @param id        brand's id.
     * @param brandName brand's name.
     * @return updated brand.
     */
    @Override
    public Brand updateBrand(int id, String brandName) {
        final Brand brand = getBrand(id);
        brand.setBrandName(brandName);
        brandRepository.save(brand);
        return brand;
    }

    /**
     * Gets iterable collection, that contains all brands in database.
     *
     * @return all brands in database.
     */
    @Override
    public Iterable<Brand> getAllBrands() {
        return brandRepository.findAll();
    }
}
