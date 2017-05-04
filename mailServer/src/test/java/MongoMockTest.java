import junit.framework.JUnit4TestAdapter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.logging.Level;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by inigo on 3/05/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class MongoMockTest {

    @Mock
    private MongoDB db;
    final static  Logger logger = LoggerFactory.getLogger(MongoMockTest.class);
    static int iteration = 0;

    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(MongoMockTest.class);
    }

    @Before
    public void setUp() throws Exception {
        logger.info("Entering setUp: {}", iteration++);
//        db = Mockito.mock(MongoDB.class);
        db=new MongoDB();
        logger.info("Leaving setUp");
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void sign_up() throws Exception {
        logger.info("Starting testRegisterUserCorrectly() ");
        logger.info("Introduce new user");
//        when( this.db.sign_up("user","user")).thenReturn( true );
        assertEquals( this.db.sign_up("user","user"),true );
        logger.info("User already in the db");
//        verify(this.db.sign_up("user","user"));
        assertEquals(this.db.sign_up("user","user"),false);
    }

    @Test
    public void sign_in() throws Exception {
        assertEquals(db.sign_in("usuario","usuario"), false);
        db.sign_up("usuario","usuario");
        assertEquals(db.sign_in("usuario","usuario"),true);
    }

    @Test
    public void save_emails_AND_getEmails() throws Exception {
        db.sign_up("inigo","inigo");
        int emails = db.getEmails("inigo").size();
        Email m = new Email("inigo","inigo","mensaje test","mensaje test");
        db.save_emails(m);
        ArrayList<Email> emails2 = db.getEmails("inigo");
        assertTrue((emails+1)==emails2.size());
        assertEquals(m.message,emails2.get(emails2.size()-1).message);
    }
    
    @Test
    public void save_email_crash() {
        Email m = new Email("inigo","inigo333","mensaje test","mensaje test");
        try {
            db.save_emails(m);
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(MongoMockTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void getEmails() throws Exception {
        db.sign_up("inigo","inigo");
        int emails = db.getEmails("inigo").size();
        Email m = new Email("inigo","inigo","mensaje test","mensaje test");
        db.save_emails(m);
        int emails2 = db.getEmails("inigo").size();
        assertTrue((emails+1)==emails2);
    }

    @org.junit.Test
    public void delete_message() throws Exception {
        db.sign_up("inigo","inigo");
        int emails = db.getEmails("inigo").size();
        Email m = new Email("inigo2","inigo","mensaje test","mensaje test");
        Email m2 = new Email("inigo3","inigo","mensaje test","mensaje test");
        db.save_emails(m);
        db.save_emails(m2);
        db.delete_message(new Delete(m.target, m.source, m.time));
        int emails2 = db.getEmails("inigo").size();
        assertTrue((emails+1)==emails2);
    }

}