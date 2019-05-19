package lk.ijse.librarymanagementsystem.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lk.ijse.librarymanagementsystem.business.BOFactory;
import lk.ijse.librarymanagementsystem.business.BOTypes;
import lk.ijse.librarymanagementsystem.business.custom.BookBO;
import lk.ijse.librarymanagementsystem.business.custom.Book_BorrowsBO;
import lk.ijse.librarymanagementsystem.business.custom.Book_ReturnBO;
import lk.ijse.librarymanagementsystem.business.custom.StudentBO;
import lk.ijse.librarymanagementsystem.db.DBConnection;
import lk.ijse.librarymanagementsystem.dto.*;
import lk.ijse.librarymanagementsystem.util.BooksTM;
import lk.ijse.librarymanagementsystem.util.BorrowTM;
import lk.ijse.librarymanagementsystem.util.SearchTM;
import lk.ijse.librarymanagementsystem.util.StudentTM;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SearchBooksController
{
    public Button btnHome;
    public TableView tblNew;
    public TableView tblOld;
    public JFXButton btnBooks;
    public JFXButton btnStudent;

    public TableView<BooksTM> tblBooks;
    public TableView<StudentTM> tblStudent;
    public JFXTextField txtSearch;
    public TableView<BorrowTM> tblBorrows;
    public JFXButton btnBorrows;
    public JFXButton btnReturns;
    public TableView<SearchTM> tblReturns;
    public JFXButton btnNonReturns;
    public TableView<BorrowTM> tblNonReturns;

    ObservableList<BooksTM> olBooks;
    ObservableList<StudentTM> olStudents;
    ObservableList<BorrowTM> olBorrows;
    ObservableList<BorrowTM> olNonReturn;
    ObservableList<SearchTM> olReturns;

    private BookBO bookBO = BOFactory.getInstance().getBO(BOTypes.BOOK) ;
    private StudentBO studentBO = BOFactory.getInstance().getBO(BOTypes.STUDENT);
    private Book_BorrowsBO book_borrowsBO = BOFactory.getInstance().getBO(BOTypes.BOOK_BORROWS);
    private Book_ReturnBO book_returnBO = BOFactory.getInstance().getBO(BOTypes.BOOK_RETURNS);

    public void initialize() throws Exception {

//        btnReturn.setAlignment(Pos.BASELINE_LEFT);
        setTblBooks();

        tblStudent.setVisible(false);
        tblBorrows.setVisible(false);
        tblReturns.setVisible(false);
        tblNonReturns.setVisible(false);

        olStudents=tblStudent.getItems();

        FilteredList<BooksTM> filteredData=new FilteredList<>(olBooks, e-> true);
        txtSearch.setOnKeyReleased(e ->{
            txtSearch.textProperty().addListener((observable, oldValue, newValue) ->  {
                filteredData.setPredicate((Predicate<? super BooksTM>) BooksTM ->{
                    if(newValue==null  || newValue.isEmpty()){
                        return true;
                    }
                    String lowerCaseFilter=newValue.toLowerCase();
                    if(BooksTM.getBookId().toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    }else if(BooksTM.getAuthor().toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    }else if(BooksTM.getBookName().toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    }
//                    else if(BooksTM.getEdition().contains(newValue)){
                    //   return true;
//                    }else if (BooksTM.getPublisher().toLowerCase().contains(lowerCaseFilter)){
//                        return true;
//                    }
                    else if (BooksTM.getPrice().toString().contains(newValue)){
                        return true;
                    }else if (BooksTM.getQty().toString().contains(newValue)){
                        return true;
                    }
                    return false;
                });
            });
            SortedList<BooksTM> sortedData=new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(tblBooks.comparatorProperty());
            tblBooks.setItems(sortedData);
        });


    }

    public void setTblBooks() throws Exception {


        olBooks=tblBooks.getItems();
//        String sqlBook="SELECT * from book";
//        PreparedStatement pstmBook= DBConnection.getInstance().getConnection().prepareStatement(sqlBook);
//
//        ResultSet rstBook=pstmBook.executeQuery();

        tblBooks.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("bookId"));
        tblBooks.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("bookName"));
        tblBooks.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("author"));
        tblBooks.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("price"));
        tblBooks.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("qty"));
        tblBooks.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("publisher"));
        tblBooks.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("edition"));
        tblBooks.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("availability"));

