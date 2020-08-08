package ru.vasilev.webinnovations.truckManagement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.vasilev.webinnovations.truckManagement.controllers.UnitController;
import ru.vasilev.webinnovations.truckManagement.data.Unit;
import ru.vasilev.webinnovations.truckManagement.exceptions.EntityNotFoundException;
import ru.vasilev.webinnovations.truckManagement.service.UnitService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UnitController.class)
class UnitControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UnitService unitService;

    private final List<Unit> units = new ArrayList<>();

    @BeforeEach
    public void initUnits() {
        Unit unit = new Unit();
        unit.setUnitName("someUnit 1");
        unit.setId(1);
        units.add(unit);

        unit = new Unit();
        unit.setId(2);
        unit.setUnitName("Some unit 2");
        units.add(unit);
    }

    @Test
    void testGetAllUnits() throws Exception {
        when(this.unitService.getAllUnits()).thenReturn(units);
        mockMvc.perform(get("/unit"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testUnitNotFound() throws Exception {
        int id = -1;
        given(this.unitService.getUnit(id)).willThrow(EntityNotFoundException.class);
        mockMvc.perform(get("/unit/" + id))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testUnitFound() throws Exception {
        int id = 5;
        Unit unitWithName = new Unit();
        unitWithName.setId(id);
        unitWithName.setUnitName("NameUnit");
        given(unitService.getUnit(id)).willReturn(unitWithName);
        mockMvc.perform(get("/unit/" + id).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.unitName").value(unitWithName.getUnitName()));
    }

    @Test
    void testAddUnit() throws Exception {
        Unit unit = new Unit();
        unit.setId(1);
        unit.setUnitName("TestUnit");

        when(unitService.addUnit(any())).thenReturn(unit);
        mockMvc.perform(post("/unit")
                .param("unit_name", "TestUnit"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.unitName").value(unit.getUnitName()))
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void testUpdateUnit() throws Exception {
        Unit unit = new Unit();
        unit.setId(5);
        unit.setUnitName("New unit's name");

        when(unitService.updateUnit(anyInt(), anyString())).thenReturn(unit);
        mockMvc.perform(put("/unit/5")
                .param("unit_name", "New unit's name"))
                .andDo(print())
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.unitName").value(unit.getUnitName()));
    }
}
