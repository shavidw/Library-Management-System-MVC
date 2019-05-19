package lk.ijse.librarymanagementsystem.business.custom.impl;

import lk.ijse.librarymanagementsystem.business.custom.BookBO;
import lk.ijse.librarymanagementsystem.business.custom.Book_BorrowsBO;
import lk.ijse.librarymanagementsystem.dao.DAOFactory;
import lk.ijse.librarymanagementsystem.dao.DAOTypes;
import lk.ijse.librarymanagementsystem.dao.custom.BookDAO;
import lk.ijse.librarymanagementsystem.dao.custom.Book_BorrowsDAO;
import lk.ijse.librarymanagementsystem.dao.custom.QuerryDAO;
import lk.ijse.librarymanagementsystem.dto.BookDTO;
import lk.ijse.librarymanagementsystem.dto.Book_BorrowsDTO;
import lk.ijse.librarymanagementsystem.dto.CustomDTO;
import lk.ijse.librarymanagementsystem.entity.Book;
import lk.ijse.librarymanagementsystem.entity.Book_Borrows;

import java.util.List;
import java.util.stream.Collectors;

public class Book_BorrowsBOImpl implements Book_BorrowsBO {

    private Book_BorrowsDAO book_borrowsDAO = DAOFactory.getInstance().getDAO(DAOTypes.BOOK_BORROWS);
    private QuerryDAO querryDAO = DAOFactory.getInstance().getDAO(DAOTypes.QUERRY);

    public List<Book_BorrowsDTO> getAllBook_Borrows() throws Exception {
        return book_borrowsDAO.findAll().stream().map(book_borrows -> new Book_BorrowsDTO(book_borrows.getBorrow_id(),
        book_borrows.getB_date(),
                book_borrows.getS_id(),
                book_borrows.getB_id())).collect(Collectors.toList());

    }

    public boolean saveBook_Borrow(Book_BorrowsDTO book_borrows) throws Exception {
        return book_borrowsDAO.save(new Book_Borrows(book_borrows.getBorrow_id(),
                book_borrows.getB_date(),
                book_borrows.getS_id(),
                book_borrows.getB_id()));
    }

    public boolean deleteBook_Borrow(String code) throws Exception {
        return book_borrowsDAO.delete(code);
    }

    public String generateBorrowId() throws Exception {
        return book_borrowsDAO.getLastBorrowId();
    }

    public List<CustomDTO> generateBorrowDetail(String id) throws Exception {
        return querryDAO.getBorrowDetail(id).stream().map(customEntity -> new CustomDTO(customEntity.getBook_id(),
                customEntity.getStudent_id(),
                customEntity.getBorrow_id(),
                customEntity.getB_date()
                )).collect(Collectors.toList());
    }

}
