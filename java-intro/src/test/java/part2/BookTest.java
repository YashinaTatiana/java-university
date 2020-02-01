package part2;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


public class BookTest {

    Book testBook;

    @Test(expected = IllegalArgumentException.class)
    public void incorrectTitle() {
        testBook = new Book("Title", 2019, 200, 80);
        testBook.setTitle("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void incorrectYear() {
        testBook = new Book("Title", 2019, 200, 80);
        testBook.setYear(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void incorrectPrice() {
        testBook = new Book("Title", 2019, 200, 80);
        testBook.setPages(-2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void incorrectPages() {
        testBook = new Book("Title", 2019, 200, 80);
        testBook.setPrice(-2);
    }

    @Test
    public void testAvgPrice() {
        Book book = new Book();
        book.setYear(2019);
        book.setTitle("Mages without time");
        book.setPrice(500);
        book.setPages(100);
        assertEquals(0.2, book.getAvgPagePrice(), 0.01);
    }

    @Test
    public void testAfterPublishDate() {
        Book book = new Book("New", 2020, 500, 120);
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            long startInMls = dateFormat.parse("01.01." + book.getYear()).getTime();
            long todayInMls = System.currentTimeMillis();
            long days = TimeUnit.DAYS.convert((todayInMls - startInMls), MILLISECONDS);
            assertEquals(days, book.getDayAfterPublishing());
        } catch (Exception ex) {
            fail();
        }
    }
}
