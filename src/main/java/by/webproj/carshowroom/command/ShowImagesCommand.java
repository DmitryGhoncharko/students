package by.webproj.carshowroom.command;

import by.webproj.carshowroom.controller.RequestFactory;
import by.webproj.carshowroom.entity.Image;
import by.webproj.carshowroom.entity.User;
import by.webproj.carshowroom.exception.ServiceError;
import by.webproj.carshowroom.model.service.ImagesService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ShowImagesCommand implements Command{
    private final RequestFactory requestFactory;
    private final ImagesService imagesService;
    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError {
        User user = (User)request.retrieveFromSession("user").get();
        List<Image> imageList = imagesService.getByUserId(user.getId());
        request.addAttributeToJsp("images",imageList);
        return requestFactory.createForwardResponse(PagePath.IMAGES_PAGE.getPath());
    }
}
