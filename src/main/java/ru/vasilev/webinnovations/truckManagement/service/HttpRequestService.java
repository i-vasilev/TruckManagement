package ru.vasilev.webinnovations.truckManagement.service;

import javax.servlet.http.HttpServletRequest;

/**
 * Interface for working with http requests.
 */
public interface HttpRequestService {
    String getParameter(HttpServletRequest request, String parameter);
}
