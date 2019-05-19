package lk.ijse.librarymanagementsystem.business.custom;

import lk.ijse.librarymanagementsystem.business.SuperBO;
import lk.ijse.librarymanagementsystem.dto.BookDTO;
import lk.ijse.librarymanagementsystem.dto.PasswordDTO;
import lk.ijse.librarymanagementsystem.entity.Password;

import java.util.List;

public interface PasswordBO extends SuperBO {

    public List<PasswordDTO> getAllPasswords() throws Exception;

    public boolean savePassword(PasswordDTO password) throws Exception;

    public String getPassword(String password) throws Exception;

}
