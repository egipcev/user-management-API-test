package users;

import java.util.HashMap;
import java.util.Map;

public class User {

    private String username;
    private String password;
    private String id;
    private String email;
    private Map<String, String> profile;

    public User(String username, String password, String id, String email, Map<String, String> profile) {
        this.username = username;
        this.password = password;
        this.id = id;
        this.email = email;
        this.profile = new HashMap<>();
        this.profile.putAll(profile);

    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;

    }

    public Map<String, String> getProfile() {
        return profile;
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
