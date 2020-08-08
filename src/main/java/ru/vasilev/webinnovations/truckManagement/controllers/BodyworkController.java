package ru.vasilev.webinnovations.truckManagement.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vasilev.webinnovations.truckManagement.data.Bodywork;
import ru.vasilev.webinnovations.truckManagement.data.Brand;
import ru.vasilev.webinnovations.truckManagement.exceptions.FieldsIsAbsentException;
import ru.vasilev.webinnovations.truckManagement.service.BodyworkService;
import ru.vasilev.webinnovations.truckManagement.service.BrandService;

import javax.servlet.http.HttpServletRequest;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/bodywork")
public class BodyworkController extends RestController {

    private final BodyworkService bodyworkService;
    private final BrandService brandService;

    public BodyworkController(BodyworkService bodyworkService, BrandService brandService) {
        this.bodyworkService = bodyworkService;
        this.brandService = brandService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Bodywork>> getAllBodyworks() {
        final Iterable<Bodywork> allBodywork = bodyworkService.getAllBodywork();
        return new ResponseEntity<>(allBodywork, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Bodywork> getBodywork(@PathVariable int id) {
        final Bodywork bodywork = bodyworkService.getBodywork(id);
        return new ResponseEntity<>(bodywork, HttpStatus.OK);
    }


    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Bodywork> addBodywork(HttpServletRequest request) {
        final String brandIdStr = getParameter(request, "brand");
        int brandId = Integer.parseInt(brandIdStr);

        final String model = getParameter(request, "model");
        final Brand brand = brandService.getBrand(brandId);

        final Bodywork bodywork = new Bodywork();
        bodywork.setModel(model);
        bodywork.setBrand(brand);
        bodyworkService.addBodywork(bodywork);

        return new ResponseEntity<>(bodywork, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Bodywork> updateBodywork(HttpServletRequest request, @PathVariable int id) {
        Bodywork bodywork = bodyworkService.getBodywork(id);

        if (request.getParameterMap().isEmpty()) {
            throw new FieldsIsAbsentException("At least one of the parameters mustn't be empty!");
        }

        final String model = request.getParameter("model");
        if (model != null) {
            bodywork.setModel(model);
        }
        final String brandIdStr = request.getParameter("brand");
        if (brandIdStr != null) {
            final Brand brand = brandService.getBrand(Integer.parseInt(brandIdStr));
            bodywork.setBrand(brand);
        }

        bodywork = bodyworkService.updateBodywork(bodywork);
        return new ResponseEntity<>(bodywork, HttpStatus.ACCEPTED);
    }
}
