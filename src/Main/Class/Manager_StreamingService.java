import java.util.ArrayList;
import java.util.List;

public class Manager_StreamingService {
    private List<Streaming_Service> streamingServices;

    // Constructor
    public Manager_StreamingService() {
        this.streamingServices = new ArrayList<>();
    }

    // Add a new Streaming Service
    public void addStreamingService(Streaming_Service service) {
        streamingServices.add(service);
    }

    // Remove a Streaming Service by name
    public boolean removeStreamingService(String serviceName) {
        return streamingServices.removeIf(service -> service.getServiceName().equals(serviceName));
    }

    // Get Streaming Service by name
    public Streaming_Service getStreamingService(String serviceName) {
        for (Streaming_Service service : streamingServices) {
            if (service.getServiceName().equals(serviceName)) {
                return service;
            }
        }
        return null; // If not found
    }

    // List all Streaming Services
    public List<Streaming_Service> getAllStreamingServices() {
        return new ArrayList<>(streamingServices);
    }

    // Example of listing by region
    public List<Streaming_Service> getStreamingServicesByRegion(String region) {
        List<Streaming_Service> result = new ArrayList<>();
        for (Streaming_Service service : streamingServices) {
            if (service.getSupportedRegions().contains(region)) {
                result.add(service);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        // Example usage
        Manager_StreamingService manager = new Manager_StreamingService();

        // Adding streaming services
        manager.addStreamingService(new Streaming_Service("Netflix", "Premium", "Worldwide", 15.99));
        manager.addStreamingService(new Streaming_Service("Hulu", "Basic", "US", 9.99));

        // Get a specific streaming service
        Streaming_Service netflix = manager.getStreamingService("Netflix");
        System.out.println(netflix);

        // List streaming services by region
        List<Streaming_Service> usServices = manager.getStreamingServicesByRegion("US");
        System.out.println("US Streaming Services: " + usServices);
    }
}