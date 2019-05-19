package lk.ijse.librarymanagementsystem.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import lk.ijse.librarymanagementsystem.business.BOFactory;
import lk.ijse.librarymanagementsystem.business.BOTypes;
import lk.ijse.librarymanagementsystem.business.custom.BookBO;
import lk.ijse.librarymanagementsystem.business.custom.Book_BorrowsBO;
import lk.ijse.librarymanagementsystem.business.custom.Book_ReturnBO;
import lk.ijse.librarymanagementsystem.business.custom.StudentBO;
import lk.ijse.librarymanagementsystem.db.DBConnection;
import lk.ijse.librarymanagementsystem.dto.*;
import lk.ijse.librarymanagementsystem.util.AddBookTM;
import lk.ijse.librarymanagementsystem.util.BorrowTM;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class ReturnBooksController {
    public Button btnHome;
    public Button btnLogout;
    public Button btnDel;
    public Button btnReturn;
    public Button btnAdd;
    public JFXTextField txtReturnId;
    public JFXTextField txtDate;
    public JFXComboBox cmbStuId;
    public JFXComboBox cmbBookId;
    public JFXTextField txtBookName;
    public JFXTextField txtDueDate;
    public JFXTextField txtstuName;
    public JFXListView lstBooks;
    ObservableList<CustomDTO> bookDetails= FXCollections.observableArrayList();
    LocalDate localDate;
    ObservableList returnedBooks=FXCollections.observableArrayList();
    String borrowId;

    private BookBO bookBO = BOFactory.getInstance().getBO(BOTypes.BOOK) ;
    private StudentBO studentBO = BOFactory.getInstance().getBO(BOTypes.STUDENT);
    private Book_ReturnBO book_returnBO = BOFactory.getInstance().getBO(BOTypes.BOOK_RETURNS);
    private Book_BorrowsBO book_borrowsBO = BOFactory.getInstance().getBO(BOTypes.BOOK_BORROWS);

    public void initialize() throws Exception {

        generateId();

//        btnReturn.setAlignment(Pos.BASELINE_LEFT);
        btnHome.setAlignment(Pos.BASELINE_LEFT);
//        btnDel.setAlignment(Pos.BASELINE_LEFT);
        btnAdd.setAlignment(Pos.BASELINE_LEFT);
        btnLogout.setAlignment(Pos.BASELINE_LEFT);

        localDate= LocalDate.now();
        txtDate.setText(localDate+"");
        txtDate.setDisable(true);

//        String sqlBorrwedBooks = "SELECT b_id,s_id,borrow_id FROM book_borrows WHERE borrow_id NOT IN (SELECT br_id FROM book_returns)";
//        PreparedStatement pstmBorrowedBooks = DBConnection.getInstance().getConnection().prepareStatement(sqlBorrwedBooks);

//        ResultSet rstBorrowedBooks = pstmBorrowedBooks.executeQuery();

        ObservableList borrowedStudents = cmbStuId.getItems();
        List<CustomDTO> borrowedBooks = book_returnBO.getBorrowedBooks();

        List<CustomDTO> brStu=book_returnBO.getBorrowedStudetns();
//        while (rstBorrowedBooks.next()) {
        for (CustomDTO customDTO:brStu) {
            borrowedStudents.add(customDTO.getStudent_id());
        }
//            borrowedStudents.add(rstBorrowedBooks.getString(2));

//        }

        cmbStuId.setItems(borrowedStudents);

    }

    public void generateId() throws Exception {
//        PreparedStatement pstmGetReturnId = DBConnection.getInstance().getConnection().prepareStatement("select return_id from book_returns order by 1 desc limit 1");
//        ResultSet rstReturnId = pstmGetReturnId.executeQuery();

//        rstReturnId.next();
        String returnId = book_returnBO.generateReturnId();
//        String returnId = rstReturnId.getString(1);


        String returnNum = returnId.substring(1, returnId.length());
        int returnNumber = Integer.parseInt(returnNum);
        String newReturnId = String.format("R" + "%03d", returnNumber + 1);

        txtReturnId.setText(newReturnId);
        txtReturnId.setDisable(true);
    }

    public void btnHome_OnAction() throws IOException {

        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/librarymanagementsystem/view/Home.fxml"));
        Scene home = new Scene(root);

        Stage homeStage = (Stage) btnHome.getScene().getWindow();
        homeStage.setScene(home);


    }

    public void btnBorrows_OnAction(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/librarymanagementsystem/view/IssueBooks.fxml"));
        Scene home = new Scene(root);

        Stage homeStage = (Stage) btnHome.getScene().getWindow();
        homeStage.setScene(home);


    }


    public void btnLogout_OnAction(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/librarymanagementsystem/view/Login.fxml"));
        Scene student=new Scene(root);

        Stage studentStage =(Stage) btnReturn.getScene().getWindow();
        studentStage.setScene(student);


    }

    public void cmbStuId_OnAction(ActionEvent actionEvent) throws Exception {

//        String sqlSelectBookId = "SELECT b_id,s_id,b_date,borrow_id FROM book_borrows WHERE borrow_id NOT IN (SELECT br_id FROM book_returns) having s_id=?";
//        PreparedStatement pstmSelectBookId = DBConnection.getInstance().getConnection().prepareStatement(sqlSelectBookId);
//        pstmSelectBookId.setString(1, cmbStuId.getValue().toString());

//        ResultSet rstBookId = pstmSelectBookId.executeQuery();

        ObservableList bookId = cmbBookId.getItems();
        List<CustomDTO> customDTOS = book_borrowsBO.generateBorrowDetail(cmbStuId.getValue().toString());
//        if (rstBookId.next()) {
        bookId.clear();
        for (CustomDTO customDTO:customDTOS) {


            bookDetails.add(new CustomDTO(customDTO.getBook_id(),
                    customDTO.getStudent_id(),
                    customDTO.getBorrow_id(),
                    customDTO.getLocalDate()));

            bookId.add(customDTO.getBook_id());
        }
//        }

        cmbBookId.setItems(bookId);

//        String sqlSelectStudentName = "SELECT student_name FROM student WHERE student_id=?";
//        PreparedStatement pstmSelectStudentName = DBConnection.getInstance().getConnection().prepareStatement(sqlSelectStudentName);
//        pstmSelectStudentName.setString(1, cmbStuId.getValue().toString());
//
//        ResultSet rstStudentName = pstmSelectStudentName.executeQuery();
//        rstStudentName.next();

        StudentDTO studentById = studentBO.getStudentById(cmbStuId.getValue().toString());
        txtstuName.setText(studentById.getStudent_name());

    }

    public void cmbBookId_OnAction(ActionEvent actionEvent) throws Exception {

//        String sqlSelectBookName = "SELECT b_name FROM book WHERE book_id=?";
//        PreparedStatement pstmSelectBookName = DBConnection.getInstance().getConnection().prepareStatement(sqlSelectBookName);
//        pstmSelectBookName.setString(1, cmbBookId.getValue().toString());
//
//        ResultSet rstBookId = pstmSelectBookName.executeQuery();
//
//        rstBookId.next();

        BookDTO bookById = bookBO.getBookById(cmbBookId.getValue().toString());
        txtBookName.setText(bookById.getB_name());

        for (int i = 0; i <bookDetails.size() ; i++) {

            System.out.println(bookDetails.size());

//            if(bookDetails.get(i).getStudentId().equals(cmbStuId.getValue())){
//
//                System.out.println(2);

                if(bookDetails.get(i).getBook_id().equals(cmbBookId.getValue())){

                    System.out.println(3);
                    System.out.println(bookDetails.get(i).getLocalDate());
                    borrowId=bookDetails.get(i).getBorrow_id();
                    LocalDate dueDtae = bookDetails.get(i).getLocalDate().plusWeeks(2);

                    if(dueDtae.isAfter(dueDtae)){

                        Alert alert=new Alert(Alert.AlertType.WARNING,"You are "+localDate.compareTo(dueDtae)+" day(s) late!");
                        alert.show();
                        txtDueDate.setText(dueDtae.toString());

                    }else {
                        txtDueDate.setText(dueDtae.toString());
                    }
                }

//            }

        }
    }

    public void btnAdd_OnAction(ActionEvent actionEvent) throws Exception {

        Connection connection=DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

//        String sqlinsert="INSERT INTO book_returns values(?,?,?)";
//        PreparedStatement pstminsert=connection.prepareStatement(sqlinsert);

        Book_ReturnsDTO book_returns=new Book_ReturnsDTO(txtReturnId.getText(),
                LocalDate.now(),
                borrowId);
                book_returnBO.saveReturnedBook(book_returns);

//        pstminsert.setString(1,txtReturnId.getText());
//        pstminsert.setString(2,txtDate.getText());
//        pstminsert.setString(3,borrowId);

//        pstminsert.executeUpdate();

//        String sqldelete= "DELETE FROM book_borrows WHERE borrow_id=?";
//        PreparedStatement pstmDel = connection.prepareStatement(sqldelete);
//
//        pstmDel.setString(1,borrowId);
//
//        pstmDel.executeUpdate();

//        String sqlUpdate="UPDATE book SET availability=availability+1 where book_id=?";
//        PreparedStatement pstmUpdate= connection.prepareStatement(sqlUpdate);

//        pstmUpdate.setString(1,cmbBookId.getValue().toString());

//        pstmUpdate.executeUpdate();

        bookBO.updateAvailable(1,cmbBookId.getValue().toString());

        connection.commit();

        txtstuName.clear();
        txtBookName.clear();
        txtDueDate.clear();
//        cmbStuId.setValue(-1);
//        cmbBookId.setValue(-1);

        generateId();

        Alert alert=new Alert(Alert.AlertType.INFORMATION,"Succesfuly returned!");
        alert.show();

    }
}