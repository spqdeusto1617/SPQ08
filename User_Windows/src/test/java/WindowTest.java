///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//
//import static org.junit.Assert.*;
//
//import java.rmi.RemoteException;
//import java.util.ArrayList;
//import java.util.logging.Level;
//import junit.framework.JUnit4TestAdapter;
//import org.junit.Before;
//import org.junit.Test;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
///**
// *
// * @author gotzon gerrikabeitia
// */
//public class WindowTest {
//
//    private Controller remote;
//    Logger logger = LoggerFactory.getLogger(WindowTest.class);
//    private ArrayList<Email> emails ;
//    public static junit.framework.Test suite() {
//        return new JUnit4TestAdapter(WindowTest.class);
//    }
//    @Before
//    public void setUp(){
//         try {
//            remote = new Controller("127.0.0.1", "1099", "EmailServer");
//        } catch (RemoteException ex) {
//            java.util.logging.Logger.getLogger(WindowTest.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//
//    @Test
//    public void registerNewUserTest() {
//        try {
//            logger.info("Test 1 - Register new user");
//            assertEquals( remote.signUp("victor" + Math.random(), "victor" + Math.random()),true );
//        } catch (Exception re) {
//            logger.error(" RemoteException: ");
//            logger.trace(re.getMessage());
//        }
//    }
//
//    @Test
//    public void loginTest() {
//        try {
//            logger.info("Test 2 - Register existing user.");
//           assertEquals( remote.signIn("gotzon", "gotzon"),true);
//
//        } catch (Exception re) {
//            logger.error(" RemoteException: " + re.getMessage());
//            logger.trace(re.getMessage());
//        }
//
//    }
//
//    @Test
//    public void emailRetrieveTest() {
//        logger.info("Test 3 - email retrieve test ");
//        boolean t = true;
//        try {
//            emails = remote.getEmails("gotzon");
//        } catch (RemoteException e) {
//            logger.error(" # RemoteException: " + e.getMessage());
//            logger.trace(e.getMessage());
//        }
//        if (emails.isEmpty()) {
//            t = false;
//        }
//        assertTrue(t);
//    }
//
//    @Test
//    public void deleteEmailTest() {
//        logger.info("Test 4 - email delete test ");
//        try {
//            emails = remote.getEmails("gotzon");
//        } catch (RemoteException ex) {
//            java.util.logging.Logger.getLogger(WindowTest.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        Delete delete = new Delete(emails.get(emails.size() - 1).source, emails.get(emails.size() - 1).target, emails.get(emails.size() - 1).time);
//        logger.info(delete.toString());
//        try {
//            assertEquals( remote.deleteEmail(delete),true);
//
//        } catch (RemoteException e) {
//            logger.error(" # RemoteException: " + e.getMessage());
//            logger.trace(e.getMessage());
//
//        }
//
//    }
//
//    @Test
//    public void sendEmailTest() {
//        logger.info("Test 5 - email sending test ");
//        Email email = new Email("javi", "julen55555", "Test", "Email Test");
//        try {
//            assertEquals(  remote.sendEmail(email),true);
//        } catch (RemoteException e) {
//            logger.error(" # RemoteException: " + e.getMessage());
//            logger.trace(e.getMessage());
//        }
//
//    }
//
//    @Test
//    public void helloTest() {
//        logger.info("Test 6 - hello test ");
//        boolean t = false;
//        String a=null;
//        try {
//            a = remote.helloTo("gotzon");
//            logger.info(a);
//        } catch (RemoteException e) {
//            logger.error(" # RemoteException: " + e.getMessage());
//            logger.trace(e.getMessage());
//        }
//        if(a!=null) {
//            t = true;
//        }
//        assertTrue(t);
//    }
//    @Test
//    public void deleteClassTest() {
//        logger.info("Test 6 - hello test ");
//        boolean t = false;
//        Delete delete= new Delete("gotzon", "inigo", Long.MIN_VALUE);
//        Delete delete2= new Delete("gotzon44", "inig3o", Long.MIN_VALUE);
//        delete2.setDate(delete.getDate());
//        delete2.setSource(delete.getSource());
//        delete2.setUser(delete.getUser());
//        if (delete.toString().equals(delete2.toString())) {
//            t=true;
//        }
//        assertTrue(t);
//    }
//    @Test
//    public void emailClassTest() {
//        logger.info("Test 6 - hello test ");
//        boolean t = true;
//        Email email=new Email("gotzon", "inigo", "test", "test", Long.MIN_VALUE);
//
//        assertTrue(t);
//    }
//}
