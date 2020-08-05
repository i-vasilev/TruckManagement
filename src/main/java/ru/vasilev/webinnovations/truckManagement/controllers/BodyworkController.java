package ru.vasilev.webinnovations.truckManagement.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vasilev.webinnovations.truckManagement.data.Bodywork;
import ru.vasilev.webinnovations.truckManagement.data.Brand;
import ru.vasilev.webinnovations.truckManagement.database.repository.BrandRepository;
import ru.vasilev.webinnovations.truckManagement.service.BodyworkService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/bodywork")
public class BodyworkController {

    private final BodyworkService bodyworkService;


    public BodyworkController(BodyworkService bodyworkService) {
        this.bodyworkService = bodyworkService;
    }

    @GetMapping
    public ResponseEntity<Iterable<Bodywork>> getAllBodyworks() {
        final Iterable<Bodywork> allBodywork = bodyworkService.getAllBodywork();
        return new ResponseEntity<>(allBodywork, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bodywork> getBodywork(@PathVariable int id) {
        final Bodywork bodywork = bodyworkService.getBodywork(id);
        return new ResponseEntity<>(bodywork, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Bodywork> addBodywork(HttpServletRequest request) {
        final Bodywork bodywork = bodyworkService.addBodywork(request);
        return new ResponseEntity<>(bodywork, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bodywork> updateBodywork(HttpServletRequest request, @PathVariable int id) {
        Bodywork bodywork = bodyworkService.getBodywork(id);
        bodywork = bodyworkService.updateBodywork(bodywork, request);
        return new ResponseEntity<>(bodywork, HttpStatus.ACCEPTED);
    }
}
