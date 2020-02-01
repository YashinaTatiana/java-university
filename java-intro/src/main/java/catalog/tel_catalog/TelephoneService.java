package catalog.tel_catalog;

import catalog.model.Subscriber;

import java.util.Scanner;

public class TelephoneService {

    private static TelephoneCatalogDao catalogDao;

    public TelephoneService() {
        catalogDao = new TelephoneCatalogDao();
    }

    public TelephoneCatalogDao getCatalogDao() {
        return catalogDao;
    }

    public static Subscriber inputUser() {
        Scanner sc = new Scanner(System.in);
        System.out.print("First name: ");
        String firstName = sc.next();
        System.out.print("Last name: ");
        String lastName = sc.next();
        System.out.print("Telephone (10 digits): +7 ");
        String telephone = sc.next();
        System.out.print("Address: ");
        String address = sc.next();
        return new Subscriber(firstName, lastName, telephone, address);
    }

    public static void run() {
        while (true)
        try {
            System.out.print(
                    "\n--------------------------------" +
                    "\n-------Choose the action:-------" +
                    "\n--------------------------------" +
                    "\n1 - Add info to the catalog;" +
                    "\n2 - Update the subscriber info in the catalog;" +
                    "\n3 - Delete info from the catalog;" +
                    "\n4 - Get subscriber info by the telephone;" +
                    "\n5 - Get the catalog info;" +
                    "\n6 - Exit...\n>> ");
            Scanner sc = new Scanner(System.in);
            String choice = sc.next();
            switch (choice) {
                case "1": {
                    catalogDao.addUser(inputUser());
                } break;
                case "2" : {
                    catalogDao.updateUser(inputUser());
                } break;
                case "3" : {
                    System.out.print("Input telephone number: ");
                    catalogDao.deleteUser(sc.next());
                } break;
                case "4" : {
                    System.out.print("Input telephone number: ");
                    System.out.println(catalogDao.getSubscriber(sc.next()));
                } break;
                case "5" : {
                    catalogDao.getSubscribers().forEach((k,v) -> System.out.println(v));
                } break;
                case "6" : return;
                default: break;
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
