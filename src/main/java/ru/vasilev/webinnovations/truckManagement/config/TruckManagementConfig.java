package ru.vasilev.webinnovations.truckManagement.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.vasilev.webinnovations.truckManagement.database.repository.BodyworkRepository;
import ru.vasilev.webinnovations.truckManagement.database.repository.BrandRepository;
import ru.vasilev.webinnovations.truckManagement.database.repository.EngineRepository;
import ru.vasilev.webinnovations.truckManagement.database.repository.UnitRepository;
import ru.vasilev.webinnovations.truckManagement.service.*;

@Configuration
public class TruckManagementConfig {
    @Bean
    public UnitService managementService(@Autowired UnitRepository unitRepository) {
        return new UnitServiceImpl(unitRepository);
    }

    @Bean
    public BodyworkService bodyworkService(@Autowired BodyworkRepository bodyworkRepository, @Autowired BrandService brandService) {
        return new BodyworkServiceImpl(bodyworkRepository, brandService);
    }

    @Bean
    public BrandService brandService(@Autowired BrandRepository brandRepository) {
        return new BrandServiceImpl(brandRepository);
    }

    @Bean
    public EngineService engineService(@Autowired EngineRepository engineRepository, @Autowired UnitService unitService) {
        return new EngineServiceImpl(engineRepository, unitService);
    }
}
