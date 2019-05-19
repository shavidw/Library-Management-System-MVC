package lk.ijse.librarymanagementsystem.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lk.ijse.librarymanagementsystem.business.BOFactory;
import lk.ijse.librarymanagementsystem.business.BOTypes;
import lk.ijse.librarymanagementsystem.business.custom.PasswordBO;
import lk.ijse.librarymanagementsystem.db.DBConnection;
import lk.ijse.librarymanagementsystem.dto.BookDTO;
import lk.ijse.librarymanagementsystem.dto.PasswordDTO;
import lk.ijse.librarymanagementsystem.util.PasswordTM;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SignUpController {
    public TextField txtUsername;


    public ImageView imgHome;
    public JFXPasswordField psfPass;
    public JFXPasswordField psfRePass;

    public Label lblMessage;

    ObservableList olpass= FXCollections.observableArrayList();

    private PasswordBO passwordBO = BOFactory.getInstance().getBO(BOTypes.PASSWORD) ;

    public void initialize() throws Exception {

//        PreparedStatement pstmPass = DBConnection.getInstance().getConnection().prepareStatement("select username from password;");
//        ResultSet rstPass = pstmPass.executeQuery();

//        while (rstPass.next()){
            List<PasswordDTO> allPasswords = passwordBO.getAllPasswords();
            for (PasswordDTO passwordDTO : allPasswords) {
//

//                olpass.add(new PasswordTM(passwordDTO.getPassword(), passwordDTO.getUsername()));
                olpass.add(passwordDTO.getUsername() );
            }

//            olpass.add(rstPass.getString(1) );

//        }


    }

    public void btnSignUp_OnAction(ActionEvent actionEvent) throws Exception {

       for (int i = 0; i <olpass.size() ; i++) {
           if (txtUsername.getText().equals(olpass.get(i))) {

               lblMessage.setText("please select a different username");
               txtUsername.clear();
               txtUsername.requestFocus();

               return;


           }
       }

        if(!psfPass.getText().equals(psfRePass.getText())){

            lblMessage.setText("Password Doesn't match");
            psfPass.clear();
            psfRePass.clear();
            psfPass.requestFocus();



            return;

        }

//        PreparedStatement pstmValues = DBConnection.getInstance().getConnection().prepareStatement("INSERT into password values (?,?)");
//        pstmValues.setString(1,psfPass.getText());
//        pstmValues.setString(2,txtUsername.getText());
//
//        pstmValues.executeUpdate();

        PasswordDTO passwordDTO = new PasswordDTO(psfPass.getText(),txtUsername.getText());

        boolean result = passwordBO.savePassword(passwordDTO);

        psfPass.clear();
        txtUsername.clear();
        psfRePass.clear();

        Alert alert=new Alert(Alert.AlertType.INFORMATION,"Your account was created successfully!");
        alert.show();

        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/librarymanagementsystem/view/Login.fxml"));
        Scene scene=new Scene(root);

        Stage stage=(Stage)txtUsername.getScene().getWindow();
        stage.setScene(scene);

    }

    public void btnCancel_OnAction(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/librarymanagementsystem/view/Login.fxml"));
        Scene scene=new Scene(root);

        Stage stage=(Stage)txtUsername.getScene().getWindow();
        stage.setScene(scene);

    }

    public void txtUsername_OnAction(ActionEvent actionEvent) {

        for (int i = 0; i <olpass.size() ; i++) {
            if(txtUsername.getText().equals(olpass.get(i))){

                lblMessage.setText("username was taken");

            }
            else{lblMessage.setText("");}
        }



    }

    public void txtPassRe_OnAction(ActionEvent actionEvent) {

        if(!psfPass.getText().equals(psfRePass.getText())){

            lblMessage.setText("Password Doesn't match");

        }else lblMessage.setText("");


    }

    public void txtUsername_OnKeyReleased(KeyEvent keyEvent) {


        for (int i = 0; i <olpass.size() ; i++) {
            if(txtUsername.getText().equals(olpass.get(i))){

                lblMessage.setText("please select a different username");

            }
            else{lblMessage.setText("");}
        }
    }

    public void txtPassRe_OnKeyReleased(KeyEvent keyEvent) {

        if(!psfPass.getText().equals(psfRePass.getText())){

            lblMessage.setText("Password Doesn't match");

        }else lblMessage.setText("");

    }

    public void imgHome_OnMouseEntered(MouseEvent mouseEvent) {
    }

    public void imgHome_OnMouseExited(MouseEvent mouseEvent) {
    }

    public void imgHome_OnMouseClicked(MouseEvent mouseEvent) throws IOException {

        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/librarymanagementsystem/view/Login.fxml"));
        Scene scene=new Scene(root);

        Stage stage=(Stage) imgHome.getScene().getWindow();
        stage.setScene(scene);

    }

    public void psfPass_OnKeyReleased(KeyEvent keyEvent) {
    }

    public void psfRePass_OnKeyReleased(KeyEvent keyEvent) {

        if(!psfPass.getText().equals(psfRePass.getText())){

            lblMessage.setText("Password Doesn't match");

        }else lblMessage.setText("");

    }
}
