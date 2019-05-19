package lk.ijse.librarymanagementsystem.business.custom;

import lk.ijse.librarymanagementsystem.business.SuperBO;
import lk.ijse.librarymanagementsystem.dto.StudentDTO;

import java.util.List;

public interface StudentBO extends SuperBO {

    public List<StudentDTO> getAllStudents() throws Exception;

    public boolean saveStudent(StudentDTO dto)  throws Exception;

    public boolean updateStudent(StudentDTO dto)throws Exception;

    public boolean removeStudent(String id)throws Exception;

    public StudentDTO getStudentById(String id)throws Exception;

}
