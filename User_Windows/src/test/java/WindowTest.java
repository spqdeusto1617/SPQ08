/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import static org.junit.Assert.*;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;
import junit.framework.JUnit4TestAdapter;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author gotzon gerrikabeitia
 */
public class WindowTest {

    private Controller remote;
    final static Logger logger = LoggerFactory.getLogger(WindowTest.class);
    ArrayList<Email> emails = null;
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(WindowTest.class);
    }

    @Before
    public void setUp() {
        try {
            remote = new Controller("127.0.0.1", "1099", "EmailServer");
        } catch (RemoteException ex) {
            java.util.logging.Logger.getLogger(WindowTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    @Test
    public void registerNewUserTest() {
        boolean t = false;
        try {
            logger.info("Test 1 - Register new user");
            t = remote.signUp("victor" + Math.random(), "victor" + Math.random());
        } catch (Exception re) {
            logger.error(" RemoteException: ");
            logger.trace(re.getMessage());
        }
        assertTrue(t);

    }

    @Test
    public void loginTest() {
        boolean t = false;
        try {
            logger.info("Test 2 - Register existing user.");
            t = remote.signIn("gotzon", "gotzon");
        } catch (Exception re) {
            logger.error(" RemoteException: " + re.getMessage());
            logger.trace(re.getMessage());
        }
        /*
		 * Very simple test
         */
        assertTrue(t);

    }

    @Test
    public void emailRetrieveTest() {
        logger.info("Test 3 - email retrieve test ");
        
        boolean t = true;
        try {
            emails = remote.getEmails("gotzon");

        } catch (RemoteException e) {
            logger.error(" # RemoteException: " + e.getMessage());
            logger.trace(e.getMessage());
        }
        if (emails.isEmpty()) {
            t = false;
        }
        assertTrue(t);

    }

    @Test
    public void deleteEmailTest() {
        logger.info("Test 4 - email delete test ");
        Delete delete = new Delete(emails.get(emails.size() - 1).source, emails.get(emails.size() - 1).target, emails.get(emails.size() - 1).time);

        boolean t = false;
        try {
            t = remote.deleteEmail(delete);

        } catch (RemoteException e) {
            logger.error(" # RemoteException: " + e.getMessage());
            logger.trace(e.getMessage());

        }
        assertTrue(t);

    }

    @Test
    public void sendEmailTest() {
        logger.info("Test 5 - email sending test ");
        Email email = new Email("inigo", "gotzon", "Test", "Email Test");
        boolean t = false;
        try {
            t = remote.sendEmail(email);
        } catch (RemoteException e) {
            logger.error(" # RemoteException: " + e.getMessage());
            logger.trace(e.getMessage());
        }
        assertTrue(t);

    }

    @Test
    public void helloTest() {
        logger.info("Test 6 - hello test ");
        boolean t = false;
        String a = null;
        try {
            a = remote.helloTo("gotzon");

        } catch (RemoteException e) {
            logger.error(" # RemoteException: " + e.getMessage());
            logger.trace(e.getMessage());
            e.printStackTrace();
        }
        if (!a.equals("")) {
            t = true;
        }
        assertTrue(t);

    }
    @Test
    public void deleteClassTest() {
        logger.info("Test 6 - hello test ");
        Delete delete= new Delete("gotzon", "inigo", Long.MIN_VALUE);
        Long date=delete.getDate();
        String source=delete.getSource();
        String user=delete.getUser();
        Delete delete2 = null;
        delete2.setDate(date);
        delete2.setSource(source);
        delete2.setUser(user);
        assertSame(user, delete, delete2);
    }
    @Test
    public void emailClassTest() {
        logger.info("Test 6 - hello test ");
        boolean t = false;
        Email email=new Email("gotzon", "inigo", "test", "test", Long.MIN_VALUE);
        if (email!= null) {
            t = true;
        }
        assertTrue(t);
    }
}
