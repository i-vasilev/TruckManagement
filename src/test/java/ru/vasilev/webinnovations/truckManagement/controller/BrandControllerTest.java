package ru.vasilev.webinnovations.truckManagement.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.vasilev.webinnovations.truckManagement.controllers.BrandController;
import ru.vasilev.webinnovations.truckManagement.data.Brand;
import ru.vasilev.webinnovations.truckManagement.exceptions.EntityNotFoundException;
import ru.vasilev.webinnovations.truckManagement.service.BrandService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BrandController.class)
public class BrandControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BrandService brandService;

    private List<Brand> brands = new ArrayList<>();

    @BeforeEach
    public void initBrands() {
        Brand brand = new Brand();
        brand.setBrandName("First brand's name");
        brand.setId(1);
        brands.add(brand);

        brand = new Brand();
        brand.setId(2);
        brand.setBrandName("Second brand's name");
        brands.add(brand);
    }

    @Test
    public void testGetAllBrands() throws Exception {
        when(brandService.getAllBrands()).thenReturn(brands);

        mockMvc.perform(get("/brand"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(brands.get(0).getId()))
                .andExpect(jsonPath("$[0].brandName").value(brands.get(0).getBrandName()))
                .andExpect(jsonPath("$[1].id").value(brands.get(1).getId()))
                .andExpect(jsonPath("$[1].brandName").value(brands.get(1).getBrandName()));
    }

    @Test
    public void testGetBrandFound() throws Exception {
        final int id = brands.get(0).getId();
        when(brandService.getBrand(id))
                .thenReturn(brands.get(0));

        mockMvc.perform(get("/brand/" + id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(brands.get(0).getId()))
                .andExpect(jsonPath("$.brandName").value(brands.get(0).getBrandName()));
    }

    @Test
    public void testGetBrandNotFound() throws Exception {
        final int id = -1;
        when(brandService.getBrand(id))
                .thenThrow(new EntityNotFoundException("Entity not found!"));

        mockMvc.perform(get("/brand/" + id))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Entity not found!")));
    }

    @Test
    public void testAddBrand() throws Exception {
        Brand brand = brands.get(0);
        when(brandService.addBrand(brand.getBrandName())).thenReturn(brand);

        mockMvc.perform(post("/brand").param("brand_name", brand.getBrandName()))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(brand.getId()))
                .andExpect(jsonPath("$.brandName").value(brand.getBrandName()));
    }

    @Test
    public void testUpdateBrand() throws Exception {
        Brand brand = brands.get(0);
        when(brandService.updateBrand(brand.getId(), brand.getBrandName())).thenReturn(brand);

        mockMvc.perform(put("/brand/" + brand.getId()).param("brand_name", brand.getBrandName()))
                .andDo(print())
                .andExpect(status().isAccepted())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(brand.getId()))
                .andExpect(jsonPath("$.brandName").value(brand.getBrandName()));
    }
}
