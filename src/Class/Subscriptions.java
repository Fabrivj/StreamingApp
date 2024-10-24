package Class;

/**
 * Represents a subscription in the streaming service.
 *
 * @author Jonathan Méndez Herrera
 */
public class Subscriptions {
    private String subscriptionId;
    private String name;
    private String accessLevel;
    private String description;
    private double price;
    /**
     * Constructs a new subscription with the specified details.
     *
     * @param subscriptionId The unique identifier of the subscription.
     * @param name The name of the subscription.
     * @param accessLevel The access level of the subscription (e.g., basic, premium).
     * @param description A description of the subscription.
     * @param price The price of the subscription.
     */
    public Subscriptions(String subscriptionId, String name, String accessLevel, String description, double price) {
        this.subscriptionId = subscriptionId;
        this.name = name;
        this.accessLevel = accessLevel;
        this.description = description;
        this.price = price;
    }
    /**
     * Returns the unique identifier of the subscription.
     *
     * @return The subscription ID.
     */
    public String getSubscriptionId() {
        return subscriptionId;
    }
    /**
     * Sets the unique identifier of the subscription.
     *
     * @param subscriptionId The subscription ID to set.
     */
    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }
    /**
     * Returns the name of the subscription.
     *
     * @return The name of the subscription.
     */
    public String getName() {
        return name;
    }
    /**
     * Sets the name of the subscription.
     *
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Returns the access level of the subscription.
     *
     * @return The access level of the subscription.
     */
    public String getAccessLevel() {
        return accessLevel;
    }
    /**
     * Sets the access level of the subscription.
     *
     * @param accessLevel The access level to set.
     */
    public void setAccessLevel(String accessLevel) {
        this.accessLevel = accessLevel;
    }
    /**
     * Returns the description of the subscription.
     *
     * @return The description of the subscription.
     */
    public String getDescription() {
        return description;
    }
    /**
     * Sets the description of the subscription.
     *
     * @param description The description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * Returns the price of the subscription.
     *
     * @return The price of the subscription.
     */
    public double getPrice() {
        return price;
    }
    /**
     * Sets the price of the subscription.
     *
     * @param price The price to set.
     */
    public void setPrice(double price) {
        this.price = price;
    }
}
