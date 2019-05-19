package lk.ijse.librarymanagementsystem.business.custom.impl;

import lk.ijse.librarymanagementsystem.business.custom.StudentBO;
import lk.ijse.librarymanagementsystem.dao.DAOFactory;
import lk.ijse.librarymanagementsystem.dao.DAOTypes;
import lk.ijse.librarymanagementsystem.dao.custom.StudentDAO;
import lk.ijse.librarymanagementsystem.dto.StudentDTO;
import lk.ijse.librarymanagementsystem.entity.Student;

import java.util.List;
import java.util.stream.Collectors;

public class StudentBOImpl implements StudentBO {

    private StudentDAO studentDAO = DAOFactory.getInstance().getDAO(DAOTypes.STUDENT);

//    public CustomerBOImpl(){
//        String dao = DAOFactory.getInstance().<String>getDAO(DAOTypes.CUSTOMER);
//    }

    @Override
    public StudentDTO getStudentById(String id) throws Exception {
        Student student = studentDAO.find(id);
        return new StudentDTO(student.getStudent_id(),
                student.getStudent_name(),
                student.getAddress(),
                student.getGender(),
                student.getDepartment(),
                student.getContact_num());
    }

    public List<StudentDTO> getAllStudents() throws Exception {
        return studentDAO.findAll().stream().map(student -> new StudentDTO(student.getStudent_id(),
                student.getStudent_name(),
                student.getAddress(),
                student.getGender(),
                student.getDepartment(),
                student.getContact_num())).collect(Collectors.toList());

    }

    public boolean saveStudent(StudentDTO dto) throws Exception {
        return studentDAO.save(new Student(dto.getStudent_id(),
                dto.getStudent_name(),
                dto.getAddress(),
                dto.getGender(),
                dto.getDepartment(),
                dto.getContact_num()));
    }

    public boolean updateStudent(StudentDTO dto) throws Exception {
        return studentDAO.update(new Student(dto.getStudent_id(),
                dto.getStudent_name(),
                dto.getAddress(),
                dto.getGender(),
                dto.getDepartment(),
                dto.getContact_num()));
    }

    public boolean removeStudent(String id) throws Exception {
        return studentDAO.delete(id);
    }


}
