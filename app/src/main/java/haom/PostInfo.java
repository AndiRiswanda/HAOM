package haom;


public class PostInfo {
    private String genre;
    private String title;
    private String description;
    private String imageURL;

    public PostInfo(String genre, String title, String description, String imageURL) {
        this.genre = genre;
        this.title = title;
        this.description = description;
        this.imageURL = imageURL;
    }

    public String getGenre() {
        return genre;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImageURL() {
        return imageURL;
    }
}

