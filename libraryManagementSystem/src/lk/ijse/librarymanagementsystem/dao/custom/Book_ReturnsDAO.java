package lk.ijse.librarymanagementsystem.dao.custom;


import lk.ijse.librarymanagementsystem.dao.CrudDAO;
import lk.ijse.librarymanagementsystem.entity.Book_Returns;

public interface Book_ReturnsDAO extends CrudDAO <Book_Returns,String> {

    String getLastReturnId()throws Exception;

}
