package lk.ijse.librarymanagementsystem.dao.custom;

import lk.ijse.librarymanagementsystem.dao.CrudDAO;
import lk.ijse.librarymanagementsystem.entity.Book;


public interface BookDAO extends CrudDAO<Book,String> {

    String getLastBookId()throws Exception;

    public boolean updateAvailability(int i,String id)throws Exception;

}
