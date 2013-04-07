package ohtu.domain;

import org.springframework.stereotype.Component;

@Component
public class User {

    private String username;
    private String password;
    
    public User() {
        username = "pekka";
        password = "akkep";
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
}
