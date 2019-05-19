package lk.ijse.librarymanagementsystem.dto;

import java.time.LocalDate;

public class Book_ReturnsDTO {

    private String return_id;
    private LocalDate returned_date;
    private String br_id;

    public Book_ReturnsDTO() {
    }

    public Book_ReturnsDTO(String return_id, LocalDate returned_date, String br_id) {
        this.return_id = return_id;
        this.returned_date = returned_date;
        this.br_id = br_id;
    }

    @Override
    public String toString() {
        return "Book_ReturnsDTO{" +
                "return_id='" + return_id + '\'' +
                ", returned_date=" + returned_date +
                ", br_id='" + br_id + '\'' +
                '}';
    }

    public String getReturn_id() {
        return return_id;
    }

    public void setReturn_id(String return_id) {
        this.return_id = return_id;
    }

    public LocalDate getReturned_date() {
        return returned_date;
    }

    public void setReturned_date(LocalDate returned_date) {
        this.returned_date = returned_date;
    }

    public String getBr_id() {
        return br_id;
    }

    public void setBr_id(String br_id) {
        this.br_id = br_id;
    }
}
