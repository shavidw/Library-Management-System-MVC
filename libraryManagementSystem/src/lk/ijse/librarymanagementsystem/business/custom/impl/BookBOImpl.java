package lk.ijse.librarymanagementsystem.business.custom.impl;

import lk.ijse.librarymanagementsystem.business.custom.BookBO;
import lk.ijse.librarymanagementsystem.dao.DAOFactory;
import lk.ijse.librarymanagementsystem.dao.DAOTypes;
import lk.ijse.librarymanagementsystem.dao.custom.BookDAO;
import lk.ijse.librarymanagementsystem.dto.BookDTO;
import lk.ijse.librarymanagementsystem.dto.StudentDTO;
import lk.ijse.librarymanagementsystem.entity.Book;
import lk.ijse.librarymanagementsystem.entity.Student;

import java.util.List;
import java.util.stream.Collectors;

public class BookBOImpl implements BookBO {

    private BookDAO bookDAO = DAOFactory.getInstance().getDAO(DAOTypes.BOOK);

    @Override
    public BookDTO getBookById(String id) throws Exception {
        Book book = bookDAO.find(id);
        return new BookDTO(book.getBook_id(),
                book.getB_name(),
                book.getAuthor(),
                book.getPrice(),
                book.getCount(),
                book.getPublisher(),
                book.getEdition(),
                book.getAvailability());
    }

    public List<BookDTO> getAllBooks() throws Exception {
        return bookDAO.findAll().stream().map(book -> new BookDTO(book.getBook_id(),
                book.getB_name(),
                book.getAuthor(),
                book.getPrice(),
                book.getCount(),
                book.getPublisher(),
                book.getEdition(),
                book.getAvailability())).collect(Collectors.toList());

    }

    public boolean saveBook(BookDTO book) throws Exception {
        return bookDAO.save(new Book(book.getBook_id(),
                book.getB_name(),
                book.getAuthor(),
                book.getPrice(),
                book.getCount(),
                book.getPublisher(),
                book.getEdition(),
                book.getAvailability()));
    }

    public boolean updateBook(BookDTO book) throws Exception {
        return bookDAO.update(new Book(book.getBook_id(),
                book.getB_name(),
                book.getAuthor(),
                book.getPrice(),
                book.getCount(),
                book.getPublisher(),
                book.getEdition(),
                book.getAvailability()));
    }

    public boolean deleteBook(String code) throws Exception {
        return bookDAO.delete(code);
    }

    public String generateBookId() throws Exception {
        return bookDAO.getLastBookId();
    }

    public boolean updateAvailable(int i,String id)throws Exception{
        return bookDAO.updateAvailability(i,id);
    }
}
