package lk.ijse.librarymanagementsystem.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import lk.ijse.librarymanagementsystem.business.BOFactory;
import lk.ijse.librarymanagementsystem.business.BOTypes;
import lk.ijse.librarymanagementsystem.business.custom.BookBO;
import lk.ijse.librarymanagementsystem.business.custom.Book_BorrowsBO;
import lk.ijse.librarymanagementsystem.business.custom.PasswordBO;
import lk.ijse.librarymanagementsystem.business.custom.StudentBO;
import lk.ijse.librarymanagementsystem.db.DBConnection;
import lk.ijse.librarymanagementsystem.dto.BookDTO;
import lk.ijse.librarymanagementsystem.dto.Book_BorrowsDTO;
import lk.ijse.librarymanagementsystem.dto.CustomDTO;
import lk.ijse.librarymanagementsystem.dto.StudentDTO;
import lk.ijse.librarymanagementsystem.util.AddBookTM;
import lk.ijse.librarymanagementsystem.util.BooksTM;
import lk.ijse.librarymanagementsystem.util.StudentTM;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class IssueBooksController {
    public Button btnHome;
    public ListView lstBooks;
    public TextField txtReturningDate;
    public TextField txtDate;
    public TextField txtBookName;
    public TextField txtstuName;
    public TextField txtBorrowId;
    public ComboBox cmbBookId;
    public ComboBox cmbStuId;
    public Button btnSave;
    public Button btnLogout;
    public Button btnDel;
    public Button btnAdd;
    public Button btnDecline;
    ObservableList lstBookId;
    ObservableList lstStudentId;
    ObservableList<AddBookTM> olBorrowedBooks=FXCollections.observableArrayList();
    LocalDate localDate;
    ObservableList<AddBookTM> olAddingBooks=FXCollections.observableArrayList();
    ObservableList olempty;
ObservableList olFilteredBorrowed=FXCollections.observableArrayList();
int issued=0;

    private BookBO bookBO = BOFactory.getInstance().getBO(BOTypes.BOOK) ;
    private StudentBO studentBO = BOFactory.getInstance().getBO(BOTypes.STUDENT);
    private Book_BorrowsBO book_borrowsBO = BOFactory.getInstance().getBO(BOTypes.BOOK_BORROWS);

    public void initialize() throws Exception {

        btnSave.setAlignment(Pos.BASELINE_LEFT);
        btnHome.setAlignment(Pos.BASELINE_LEFT);
        btnDel.setAlignment(Pos.BASELINE_LEFT);
        btnAdd.setAlignment(Pos.BASELINE_LEFT);
        btnLogout.setAlignment(Pos.BASELINE_LEFT);
        btnDecline.setAlignment(Pos.BASELINE_LEFT);

        olempty=lstBooks.getItems();

        generateId();

        localDate=LocalDate.now();
        txtDate.setText(localDate+"");
        txtDate.setDisable(true);

//        String sqlCmbBookId="SELECT book_id FROM book";
//        PreparedStatement pstmCmbList=DBConnection.getInstance().getConnection().prepareStatement(sqlCmbBookId);
//
//        ResultSet rstBookId=pstmCmbList.executeQuery();

        List<BookDTO> allBooks = bookBO.getAllBooks();
        for (BookDTO bookDTO : allBooks) {


            lstBookId = cmbBookId.getItems();
            lstBookId.add(bookDTO.getBook_id());
//        while (rstBookId.next())
//        {       }
        }
//            String sqlCmbStudentId = "SELECT student_id FROM student";
//            PreparedStatement pstmCmbStudentList = DBConnection.getInstance().getConnection().prepareStatement(sqlCmbStudentId);

//            ResultSet rstStudentId = pstmCmbStudentList.executeQuery();


            List<StudentDTO> allStudents = studentBO.getAllStudents();
            for (StudentDTO studentDTO : allStudents) {
                lstStudentId = cmbStuId.getItems();
                lstStudentId.add(studentDTO.getStudent_id());

//            while (rstStudentId.next()) {

//            }
            }

    }

    public void generateId() throws Exception {
//        PreparedStatement pstmGetBorrowId= DBConnection.getInstance().getConnection().prepareStatement("select borrow_id from book_borrows order by 1 desc limit 1");
//        ResultSet rstBorrowId=pstmGetBorrowId.executeQuery();

//        rstBorrowId.next();

        String borrowId;
        borrowId = book_borrowsBO.generateBorrowId();

//        String borrowId=rstBorrowId.getString(1);


        String borrowNum=borrowId.substring(2,borrowId.length());
        int borrowNumber=Integer.parseInt(borrowNum);
        String newBorrowId=String.format("BO"+"%03d",borrowNumber+1);

        txtBorrowId.setText(newBorrowId);
        txtBorrowId.setDisable(true);
    }

    public void btnHome_OnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/librarymanagementsystem/view/Home.fxml"));
        Scene home=new Scene(root);

        Stage homeStage=(Stage)btnHome.getScene().getWindow();
        homeStage.setScene(home);


    }

    public void cmbStuId_OnAction(ActionEvent actionEvent) throws Exception {

        lstBooks.getItems().clear();
        olFilteredBorrowed.clear();
        olBorrowedBooks.clear();
        olAddingBooks.clear();

        String studentId=cmbStuId.getValue().toString();

//        String sqlSelectStudentName="SELECT student_name FROM student WHERE student_id=?";
//        PreparedStatement pstmSelectStudentName=DBConnection.getInstance().getConnection().prepareStatement(sqlSelectStudentName);
//        pstmSelectStudentName.setString(1,studentId);

//        ResultSet rstStudentName=pstmSelectStudentName.executeQuery();
        StudentDTO studentById = studentBO.getStudentById(studentId);
//        if(rstStudentName.next())
        { txtstuName.setText(studentById.getStudent_name());}
        txtstuName.setDisable(true);

//        String sqlBorrwedBooks = "SELECT b_id,s_id,borrow_id FROM book_borrows WHERE borrow_id NOT IN (SELECT br_id FROM book_returns) having s_id=?";
//        PreparedStatement pstmBorrowedBooks = DBConnection.getInstance().getConnection().prepareStatement(sqlBorrwedBooks);


        List<CustomDTO> customDTOS = book_borrowsBO.generateBorrowDetail(cmbStuId.getValue().toString());

        for (CustomDTO bookDTO : customDTOS) {

//        pstmBorrowedBooks.setString(1, cmbStuId.getValue().toString());
//        ResultSet rstBorrowedBooks = pstmBorrowedBooks.executeQuery();

//        while (rstBorrowedBooks.next()) {
            olBorrowedBooks.add(new AddBookTM(bookDTO.getBook_id(),
                   bookDTO.getStudent_id(),bookDTO.getBorrow_id()));
//            olBorrowedBooks.add(new AddBookTM(rstBorrowedBooks.getString(1),
//                    rstBorrowedBooks.getString(2),
//                    rstBorrowedBooks.getString(3)));
        }
        for (int i = 0; i <olBorrowedBooks.size() ; i++) {
            olFilteredBorrowed.add(olBorrowedBooks.get(i).getBookId());
        }

        lstBooks.setItems(olFilteredBorrowed);

        if (olBorrowedBooks.size() >= 2) {

            Alert alert = new Alert(Alert.AlertType.WARNING, "You have already taken 2 books!");
            alert.show();
            lstBooks.setItems(olFilteredBorrowed);
            return;
        }
        olAddingBooks.clear();
    }

    public void cmbBookId_OnAction(ActionEvent actionEvent) throws Exception {

//        olBorrowedBooks = lstBooks.getItems();
//        olBorrowedBooks.removeAll();
//        lstBooks.getItems().clear();

        String bookId = cmbBookId.getValue().toString();

//        String sqlSelectBookName = "SELECT b_name FROM book WHERE book_id=?";
//        PreparedStatement pstmSelectBookName = DBConnection.getInstance().getConnection().prepareStatement(sqlSelectBookName);
//        pstmSelectBookName.setString(1, bookId);

//        ResultSet rstBookName = pstmSelectBookName.executeQuery();

//        if (rstBookName.next()) {
        BookDTO bookById = bookBO.getBookById(bookId);
        txtBookName.setText(bookById.getB_name());
//        }
        txtBookName.setDisable(true);

        txtReturningDate.setText(localDate.plusWeeks(2).toString());
        txtReturningDate.setDisable(true);


//
//        String sqlBorrwedBooks = "SELECT b_id,s_id,borrow_id FROM book_borrows WHERE borrow_id NOT IN (SELECT br_id FROM book_returns) having s_id=?";
//        PreparedStatement pstmBorrowedBooks = DBConnection.getInstance().getConnection().prepareStatement(sqlBorrwedBooks);
//
//        pstmBorrowedBooks.setString(1, cmbStuId.getValue().toString());
//        ResultSet rstBorrowedBooks = pstmBorrowedBooks.executeQuery();
//
//        while (rstBorrowedBooks.next()) {
//
//            olBorrowedBooks.add(new AddBookTM(rstBorrowedBooks.getString(1),
//                    rstBorrowedBooks.getString(2),
//                    rstBorrowedBooks.getString(3)));
//        }
        lstBooks.getItems().clear();
        for (int i = 0; i <olBorrowedBooks.size() ; i++) {
            olFilteredBorrowed.add(olBorrowedBooks.get(i).getBookId());
        }
//
        lstBooks.setItems(olFilteredBorrowed);

//        if (olBorrowedBooks.size() >= 2) {
//
//            Alert alert = new Alert(Alert.AlertType.WARNING, "You have already taken 2 books!");
//            alert.show();
//            lstBooks.setItems(olFilteredBorrowed);
//            return;
//        }
if(olBorrowedBooks.size()==1){
    if(olAddingBooks.size()==1){
        lstBooks.getItems().clear();
        olempty.clear();
        olempty.add(olBorrowedBooks.get(0).getBookId());
        olempty.add(olAddingBooks.get(0).getBookId());
        lstBooks.setItems(olempty);
        return;
    }
}

        if(olAddingBooks.size()==1){
            lstBooks.getItems().clear();
            olempty.add(olAddingBooks.get(0).getBookId());
            lstBooks.setItems(olempty);
        }
        if(olAddingBooks.size()==2){
            olempty.add(olAddingBooks.get(0).getBookId());
            olempty.add(olAddingBooks.get(1).getBookId());
            lstBooks.setItems(olempty);
        }
    }
    public void btnAdd_OnAction(ActionEvent actionEvent) throws Exception {

        if(txtBorrowId.getText().length()==0 || txtstuName.getText().length()==0 || txtBookName.getText().length()==0) return;

        if(olBorrowedBooks.isEmpty()){

            if(olAddingBooks.size()==2){
                Alert alert=new Alert(Alert.AlertType.WARNING,"You have already taken 2 books!");
                alert.show();
                return;
            }
            olempty.clear();
            String borrowId=txtBorrowId.getText();
            if(olAddingBooks.size()==1){
                String borrowNum=borrowId.substring(2,borrowId.length());
                int borrowNumber=Integer.parseInt(borrowNum);
                borrowId=String.format("BO"+"%03d",borrowNumber+1);

            }else {borrowId=txtBorrowId.getText();}
            System.out.println(olAddingBooks.size()+" : "+borrowId);
            olAddingBooks.add(new AddBookTM(cmbBookId.getValue().toString(),cmbStuId.getValue().toString(),borrowId));

            for (int i = 0; i <olAddingBooks.size() ; i++) {
                olempty.add(olAddingBooks.get(i).getBookId());
            }

            lstBooks.setItems(olempty);

        }

        else if(olBorrowedBooks.size()==1) {

            if(olAddingBooks.size()==1){
                Alert alert=new Alert(Alert.AlertType.WARNING,"You have already taken 2 books!");
                alert.show();
                return;
            }

            lstBooks.getItems().clear();
            olAddingBooks.add(new AddBookTM(cmbBookId.getValue().toString(),cmbStuId.getValue().toString(),txtBorrowId.getText()));
            olempty.add(olBorrowedBooks.get(0).getBookId());
            olempty.add(olAddingBooks.get(0).getBookId());
            lstBooks.setItems(olempty);


        }

        else {
            Alert alert=new Alert(Alert.AlertType.WARNING,"You have already taken 2 books!");
            alert.show();

        }

    }



    public void btnDecline_OnAction(ActionEvent actionEvent) throws Exception {

        generateId();

        localDate=LocalDate.now();
        txtDate.setText(localDate+"");
        txtDate.setDisable(true);

        if(issued==0){
            Alert alert=new Alert(Alert.AlertType.ERROR,"Haven't borrowed any books");
            alert.show();
            return;
        }else {

            Alert delAlert = new Alert(Alert.AlertType.CONFIRMATION, "Are you Sure?", ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> clickedbtn = delAlert.showAndWait();
            if (clickedbtn.get() == ButtonType.YES) {

                for (int i = 0; i < olAddingBooks.size(); i++) {
//
//                    PreparedStatement pstmDecline = DBConnection.getInstance().getConnection().prepareStatement
//                            ("DELETE FROM book_borrows WHERE borrow_id=?");
//                    pstmDecline.setString(1, olAddingBooks.get(i).getBorrowId());
//                    pstmDecline.executeUpdate();

                    book_borrowsBO.deleteBook_Borrow(olAddingBooks.get(i).getBorrowId());

                }

            }

        }

        cmbStuId.setValue("");
        txtstuName.setText("");

        cmbBookId.setValue("");
        txtBookName.clear();

        txtReturningDate.clear();

        olAddingBooks.clear();

        lstBooks.getItems().clear();

    }

    public void btnDel_OnAction(ActionEvent actionEvent) {


        Alert delAlert = new Alert(Alert.AlertType.CONFIRMATION, "Are you Sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> clickedbtn = delAlert.showAndWait();
        if (clickedbtn.get() == ButtonType.YES) {

            String selectedId = lstBooks.getSelectionModel().getSelectedItems().toString();


            for (int i = 0; i < olAddingBooks.size(); i++) {

                if (olAddingBooks.get(i).getBookId().equals(selectedId.substring(1, 7))) {
                    olAddingBooks.remove(i);
                    lstBooks.getItems().remove(selectedId.substring(1, 7));
                }

            }
        }

    }

    public void btnSave_OnAction(ActionEvent actionEvent) throws Exception {

        if(olAddingBooks.size()==0){

            Alert alert=new Alert(Alert.AlertType.ERROR,"Haven't borrowed any books");
            alert.show();
            return;
        }

        Connection connection=DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        for (int i = 0; i <olAddingBooks.size() ; i++) {

//            PreparedStatement pstmIssue = DBConnection.getInstance().getConnection().prepareStatement("INSERT INTO book_borrows values(?,?,?,?)");
//            pstmIssue.setString(1, txtBorrowId.getText());
//            pstmIssue.setString(2, txtDate.getText());
//            pstmIssue.setString(3, cmbStuId.getValue().toString());
//            pstmIssue.setString(4, olAddingBooks.get(i).getBookId());

            Book_BorrowsDTO book_borrowsDTO=new Book_BorrowsDTO(olAddingBooks.get(i).getBorrowId(),
                    LocalDate.now(),
                    olAddingBooks.get(i).getStudentId(),
                    olAddingBooks.get(i).getBookId());
            book_borrowsBO.saveBook_Borrow(book_borrowsDTO);

            //String sqlUpdate="UPDATE book SET availability=availability-1 where book_id=?";
//            PreparedStatement pstmUpdate= DBConnection.getInstance().getConnection().prepareStatement(sqlUpdate);

//            pstmUpdate.setString(1,olAddingBooks.get(i).getBookId());

//            pstmUpdate.executeUpdate();

            bookBO.updateAvailable(-1,olAddingBooks.get(i).getBookId());

//            pstmIssue.executeUpdate();
        }

        connection.commit();



Alert alert=new Alert(Alert.AlertType.INFORMATION,"Successfully Issued!");
        alert.show();
        issued=1;
    }

    public void btnLogout_OnAction(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/librarymanagementsystem/view/Login.fxml"));
        Scene student=new Scene(root);

        Stage studentStage =(Stage) btnSave.getScene().getWindow();
        studentStage.setScene(student);

    }

    public void btnReturns_OnAction(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/librarymanagementsystem/view/ReturnBooks.fxml"));
        Scene home=new Scene(root);

        Stage homeStage=(Stage)btnHome.getScene().getWindow();
        homeStage.setScene(home);

    }
}
