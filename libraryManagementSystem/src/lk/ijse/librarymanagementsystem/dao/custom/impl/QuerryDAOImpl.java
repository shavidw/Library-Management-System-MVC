package lk.ijse.librarymanagementsystem.dao.custom.impl;

import lk.ijse.librarymanagementsystem.dao.CrudUtil;
import lk.ijse.librarymanagementsystem.dao.custom.QuerryDAO;
import lk.ijse.librarymanagementsystem.entity.CustomEntity;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class QuerryDAOImpl implements QuerryDAO {

    @Override
    public List<CustomEntity> getBorrowDetail(String id) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT b_id,s_id,borrow_id,b_date FROM book_borrows WHERE borrow_id NOT IN (SELECT br_id FROM book_returns) having s_id=?",id);
        List<CustomEntity> al = new ArrayList<>();
        while(rst.next()){
            String b_id = rst.getString(1);
            String s_id = rst.getString(2);
            String borrow_id=rst.getString(3);
            Date b_date=rst.getDate(4);

            al.add(new CustomEntity(b_id,s_id,borrow_id,((java.sql.Date) b_date).toLocalDate()));
        }
        return al;
    }
    @Override
    public List<CustomEntity> getBorrowedBooks() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT distinct s_id,b_id,borrow_id FROM book_borrows WHERE borrow_id NOT IN (SELECT br_id FROM book_returns)");
        List<CustomEntity> al = new ArrayList<>();
        while (rst.next()) {
            String b_id = rst.getString(2);
            String s_id = rst.getString(1);
            String borrow_id = rst.getString(3);

            al.add(new CustomEntity(s_id, b_id, borrow_id));
        }
        return al;
    }

        @Override
        public List<CustomEntity> getBorrowedStudents() throws Exception{
            ResultSet rst = CrudUtil.execute("SELECT distinct s_id FROM book_borrows WHERE borrow_id NOT IN (SELECT br_id FROM book_returns)");
            List<CustomEntity> al = new ArrayList<>();
            while(rst.next()){

                al.add(new CustomEntity(rst.getString(1)));
            }
            return al;

    }




}
