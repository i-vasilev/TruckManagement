package ru.vasilev.webinnovations.truckManagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.vasilev.webinnovations.truckManagement.database.repository.UnitRepository;
import ru.vasilev.webinnovations.truckManagement.service.UnitService;
import ru.vasilev.webinnovations.truckManagement.service.UnitServiceImpl;

@Configuration
public class TruckManagementConfig {
    private final UnitRepository unitRepository;

    public TruckManagementConfig(UnitRepository unitRepository) {
        this.unitRepository = unitRepository;
    }

//    @Bean
//    public UnitService managementService() {
//        return new UnitServiceImpl(unitRepository);
//    }
}
