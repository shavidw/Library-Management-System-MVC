package lk.ijse.librarymanagementsystem.entity;

public class Password extends SuperEntity{
    private String password;
    private String username;

    public Password() {
    }

    public Password(String password, String username) {
        this.password = password;
        this.username = username;
    }

    @Override
    public String toString() {
        return "Password{" +
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
