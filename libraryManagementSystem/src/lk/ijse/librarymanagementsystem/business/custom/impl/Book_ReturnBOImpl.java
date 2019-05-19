package lk.ijse.librarymanagementsystem.business.custom.impl;

import lk.ijse.librarymanagementsystem.business.custom.Book_ReturnBO;
import lk.ijse.librarymanagementsystem.dao.DAOFactory;
import lk.ijse.librarymanagementsystem.dao.DAOTypes;
import lk.ijse.librarymanagementsystem.dao.custom.Book_BorrowsDAO;
import lk.ijse.librarymanagementsystem.dao.custom.Book_ReturnsDAO;
import lk.ijse.librarymanagementsystem.dao.custom.QuerryDAO;
import lk.ijse.librarymanagementsystem.dto.BookDTO;
import lk.ijse.librarymanagementsystem.dto.Book_ReturnsDTO;
import lk.ijse.librarymanagementsystem.dto.CustomDTO;
import lk.ijse.librarymanagementsystem.entity.Book;
import lk.ijse.librarymanagementsystem.entity.Book_Returns;

import java.util.List;
import java.util.stream.Collectors;

public class Book_ReturnBOImpl implements Book_ReturnBO {

    private QuerryDAO querryDAO = DAOFactory.getInstance().getDAO(DAOTypes.QUERRY);

    private Book_ReturnsDAO book_returnsDAO = DAOFactory.getInstance().getDAO(DAOTypes.BOOK_RETURNS);

    public String generateReturnId() throws Exception {
        return book_returnsDAO.getLastReturnId();
    }

    public List<CustomDTO> getBorrowedBooks() throws Exception {
        return querryDAO.getBorrowedBooks().stream().map(customEntity -> new CustomDTO(customEntity.getBook_id(),
                customEntity.getStudent_id(),
                customEntity.getBorrow_id()
        )).collect(Collectors.toList());
    }

    public List<CustomDTO> getBorrowedStudetns() throws Exception{
        return querryDAO.getBorrowedStudents().stream().map(customEntity -> new CustomDTO(customEntity.getStudent_id())).collect(Collectors.toList());
    }

    public boolean saveReturnedBook(Book_ReturnsDTO book_returns) throws Exception {
        return book_returnsDAO.save(new Book_Returns(book_returns.getReturn_id(),
                book_returns.getReturned_date(),
                book_returns.getBr_id()));
    }

    public List<Book_ReturnsDTO> getReturnededBooks() throws Exception {
        return book_returnsDAO.findAll().stream().map(book_returns -> new Book_ReturnsDTO(book_returns.getReturn_id(),
                book_returns.getReturned_date(),book_returns.getBr_id()
        )).collect(Collectors.toList());
    }

}
