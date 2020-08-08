package ru.vasilev.webinnovations.truckManagement.service;

import org.springframework.stereotype.Service;
import ru.vasilev.webinnovations.truckManagement.data.Bodywork;
import ru.vasilev.webinnovations.truckManagement.database.repository.BodyworkRepository;
import ru.vasilev.webinnovations.truckManagement.exceptions.EntityNotFoundException;

/**
 * Service that works with bodywork entity.
 */
@Service
public class BodyworkServiceImpl implements BodyworkService {
    private final BodyworkRepository bodyworkRepository;
    private final BrandService brandService;

    public BodyworkServiceImpl(BodyworkRepository bodyworkRepository, BrandService brandService) {
        this.bodyworkRepository = bodyworkRepository;
        this.brandService = brandService;
    }

    /**
     * Executes adding bodywork into database.
     *
     * @param bodywork Entity for inserting into database.
     * @return new bodywork from db.
     */
    @Override
    public Bodywork addBodywork(Bodywork bodywork) {
        bodyworkRepository.save(bodywork);
        return bodywork;
    }

    /**
     * Gets bodywork by its id.
     *
     * @param id bodywork's id.
     * @return bodywork with id.
     */
    @Override
    public Bodywork getBodywork(int id) {
        final Bodywork bodywork = bodyworkRepository.getById(id);
        if (bodywork == null) {
            throw new EntityNotFoundException("Bodywork not found.");
        }
        return bodywork;
    }

    /**
     * Updates bodywork in db.
     *
     * @param bodywork {@link Bodywork} for updating.
     * @return Updated bodywork.
     */
    @Override
    public Bodywork updateBodywork(Bodywork bodywork) {
        bodyworkRepository.save(bodywork);
        return bodywork;
    }

    /**
     * Gets iterable collection, that contains all bodyworks in database.
     *
     * @return all bodyworks in database.
     */
    @Override
    public Iterable<Bodywork> getAllBodywork() {
        return bodyworkRepository.findAll();
    }
}