//        while (rstBook.next()){

        List<BookDTO> allBooks = bookBO.getAllBooks();
        for (BookDTO bookDTO : allBooks) {
            try{olBooks.add(new BooksTM
                    (bookDTO.getBook_id(),
                    bookDTO.getB_name(),
                    bookDTO.getAuthor(),
                    bookDTO.getPrice(),
                    bookDTO.getCount(),
                    bookDTO.getPublisher(),
                    bookDTO.getEdition(),
                    bookDTO.getAvailability()));}
                    catch (UnsupportedOperationException ex){
                        System.out.println("ex");
//                        Logger.getLogger("lk.ijse.librarymanagementsystem.controller").log(Level.SEVERE, null,ex);
                    }
}

//        olBooks.add(new BooksTM(rstBook.getString(1),
//                    rstBook.getString(2),
//                    rstBook.getString(3),
//                    rstBook.getDouble(4),
//                    rstBook.getInt(5),
//                    rstBook.getString(6),
//                    rstBook.getString(7),
//                    rstBook.getInt(8)));
//        }

//        pstmBook.executeQuery();

    }

    public void setTblStudents() throws Exception {


        olStudents=tblStudent.getItems();
//        String sqlStudent="SELECT * from student";
//        PreparedStatement pstmStudent= DBConnection.getInstance().getConnection().prepareStatement(sqlStudent);
//
//        ResultSet rstStudent=pstmStudent.executeQuery();

        tblStudent.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("studentId"));
        tblStudent.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("studentName"));
        tblStudent.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));
        tblStudent.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("gender"));
        tblStudent.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("department"));
        tblStudent.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("contactNum"));


//        while (rstStudent.next()){
        List<StudentDTO> allStudents = studentBO.getAllStudents();
        for (StudentDTO studentDTO : allStudents) {
            olStudents.add(new StudentTM(studentDTO.getStudent_id(),
                    studentDTO.getStudent_name(),
                    studentDTO.getAddress(),
                    studentDTO.getGender(),
                    studentDTO.getDepartment(),
                    studentDTO.getContact_num()));
        }
//        olStudents.add(new StudentTM(rstStudent.getString(1),
//                    rstStudent.getString(2),
//                    rstStudent.getString(3),
//                    rstStudent.getString(4),
//                    rstStudent.getString(5),
//                    rstStudent.getString(6)
//            ));
//        }

//        pstmStudent.executeQuery();

    }

    public void setTblBorrowss() throws Exception {


        olBorrows=tblBorrows.getItems();
//        String sqlStudent="SELECT * from student";
//        PreparedStatement pstmStudent= DBConnection.getInstance().getConnection().prepareStatement(sqlStudent);
//
//        ResultSet rstStudent=pstmStudent.executeQuery();

        tblBorrows.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("borrowId"));
        tblBorrows.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("studentId"));
        tblBorrows.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("borrowDate"));
        tblBorrows.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("bookId"));



//        while (rstStudent.next()){
        List<Book_BorrowsDTO> allBorrows = book_borrowsBO.getAllBook_Borrows();
        for (Book_BorrowsDTO book_borrowsDTO : allBorrows) {
            olBorrows.add(new BorrowTM(book_borrowsDTO.getBorrow_id(),
                    book_borrowsDTO.getS_id(),
                    book_borrowsDTO.getB_date(),
                    book_borrowsDTO.getB_id()));
        }
//        olStudents.add(new StudentTM(rstStudent.getString(1),
//                    rstStudent.getString(2),
//                    rstStudent.getString(3),
//                    rstStudent.getString(4),
//                    rstStudent.getString(5),
//                    rstStudent.getString(6)
//            ));
//        }

