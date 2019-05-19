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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lk.ijse.librarymanagementsystem.business.BOFactory;
import lk.ijse.librarymanagementsystem.business.BOTypes;
import lk.ijse.librarymanagementsystem.business.custom.StudentBO;
import lk.ijse.librarymanagementsystem.db.DBConnection;
import lk.ijse.librarymanagementsystem.dto.StudentDTO;
import lk.ijse.librarymanagementsystem.util.StudentTM;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class ManageStudentsController {
    public Button btnHome;
    public TextField txtStuId;
    public TextField txtStuName;
    public TextField txtAddress;
    public TextField txtContact;
    public TableView<StudentTM> tblStudent;
    public ComboBox cmbDep;
    public RadioButton rdoMale;
    public RadioButton rdoFemale;
    public Button btnSave;
    public Button btnLogout;
    public Button btnDelete;
    public Button btnNew;
    public JFXTextField txtSearch;
    ObservableList<StudentTM> lstStudents;
    private int delValue;

    private StudentBO studentBO = BOFactory.getInstance().getBO(BOTypes.STUDENT);

    public void initialize() throws Exception {

        btnSave.setAlignment(Pos.BASELINE_LEFT);
        btnHome.setAlignment(Pos.BASELINE_LEFT);
        btnDelete.setAlignment(Pos.BASELINE_LEFT);
        btnNew.setAlignment(Pos.BASELINE_LEFT);
        btnLogout.setAlignment(Pos.BASELINE_LEFT);

        txtStuId.setDisable(true);
        txtStuName.setDisable(true);
        txtAddress.setDisable(true);
        txtContact.setDisable(true);

        ObservableList lstDep = cmbDep.getItems();

        lstDep.add("DEIE");
        lstDep.add("DCEE");
        lstDep.add("DMME");

        lstStudents = tblStudent.getItems();



        tblStudent.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("studentId"));
        tblStudent.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("studentName"));
        tblStudent.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));
        tblStudent.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("gender"));
        tblStudent.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("department"));
        tblStudent.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("contactNum"));
//
//        String sqlStudent = "SELECT * from student";
//        PreparedStatement pstmStudent = DBConnection.getInstance().getConnection().prepareStatement(sqlStudent);
//
//        ResultSet rstStudent = pstmStudent.executeQuery();
//
//        while (rstStudent.next()) {

        List<StudentDTO> allStudents = studentBO.getAllStudents();
        for (StudentDTO studentDTO : allStudents) {
            lstStudents.add(new StudentTM(studentDTO.getStudent_id(),
                    studentDTO.getStudent_name(),
                    studentDTO.getAddress(),
                    studentDTO.getGender(),
                    studentDTO.getDepartment(),
                    studentDTO.getContact_num()));
//            lstStudents.add(new StudentTM(rstStudent.getString(1),
//                    rstStudent.getString(2),
//                    rstStudent.getString(3),
//                    rstStudent.getString(4),
//                    rstStudent.getString(5),
//                    rstStudent.getString(6)));

        }

        ToggleGroup tglGender = new ToggleGroup();
        rdoFemale.setToggleGroup(tglGender);
        rdoMale.setToggleGroup(tglGender);

        tblStudent.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<StudentTM>() {
                    @Override
                    public void changed(ObservableValue<? extends StudentTM> observable, StudentTM oldValue, StudentTM newValue) {
                        if (newValue == null) {
                            txtStuId.clear();
                            txtStuName.clear();
                            txtAddress.clear();
                            txtContact.clear();
                            cmbDep.setValue("");
                            return;

                        }
                        txtStuId.setText(newValue.getStudentId());
                        txtStuName.setText(newValue.getStudentName());
                        txtAddress.setText(newValue.getAddress());
                        txtContact.setText(newValue.getContactNum());
                        cmbDep.setValue(newValue.getDepartment());


                    }
                }
        );

        FilteredList<StudentTM> filteredData=new FilteredList<>(lstStudents, e-> true);
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

    }

    public void btnHome_OnAction(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/librarymanagementsystem/view/Home.fxml"));
        Scene home = new Scene(root);

        Stage homeStage = (Stage) btnHome.getScene().getWindow();
        homeStage.setScene(home);


    }

    public void btnNew_OnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

