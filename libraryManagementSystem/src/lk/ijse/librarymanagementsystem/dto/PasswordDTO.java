package lk.ijse.librarymanagementsystem.dto;

public class PasswordDTO {

    private String password;
    private String username;

    public PasswordDTO() {
    }

    public PasswordDTO(String password, String username) {
        this.password = password;
        this.username = username;
    }

    @Override
    public String toString() {
        return "PasswordDTO{" +
                "password='" + password + '\'' +
                ", username='" + username + '\'' +
                '}';
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
