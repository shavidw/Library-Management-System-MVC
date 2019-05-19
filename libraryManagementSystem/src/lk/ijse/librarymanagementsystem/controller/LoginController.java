package lk.ijse.librarymanagementsystem.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;
import lk.ijse.librarymanagementsystem.business.BOFactory;
import lk.ijse.librarymanagementsystem.business.BOTypes;
import lk.ijse.librarymanagementsystem.business.custom.BookBO;
import lk.ijse.librarymanagementsystem.business.custom.PasswordBO;
import lk.ijse.librarymanagementsystem.db.DBConnection;
import lk.ijse.librarymanagementsystem.dto.BookDTO;
import lk.ijse.librarymanagementsystem.dto.PasswordDTO;
import lk.ijse.librarymanagementsystem.util.BooksTM;
import lk.ijse.librarymanagementsystem.util.PasswordTM;
import lk.ijse.librarymanagementsystem.util.StudentTM;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class LoginController {

    public Label lblForgotPass;
    int index;
    public TextField txtPassword;
    public TextField txtUsername;
    public Label lblmessage;
    public PasswordField pssPassword;
    private ObservableList<PasswordTM> olpass= FXCollections.observableArrayList();
    public static String getUser;
private PasswordBO passwordBO = BOFactory.getInstance().getBO(BOTypes.PASSWORD) ;

    public void initialize() throws Exception {

//        PreparedStatement pstmSelect = DBConnection.getInstance().getConnection().prepareStatement("select * from password");
//        ResultSet rstPass = pstmSelect.executeQuery();

//        while (rstPass.next()){

        List<PasswordDTO> allPasswords = passwordBO.getAllPasswords();
        for (PasswordDTO passwordDTO : allPasswords) {
//

            olpass.add(new PasswordTM(passwordDTO.getPassword(), passwordDTO.getUsername()));

        }
//        user=txtUsername.getText();

//    }
    }

    public void btnLogin_OnAction(ActionEvent actionEvent) throws IOException {

        String password=pssPassword.getText();

        String user1=txtUsername.getText();
        getUser=txtUsername.getText();

        for (int i = 0; i <olpass.size() ; i++) {

            if (olpass.get(i).getUsername().equals(user1)) {
                index = i;
                System.out.println(index + " :: index");

            }else lblmessage.setText("Username is incorrect");

        }

                if (olpass.get(index).getPassword().equals(pssPassword.getText())) {

                    Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/librarymanagementsystem/view/Home.fxml "));
                    Scene scene = new Scene(root);

                    Stage stage = (Stage) txtUsername.getScene().getWindow();
                    stage.setScene(scene);
                    return;

                } else {
                    lblmessage.setText("Username/Password is incorrect");
                    return;
                }




    }

    public void btnSignUp_OnAction(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/librarymanagementsystem/view/SignUp.fxml"));
        Scene scene=new Scene(root);

        Stage stage=(Stage) txtUsername.getScene().getWindow();
        stage.setScene(scene);

    }

    public void lblForgotPass_OnMouseClicked(MouseEvent mouseEvent) throws IOException {

        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/librarymanagementsystem/view/ForgotPass.fxml"));
        Scene scene=new Scene(root);

        Stage stage=(Stage) txtUsername.getScene().getWindow();
        stage.setScene(scene);

    }
}