//        PreparedStatement pstmGetStudentId=DBConnection.getInstance().getConnection().prepareStatement("select student_id from student order by 1 desc limit 1");
//        ResultSet rstStudentId=pstmGetStudentId.executeQuery();
//
//        rstStudentId.next();
//        String getStudentId=rstStudentId.getString(1);
//
//
//        String studentNum=getStudentId.substring(3,getStudentId.length());
//        int orderNumber=Integer.parseInt(studentNum);
//        String newBookId=String.format("SID"+"%03d",orderNumber+1);


        txtStuId.setDisable(false);


        txtStuName.setDisable(false);
        txtAddress.setDisable(false);
        txtContact.setDisable(false);


    }

    public void btnSave_OnAction(ActionEvent actionEvent) throws Exception {

        if (txtStuId.isDisable() || txtStuId.getText().length() == 0){

            Alert alert= new Alert(Alert.AlertType.ERROR,"Student Id field must be filled");
            alert.show();

            return;}

        String rdoSelection;

        if (rdoMale.isSelected()) rdoSelection = "Male";
        else rdoSelection = "Female";

        String id = txtStuId.getText();
        String name = txtStuName.getText();
        String address = txtAddress.getText();
        String dep = cmbDep.getValue() + "";
        String contact = txtContact.getText();

        if (!contact.matches("\\d{3}-\\d{7}")){
           Alert alert=new Alert(Alert.AlertType.ERROR,"Invalid Contact Number");
           alert.show();

           txtContact.clear();
           txtContact.requestFocus();
           return;
        }


        if (tblStudent.getSelectionModel().isSelected(tblStudent.getSelectionModel().getSelectedIndex())) {

            StudentDTO studentDTO = new StudentDTO(id,name,address,rdoSelection,dep,contact);

            boolean result = studentBO.updateStudent(studentDTO);

//            String sqlUpdate = "UPDATE student SET student_name=?,address=?,gender=?,department=?,contact_num=? where student_id=?";
//            PreparedStatement pstmUpdate = DBConnection.getInstance().getConnection().prepareStatement(sqlUpdate);
//
//            pstmUpdate.setString(1, name);
//            pstmUpdate.setString(2, address);
//            pstmUpdate.setString(3, rdoSelection);
//            pstmUpdate.setString(4, dep);
//            pstmUpdate.setString(5, contact);
//            pstmUpdate.setString(6, id);
//
//            pstmUpdate.executeUpdate();

            int selectedIndex = tblStudent.getSelectionModel().getSelectedIndex();
            lstStudents.remove(selectedIndex);
            lstStudents.add(selectedIndex, new StudentTM(id, name, address, rdoSelection, dep, contact));

            tblStudent.getSelectionModel().clearSelection();
            btnSave.setText("Save");

            txtStuId.clear();
            txtStuName.clear();
            txtAddress.clear();
            txtContact.clear();
            cmbDep.setValue("");

            txtStuId.setDisable(true);
            txtStuName.setDisable(true);
            txtAddress.setDisable(true);
            txtContact.setDisable(true);
            return;

        }

        StudentDTO studentDTO = new StudentDTO(id,name,address,rdoSelection,dep,contact);

            boolean result = studentBO.saveStudent(studentDTO);

//        String sqlinsert = "INSERT INTO student values(?,?,?,?,?,?)";
//        PreparedStatement pstminsert = DBConnection.getInstance().getConnection().prepareStatement(sqlinsert);
//
//
//        pstminsert.setString(1, id);
//        pstminsert.setString(2, name);
//        pstminsert.setString(3, address);
//        pstminsert.setString(4, rdoSelection);
//        pstminsert.setString(5, dep);
//        pstminsert.setString(6, contact);
//
//        pstminsert.executeUpdate();

        txtStuId.clear();
        txtStuName.clear();
        txtAddress.clear();
        txtContact.clear();
        cmbDep.setValue("");

        tblStudent.getItems().add(new StudentTM(id,
                name,
                address,
                rdoSelection,
                dep,
                contact));

        txtStuId.setDisable(true);
        txtStuName.setDisable(true);
        txtAddress.setDisable(true);
        txtContact.setDisable(true);

    }

    private void refreshTable() {
    }

    public void tblStudent_OnMouseClicked(MouseEvent mouseEvent) {

        btnSave.setText("Update");

        txtStuId.setDisable(false);
        txtStuName.setDisable(false);
        txtAddress.setDisable(false);
        txtContact.setDisable(false);

    }

    public void btnDelete_OnMouseClicked(MouseEvent mouseEvent) {

        int index = tblStudent.getSelectionModel().getSelectedIndex();

        Alert delAlert = new Alert(Alert.AlertType.CONFIRMATION, "Are you Sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> clickedbtn = delAlert.showAndWait();
        if (clickedbtn.get() == ButtonType.YES) {
            delValue = 0;

//            String sqldelete = "DELETE FROM student WHERE student_id=?";
            try {
//                PreparedStatement pstmdel = DBConnection.getInstance().getConnection().prepareStatement(sqldelete);

                String selectedId = lstStudents.get(index).getStudentId();

                studentBO.removeStudent(selectedId);

//                pstmdel.setString(1, selectedId);
                lstStudents.remove(index);
//                pstmdel.executeUpdate();

            } catch (SQLIntegrityConstraintViolationException e) {
                delValue = 1;
                Alert sqlAlert = new Alert(Alert.AlertType.ERROR, "The selected data cannot be deleted");
                sqlAlert.show();

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }catch (Exception e){
                e.printStackTrace();
            }

            btnSave.setText("Save");
            tblStudent.getSelectionModel().clearSelection();
        }
    }

//    public void generateId() throws SQLException, ClassNotFoundException {
//        PreparedStatement pstmGetStudentId = DBConnection.getInstance().getConnection().prepareStatement("select student_id from student order by 1 desc limit 1");
//        ResultSet rstStudentId = pstmGetStudentId.executeQuery();
//
//        rstStudentId.next();
//        String studentId = rstStudentId.getString(1);
//
//
//        String studentNum = studentId.substring(1, studentId.length());
//        int studentNumber = Integer.parseInt(studentNum);
//        String newStudentId = String.format("S" + "%03d", studentNumber + 1);
//
//        txtStuId.setText(newStudentId);
//        txtStuId.setDisable(true);
//    }

    public void btnLogout_OnAction(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/librarymanagementsystem/view/Login.fxml"));
        Scene student=new Scene(root);

        Stage studentStage =(Stage) btnSave.getScene().getWindow();
        studentStage.setScene(student);

    }
}





