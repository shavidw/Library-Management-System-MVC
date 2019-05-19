package lk.ijse.librarymanagementsystem.dao.custom;

import lk.ijse.librarymanagementsystem.dao.CrudDAO;
import lk.ijse.librarymanagementsystem.entity.Book_Borrows;

public interface Book_BorrowsDAO extends CrudDAO<Book_Borrows,String> {

    String getLastBorrowId()throws Exception;

}
