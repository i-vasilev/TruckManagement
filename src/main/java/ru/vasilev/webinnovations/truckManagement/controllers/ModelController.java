package ru.vasilev.webinnovations.truckManagement.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.vasilev.webinnovations.truckManagement.data.Model;
import ru.vasilev.webinnovations.truckManagement.database.repository.ModelRepository;
import ru.vasilev.webinnovations.truckManagement.exceptions.EntityNotFoundException;
import ru.vasilev.webinnovations.truckManagement.exceptions.FieldsIsAbsentException;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/model")
public class ModelController {
    private final ModelRepository modelRepository;

    public ModelController(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    @GetMapping("/")
    public Iterable<Model> getAllModels() {
        return modelRepository.findAll();
    }

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public Model addModel(HttpServletRequest request) {
        final String modelName = getModelName(request);
        final Model model = new Model();
        model.setModelName(modelName);
        modelRepository.save(model);
        return model;
    }

    @GetMapping("/{id}")
    public Model getModel(@PathVariable int id) {
        return getModelById(id);
    }

    @PutMapping("/{id}")
    public Model updateModel(HttpServletRequest request, @PathVariable int id) {
        final String modelName = getModelName(request);
        final Model model = getModelById(id);
        model.setModelName(modelName);
        modelRepository.save(model);
        return model;
    }

    private Model getModelById(int id) {
        final Model model = modelRepository.findModelById(id);
        if (model == null) {
            throw new EntityNotFoundException("Model not found.");
        }
        return model;
    }

    private String getModelName(HttpServletRequest request) {
        final String modelName = request.getParameter("model_name");
        if (modelName == null) {
            throw new FieldsIsAbsentException("Mandatory field model_name is absent");
        }
        return modelName;
    }
}
