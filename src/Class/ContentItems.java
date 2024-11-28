package Class;

import java.util.Date;

public class ContentItems {
    private String idMedia;
    private String title;
    private String type;
    private String filmGenre;
    private Date releaseDate;
    private String description;
    private double qualification;
    private String platform;
    private String quality;
    private String availabilityByRegion;

    // Constructor
    public ContentItems(String idMedia, String title, String type, String filmGenre, Date releaseDate, String description, double qualification, String platform, String quality, String availabilityByRegion) {
        this.idMedia = idMedia;
        this.title = title;
        this.type = type;
        this.filmGenre = filmGenre;
        this.releaseDate = releaseDate;
        this.description = description;
        this.qualification = qualification;
        this.platform = platform;
        this.quality = quality;
        this.availabilityByRegion = availabilityByRegion;
    }

    // Getters and Setters
    public String getIdMedia() {
        return idMedia;
    }

    
    public void setIdMedia(String idMedia) {
        this.idMedia = idMedia;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFilmGenre() {
        return filmGenre;
    }

    public void setFilmGenre(String filmGenre) {
        this.filmGenre = filmGenre;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getQualification() {
        return qualification;
    }

    public void setQualification(double qualification) {
        this.qualification = qualification;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getAvailabilityByRegion() {
        return availabilityByRegion;
    }

    public void setAvailabilityByRegion(String availabilityByRegion) {
        this.availabilityByRegion = availabilityByRegion;
    }

    @Override
    public String toString() {
        return "ContentItems [ID_Media=" + idMedia + ", Title=" + title + ", Type=" + type +
                ", Film_Genre=" + filmGenre + ", Release_Date=" + releaseDate +
                ", Description=" + description + ", Qualification=" + qualification +
                ", Platform=" + platform + ", Quality=" + quality +
                ", Availability_By_Region=" + availabilityByRegion + "]";
    }
}
