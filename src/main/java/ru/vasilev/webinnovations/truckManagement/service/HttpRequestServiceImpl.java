package ru.vasilev.webinnovations.truckManagement.service;

import ru.vasilev.webinnovations.truckManagement.exceptions.FieldsIsAbsentException;

import javax.servlet.http.HttpServletRequest;

/**
 * Abstract class for working with http requests.
 */
public abstract class HttpRequestServiceImpl implements HttpRequestService {
    /**
     * Gets parameter by its name from request.
     *
     * @param request   User's request.
     * @param parameter Parameter's name.
     * @return parameter's value.
     */
    public String getParameter(HttpServletRequest request, String parameter) {
        final String brand = request.getParameter(parameter);
        if (brand == null) {
            throw new FieldsIsAbsentException(String.format("Mandatory field \"%s\" is absent", parameter));
        }
        return brand;
    }

}
