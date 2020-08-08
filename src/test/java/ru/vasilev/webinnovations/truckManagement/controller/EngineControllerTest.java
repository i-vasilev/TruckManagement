package ru.vasilev.webinnovations.truckManagement.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.vasilev.webinnovations.truckManagement.controllers.EngineController;
import ru.vasilev.webinnovations.truckManagement.data.Engine;
import ru.vasilev.webinnovations.truckManagement.data.Unit;
import ru.vasilev.webinnovations.truckManagement.exceptions.EntityNotFoundException;
import ru.vasilev.webinnovations.truckManagement.service.EngineService;
import ru.vasilev.webinnovations.truckManagement.service.UnitService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EngineController.class)
public class EngineControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EngineService engineService;

    @MockBean
    private UnitService unitService;

    private List<Engine> engines = new ArrayList<>();

    @BeforeEach
    public void initEngines() {
        Unit unit = new Unit();
        unit.setId(1);
        unit.setUnitName("Test unit");

        Engine engine = new Engine();
        engine.setId(1);
        engine.setPower(100);
        engine.setName("Engine 1");
        engine.setUnit(unit);
        engines.add(engine);

        engine = new Engine();
        engine.setId(2);
        engine.setPower(200);
        engine.setName("Engine 2");
        engine.setUnit(unit);
        engines.add(engine);
    }

    @Test
    public void testGetAllEngines() throws Exception {
        when(engineService.getAllEngines()).thenReturn(engines);

        mockMvc.perform(get("/engine"))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[1].id").value(engines.get(1).getId()))
                .andExpect(jsonPath("$[1].power").value(engines.get(1).getPower()))
                .andExpect(jsonPath("$[1].name").value(engines.get(1).getName()))
                .andExpect(jsonPath("$[1].unit.id").value(engines.get(1).getUnit().getId()))
                .andExpect(jsonPath("$[1].unit.unitName").value(engines.get(1).getUnit().getUnitName()));
    }

    @Test
    public void testGetEngineFound() throws Exception {
        when(engineService.getEngine(1)).thenReturn(engines.get(0));

        mockMvc.perform(get("/engine/1"))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(engines.get(0).getId()))
                .andExpect(jsonPath("$.power").value(engines.get(0).getPower()))
                .andExpect(jsonPath("$.name").value(engines.get(0).getName()));
    }

    @Test
    public void testGetEngineNotFound() throws Exception {
        when(engineService.getEngine(-1)).thenThrow(new EntityNotFoundException("Entity not found!"));

        mockMvc.perform(get("/engine/-1"))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Entity not found!"));
    }

    @Test
    public void testAddEngine() throws Exception {
        Engine engine = engines.get(0);

        when(unitService.getUnit(engine.getUnit().getId())).thenReturn(engine.getUnit());
        when(engineService.addEngine(engine)).thenReturn(engine);

        mockMvc.perform(post("/engine")
                .param("power", String.valueOf(engine.getPower()))
                .param("engine_name", engine.getName())
                .param("unit_id", String.valueOf(engine.getUnit().getId())))
                .andDo(print())
                .andExpect(jsonPath("$.power").value(engine.getPower()))
                .andExpect(jsonPath("$.name").value(engine.getName()));
    }

    @Test
    public void testUpdateEngine() throws Exception {
        Unit unit = new Unit();
        unit.setId(4);
        unit.setUnitName("Test unit updated");

        Engine engine = engines.get(0);

        when(engineService.getEngine(1)).thenReturn(engine);
        when(unitService.getUnit(4)).thenReturn(unit);
        when(engineService.updateEngine(engine)).thenReturn(engine);

        mockMvc.perform(put("/engine/1")
                .param("power", String.valueOf(150))
                .param("unit_id", String.valueOf(4)))
                .andDo(print())
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.power").value(150))
                .andExpect(jsonPath("$.unit.unitName").value("Test unit updated"));
    }
}
