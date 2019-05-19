package lk.ijse.librarymanagementsystem.util;

public class StudentTM {

    private String studentId;
    private String studentName;
    private String address;
    private String gender;
    private String department;
    private String contactNum;

    public StudentTM(String studentId, String studentName, String address,String gender, String department, String contactNum) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.address = address;
        this.gender=gender;
        this.department = department;
        this.contactNum = contactNum;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getContactNum() {
        return contactNum;
    }

    public void setContactNum(String contactNum) {
        this.contactNum = contactNum;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
