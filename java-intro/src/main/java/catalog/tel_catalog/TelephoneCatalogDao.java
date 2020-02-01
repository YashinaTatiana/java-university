package catalog.tel_catalog;

import catalog.exceptions.TelephoneCatalogException;
import catalog.model.Subscriber;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TelephoneCatalogDao {

    private Map<String, Subscriber> catalog;

    public TelephoneCatalogDao() {
        Catalog.createCatalog();
    }

    public void validateNumber(String telephone) throws TelephoneCatalogException {
        if (telephone == null || telephone.trim().isEmpty()) {
            throw new TelephoneCatalogException("\nTelephone number is required!\n");
        }
        Pattern pattern = Pattern.compile("\\d{10}");
        Matcher matcher = pattern.matcher(telephone);
        if (!matcher.matches()) {
            throw new TelephoneCatalogException("\nTelephone length is invalid!\n");
        }
    }

    public void addUser(Subscriber subscriber) throws TelephoneCatalogException {
        if (subscriber == null || Catalog.containsSubscriber(subscriber)) {
            throw new TelephoneCatalogException("\nUser with such telephone number is already exist!\n");
        }
        validateNumber(subscriber.getTelephone());
        Catalog.addSubscriber(subscriber);
    }

    public void updateUser(Subscriber subscriber) throws TelephoneCatalogException {
        if (subscriber == null || !Catalog.containsSubscriber(subscriber)) {
            throw new TelephoneCatalogException("\nUser is not found!\n");
        }
        validateNumber(subscriber.getTelephone());
        Catalog.addSubscriber(subscriber);
    }

    public void deleteUser(String telephone) throws TelephoneCatalogException {
        if (telephone == null || telephone.trim().isEmpty() || !Catalog.containsSubscriber(telephone)) {
            throw new TelephoneCatalogException("\nUser is not found!\n");
        }
        validateNumber(telephone);
        Catalog.deleteSubscriber(telephone);
    }

    public Subscriber getSubscriber(String telephone) throws TelephoneCatalogException {
        if (telephone == null || telephone.trim().isEmpty() || !Catalog.containsSubscriber(telephone)) {
            throw new TelephoneCatalogException("\nUser is not found!\n");
        }
        validateNumber(telephone);
        return Catalog.getSubscriber(telephone);
    }

    public Map getSubscribers() {
        return Catalog.getSubscribers();
    }

    public void clear() {
        Catalog.clear();
    }

}
