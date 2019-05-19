package lk.ijse.librarymanagementsystem.util;

import java.sql.Date;
import java.time.LocalDate;

public class BorrowTM {

    private String bookId;
    private String studentId;
    private LocalDate borrowDate;
    private String borrowId;

    public BorrowTM(String bookId, String studentId, LocalDate borrowDate, String borrowId) {
        this.bookId = bookId;
        this.studentId = studentId;
        this.borrowDate = borrowDate;
        this.borrowId = borrowId;
    }

    public BorrowTM(String bookId, String studentId, LocalDate borrowDate) {
        this.bookId = bookId;
        this.studentId = studentId;
        this.borrowDate = borrowDate;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public String getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(String borrowId) {
        this.borrowId = borrowId;
    }
}
