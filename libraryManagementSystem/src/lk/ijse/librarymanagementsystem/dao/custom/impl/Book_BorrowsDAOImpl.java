package lk.ijse.librarymanagementsystem.dao.custom.impl;

import lk.ijse.librarymanagementsystem.dao.CrudUtil;
import lk.ijse.librarymanagementsystem.dao.custom.Book_BorrowsDAO;
import lk.ijse.librarymanagementsystem.entity.Book_Borrows;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Book_BorrowsDAOImpl implements Book_BorrowsDAO {

    public boolean save(Book_Borrows book_borrows) throws Exception{
        return CrudUtil.execute("INSERT INTO book_borrows VALUES (?,?,?,?)", book_borrows.getBorrow_id(),
                book_borrows.getB_date(), book_borrows.getS_id(),book_borrows.getB_id());
    }

    public boolean update(Book_Borrows book_borrows)throws Exception{
        return CrudUtil.execute("UPDATE book_borrows SET b_date=?, s_id=?, b_id=? WHERE borrow_id=?", book_borrows.getB_date(),
                book_borrows.getS_id(), book_borrows.getB_id(), book_borrows.getBorrow_id());
    }

    public boolean delete(String id)throws Exception{
        return CrudUtil.execute("DELETE FROM book_borrows WHERE borrow_id=?", id);
    }

    public Book_Borrows find(String id) throws Exception{

        ResultSet rst = CrudUtil.execute("SELECT * FROM book_borrows WHERE borrow_id=?", id);

        if (rst.next()) {
            return new Book_Borrows(rst.getString(1),
                    rst.getDate(2).toLocalDate(),
                    rst.getString(3),
                    rst.getString(4));
        }
        return null;
    }

    public List<Book_Borrows> findAll() throws Exception{
        ResultSet rst = CrudUtil.execute("  SELECT * FROM book_borrows");
        List<Book_Borrows> alBookBorrows = new ArrayList<>();
        while (rst.next()) {
            alBookBorrows.add(new Book_Borrows(rst.getString(1),
                    rst.getDate(2).toLocalDate(),
                    rst.getString(3),
                    rst.getString(4)));
        }
        return alBookBorrows;
    }

    @Override
    public String getLastBorrowId() throws Exception {
        System.out.println("Called");

        ResultSet rst = CrudUtil.execute("SELECT borrow_id from book_borrows order by 1 desc limit 1");
        if (rst.next()){
            System.out.println("Not");
            return rst.getString(1);
        }else{
            return "0";
        }
    }

}
