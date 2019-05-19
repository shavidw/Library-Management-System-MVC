package lk.ijse.librarymanagementsystem.dao.custom.impl;

import lk.ijse.librarymanagementsystem.dao.CrudUtil;
import lk.ijse.librarymanagementsystem.dao.custom.BookDAO;
import lk.ijse.librarymanagementsystem.dao.custom.Book_ReturnsDAO;
import lk.ijse.librarymanagementsystem.entity.Book;
import lk.ijse.librarymanagementsystem.entity.Book_Returns;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BookDAOImpl implements BookDAO {

    public boolean save(Book book) throws Exception{
        return CrudUtil.execute("INSERT INTO book VALUES (?,?,?,?,?,?,?,?)", book.getBook_id(),
                book.getB_name(),
                book.getAuthor(),
                book.getPrice(),
                book.getCount(),
                book.getPublisher(),
                book.getEdition(),
                book.getAvailability()
        );
    }

    public boolean update(Book book)throws Exception{
        return CrudUtil.execute("UPDATE book SET b_name=?, author=?, price=?, count=?, publisher=?, edition=?, availability=? WHERE book_id=?",
                book.getB_name(),
                book.getAuthor(),
                book.getPrice(),
                book.getCount(),
                book.getPublisher(),
                book.getEdition(),
                book.getAvailability(),
                book.getBook_id()
        );
    }

    public boolean delete(String id)throws Exception{
        return CrudUtil.execute("DELETE FROM book WHERE book_id=?", id);
    }

    public boolean updateAvailability(int i,String id)throws Exception{
        return CrudUtil.execute("UPDATE book SET availability=availability+? where book_id=?",i,id);
    }

    public Book find(String id) throws Exception{

        ResultSet rst = CrudUtil.execute("SELECT * FROM book WHERE book_id=?", id);

        if (rst.next()) {
            return new Book(rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDouble(4),
                    rst.getInt(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getInt(8)
            );
        }
        return null;
    }

    public List<Book> findAll() throws Exception{
        ResultSet rst = CrudUtil.execute("  SELECT * FROM book");
        List<Book> alBooks = new ArrayList<>();
        while (rst.next()) {
            alBooks.add(new Book(rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDouble(4),
                    rst.getInt(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getInt(8)
            ));
        }
        return alBooks;
    }
    @Override
    public String getLastBookId() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT book_id from book order by 1 desc limit 1");
        if (rst.next()){
            return rst.getString(1);
        }else{
            return null;
        }
    }
}
