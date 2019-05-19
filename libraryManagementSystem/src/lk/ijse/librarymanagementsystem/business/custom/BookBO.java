package lk.ijse.librarymanagementsystem.business.custom;

import lk.ijse.librarymanagementsystem.business.SuperBO;
import lk.ijse.librarymanagementsystem.dto.BookDTO;
import lk.ijse.librarymanagementsystem.dto.StudentDTO;

import java.util.List;

public interface BookBO extends SuperBO {

    public List<BookDTO> getAllBooks() throws Exception;

    public boolean saveBook(BookDTO item) throws Exception;

    public boolean updateBook(BookDTO item)throws Exception;

    public boolean deleteBook(String code) throws Exception;

    public String generateBookId() throws Exception;

    public BookDTO getBookById(String id)throws Exception;

    public boolean updateAvailable(int i,String id)throws Exception;

}