//        pstmStudent.executeQuery();

    }



    public void btnHome_OnAction(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/librarymanagementsystem/view/Home.fxml"));
        Scene home=new Scene(root);

        Stage homeStage=(Stage)btnHome.getScene().getWindow();
        homeStage.setScene(home);


    }

    public void btnBooks_OnAction(ActionEvent actionEvent) throws Exception {

        if(olBooks.size()!=0) {olBooks.clear();
        System.out.println("cleared");}
        txtSearch.clear();
        setTblBooks();


        tblBooks.setVisible(true);
        tblStudent.setVisible(false);
        tblBorrows.setVisible(false);
        tblReturns.setVisible(false);
        tblNonReturns.setVisible(false);

        btnBooks.setStyle("-fx-background-color: #193531");
        btnStudent.setStyle("-fx-background-color:  #09251E");
        btnBorrows.setStyle("-fx-background-color: #09251E");
        btnReturns.setStyle("-fx-background-color: #09251E");
        btnNonReturns.setStyle("-fx-background-color: #09251E");

        FilteredList<BooksTM> filteredData=new FilteredList<>(olBooks, e-> true);
        txtSearch.setOnKeyReleased(e ->{
            txtSearch.textProperty().addListener((observable, oldValue, newValue) ->  {
                filteredData.setPredicate((Predicate<? super BooksTM>) BooksTM ->{
                    if(newValue==null  || newValue.isEmpty()){
                        return true;
                    }
                    String lowerCaseFilter=newValue.toLowerCase();
                    if(BooksTM.getBookId().toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    }else if(BooksTM.getAuthor().toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    }else if(BooksTM.getBookName().toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    }
//                    else if(BooksTM.getEdition().contains(newValue)){
                    //   return true;
//                    }else if (BooksTM.getPublisher().toLowerCase().contains(lowerCaseFilter)){
//                        return true;
//                    }
                    else if (BooksTM.getPrice().toString().contains(newValue)){
                        return true;
                    }else if (BooksTM.getQty().toString().contains(newValue)){
                        return true;
                    }
                    return false;
                });
            });
            SortedList<BooksTM> sortedData=new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(tblBooks.comparatorProperty());
            tblBooks.setItems(sortedData);
        });


    }

    public void btnStudent_OnAction(ActionEvent actionEvent) throws Exception {

//       if(olStudents.size()!=0) olStudents.clear();
//       txtSearch.clear();
        setTblStudents();

        FilteredList<StudentTM> filteredData=new FilteredList<>(olStudents, e-> true);
        txtSearch.setOnKeyReleased(e ->{
            txtSearch.textProperty().addListener((observable, oldValue, newValue) ->  {
                filteredData.setPredicate((Predicate<? super StudentTM>) StudentTM ->{
                    if(newValue==null  || newValue.isEmpty()){
                        return true;
                    }
                    String lowerCaseFilter=newValue.toLowerCase();
                    if(StudentTM.getStudentId().toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    }else if(StudentTM.getStudentName().toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    }else if(StudentTM.getAddress().toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    }
                    else if(StudentTM.getContactNum().contains(newValue)){
                        return true;
                    }else if (StudentTM.getGender().toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    }else if (StudentTM.getDepartment().toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    }
                    return false;
                });
            });
            SortedList<StudentTM> sortedData=new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(tblStudent.comparatorProperty());
            tblStudent.setItems(sortedData);
        });


        tblBooks.setVisible(false);
        tblStudent.setVisible(true);
        tblBorrows.setVisible(false);
        tblReturns.setVisible(false);
        tblNonReturns.setVisible(false);

        btnStudent.setStyle("-fx-background-color: #193531");
        btnBooks.setStyle("-fx-background-color:  #09251E");
        btnBorrows.setStyle("-fx-background-color: #09251E");
        btnReturns.setStyle("-fx-background-color: #09251E");
        btnNonReturns.setStyle("-fx-background-color: #09251E");
    }

    public void btnLogout_OnAction(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/librarymanagementsystem/view/Login.fxml"));
        Scene student=new Scene(root);

        Stage studentStage =(Stage) btnBooks.getScene().getWindow();
        studentStage.setScene(student);

    }

    public void btnBorrows_OnAction(ActionEvent actionEvent) throws Exception {

        setTblBorrowss();

        tblBorrows.setVisible(true);
        tblStudent.setVisible(false);
        tblBooks.setVisible(false);
        tblReturns.setVisible(false);
        tblNonReturns.setVisible(false);

        btnBorrows.setStyle("-fx-background-color: #193531");
        btnBooks.setStyle("-fx-background-color:  #09251E");
        btnStudent.setStyle("-fx-background-color:  #09251E");
        btnReturns.setStyle("-fx-background-color: #09251E");
        btnNonReturns.setStyle("-fx-background-color: #09251E");
    }

    public void btnReturns_OnAction(ActionEvent actionEvent) throws Exception {


        olReturns=tblReturns.getItems();

//        String sqlBook="SELECT * from book";
//        PreparedStatement pstmBook= DBConnection.getInstance().getConnection().prepareStatement(sqlBook);
//
//        ResultSet rstBook=pstmBook.executeQuery();

        tblReturns.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("return_id"));
        tblReturns.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("returned_date"));
        tblReturns.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("br_id"));


