package lk.ijse.librarymanagementsystem.util;

import java.time.LocalDate;

public class SearchTM {

    private String return_id;
    private LocalDate returned_date;
    private String br_id;

    public SearchTM(String return_id, LocalDate returned_date, String br_id) {
        this.return_id = return_id;
        this.returned_date = returned_date;
        this.br_id = br_id;
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
