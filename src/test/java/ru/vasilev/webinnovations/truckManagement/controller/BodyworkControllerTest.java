package ru.vasilev.webinnovations.truckManagement.controller;


import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.vasilev.webinnovations.truckManagement.controllers.BodyworkController;
import ru.vasilev.webinnovations.truckManagement.data.Bodywork;
import ru.vasilev.webinnovations.truckManagement.data.Brand;
import ru.vasilev.webinnovations.truckManagement.exceptions.EntityNotFoundException;
import ru.vasilev.webinnovations.truckManagement.service.BodyworkService;
import ru.vasilev.webinnovations.truckManagement.service.BrandService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BodyworkController.class)
public class BodyworkControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BodyworkService bodyworkService;

    @MockBean
    private BrandService brandService;

    private List<Bodywork> bodyworks = new ArrayList<>();

    @BeforeEach
    public void initBodyworks() {
        Brand brand = new Brand();
        brand.setId(1);
        brand.setBrandName("brand's name");

        Bodywork bodywork = new Bodywork();
        bodywork.setId(1);
        bodywork.setModel("First model");
        bodywork.setBrand(brand);
        bodyworks.add(bodywork);

        bodywork = new Bodywork();
        bodywork.setId(2);
        bodywork.setModel("Second brand's name");
        bodywork.setBrand(brand);
        bodyworks.add(bodywork);
    }

    @Test
    public void testGetAllBodyworks() throws Exception {
        when(bodyworkService.getAllBodywork()).thenReturn(bodyworks);

        mockMvc.perform(get("/bodywork"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(bodyworks.get(0).getId()))
                .andExpect(jsonPath("$[0].model").value(bodyworks.get(0).getModel()))
                .andExpect(jsonPath("$[0].brand.id").value(bodyworks.get(0).getBrand().getId()))
                .andExpect(jsonPath("$[0].brand.brandName").value(bodyworks.get(0).getBrand().getBrandName()));
    }

    @Test
    public void testGetBodyworkFound() throws Exception {
        final Bodywork bodywork = bodyworks.get(0);
        when(bodyworkService.getBodywork(bodywork.getId())).thenReturn(bodywork);

        mockMvc.perform(get("/bodywork/" + bodywork.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(bodywork.getId()))
                .andExpect(jsonPath("$.model").value(bodywork.getModel()))
                .andExpect(jsonPath("$.brand.id").value(bodywork.getBrand().getId()))
                .andExpect(jsonPath("$.brand.brandName").value(bodywork.getBrand().getBrandName()));
    }

    @Test
    public void testGetBodyworkNotFound() throws Exception {
        when(bodyworkService.getBodywork(-1)).thenThrow(new EntityNotFoundException("Entity not found!"));

        mockMvc.perform(get("/bodywork/-1"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(Matchers.containsString("Entity not found!")));

    }

    @Test
    public void testAddNewBodywork() throws Exception {
        Bodywork bodywork = bodyworks.get(0);
        when(bodyworkService.addBodywork(bodywork)).thenReturn(bodywork);
        when(brandService.getBrand(bodywork.getBrand().getId())).thenReturn(bodywork.getBrand());

        mockMvc.perform(post("/bodywork")
                .param("model", bodywork.getModel())
                .param("brand", String.valueOf(bodywork.getBrand().getId())))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.model").value(bodywork.getModel()))
                .andExpect(jsonPath("$.brand.id").value(bodywork.getBrand().getId()))
                .andExpect(jsonPath("$.brand.brandName").value(bodywork.getBrand().getBrandName()));
    }

    @Test
    public void testUpdateBodywork() throws Exception {
        Brand brand = new Brand();
        brand.setId(4);
        brand.setBrandName("Fourth brand");
        Bodywork bodywork = bodyworks.get(0);

        when(bodyworkService.getBodywork(bodywork.getId())).thenReturn(bodywork);
        when(brandService.getBrand(4)).thenReturn(brand);
        when(bodyworkService.updateBodywork(bodywork)).thenReturn(bodywork);

        mockMvc.perform(put("/bodywork/" + bodywork.getId())
                .param("brand", String.valueOf(4)))
                .andDo(print())
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.brand.id").value(brand.getId()))
                .andExpect(jsonPath("$.brand.brandName").value(brand.getBrandName()));
    }
}

