package lk.ijse.librarymanagementsystem.dao.custom.impl;

import lk.ijse.librarymanagementsystem.dao.CrudUtil;
import lk.ijse.librarymanagementsystem.dao.custom.Book_ReturnsDAO;
import lk.ijse.librarymanagementsystem.entity.Book_Returns;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Book_ReturnsDAOImpl implements Book_ReturnsDAO {

    public boolean save(Book_Returns book_returns) throws Exception{
        return CrudUtil.execute("INSERT INTO book_returns VALUES (?,?,?)", book_returns.getReturn_id(), book_returns.getReturned_date(), book_returns.getBr_id());
    }

    public boolean update(Book_Returns book_returns)throws Exception{
        return CrudUtil.execute("UPDATE book_returns SET returned_date=?, br_id=? WHERE return_id=?", book_returns.getReturned_date(), book_returns.getBr_id(), book_returns.getReturn_id());
    }

    public boolean delete(String id)throws Exception{
        return CrudUtil.execute("DELETE FROM book_returns WHERE return_id=?", id);
    }

    public Book_Returns find(String id) throws Exception{

        ResultSet rst = CrudUtil.execute("SELECT * FROM book_returns WHERE return_id=?", id);

        if (rst.next()) {
            return new Book_Returns(rst.getString(1),
                    rst.getDate(2).toLocalDate(),
                    rst.getString(3));
        }
        return null;
    }

    public List<Book_Returns> findAll() throws Exception{
        ResultSet rst = CrudUtil.execute("  SELECT * FROM book_returns");
        List<Book_Returns> alBookReturns = new ArrayList<>();
        while (rst.next()) {
            alBookReturns.add(new Book_Returns(rst.getString(1),
                    rst.getDate(2).toLocalDate(),
                    rst.getString(3)));
        }
        return alBookReturns;
    }

    @Override
    public String getLastReturnId() throws Exception {


        ResultSet rst = CrudUtil.execute("SELECT return_id from book_returns order by 1 desc limit 1");
        if (rst.next()){

            return rst.getString(1);
        }else{
            return "0";
        }
    }

}
