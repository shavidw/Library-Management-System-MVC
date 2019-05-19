package lk.ijse.librarymanagementsystem.business.custom;

import lk.ijse.librarymanagementsystem.business.SuperBO;
import lk.ijse.librarymanagementsystem.dto.BookDTO;
import lk.ijse.librarymanagementsystem.dto.Book_BorrowsDTO;
import lk.ijse.librarymanagementsystem.dto.CustomDTO;

import java.util.List;

public interface Book_BorrowsBO extends SuperBO {

    public List<Book_BorrowsDTO> getAllBook_Borrows() throws Exception;

    public boolean saveBook_Borrow(Book_BorrowsDTO item) throws Exception;

    public boolean deleteBook_Borrow(String code) throws Exception;

    public String generateBorrowId() throws Exception;

    public List<CustomDTO> generateBorrowDetail(String id) throws Exception;

}
