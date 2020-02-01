package catalog.tel_catalog;

import catalog.model.Subscriber;

import java.util.HashMap;
import java.util.Map;

public class Catalog {

    private static Map<String, Subscriber> catalog;

    public static void createCatalog() {
        if (catalog == null) {
            catalog = new HashMap<>();
        }
    }

    static void addSubscriber(Subscriber subscriber) {
        catalog.put(subscriber.getTelephone(), subscriber);
    }

    static boolean containsSubscriber(String telephone) {
        return catalog.containsKey(telephone);
    }

    static boolean containsSubscriber(Subscriber subscriber) {
        return catalog.containsKey(subscriber.getTelephone());
    }

    static void deleteSubscriber(String telephone) {
        catalog.remove(telephone);
    }

    static Subscriber getSubscriber(String telephone) {
        return catalog.get(telephone);
    }

    public static Map getSubscribers() {
        return catalog;
    }

    public static void clear() {
        catalog = new HashMap<>();
    }
}
