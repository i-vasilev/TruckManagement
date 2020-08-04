package ru.vasilev.webinnovations.truckManagement.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.vasilev.webinnovations.truckManagement.data.Brand;
import ru.vasilev.webinnovations.truckManagement.service.BrandServiceImpl;

import javax.servlet.http.HttpServletRequest;

@RestController
public class BrandController {
    private final BrandServiceImpl brandService;


    public BrandController(BrandServiceImpl brandService) {
        this.brandService = brandService;
    }

    @GetMapping("/brand")
    public Iterable<Brand> getAllBrands() {
        return brandService.getAllBrands();
    }

    @PostMapping(value = "/brand", produces = MediaType.APPLICATION_JSON_VALUE)
    public Brand addBrand(HttpServletRequest request) {
        final String brandName = brandService.getParameter(request, "brand_name");
        return brandService.addBrand(brandName);
    }

    @GetMapping("/brand/{id}")
    public Brand getBrand(@PathVariable int id) {
        return brandService.getBrand(id);
    }

    @PutMapping("/brand/{id}")
    public Brand updateBrand(HttpServletRequest request, @PathVariable int id) {
        final String brandName = brandService.getParameter(request, "brand_name");
        return brandService.updateBrand(id, brandName);
    }

}
