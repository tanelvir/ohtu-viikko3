/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 *
 * @author taneli
 */
public class Main {
    
        public static void main(String[] args) throws Exception {
//        UserDao dao = new InMemoryUserDao();
//        IO io = new ConsoleIO();
//        AuthenticationService auth = new AuthenticationService(dao);
//        new App(io, auth).run();


        // testejä debugatessa saattaa olla hyödyllistä testata ohjelman ajamista
        // samoin kuin testi tekee, eli injektoimalla käyttäjän syötteen StubIO:n avulla
        //
        ApplicationContext ctx = new FileSystemXmlApplicationContext("src/main/resources/spring-context.xml");
        App application = ctx.getBean(App.class);
        application.run();
    }
    
}
