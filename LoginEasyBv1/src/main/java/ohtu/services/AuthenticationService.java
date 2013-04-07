package ohtu.services;

import ohtu.domain.User;
import java.util.ArrayList;
import java.util.List;
import ohtu.data_access.FileUserDAO;
import ohtu.data_access.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationService {

    private UserDao userDao;
    
    @Autowired
    public AuthenticationService(UserDao userDao) {
        this.userDao = userDao;
    }
    
//    public AuthenticationService(FileUserDAO userDao) {
//        this.userDao = userDao;
//    }

    public boolean logIn(String username, String password) {
        for (User user : userDao.listAll()) {
            if (user.getUsername().equals(username)
                    && user.getPassword().equals(password)) {
                return true;
            }
        }

        return false;
    }

    public boolean createUser(String username, String password) throws Exception {
        if (userDao.findByName(username) != null) {
            return false;
        }

        if (!invalid(username, password)) {
            return false;
        }

        userDao.add(new User(username, password));

        return true;
    }

    private boolean invalid(String username, String password) {
        // validity check of username and password
        
        if (username.isEmpty() || password.isEmpty()) {
            return false;
        }
        
        
        for (int i = 0; i < username.length(); i++) {
            if (!Character.isLetter(username.charAt(i))) {
                return false;
            }
        }
        
        if (username.length() < 3) {
            return false;
        }
        if (password.length() < 8) {
            return false;
        }
        
        boolean sisaltaa = false;
        
        for (int i = 0; i < password.length(); i++) {
            if (!Character.isLetter(password.charAt(i))) {
                sisaltaa = true;
            }
        }
        return sisaltaa;
    }
}
