package by.webproj.carshowroom.controller;

import by.webproject.carshowroom.command.CommandRequest;
import by.webproject.carshowroom.command.CommandResponse;

import javax.servlet.http.HttpServletRequest;

public interface RequestFactory {

    CommandRequest createRequest(HttpServletRequest request);

    CommandResponse createForwardResponse(String path);

    CommandResponse createRedirectResponse(String path);

}
