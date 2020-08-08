package ru.vasilev.webinnovations.truckManagement.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.vasilev.webinnovations.truckManagement.controllers.CarController;
import ru.vasilev.webinnovations.truckManagement.data.Bodywork;
import ru.vasilev.webinnovations.truckManagement.data.Brand;
import ru.vasilev.webinnovations.truckManagement.data.Car;
import ru.vasilev.webinnovations.truckManagement.data.Engine;
import ru.vasilev.webinnovations.truckManagement.exceptions.EntityNotFoundException;
import ru.vasilev.webinnovations.truckManagement.service.BodyworkService;
import ru.vasilev.webinnovations.truckManagement.service.CarService;
import ru.vasilev.webinnovations.truckManagement.service.EngineService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CarController.class)
public class CarControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    private CarService carService;

    @MockBean
    private EngineService engineService;

    @MockBean
    private BodyworkService bodyworkService;

    private Bodywork bodywork;
    private Engine engine;

    private final List<Car> cars = new ArrayList<>();

    @BeforeEach
    public void initCars() {
        engine = new Engine();
        engine.setId(1);
        engine.setName("Test engine's updated name");
        engine.setPower(1550);

        Brand brand = new Brand();
        brand.setBrandName("Test brand's updated name");

        bodywork = new Bodywork();
        bodywork.setId(4);
        bodywork.setBrand(brand);

        Engine engineInCars = new Engine();
        engineInCars.setId(1);
        engineInCars.setName("Test engine's name");
        engineInCars.setPower(100);

        Brand brandInCars = new Brand();
        brandInCars.setBrandName("Test brand's name");

        Bodywork bodyworkInCars = new Bodywork();
        bodyworkInCars.setBrand(brandInCars);

        Car car = new Car();
        car.setId(6);
        car.setEngine(engineInCars);
        car.setBodywork(bodyworkInCars);
        cars.add(car);
    }

    @Test
    public void testGetAllCars() throws Exception {
        when(carService.getAllCars()).thenReturn(cars);

        final Car car = cars.get(0);
        mockMvc.perform(get("/car"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].bodywork.brand.brandName").value(car.getBodywork().getBrand().getBrandName()))
                .andExpect(jsonPath("$[0].engine.power").value(car.getEngine().getPower()))
                .andExpect(jsonPath("$[0].engine.name").value(car.getEngine().getName()));
    }

    @Test
    public void testGetCarFound() throws Exception {
        final Car car = cars.get(0);
        when(carService.getCar(6)).thenReturn(car);

        mockMvc.perform(get("/car/6"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.bodywork.brand.brandName").value(car.getBodywork().getBrand().getBrandName()))
                .andExpect(jsonPath("$.engine.power").value(car.getEngine().getPower()))
                .andExpect(jsonPath("$.engine.name").value(car.getEngine().getName()));
    }

    @Test
    public void testGetCarNotFound() throws Exception {
        when(carService.getCar(-1)).thenThrow(new EntityNotFoundException("Entity not found"));

        mockMvc.perform(get("/car/-1"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Entity not found")));
    }

    @Test
    public void testAddCar() throws Exception {
        final Car car = cars.get(0);

        when(engineService.getEngine(1)).thenReturn(engine);
        when(bodyworkService.getBodywork(4)).thenReturn(bodywork);
        when(carService.addCar(car)).thenReturn(car);

        mockMvc.perform(post("/car")
                .param("engine_id", String.valueOf(1))
                .param("bodywork_id", String.valueOf(4)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.bodywork.brand.brandName").value(bodywork.getBrand().getBrandName()))
                .andExpect(jsonPath("$.engine.power").value(engine.getPower()));
    }

    @Test
    public void testUpdatedCar() throws Exception {
        final Car car = cars.get(0);
        when(carService.getCar(6)).thenReturn(car);
        when(carService.updateCar(car)).thenReturn(car);
        when(engineService.getEngine(1)).thenReturn(engine);
        when(bodyworkService.getBodywork(4)).thenReturn(bodywork);

        mockMvc.perform(put("/car/6")
                .param("engine_id", String.valueOf(1))
                .param("bodywork_id", String.valueOf(4)))
                .andDo(print())
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.bodywork.brand.brandName").value(bodywork.getBrand().getBrandName()));
    }
}
