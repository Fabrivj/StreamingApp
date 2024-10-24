package Class;

public class User_Preference {
    private String userId;
    private String preferredGenre;
    private String preferredPlatform;
    private String preferredQuality;
    private String preferredRegion;

    // Constructor
    public User_Preference(String userId, String preferredGenre, String preferredPlatform, String preferredQuality, String preferredRegion) {
        this.userId = userId;
        this.preferredGenre = preferredGenre;
        this.preferredPlatform = preferredPlatform;
        this.preferredQuality = preferredQuality;
        this.preferredRegion = preferredRegion;
    }

    // Getters and Setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPreferredGenre() {
        return preferredGenre;
    }

    public void setPreferredGenre(String preferredGenre) {
        this.preferredGenre = preferredGenre;
    }

    public String getPreferredPlatform() {
        return preferredPlatform;
    }

    public void setPreferredPlatform(String preferredPlatform) {
        this.preferredPlatform = preferredPlatform;
    }

    public String getPreferredQuality() {
        return preferredQuality;
    }

    public void setPreferredQuality(String preferredQuality) {
        this.preferredQuality = preferredQuality;
    }

    public String getPreferredRegion() {
        return preferredRegion;
    }

    public void setPreferredRegion(String preferredRegion) {
        this.preferredRegion = preferredRegion;
    }

    @Override
    public String toString() {
        return "User_Preference [User ID=" + userId +
                ", Preferred Genre=" + preferredGenre +
                ", Preferred Platform=" + preferredPlatform +
                ", Preferred Quality=" + preferredQuality +
                ", Preferred Region=" + preferredRegion + "]";
    }
}
