package by.webproj.carshowroom.command;

import by.webproj.carshowroom.command.utils.ImageColorAnalyzer;
import by.webproj.carshowroom.controller.RequestFactory;
import by.webproj.carshowroom.entity.User;
import by.webproj.carshowroom.exception.ServiceError;
import by.webproj.carshowroom.model.service.ImagesService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.Part;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class AnalyzeImageCommand implements Command {
    private static final Logger LOG = LoggerFactory.getLogger(AnalyzeImageCommand.class);
    private static final String DIRECTORY = "serverforapp";
    private static final String FILE_EXTENSION = ".png";

    private final RequestFactory requestFactory;
    private final ImagesService imagesService;
    @SneakyThrows
    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError {
        request.removeFromSession("imageId");
        request.removeFromSession("colors");
        User user = (User) request.retrieveFromSession("user").get();
        String saveImage = request.getParameter("save");
        long imageId = 0;
        if(saveImage==null){
            imageId = imagesService.addImage(user.getId());
            saveImage(request, imageId);
        }
        imageId = Long.parseLong(request.getParameter("imageId"));
        request.addToSession("imageId",imageId);
        String uploadPath = "E:\\serverData";
        ImageColorAnalyzer imageColorAnalyzer = new ImageColorAnalyzer(uploadPath + File.separator + imageId + FILE_EXTENSION,10);
        imageColorAnalyzer.getImageData();
        ArrayList<int[]> result = imageColorAnalyzer.getPalette(16, ImageColorAnalyzer.ANALYZER_ALGORITHM.ALG_K_MEANS);
        List<String> hexColors = new ArrayList<>();
        for (int[] color : result){
            Color your_color = new Color(color[0], color[1],color[2]);
            String hex = Integer.toHexString(your_color.getRGB()).substring(2);
            hexColors.add(hex);
        }
        request.addToSession("colors",hexColors);
        return requestFactory.createRedirectResponse("/controller?command=result");
    }

    private void saveImage(CommandRequest request, long imageId) {
        String uploadPath = "E:\\serverData";
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        try {
            for (Part part : request.getParts()) {
                String fileName = part.getSubmittedFileName();
                if (fileName != null) {
                    part.write(uploadPath + File.separator + imageId + FILE_EXTENSION);
                }
            }
        } catch (IOException | ServletException e) {
            LOG.debug(e.getMessage(), e);
        }
    }
}
