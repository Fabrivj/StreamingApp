public class Streaming_Service {
    private String serviceName;
    private String subscriptionType; // e.g., Free, Premium, etc.
    private String supportedRegions; // Could be a string or a collection of regions
    private double subscriptionCost;

    // Constructor
    public Streaming_Service(String serviceName, String subscriptionType, String supportedRegions, double subscriptionCost) {
        this.serviceName = serviceName;
        this.subscriptionType = subscriptionType;
        this.supportedRegions = supportedRegions;
        this.subscriptionCost = subscriptionCost;
    }

    // Getters and Setters
    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(String subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    public String getSupportedRegions() {
        return supportedRegions;
    }

    public void setSupportedRegions(String supportedRegions) {
        this.supportedRegions = supportedRegions;
    }

    public double getSubscriptionCost() {
        return subscriptionCost;
    }

    public void setSubscriptionCost(double subscriptionCost) {
        this.subscriptionCost = subscriptionCost;
    }

    @Override
    public String toString() {
        return "Streaming_Service [Service Name=" + serviceName +
                ", Subscription Type=" + subscriptionType +
                ", Supported Regions=" + supportedRegions +
                ", Subscription Cost=" + subscriptionCost + "]";
    }
}