//        while (rstBook.next()){

        List<Book_ReturnsDTO> allBooks = book_returnBO.getReturnededBooks();
        for (Book_ReturnsDTO book_returnsDTO : allBooks) {
            try{olReturns.add(new SearchTM(book_returnsDTO.getReturn_id(),
                            book_returnsDTO.getReturned_date(),
                            book_returnsDTO.getBr_id())
                    );}
            catch (UnsupportedOperationException ex){
                System.out.println("ex");
//                        Logger.getLogger("lk.ijse.librarymanagementsystem.controller").log(Level.SEVERE, null,ex);
            }
        }


        tblBorrows.setVisible(false);
        tblStudent.setVisible(false);
        tblBooks.setVisible(false);
        tblReturns.setVisible(true);
        tblNonReturns.setVisible(false);

        btnBorrows.setStyle("-fx-background-color: #09251E");
        btnBooks.setStyle("-fx-background-color:  #09251E");
        btnStudent.setStyle("-fx-background-color:  #09251E");
        btnReturns.setStyle("-fx-background-color: #193531");
        btnNonReturns.setStyle("-fx-background-color: #09251E");

    }

    public void btnNonReturns_onAction(ActionEvent actionEvent) throws Exception {




        olNonReturn=tblNonReturns.getItems();
//        String sqlBook="SELECT * from book";
//        PreparedStatement pstmBook= DBConnection.getInstance().getConnection().prepareStatement(sqlBook);
//
//        ResultSet rstBook=pstmBook.executeQuery();

        tblNonReturns.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("bookId"));
        tblNonReturns.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("studentId"));
        tblNonReturns.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("borrowDate"));
        tblNonReturns.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("borrowId"));



//        while (rstBook.next()){

        List<CustomDTO> allBooks = book_returnBO.getBorrowedBooks();
        for (CustomDTO book_borrowsDTO : allBooks) {
            try{olNonReturn.add(new BorrowTM(book_borrowsDTO.getBorrow_id(),
                    book_borrowsDTO.getStudent_id(),
                    book_borrowsDTO.getLocalDate(),
                    book_borrowsDTO.getBook_id())
            );}
            catch (UnsupportedOperationException ex){
                System.out.println("ex");
//                        Logger.getLogger("lk.ijse.librarymanagementsystem.controller").log(Level.SEVERE, null,ex);
            }
        }


        tblBorrows.setVisible(false);
        tblStudent.setVisible(false);
        tblBooks.setVisible(false);
        tblReturns.setVisible(false);
        tblNonReturns.setVisible(true);

        btnBorrows.setStyle("-fx-background-color: #09251E");
        btnBooks.setStyle("-fx-background-color:  #09251E");
        btnStudent.setStyle("-fx-background-color:  #09251E");
        btnReturns.setStyle("-fx-background-color: #09251E");
        btnNonReturns.setStyle("-fx-background-color: #193531");


    }
}
