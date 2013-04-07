/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.data_access;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import ohtu.domain.User;
import org.springframework.stereotype.Component;

/**
 *
 * @author taneli
 */

public class FileUserDAO implements UserDao {
    
    File filu;
    
    public FileUserDAO() {
        filu = new File("kayttajat.txt");
    }
    
    public FileUserDAO(String nimi) throws Exception {
        filu = new File(nimi);
    }

    @Override
    public List<User> listAll() {
        Scanner nimet = null;
        Scanner passut = null;
        List<User> kayttajat = new ArrayList<User>();
        try {
            nimet = new Scanner(filu);
            passut = new Scanner(new File("salasanat.txt"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        while (nimet.hasNextLine()) {
            String nimi = nimet.nextLine();
            String passu = passut.nextLine();
            kayttajat.add(new User(nimi,passu));
        }
        nimet.close();
        return kayttajat;
    }

    @Override
    public User findByName(String name) {
        Scanner nimet = null;
        Scanner passut = null;
        try {
            nimet = new Scanner(filu);
            passut = new Scanner(new File("salasanat.txt"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        while (nimet.hasNextLine()) {
            String nimi = nimet.nextLine();
            String passu = passut.nextLine();
            if (nimi.equals(name)) {
                nimet.close();
                return new User(name, passu);
            }
        }
        nimet.close();
        return null;
    }

    @Override
    public void add(User user) throws Exception {
        FileWriter nimet = new FileWriter(filu);
        FileWriter passut = new FileWriter(new File("salasanat.txt"));
        String nimi = user.getUsername();
        String passu = user.getPassword();
        nimet.write(nimi + "\n");
        passut.write(passu + "\n");
        nimet.close();
        passut.close();
    }
    
}
