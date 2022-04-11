package by.webproj.carshowroom.controller;

import by.webproject.carshowroom.command.CommandRequest;
import by.webproject.carshowroom.command.CommandResponse;

import javax.servlet.http.HttpServletRequest;

public class SimpleRequestFactory implements RequestFactory {

    @Override
    public CommandRequest createRequest(HttpServletRequest request) {
        return new WrappingCommandRequest(request);
    }

    @Override
    public CommandResponse createForwardResponse(String path) {
        return new PlainCommandResponse(path);
    }

    @Override
    public CommandResponse createRedirectResponse(String path) {
        return new PlainCommandResponse(true,path);
    }
}
