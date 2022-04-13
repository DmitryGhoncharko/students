package by.webproj.carshowroom.command;

public enum PagePath {
    MAIN_PAGE("/WEB-INF/jsp/main.jsp");
    private final String path;

    PagePath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
