package lk.ijse.librarymanagementsystem.util;

public class BooksTM {

    private String bookId;
    private String bookName;
    private String author;
    private Double price;
    private Integer qty;
    private String publisher;
    private String edition;
    private Integer availability;

    public BooksTM(String bookId, String bookName, String author, Double price, Integer qty, String publisher, String edition, Integer availability) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.author = author;
        this.price = price;
        this.qty = qty;
        this.publisher = publisher;
        this.edition = edition;
        this.availability = availability;
    }

    public BooksTM(String bookId, String bookName, String author, Double price, Integer qty, String publisher, String edition) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.author = author;
        this.price = price;
        this.qty = qty;
        this.publisher = publisher;
        this.edition = edition;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
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

    public Integer getAvailability() {
        return availability;
    }

    public void setAvailability(Integer availability) {
        this.availability = availability;
    }
}
