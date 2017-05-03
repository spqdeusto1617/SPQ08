import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import junit.framework.JUnit4TestAdapter;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import org.junit.Before;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        logger.info("User already in the db");
//        verify(this.db.sign_up("user","user"));
        assertEquals(this.db.sign_up("user","user"),false);
    }

    @Test
    public void sign_in() throws Exception {

    }

    @org.junit.Test
    public void save_emails() throws Exception {

    }

    @org.junit.Test
    public void getEmails() throws Exception {

    }

    @org.junit.Test
    public void delete_message() throws Exception {

    }

}