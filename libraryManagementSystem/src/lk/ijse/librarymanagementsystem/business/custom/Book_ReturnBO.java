package lk.ijse.librarymanagementsystem.business.custom;

import lk.ijse.librarymanagementsystem.business.SuperBO;
import lk.ijse.librarymanagementsystem.dto.Book_ReturnsDTO;
import lk.ijse.librarymanagementsystem.dto.CustomDTO;
import lk.ijse.librarymanagementsystem.entity.CustomEntity;

import java.util.List;

public interface Book_ReturnBO extends SuperBO {

    public String generateReturnId() throws Exception;

    public List<CustomDTO> getBorrowedBooks() throws Exception;

    public List<CustomDTO> getBorrowedStudetns() throws Exception;

    public boolean saveReturnedBook(Book_ReturnsDTO book_returns) throws Exception;

    public List<Book_ReturnsDTO> getReturnededBooks() throws Exception;

}
