package ru.vasilev.webinnovations.truckManagement.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.vasilev.webinnovations.truckManagement.database.repository.*;
import ru.vasilev.webinnovations.truckManagement.service.*;

@Configuration
public class TruckManagementConfig {
    @Bean
    public BodyworkService bodyworkService(@Autowired BodyworkRepository bodyworkRepository, @Autowired BrandService brandService) {
        return new BodyworkServiceImpl(bodyworkRepository, brandService);
    }

    @Bean
    public EngineService engineService(@Autowired EngineRepository engineRepository, @Autowired UnitService unitService) {
        return new EngineServiceImpl(engineRepository, unitService);
    }

    @Bean
    public CarService carService(@Autowired CarRepository carRepository, @Autowired EngineService engineService, @Autowired BodyworkService bodyworkService) {
        return new CarServiceImpl(carRepository, engineService, bodyworkService);
    }
}
