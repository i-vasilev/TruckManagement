package ru.vasilev.webinnovations.truckManagement.service;

import ru.vasilev.webinnovations.truckManagement.exceptions.FieldsIsAbsentException;

import javax.servlet.http.HttpServletRequest;

public abstract class HttpRequestServiceImpl implements HttpRequestService {
    public String getParameter(HttpServletRequest request, String parameter) {
        final String brand = request.getParameter(parameter);
        if (brand == null) {
            throw new FieldsIsAbsentException(String.format("Mandatory field \"%s\" is absent", parameter));
        }
        return brand;
    }

}
