package Class;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ContentManager {
    private List<ContentItems> contentList;

    
    // Constructor
    public ContentManager() {
        contentList = new ArrayList<>();
    }

    // Agregar nuevo Content Item
    public void addContentItem(ContentItems item) {
        contentList.add(item);
    }

    // Eliminar un Content Item por ID
    public boolean removeContentItem(String idMedia) {
        return contentList.removeIf(item -> item.getIdMedia().equals(idMedia));
    }

    // Get Content Item by ID
    public ContentItems getContentItem(String idMedia) {
        for (ContentItems item : contentList) {
            if (item.getIdMedia().equals(idMedia)) {
                return item;
            }
        }
        return null; // If not found
    }

    // List all Content Items
    public List<ContentItems> getAllContentItems() {
        return new ArrayList<>(contentList);
    }

    // Example of listing by platform
    public List<ContentItems> getContentItemsByPlatform(String platform) {
        List<ContentItems> result = new ArrayList<>();
        for (ContentItems item : contentList) {
            if (item.getPlatform().equalsIgnoreCase(platform)) {
                result.add(item);
            }
        }
        return result;
    }
}
