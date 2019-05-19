package lk.ijse.librarymanagementsystem.dto;

import java.time.LocalDate;

public class CustomDTO {

    private String book_id ;
    private String student_id;
    private String borrow_id;
    private LocalDate localDate;

    public CustomDTO(String student_id) {
        this.student_id = student_id;
    }

    public CustomDTO(String book_id, String student_id, String borrow_id, LocalDate localDate) {
        this.book_id = book_id;
        this.student_id = student_id;
        this.borrow_id = borrow_id;
        this.localDate = localDate;
    }

    public CustomDTO() {
    }

    public CustomDTO(String book_id, String student_id, String borrow_id) {
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

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }
}
