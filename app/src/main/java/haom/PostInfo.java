package haom;


public class PostInfo {
    private String genre;
    private String title;
    private String description;
    private String imageURL;
    private int helpPoints;

    public PostInfo(String title, String genre, String description, String imageURL, int helpPoints) {
        this.title = title;
        this.genre = genre;
        this.description = description;
        this.imageURL = imageURL;
        this.helpPoints = helpPoints;
    }

    public PostInfo(String title, String genre, String description, String imageURL) {
        this.title = title;
        this.genre = genre;
        this.description = description;
        this.imageURL = imageURL;
        this.helpPoints = 0;
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

    public int getHelpPoints() {
        return helpPoints;
    }

    public void setHelpPoints(int helpPoints) { 
        this.helpPoints = helpPoints; 
    }
}

