package lk.ijse.librarymanagementsystem.entity;

import java.time.LocalDate;

public class CustomEntity {

    private String book_id ;
    private String student_id;
    private String borrow_id;
    private LocalDate b_date;

    public CustomEntity(String student_id) {
        this.student_id = student_id;
    }

    public CustomEntity(String book_id, String student_id, String borrow_id, LocalDate b_date) {
        this.book_id = book_id;
        this.student_id = student_id;
        this.borrow_id = borrow_id;
        this.b_date = b_date;
    }

    public CustomEntity() {
    }

    public CustomEntity(String student_id,String book_id,  String borrow_id) {
        this.book_id = book_id;
        this.student_id = student_id;
        this.borrow_id = borrow_id;
    }

    public String getBook_id() {
        return book_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getBorrow_id() {
        return borrow_id;
    }

    public void setBorrow_id(String borrow_id) {
        this.borrow_id = borrow_id;
    }

    public LocalDate getB_date() {
        return b_date;
    }

    public void setB_date(LocalDate b_date) {
        this.b_date = b_date;
    }
}
