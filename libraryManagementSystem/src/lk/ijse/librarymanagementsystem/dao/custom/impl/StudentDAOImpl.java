package lk.ijse.librarymanagementsystem.dao.custom.impl;

import lk.ijse.librarymanagementsystem.dao.CrudUtil;
import lk.ijse.librarymanagementsystem.dao.custom.StudentDAO;
import lk.ijse.librarymanagementsystem.entity.Student;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {

    public boolean save(Student student) throws Exception{
        return CrudUtil.execute("INSERT INTO student VALUES (?,?,?,?,?,?)", student.getStudent_id(),
                student.getStudent_name(),
                student.getAddress(),
                student.getGender(),
                student.getDepartment(),
                student.getContact_num());
    }

    public boolean update(Student student)throws Exception{
        return CrudUtil.execute("UPDATE student SET student_name=?, address=?, gender=?, department=?, contact_num=? WHERE student_id=?", student.getStudent_name(),
                student.getAddress(),
                student.getGender(),
                student.getDepartment(),
                student.getContact_num(),
                student.getStudent_id());
    }

    public boolean delete(String id)throws Exception{
        return CrudUtil.execute("DELETE FROM student WHERE student_id=?", id);
    }

    public Student find(String id) throws Exception{

        ResultSet rst = CrudUtil.execute("SELECT * FROM student WHERE student_id=?", id);

        if (rst.next()) {
            return new Student(rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6));
        }
        return null;
    }

    public List<Student> findAll() throws Exception{
        ResultSet rst = CrudUtil.execute("  SELECT * FROM student");
        List<Student> alStudents = new ArrayList<>();
        while (rst.next()) {
            alStudents.add(new Student(rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6)
            ));
        }
        return alStudents;
    }
}
