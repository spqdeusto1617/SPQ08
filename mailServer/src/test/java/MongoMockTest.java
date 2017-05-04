<<<<<<< HEAD
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
=======
>>>>>>> afaef5ee255a82b9a767942b6d4e7b1daf040700
import junit.framework.JUnit4TestAdapter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
<<<<<<< HEAD
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import org.junit.Before;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;
=======
import org.mockito.runners.MockitoJUnitRunner;
>>>>>>> afaef5ee255a82b9a767942b6d4e7b1daf040700
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
<<<<<<< HEAD
import static org.mockito.Mockito.when;
=======
import java.util.logging.Level;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
>>>>>>> afaef5ee255a82b9a767942b6d4e7b1daf040700

/**
 * Created by inigo on 3/05/17.
 */

//@RunWith(MockitoJUnitRunner.class)
@RunWith(PowerMockRunner.class)
public class MongoMockTest {

    private MongoDB db;
    final static  Logger logger = LoggerFactory.getLogger(MongoMockTest.class);
    static int iteration = 0;

    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(MongoMockTest.class);
    }

//    @Mock
//    private MongoClient mockClient;
//    @Mock
//    private MongoCollection mockCollection;
//    @Mock
//    private MongoDatabase mockDB;
//
//    @InjectMocks
//    private MongoDB wrapper;


    @Before
    public void setUp_mockito() throws Exception {
        logger.info("Entering setUp mockito: {}", iteration++);
//        MockitoAnnotations.initMocks(this);
//        when(mockClient.getDatabase(db.getDatabasePassword())).thenReturn(mockDB);
//        when(mockDB.getCollection(db.getUserCollection())).thenReturn(mockCollection);
        Mongo mongo = PowerMockito.mock(Mongo.class);
        DB db2 = PowerMockito.mock(DB.class);
        DBCollection dbCollection = PowerMockito.mock(DBCollection.class);
        logger.info("Leaving setUp");
    }

    public void sign_in_using_mockito() throws Exception{

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
        Email m = new Email("inigo","inigo55","mensaje test","mensaje test");
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