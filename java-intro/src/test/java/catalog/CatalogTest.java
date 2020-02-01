package catalog;

import catalog.exceptions.SubscriberException;
import catalog.exceptions.TelephoneCatalogException;
import catalog.model.Subscriber;
import catalog.tel_catalog.TelephoneCatalogDao;
import catalog.tel_catalog.TelephoneService;
import org.junit.Test;

import static org.testng.Assert.*;

public class CatalogTest {

    TelephoneService service = new TelephoneService();
    TelephoneCatalogDao catalogDao = service.getCatalogDao();

    @Test
    public void testAddDeleteValidSubscriber() {
        try {
            catalogDao.clear();
            catalogDao.addUser(new Subscriber("Olena", "Berezina", "9082728287", "Some address"));
            catalogDao.addUser(new Subscriber("Egor", "Volkov", "9082728288", "Some address"));
            catalogDao.addUser(new Subscriber("Petr", "Luzin", "9082728289", "Some address"));
            assertEquals(3, catalogDao.getSubscribers().size());

            catalogDao.deleteUser("9082728287");
            catalogDao.deleteUser("9082728288");
            assertEquals(1, catalogDao.getSubscribers().size());
        } catch (TelephoneCatalogException ex) {
            fail();
        }
    }

    @Test(expected = TelephoneCatalogException.class)
    public void testAddExistsSubscriber() throws TelephoneCatalogException {
        catalogDao.clear();
        catalogDao.addUser(new Subscriber("Olena", "Berezina", "9082728287", "Some address"));
        catalogDao.addUser(new Subscriber("Olena", "Berezina", "9082728287", "Some address"));
    }

    @Test(expected = TelephoneCatalogException.class)
    public void testAddSubscriberWithInvalidTel() throws TelephoneCatalogException {
        catalogDao.clear();
        catalogDao.addUser(new Subscriber("Olena", "Berezina", "9AAAA282887", "Some address"));
    }

    @Test(expected = SubscriberException.class)
    public void testAddInvalidSubscriberData() throws TelephoneCatalogException {
        catalogDao.clear();
        Subscriber subscriber = new Subscriber(" ", "lasyName", "9082728287", "   ");
        catalogDao.addUser(subscriber);
    }

    @Test
    public void testUpdateUser() throws TelephoneCatalogException {
        catalogDao.clear();
        catalogDao.addUser(new Subscriber("Olena", "Berezina", "9082728287", "Some address"));
        catalogDao.updateUser(new Subscriber("Olena", "Berezina", "9082728287", "New address" ));
        assertEquals(1, catalogDao.getSubscribers().size());
        assertNotSame("Some address", catalogDao.getSubscriber("9082728287").getAddress());
    }

    @Test(expected = TelephoneCatalogException.class)
    public void testUpdateUserNotExists() throws TelephoneCatalogException {
        catalogDao.clear();
        catalogDao.addUser(new Subscriber("Olena", "Berezina", "9082728287", "Some address"));
        catalogDao.updateUser(new Subscriber("Olena", "Berezina", "9827299999", "New address" ));
    }

    @Test
    public void testDeleteUser() throws TelephoneCatalogException {
        catalogDao.clear();
        catalogDao.addUser(new Subscriber("Ivan", "Bokov", "9082728289", "Some address"));
        catalogDao.addUser(new Subscriber("Olena", "Berezina", "9082728287", "Some address"));
        assertEquals(2, catalogDao.getSubscribers().size());

        catalogDao.deleteUser("9082728287");
        assertEquals(1, catalogDao.getSubscribers().size());
    }

    @Test(expected = TelephoneCatalogException.class)
    public void testDeleteUnknownUser() throws TelephoneCatalogException {
        catalogDao.clear();
        catalogDao.addUser(new Subscriber("Olena", "Berezina", "9082728299887", "Some address"));
        assertEquals(1, catalogDao.getSubscribers().size());
        catalogDao.deleteUser("9082728287");
    }

}
