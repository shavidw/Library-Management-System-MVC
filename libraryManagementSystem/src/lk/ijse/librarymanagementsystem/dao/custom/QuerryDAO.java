package lk.ijse.librarymanagementsystem.dao.custom;

import lk.ijse.librarymanagementsystem.business.SuperBO;
import lk.ijse.librarymanagementsystem.dao.SuperDAO;
import lk.ijse.librarymanagementsystem.entity.CustomEntity;

import java.util.List;

public interface QuerryDAO extends SuperDAO {

    List<CustomEntity> getBorrowDetail(String id) throws Exception;

    List<CustomEntity> getBorrowedBooks() throws Exception;

    public List<CustomEntity> getBorrowedStudents() throws Exception;

}
