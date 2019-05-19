package lk.ijse.librarymanagementsystem.entity;

public class Book extends SuperEntity{
    private String book_id;
    private String b_name;
    private String author;
    private double price;
    private int count;
    private String publisher;
    private String edition;
    private int availability;

    public Book() {
    }

    @Override
    public String toString() {
        return "Book{" +
                "book_id='" + book_id + '\'' +
                ", b_name='" + b_name + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                ", count=" + count +
                ", publisher='" + publisher + '\'' +
                ", edition='" + edition + '\'' +
                ", availability=" + availability +
                '}';
    }

    public Book(String book_id, String b_name, String author, double price, int count, String publisher, String edition, int availability) {
        this.book_id = book_id;
        this.b_name = b_name;
        this.author = author;
        this.price = price;
        this.count = count;
        this.publisher = publisher;
        this.edition = edition;
        this.availability = availability;
    }

    public String getBook_id() {
        return book_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }

    public String getB_name() {
        return b_name;
    }

    public void setB_name(String b_name) {
        this.b_name = b_name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public int getAvailability() {
        return availability;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }
}
