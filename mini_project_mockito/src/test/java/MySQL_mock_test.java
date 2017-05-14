import db.DB;
import db.MySQL;
import entities.User;
import junit.framework.JUnit4TestAdapter;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by inigo on 4/05/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class MySQL_mock_test {

    DB db;
    final static Logger logger = LoggerFactory.getLogger(MySQL_mock_test.class);

    @Mock
    MySQL dao;

    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(MySQL_mock_test.class);
    }

    @Rule
    public MockitoRule mrule = MockitoJUnit.rule().silent();

    @Before
    public void setUp() throws Exception {
        logger.info("Entering setUp:");
        db = new DB(dao);
        logger.info("Leaving setUp");
    }

    @Test
    public void testLogin_AND_getUser(){
        logger.info("test login and get user");
        when( dao.getUser("fav12390") ).thenReturn( null );
        User u = new User("fav12390", "fav",40,40);
        db.storeUser(u);
        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass( User.class );
        verify (dao).storeUser(userCaptor.capture());
        User u1 = userCaptor.getValue();
        logger.info("Registering mock new user: " + u1.getUsername());
        assertEquals( "fav12390", u1.getUsername());
    }
}
