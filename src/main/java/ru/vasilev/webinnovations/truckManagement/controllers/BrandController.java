package ru.vasilev.webinnovations.truckManagement.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.vasilev.webinnovations.truckManagement.data.Brand;
import ru.vasilev.webinnovations.truckManagement.database.repository.BrandRepository;
import ru.vasilev.webinnovations.truckManagement.exceptions.EntityNotFoundException;
import ru.vasilev.webinnovations.truckManagement.exceptions.FieldsIsAbsentException;

import javax.servlet.http.HttpServletRequest;

@RestController
public class BrandController {
    private final BrandRepository brandRepository;

    public BrandController(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @GetMapping("/brand")
    public Iterable<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    @PostMapping(value = "/brand", produces = MediaType.APPLICATION_JSON_VALUE)
    public Brand addBrand(HttpServletRequest request) {
        final String brandName = getBrandName(request);
        final Brand brand = new Brand();
        brand.setBrandName(brandName);
        brandRepository.save(brand);
        return brand;
    }

    @GetMapping("/brand/{id}")
    public Brand getBrand(@PathVariable int id) {
        return getBrandById(id);
    }

    @PutMapping("/brand/{id}")
    public Brand updateBrand(HttpServletRequest request, @PathVariable int id) {
        final String brandName = getBrandName(request);
        final Brand brand = getBrandById(id);
        brand.setBrandName(brandName);
        brandRepository.save(brand);
        return brand;
    }

    private Brand getBrandById(int id) {
        final Brand brand = brandRepository.findBrandById(id);
        if (brand == null) {
            throw new EntityNotFoundException("Brand not found.");
        }
        return brand;
    }

    private String getBrandName(HttpServletRequest request) {
        final String unitName = request.getParameter("brand_name");
        if (unitName == null) {
            throw new FieldsIsAbsentException("Mandatory field brand_name is absent");
        }
        return unitName;
    }
}
