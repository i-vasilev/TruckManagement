package ru.vasilev.webinnovations.truckManagement.controllers;

import org.springframework.web.bind.annotation.*;
import ru.vasilev.webinnovations.truckManagement.data.Bodywork;
import ru.vasilev.webinnovations.truckManagement.data.Brand;
import ru.vasilev.webinnovations.truckManagement.database.repository.BodyworkRepository;
import ru.vasilev.webinnovations.truckManagement.database.repository.BrandRepository;
import ru.vasilev.webinnovations.truckManagement.service.BodyworkService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/bodywork")
public class BodyworkController {

    private final BodyworkRepository bodyworkRepository;
    private final BrandRepository brandRepository;
    private final BodyworkService bodyworkService;


    public BodyworkController(BodyworkRepository bodyworkRepository, BrandRepository brandRepository, BodyworkService bodyworkService) {
        this.bodyworkRepository = bodyworkRepository;
        this.brandRepository = brandRepository;
        this.bodyworkService = bodyworkService;
    }

    @GetMapping
    public Iterable<Bodywork> getAllBodyworks() {
        return bodyworkService.getAllBodywork();
    }

    @GetMapping("/{id}")
    public Bodywork getBodywork(@PathVariable int id) {
        return bodyworkService.getBodywork(id);
    }


    @PostMapping
    public Bodywork addBodywork(HttpServletRequest request) {
        final String brandIdStr = bodyworkService.getParameter(request, "brand");
        int brandId = Integer.parseInt(brandIdStr);
        final String model = bodyworkService.getParameter(request, "model");
        Brand brand = brandRepository.findBrandById(brandId);
        return bodyworkService.addBodywork(model, brand);
    }

    @PutMapping("/{id}")
    public Bodywork updateBodywork(HttpServletRequest request, @PathVariable int id) {
        final Bodywork bodywork = bodyworkService.getBodywork(id);
        bodyworkService.updateBodywork(bodywork, request);
        return bodywork;
    }
}
