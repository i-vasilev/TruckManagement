package ru.vasilev.webinnovations.truckManagement.service;

import javax.servlet.http.HttpServletRequest;

/**
 * Interface for working with http requests.
 */
public interface HttpRequestService {
    /**
     * Gets parameter by its name from request.
     *
     * @param request   User's request.
     * @param parameter Parameter's name.
     * @return parameter's value.
     */
    String getParameter(HttpServletRequest request, String parameter);
}
