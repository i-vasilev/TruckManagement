package ru.vasilev.webinnovations.truckManagement.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.vasilev.webinnovations.truckManagement.database.repository.BodyworkRepository;
import ru.vasilev.webinnovations.truckManagement.database.repository.CarRepository;
import ru.vasilev.webinnovations.truckManagement.database.repository.EngineRepository;
import ru.vasilev.webinnovations.truckManagement.service.*;

@Configuration
public class TruckManagementConfig {
    @Bean
    public BodyworkService bodyworkService(@Autowired BodyworkRepository bodyworkRepository) {
        return new BodyworkServiceImpl(bodyworkRepository);
    }

    @Bean
    public EngineService engineService(@Autowired EngineRepository engineRepository) {
        return new EngineServiceImpl(engineRepository);
    }

    @Bean
    public CarService carService(@Autowired CarRepository carRepository) {
        return new CarServiceImpl(carRepository);
    }
}
