package lk.ijse.librarymanagementsystem.entity;

public class Student extends SuperEntity{
    private String student_id;
    private String student_name;
    private String address;
    private String gender;
    private String department;
    private String contact_num;

    public Student(String student_id, String student_name, String address, String gender, String department, String contact_num) {
        this.student_id = student_id;
        this.student_name = student_name;
        this.address = address;
        this.gender = gender;
        this.department = department;
        this.contact_num = contact_num;
    }

    @Override
    public String toString() {
        return "Student{" +
                "student_id='" + student_id + '\'' +
                ", student_name='" + student_name + '\'' +
                ", address='" + address + '\'' +
                ", gender='" + gender + '\'' +
                ", department='" + department + '\'' +
                ", contact_num='" + contact_num + '\'' +
                '}';
    }

    public Student() {
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getContact_num() {
        return contact_num;
    }

    public void setContact_num(String contact_num) {
        this.contact_num = contact_num;
    }
}
