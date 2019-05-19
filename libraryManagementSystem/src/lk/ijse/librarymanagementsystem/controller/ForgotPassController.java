package lk.ijse.librarymanagementsystem.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import lk.ijse.librarymanagementsystem.business.BOFactory;
import lk.ijse.librarymanagementsystem.business.BOTypes;
import lk.ijse.librarymanagementsystem.business.custom.PasswordBO;
import lk.ijse.librarymanagementsystem.db.DBConnection;
import lk.ijse.librarymanagementsystem.entity.Password;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ForgotPassController {
    public JFXTextField txtUsername;
    public Label lblMessage;
    public Button btnCancel;

    private PasswordBO passwordBO = BOFactory.getInstance().getBO(BOTypes.PASSWORD) ;

    public void btnCancel_OnAction(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/librarymanagementsystem/view/Login.fxml"));
        Scene scene=new Scene(root);

        Stage stage=(Stage)txtUsername.getScene().getWindow();
        stage.setScene(scene);

    }

    public void btnPassword_OnAction(ActionEvent actionEvent)  {

        String user=txtUsername.getText().trim();

//        PreparedStatement pstmPass = DBConnection.getInstance().getConnection().prepareStatement("select * from password where username=?");
//        pstmPass.setString(1,user);
//        ResultSet rstPass = pstmPass.executeQuery();
//        if(rstPass.next()){
        try{
         String password= passwordBO.getPassword(user);

        if(!password.equals(null))
       {

            lblMessage.setText("Your Password : " + password);
            btnCancel.setText("Back");
        }}
        catch (NullPointerException e){
        lblMessage.setText("Invalid Username"); txtUsername.requestFocus();
            Logger.getLogger("lk.ijse.librarymanagementsystem.controller").log(Level.SEVERE, null,e);} catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger("lk.ijse.librarymanagementsystem.controller").log(Level.SEVERE, null,e);
        }
//catch (Exception e){
//    Logger.getLogger("lk.ijse.librarymanagementsystem.controller").log(Level.SEVERE, null,e);
//}
    }


    public void txtUsername_OnAction(ActionEvent actionEvent) {

        btnCancel.setText("Cancel");
    }
}
