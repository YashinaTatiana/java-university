package part2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class Book {

    private int pages;
    private String title;
    private int year;
    private float price;

    public Book(){};

    public Book(String title, int year, float price, int pages) {
        setPages(pages);
        setTitle(title);
        setYear(year);
        setPrice(price);
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        if (pages <= 0) {
            throw new IllegalArgumentException("Page count is incorrect!");
        }
        this.pages = pages;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Title is incorrect!");
        }
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        if (year <= 0) {
            throw new IllegalArgumentException("Year is incorrect!");
        }
        this.year = year;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(float price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Price is incorrect!");
        }
        this.price = price;
    }

    public void print() {
        System.out.println(toString());
    }

    public float getAvgPagePrice() {
        return pages / price;
    }

    public long getDayAfterPublishing() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        long startInMls = dateFormat.parse("01.01." + year).getTime();
        long todayInMls = System.currentTimeMillis();
        long days = TimeUnit.DAYS.convert((todayInMls - startInMls), MILLISECONDS);
        return days;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return pages == book.pages &&
                year == book.year &&
                Double.compare(book.price, price) == 0 &&
                Objects.equals(title, book.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pages, title, year, price);
    }

    @Override
    public String toString() {
        return "'" + title +
                "', " + year +
                ", " + price +
                ", " + pages + " p.";
    }
}
