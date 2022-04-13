package users;

public class User {

    private String username;
    private String password;
    private String id;
    private String email;

    public User(String username, String password, String id, String email) {
        this.username = username;
        this.password = password;
        this.id = id;
        this.email = email;

    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;

    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
}
