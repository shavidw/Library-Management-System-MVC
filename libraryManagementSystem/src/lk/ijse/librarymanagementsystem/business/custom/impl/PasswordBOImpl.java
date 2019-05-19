package lk.ijse.librarymanagementsystem.business.custom.impl;

import lk.ijse.librarymanagementsystem.business.custom.PasswordBO;
import lk.ijse.librarymanagementsystem.dao.DAOFactory;
import lk.ijse.librarymanagementsystem.dao.DAOTypes;
import lk.ijse.librarymanagementsystem.dao.custom.BookDAO;
import lk.ijse.librarymanagementsystem.dao.custom.PasswordDAO;
import lk.ijse.librarymanagementsystem.dto.BookDTO;
import lk.ijse.librarymanagementsystem.dto.PasswordDTO;
import lk.ijse.librarymanagementsystem.entity.Book;
import lk.ijse.librarymanagementsystem.entity.Password;

import java.util.List;
import java.util.stream.Collectors;

public class PasswordBOImpl implements PasswordBO {


    private PasswordDAO passwordDAO = DAOFactory.getInstance().getDAO(DAOTypes.PASSWORD);

    public List<PasswordDTO> getAllPasswords() throws Exception {
        return passwordDAO.findAll().stream().map(password -> new PasswordDTO(password.getPassword(),password.getUsername())).collect(Collectors.toList());

    }

    public boolean savePassword(PasswordDTO password) throws Exception {
        return passwordDAO.save(new Password(password.getPassword(),
                password.getUsername()));
    }

    public String getPassword(String password) throws Exception{
        return  passwordDAO.find(password).getPassword();


    }

}
