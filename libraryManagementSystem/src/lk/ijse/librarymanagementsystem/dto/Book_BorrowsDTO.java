package lk.ijse.librarymanagementsystem.dto;

import java.time.LocalDate;

public class Book_BorrowsDTO {

    private String borrow_id;
    private LocalDate b_date;
    private String s_id;
    private String b_id;

    public Book_BorrowsDTO(String borrow_id, LocalDate b_date, String s_id, String b_id) {
        this.borrow_id = borrow_id;
        this.b_date = b_date;
        this.s_id = s_id;
        this.b_id = b_id;
    }

    @Override
    public String toString() {
        return "Book_BorrowsDTO{" +
                "borrow_id='" + borrow_id + '\'' +
                ", b_date=" + b_date +
                ", s_id='" + s_id + '\'' +
                ", b_id='" + b_id + '\'' +
                '}';
    }

    public Book_BorrowsDTO() {
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

    public String getS_id() {
        return s_id;
    }

    public void setS_id(String s_id) {
        this.s_id = s_id;
    }

    public String getB_id() {
        return b_id;
    }

    public void setB_id(String b_id) {
        this.b_id = b_id;
    }
}
