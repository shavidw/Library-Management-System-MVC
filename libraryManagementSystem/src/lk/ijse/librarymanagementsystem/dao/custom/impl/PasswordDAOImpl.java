package lk.ijse.librarymanagementsystem.dao.custom.impl;

import lk.ijse.librarymanagementsystem.dao.CrudUtil;
import lk.ijse.librarymanagementsystem.dao.custom.PasswordDAO;
import lk.ijse.librarymanagementsystem.entity.Password;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PasswordDAOImpl implements PasswordDAO {

    public boolean save(Password password) throws Exception {
        return CrudUtil.execute("INSERT INTO password VALUES (?,?)", password.getPassword(), password.getUsername());
    }

    public boolean update(Password password) throws Exception {
        return CrudUtil.execute("UPDATE `password` SET password=? WHERE username=?", password.getPassword(), password.getUsername());
    }

    public boolean delete(String id) throws Exception {
        return CrudUtil.execute("DELETE FROM password WHERE username=?", id);
    }

    public Password find(String id) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM password WHERE username=?", id);
        if (rst.next()) {
            return new Password(rst.getString(1),rst.getString(2));
        }
        return null;
    }

    public List<Password> findAll() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM password");
        List<Password> alPasswords = new ArrayList<>();
        while (rst.next()) {
            alPasswords.add(new Password(rst.getString(1),rst.getString(2)));
        }
        return alPasswords;
    }


}
