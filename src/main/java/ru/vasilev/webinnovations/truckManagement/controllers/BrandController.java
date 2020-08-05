package ru.vasilev.webinnovations.truckManagement.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vasilev.webinnovations.truckManagement.data.Brand;
import ru.vasilev.webinnovations.truckManagement.service.BrandService;

import javax.servlet.http.HttpServletRequest;

@RestController
public class BrandController {
    private final BrandService brandService;


    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping(value = "/brand", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Brand>> getAllBrands() {
        final Iterable<Brand> allBrands = brandService.getAllBrands();
        return new ResponseEntity<>(allBrands, HttpStatus.OK);
    }

    @PostMapping(value = "/brand", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Brand> addBrand(HttpServletRequest request) {
        final String brandName = brandService.getParameter(request, "brand_name");
        final Brand brand = brandService.addBrand(brandName);
        return new ResponseEntity<>(brand, HttpStatus.CREATED);
    }

    @GetMapping(value = "/brand/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Brand> getBrand(@PathVariable int id) {
        final Brand brand = brandService.getBrand(id);
        return new ResponseEntity<>(brand, HttpStatus.OK);
    }

    @PutMapping(value = "/brand/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Brand> updateBrand(HttpServletRequest request, @PathVariable int id) {
        final String brandName = brandService.getParameter(request, "brand_name");
        final Brand brand = brandService.updateBrand(id, brandName);
        return new ResponseEntity<>(brand, HttpStatus.ACCEPTED);
    }

}
