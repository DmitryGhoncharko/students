package by.webproj.carshowroom.command;

public enum PagePath {
    MAIN_PAGE("/WEB-INF/jsp/main.jsp"), LOGIN_PAGE("/WEB-INF/jsp/login.jsp"), INDEX_PATH("/"),
    REGISTRATION_PAGE("/WEB-INF/jsp/registration.jsp"),
    UPDATE_CAR_PAGE("/WEB-INF/jsp/updateCar.jsp"), TEST_PAGE("/WEB-INF/jsp/test.jsp"), INFO_PAGE("/WEB-INF/jsp/info.jsp"), ANALYZE_PAGE("/WEB-INF/jsp/analyze.jsp"),
    IMAGES_PAGE("/WEB-INF/jsp/images.jsp"), RESULT_PAGE("/WEB-INF/jsp/result.jsp");
    private final String path;

    PagePath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
