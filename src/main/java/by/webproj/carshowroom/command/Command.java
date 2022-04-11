package by.webproj.carshowroom.command;

import by.webproject.carshowroom.exception.ServiceError;

public interface Command {

    CommandResponse execute(CommandRequest request) throws ServiceError;

}
