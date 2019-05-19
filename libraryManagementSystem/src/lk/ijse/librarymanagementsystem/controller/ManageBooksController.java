package lk.ijse.librarymanagementsystem.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lk.ijse.librarymanagementsystem.business.BOFactory;
import lk.ijse.librarymanagementsystem.business.BOTypes;
import lk.ijse.librarymanagementsystem.business.custom.BookBO;
import lk.ijse.librarymanagementsystem.db.DBConnection;
import lk.ijse.librarymanagementsystem.dto.BookDTO;
import lk.ijse.librarymanagementsystem.dto.StudentDTO;
import lk.ijse.librarymanagementsystem.util.BooksTM;
import lk.ijse.librarymanagementsystem.util.StudentTM;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

public class ManageBooksController {
    public Button btnHome;
    public TextField txtBookId;
    public TextField txtBookName;
    public TextField txtAuthor;
    public TextField txtPrice;
    public TextField txtQty;
    public TextField txtPublisher;
    public TextField txtEdition;
    public TableView<BooksTM> tblBooks;
    public ObservableList<BooksTM> lstBooks;
    public Button btnSave;
    public Button btnNew;
    public Button btnDelete;
    public Button btnLogout;
    public JFXTextField txtSearch;
    public Button btnReport;
    private int delValue;

    private BookBO bookBO = BOFactory.getInstance().getBO(BOTypes.BOOK) ;

    public void initialize() throws Exception {

        btnSave.setAlignment(Pos.BASELINE_LEFT);
        btnHome.setAlignment(Pos.BASELINE_LEFT);
        btnDelete.setAlignment(Pos.BASELINE_LEFT);
        btnNew.setAlignment(Pos.BASELINE_LEFT);
        btnLogout.setAlignment(Pos.BASELINE_LEFT);
        btnReport.setAlignment(Pos.BASELINE_LEFT);

        txtBookId.setDisable(true);
        txtBookName.setDisable(true);
        txtAuthor.setDisable(true);
        txtEdition.setDisable(true);
        txtPrice.setDisable(true);
        txtPublisher.setDisable(true);
        txtQty.setDisable(true);

        lstBooks = tblBooks.getItems();

//        String sqlBook="SELECT * from book";
//        PreparedStatement pstmBook= DBConnection.getInstance().getConnection().prepareStatement(sqlBook);
//
//        ResultSet rstBook=pstmBook.executeQuery();

        tblBooks.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("bookId"));
        tblBooks.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("bookName"));
        tblBooks.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("author"));
        tblBooks.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("price"));
        tblBooks.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("qty"));
        tblBooks.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("publisher"));
        tblBooks.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("edition"));

