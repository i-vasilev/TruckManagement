package ru.vasilev.webinnovations.truckManagement.service;

import org.springframework.stereotype.Service;
import ru.vasilev.webinnovations.truckManagement.data.Bodywork;
import ru.vasilev.webinnovations.truckManagement.data.Brand;
import ru.vasilev.webinnovations.truckManagement.database.repository.BodyworkRepository;
import ru.vasilev.webinnovations.truckManagement.exceptions.EntityNotFoundException;
import ru.vasilev.webinnovations.truckManagement.exceptions.FieldsIsAbsentException;

import javax.servlet.http.HttpServletRequest;

/**
 * Service that works with bodywork entity.
 */
@Service
public class BodyworkServiceImpl extends HttpRequestServiceImpl implements BodyworkService {
    private final BodyworkRepository bodyworkRepository;
    private final BrandService brandService;

    public BodyworkServiceImpl(BodyworkRepository bodyworkRepository, BrandService brandService) {
        this.bodyworkRepository = bodyworkRepository;
        this.brandService = brandService;
    }

    /**
     * Executes adding bodywork into database.
     *
     * @param request user's request.
     * @return new bodywork from db.
     */
    @Override
    public Bodywork addBodywork(HttpServletRequest request) {
        final String brandIdStr = getParameter(request, "brand");
        int brandId = Integer.parseInt(brandIdStr);
        final String model = getParameter(request, "model");
        Brand brand = brandService.getBrand(brandId);
        Bodywork bodywork = new Bodywork();
        bodywork.setModel(model);
        bodywork.setBrand(brand);
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
     * @param request  User's request.
     * @return Updated bodywork.
     */
    @Override
    public Bodywork updateBodywork(Bodywork bodywork, HttpServletRequest request) {
        if (request.getParameterMap().isEmpty()) {
            throw new FieldsIsAbsentException("At least one of the parameters mustn't be empty!");
        }

        final String model = request.getParameter("model");
        if (model != null) {
            bodywork.setModel(model);
        }
        final String brandIdStr = request.getParameter("brand");
        if (brandIdStr != null) {
            final Brand brand = brandService.getBrand(Integer.parseInt(brandIdStr));
            bodywork.setBrand(brand);
        }

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
