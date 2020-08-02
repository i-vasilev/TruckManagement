package ru.vasilev.webinnovations.truckManagement.controllers;

import org.springframework.web.bind.annotation.*;
import ru.vasilev.webinnovations.truckManagement.data.Bodywork;
import ru.vasilev.webinnovations.truckManagement.data.Brand;
import ru.vasilev.webinnovations.truckManagement.database.repository.BodyworkRepository;
import ru.vasilev.webinnovations.truckManagement.database.repository.BrandRepository;
import ru.vasilev.webinnovations.truckManagement.exceptions.EntityNotFoundException;
import ru.vasilev.webinnovations.truckManagement.exceptions.FieldsIsAbsentException;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/bodywork")
public class BodyworkController {

    private final BodyworkRepository bodyworkRepository;
    private final BrandRepository brandRepository;

    public BodyworkController(BodyworkRepository bodyworkRepository, BrandRepository brandRepository) {
        this.bodyworkRepository = bodyworkRepository;
        this.brandRepository = brandRepository;
    }

    @GetMapping
    public Iterable<Bodywork> getAllBodyworks() {
        return bodyworkRepository.findAll();
    }

    @PostMapping
    public Bodywork addBodywork(HttpServletRequest request) {
        final String brandIdStr = getBrand(request, "brand");
        int brandId = Integer.parseInt(brandIdStr);
        final String model = getBrand(request, "model");
        Brand brand = brandRepository.findBrandById(brandId);
        Bodywork bodywork = new Bodywork();
        bodywork.setModel(model);
        bodywork.setBrand(brand);
        bodyworkRepository.save(bodywork);
        return bodywork;
    }

    @PutMapping("/{id}")
    public Bodywork updateBodywork(HttpServletRequest request, @PathVariable int id) {
        final Bodywork bodywork = bodyworkRepository.getById(id);
        if (bodywork == null) {
            throw new EntityNotFoundException("Bodywork isn't found in database!");
        }
        if (request.getParameterMap().isEmpty()) {
            throw new FieldsIsAbsentException("At least one of the parameters mustn't be empty!");
        }

        final String model = request.getParameter("model");
        if (model != null) {
            bodywork.setModel(model);
        }

        final String brandIdStr = request.getParameter("brand");

        if (brandIdStr != null) {
            final Brand brand = brandRepository.findBrandById(Integer.parseInt(brandIdStr));
            if (brand != null) {
                bodywork.setBrand(brand);
            } else {
                throw new EntityNotFoundException("Brand not found!");
            }
        }

        bodyworkRepository.save(bodywork);
        return bodywork;
    }

    private String getBrand(HttpServletRequest request, String parameter) {
        final String brand = request.getParameter(parameter);
        if (brand == null) {
            throw new FieldsIsAbsentException(String.format("Mandatory field \"%s\" is absent", parameter));
        }
        return brand;
    }
}