//        while (rstBook.next()){
        List<BookDTO> allBooks = bookBO.getAllBooks();
        for (BookDTO bookDTO : allBooks) {
            lstBooks.add(new BooksTM(bookDTO.getBook_id(),
                    bookDTO.getB_name(),
                    bookDTO.getAuthor(),
                    bookDTO.getPrice(),
                    bookDTO.getCount(),
                    bookDTO.getPublisher(),
                    bookDTO.getEdition(),
                    bookDTO.getAvailability()));
//        }

            tblBooks.getSelectionModel().selectedItemProperty().addListener(
                    new ChangeListener<BooksTM>() {
                        @Override
                        public void changed(ObservableValue<? extends BooksTM> observable, BooksTM oldValue, BooksTM newValue) {

                            btnSave.setText("Update");

                            txtBookId.setDisable(true);
                            txtBookName.setDisable(false);
                            txtAuthor.setDisable(false);
                            txtEdition.setDisable(false);
                            txtPrice.setDisable(false);
                            txtPublisher.setDisable(false);
                            txtQty.setDisable(false);


                            if (newValue == null) {
                                txtBookId.clear();
                                txtBookName.clear();
                                txtAuthor.clear();
                                txtEdition.clear();
                                txtPrice.clear();
                                txtPublisher.clear();
                                txtQty.clear();
                                return;

                            }
                            txtBookId.setText(newValue.getBookId());
                            txtBookName.setText(newValue.getBookName());
                            txtAuthor.setText(newValue.getAuthor());
                            txtEdition.setText(newValue.getEdition());
                            txtPrice.setText(newValue.getPrice().toString());
                            txtPublisher.setText(newValue.getPublisher());
                            txtQty.setText(newValue.getQty().toString());


                        }
                    }
            );

            FilteredList<BooksTM> filteredData = new FilteredList<>(lstBooks, e -> true);
            txtSearch.setOnKeyReleased(e -> {
                txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
                    filteredData.setPredicate((Predicate<? super BooksTM>) BooksTM -> {
                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        }
                        String lowerCaseFilter = newValue.toLowerCase();
                        if (BooksTM.getBookId().toLowerCase().contains(lowerCaseFilter)) {
                            return true;
                        } else if (BooksTM.getAuthor().toLowerCase().contains(lowerCaseFilter)) {
                            return true;
                        } else if (BooksTM.getBookName().toLowerCase().contains(lowerCaseFilter)) {
                            return true;
                        }
//                    else if(BooksTM.getEdition().contains(newValue)){
                        //   return true;
//                    }else if (BooksTM.getPublisher().toLowerCase().contains(lowerCaseFilter)){
//                        return true;
//                    }
                        else if (BooksTM.getPrice().toString().contains(newValue)) {
                            return true;
                        } else if (BooksTM.getQty().toString().contains(newValue)) {
                            return true;
                        }
                        return false;
                    });
                });
                SortedList<BooksTM> sortedData = new SortedList<>(filteredData);
                sortedData.comparatorProperty().bind(tblBooks.comparatorProperty());
                tblBooks.setItems(sortedData);
            });


        }

    }

    public void btnHome_OnAction(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/librarymanagementsystem/view/Home.fxml"));
        Scene home=new Scene(root);

        Stage homeStage=(Stage)btnHome.getScene().getWindow();
        homeStage.setScene(home);


    }

    public void btnNew_OnAction(ActionEvent actionEvent) throws Exception {

        generateId();

        txtBookName.setDisable(false);
        txtAuthor.setDisable(false);
        txtEdition.setDisable(false);
        txtPrice.setDisable(false);
        txtPublisher.setDisable(false);
        txtQty.setDisable(false);

        txtBookName.clear();
        txtAuthor.clear();
        txtEdition.clear();
        txtPrice.clear();
        txtPublisher.clear();
        txtQty.clear();

    }

    public void generateId() throws Exception {
//        PreparedStatement pstmGetBookId=DBConnection.getInstance().getConnection().prepareStatement("select book_id from book order by 1 desc limit 1");
//        ResultSet rstBookId=pstmGetBookId.executeQuery();

//        rstBookId.next();
        String getBookId = bookBO.generateBookId();
//        String getBookId=rstBookId.getString(1);
//        final String getBookId = bookBO.generateBookId();


        String bookNum=getBookId.substring(3,getBookId.length());
        int orderNumber=Integer.parseInt(bookNum);
        String newBookId=String.format("BID"+"%03d",orderNumber+1);

        txtBookId.setText(newBookId);
        txtBookId.setDisable(true);
    }


    public void btnSave_OnAction(ActionEvent actionEvent) throws Exception {

        if(txtBookId.getText().length()==0) return;

        String id=txtBookId.getText();
        String name=txtBookName.getText();
        String author=txtAuthor.getText();
        Double price=Double.parseDouble(txtPrice.getText());
        Integer qty=Integer.parseInt(txtQty.getText());
        String publisher=txtPublisher.getText();
        String edition=txtEdition.getText();

        if(tblBooks.getSelectionModel().isSelected(tblBooks.getSelectionModel().getSelectedIndex())){

//            String sqlUpdate="UPDATE book SET b_name=?,author=?,price=?,count=?,publisher=?,edition=?,availability=? where book_id=?";
//            PreparedStatement pstmUpdate= DBConnection.getInstance().getConnection().prepareStatement(sqlUpdate);
//
//            pstmUpdate.setString(1,name);
//            pstmUpdate.setString(2,author);
//            pstmUpdate.setDouble(3,price);
//            pstmUpdate.setInt(4,qty);
//            pstmUpdate.setString(5,publisher);
//            pstmUpdate.setString(6,edition);
//            pstmUpdate.setInt(7,qty);
//            pstmUpdate.setString(8,id);
//
//
//            pstmUpdate.executeUpdate();

            BookDTO bookDTO = new BookDTO(id,name,author,price,qty,publisher,edition,qty);

            boolean result = bookBO.updateBook(bookDTO);

            int selectedIndex = tblBooks.getSelectionModel().getSelectedIndex();
            lstBooks.add(selectedIndex,new BooksTM(id,name,author,price,qty,publisher,edition));
            lstBooks.remove(selectedIndex+1);

            tblBooks.getSelectionModel().clearSelection();
            btnSave.setText("Save");

            txtBookId.setDisable(true);
            txtBookName.setDisable(true);
            txtAuthor.setDisable(true);
            txtEdition.setDisable(true);
            txtPrice.setDisable(true);
            txtPublisher.setDisable(true);
            txtQty.setDisable(true);

            txtBookId.clear();
            txtBookName.clear();
            txtAuthor.clear();
            txtEdition.clear();
            txtPrice.clear();
            txtPublisher.clear();
            txtQty.clear();
            return;

        }

//        String sqlinsert="INSERT INTO book values(?,?,?,?,?,?,?,?)";
//        PreparedStatement pstminsert=DBConnection.getInstance().getConnection().prepareStatement(sqlinsert);
//
//
//        pstminsert.setString(2,name);
//        pstminsert.setString(3,author);
//        pstminsert.setDouble(4,price);
//        pstminsert.setInt(5,qty);
//        pstminsert.setString(6,publisher);
//        pstminsert.setString(7,edition);
//        pstminsert.setInt(8,qty);
//        pstminsert.setString(1,id);
//
//        pstminsert.executeUpdate();

        BookDTO bookDTO = new BookDTO(id,name,author,price,qty,publisher,edition,qty);

        boolean result = bookBO.saveBook(bookDTO);

        txtBookId.clear();
        txtBookName.clear();
        txtAuthor.clear();
        txtEdition.clear();
        txtPrice.clear();
        txtPublisher.clear();
        txtQty.clear();

        tblBooks.getItems().add(new BooksTM(id,name,author,price,qty,publisher,edition));

        txtBookId.setDisable(true);
        txtBookName.setDisable(true);
        txtAuthor.setDisable(true);
        txtEdition.setDisable(true);
        txtPrice.setDisable(true);
        txtPublisher.setDisable(true);
        txtQty.setDisable(true);

    }

    public void btnDelete_OnAction(ActionEvent actionEvent) {

        int index= tblBooks.getSelectionModel().getSelectedIndex();

        Alert delAlert=new Alert(Alert.AlertType.CONFIRMATION,"Are you Sure?", ButtonType.YES,ButtonType.NO);
        Optional<ButtonType> clickedbtn=delAlert.showAndWait();
        if(clickedbtn.get()== ButtonType.YES) {
            delValue=0;

//            String sqldelete= "DELETE FROM book WHERE book_id=?";
            try {
//                PreparedStatement pstmdel=DBConnection.getInstance().getConnection().prepareStatement(sqldelete);

                String selectedId=lstBooks.get(index).getBookId();
//                pstmdel.setString(1,selectedId);
                bookBO.deleteBook(selectedId);
                lstBooks.remove(index);
//                pstmdel.executeUpdate();

            } catch (SQLIntegrityConstraintViolationException e){
                delValue=1;
                Alert sqlAlert=new Alert(Alert.AlertType.ERROR,"The selected data cannot be deleted");
                sqlAlert.show();

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }catch (Exception e){
                e.printStackTrace();
            }

            btnSave.setText("Save");}

            tblBooks.getSelectionModel().clearSelection();
    }

    public void btnLogout_OnAction(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/librarymanagementsystem/view/Login.fxml"));
        Scene student=new Scene(root);

        Stage studentStage =(Stage) btnSave.getScene().getWindow();
        studentStage.setScene(student);


    }

    public void btnReport_OnAction(ActionEvent actionEvent) throws JRException, SQLException, ClassNotFoundException {

        InputStream resourceAsStream = this.getClass().getResourceAsStream("/lk/ijse/librarymanagementsystem/report/main.jrxml");
        JasperDesign jasperDesign = JRXmlLoader.load(resourceAsStream);

        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

        Map<String,Object> params=new HashMap<>();
//        params.put("OrderId",txtOrderId.getText());
//        params.put("OrderDate",txtOrderDate.getText());
//        params.put("CustId",cmbCustomerId.getValue().toString());
//        params.put("CustName",txtPOCusName.getText());
//        params.put("netTotal",Double.parseDouble(lblNetTotal.getText()));


        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,params,DBConnection.getInstance().getConnection());
//        JasperViewer.viewReport(jasperPrint);
        JasperViewer.viewReport(jasperPrint,false);

    }
}



