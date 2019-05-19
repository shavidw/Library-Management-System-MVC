package lk.ijse.librarymanagementsystem.controller;

import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;

import java.io.IOException;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalTime;

public class HomeController {
    public Button btnManageStudents;
    public Button btnManageBooks;
    public Button btnIssueBooks;
    public Button btnReturnBooks;
    public Button btnSearchBooks;
    public Button btnUser;
    public Button btnDate;
    public Button btnTime;
    public Button btnTitle;
    public Button btnUser1;
    public Label lblInfo;
    public Label lblTitle;
    public Button btnLogout;


    public void initialize(){

        btnUser1.setText("User : "+LoginController.getUser);
//

    }

    public void onMouseEntered(Button button,double x){

        ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), button);
        scaleT.setToX(x);
        scaleT.setToY(x);
        scaleT.play();

    }


            public void student() throws IOException {

        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/librarymanagementsystem/view/ManageStudents.fxml"));
        Scene student=new Scene(root);
        student.getStylesheets().add("/lk/ijse/librarymanagementsystem/view/css/table.css");
        Stage studentStage =(Stage) btnIssueBooks.getScene().getWindow();
        studentStage.setScene(student);

    }

    public void btnManageStudents_OnAction(ActionEvent actionEvent) throws IOException {

        student();

    }


    public void btnSearchBooks_OnAction(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/librarymanagementsystem/view/SearchBooks.fxml"));
        Scene student=new Scene(root);
        student.getStylesheets().add("/lk/ijse/librarymanagementsystem/view/css/table.css");
        Stage studentStage =(Stage) btnIssueBooks.getScene().getWindow();
        studentStage.setScene(student);

    }

    public void btnManageBooks_OnAction(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/librarymanagementsystem/view/ManageBooks.fxml"));
        Scene student=new Scene(root);
        student.getStylesheets().add("/lk/ijse/librarymanagementsystem/view/css/table.css");
        Stage studentStage =(Stage) btnIssueBooks.getScene().getWindow();
        studentStage.setScene(student);

    }

    public void btnIssueBooks_OnAction(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/librarymanagementsystem/view/IssueBooks.fxml"));
        Scene student=new Scene(root);

        Stage studentStage =(Stage) btnIssueBooks.getScene().getWindow();
        studentStage.setScene(student);

    }

    public void btnLogout_OnAction(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/librarymanagementsystem/view/Login.fxml"));
        Scene student=new Scene(root);

        Stage studentStage =(Stage) btnIssueBooks.getScene().getWindow();
        studentStage.setScene(student);

    }

    public void imgStudent_OnMouseClicked(MouseEvent mouseEvent) throws IOException {

        student();

    }

    public void btnManageStudents_OnMouseEntered(MouseEvent mouseEvent) {

      lblTitle.setText("Manage Students");
      lblInfo.setText("Add/Delete/Update Student Details");

        onMouseEntered(btnManageStudents,1.1);


    }

    public void btnManageStudents_OnMouseEnxited(MouseEvent mouseEvent) {

        lblTitle.setText("");
        lblInfo.setText("");

        onMouseEntered(btnManageStudents,1);
    }

    public void btnIssueBooks_OnMouseEntered(MouseEvent mouseEvent) {

        lblTitle.setText("Issue/Return Books");
        lblInfo.setText("");

        onMouseEntered(btnIssueBooks,1.1);

    }

    public void btnSearchBooks_OnMouseEntered(MouseEvent mouseEvent) {

        lblTitle.setText("Search");
        lblInfo.setText("Search book/student Details");

        onMouseEntered(btnSearchBooks,1.1);

    }

    public void btnManageBooks_OnMouseEntered(MouseEvent mouseEvent) {

        lblTitle.setText("Manage Books");
        lblInfo.setText("Add/Delete/Update Book Details");

        onMouseEntered(btnManageBooks,1.1);

    }

    public void btnManageBooks_OnMouseExited(MouseEvent mouseEvent) {

        lblTitle.setText("");
        lblInfo.setText("");

        onMouseEntered(btnManageBooks,1);

    }

    public void btnSearchBooks_OnMouseExited(MouseEvent mouseEvent) {

        lblTitle.setText("");
        lblInfo.setText("");

        onMouseEntered(btnSearchBooks,1);

    }

    public void btnIssueBooks_OnMouseExited(MouseEvent mouseEvent) {

        lblTitle.setText("");
        lblInfo.setText("");

        onMouseEntered(btnIssueBooks,1);

    }


}
