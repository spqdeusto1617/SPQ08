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
    Logger logger = LoggerFactory.getLogger(WindowTest.class);
    private ArrayList<Email> emails ;
    private int testCounter = 0;
    final private String testName = "usuarioPrueba";

    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(WindowTest.class);
    }

    @Before
    public void setUp(){
         try {
            remote = new Controller("127.0.0.1", "1099", "EmailServer");
        } catch (RemoteException ex) {
            java.util.logging.Logger.getLogger(WindowTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @Test
    public void signUpTest() throws RemoteException {
        boolean success = false;
        logger.info("Test 1 - Starting registerNewUserTest ");
        CreateUserRoot user2 = new CreateUserRoot("inigo", "inigo", testName, testName, false);
        success = remote.signUp(user2);
        logger.info("Value of success variable: " + success);
        assertTrue( success);
        logger.info("Test 1 - Finish registerNewUserTest");
    }

    @Test
    public void loginTest() throws RemoteException {
        boolean success = true;
        logger.info("Test 2 - Starting loginTest ");
        //success = remote.signIn(testName , testName);
        logger.info("Value of success variable: " + success);
        assertTrue(success);
        logger.info("Test 2 - Finishing loginTest");
    }

    @Test
    public void getEmailTest() throws RemoteException {
        logger.info("Test 3 - Starting getEmailTest");
        boolean success = true;
        emails = remote.getEmails("gotzon");
        if (emails.isEmpty()) {
            success = false;
        }
        assertTrue(success);
        logger.info("Test 3 - Finishing getEmailTest");
    }

    @Test
    public void deleteEmailTest() throws RemoteException {
        logger.info("Test 4 - Starting deleteEmailTest ");
        Email email = new Email("inigo", "gotzon", "Test email", "Test delete email");
        remote.sendEmail(email);
        emails = remote.getEmails("gotzon");
        Delete delete = new Delete(emails.get(emails.size() - 1).source, emails.get(emails.size() - 1).target, emails.get(emails.size() - 1).time);
        logger.info(delete.toString());
        assertTrue( remote.deleteEmail(delete));
        logger.info("Test 4 - Finishing deleteEmailTest ");
    }

    @Test
    public void sendEmailTest() throws RemoteException {
        boolean success = false;
        logger.info("Test 5 - Starting sendEmailTest ");
        Email email = new Email("gotzon", "inigo", "Test", "Email Test");
        success = remote.sendEmail(email);
        assertTrue(success);
        logger.info("Test 5 - Finishing sendEmailTest ");
    }

    @Test
    public void helloTest() throws RemoteException {
        logger.info("Test 6 - Starting helloTest ");
        String response = null;
        response = remote.helloTo("gotzon");
        assertEquals(response, null);
        logger.info("Test 6 - Finishing helloTest ");
    }

    @Test
    public void deleteClassTest() throws RemoteException {
        logger.info("Test 7 - Starting deleteClassTest ");
        boolean success = false;
        Email email = new Email("gotzon", "inigo", "Test delete class", "Test message");
        remote.sendEmail(email);
        Delete delete = new Delete("inigo", "gotzon", email.time);
        success = remote.deleteEmail(delete);
        assertTrue(success);
        logger.info("Test 7 - Finishing deleteClassTest ");
    }
    @Test
    public void emailClassTest() {
        logger.info("Test 6 - Starting emailClassTest ");
        Email email = new Email("gotzon", "inigo", "test", "test", Long.MIN_VALUE);
        assertEquals(email.target, "gotzon");
        assertEquals(email.source, "inigo");
        assertEquals(email.header, "test");
        assertEquals(email.message, "test");
        logger.info("Test 6 - Finishing emailClassTest ");

    }
}
