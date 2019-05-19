package lk.ijse.librarymanagementsystem.util;

public class AddBookTM {

    private String bookId;
    private String StudentId;
    private String borrowId;


    public AddBookTM(String bookId, String studentId, String borrowId) {
        this.setBookId(bookId);
        setStudentId(studentId);
        this.setBorrowId(borrowId);
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getStudentId() {
        return StudentId;
    }

    public void setStudentId(String studentId) {
        StudentId = studentId;
    }

    public String getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(String borrowId) {
        this.borrowId = borrowId;
    }
}
