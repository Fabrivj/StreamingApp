package Class;
import java.util.ArrayList;
import java.util.List;
/**
 * Manages a list of subscriptions, providing methods to add, delete, search, and list subscriptions.
 *
 * @author Jonathan Méndez Herrera
 */
public class SubscriptionManager {
    /**
     * Constructs a new SubscriptionManager with an empty list of subscriptions.
     */
    private List<Subscriptions> susbcriptionList;

    public SubscriptionManager(){
        this.susbcriptionList= new ArrayList<>();
    }
    /**
     * Adds a subscription to the subscription list.
     *
     * @param subscription The subscription to add.
     */
    public void addSubscription(Subscriptions subscription){
        susbcriptionList.add(subscription);
    }
    /**
     * Deletes a subscription from the subscription list based on the name.
     *
     * @param name The subscription to delete.
     */
    public void deleteSubscription(Subscriptions name){
        susbcriptionList.removeIf(sub -> sub.getName().equals(name));
    }
    /**
     * Searches for a subscription in the subscription list based on the name.
     *
     * @param name The subscription to search for.
     * @return The subscription if found, otherwise null.
     */
    public Subscriptions  searchSubscription(Subscriptions name){
        for (Subscriptions sub : susbcriptionList){
            if(sub.getName().equals(name)){
                return sub;
            }
        }
        return null;
    }
    /**
     * Lists all subscriptions that match the given name, printing their details.
     *
     * @param name The subscription to list.
     */
    public void listSubscription(Subscriptions name){
        for (Subscriptions sub : susbcriptionList){
            if(sub.getName().equals(name)){
                System.out.println(sub.getName() + " - " + sub.getAccessLevel());
            }
        }
    }
}