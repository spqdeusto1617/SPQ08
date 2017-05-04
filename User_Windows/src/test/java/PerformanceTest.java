/**
 * Created by vitiok on 5/3/17.
 */

import static org.junit.Assert.assertTrue;
import junit.framework.JUnit4TestAdapter;
import org.junit.BeforeClass;
import org.junit.Test;

import org.junit.Rule;
import org.databene.contiperf.Required;
import org.databene.contiperf.PerfTest;
import org.databene.contiperf.junit.ContiPerfRule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.rmi.RemoteException;
import java.util.logging.Level;


@PerfTest(invocations = 100, threads = 20)
@Required(max = 1200, average = 250)
public class PerformanceTest {
    private boolean user1LoggedIn = false;
    private boolean user2LoggedIn = false;
    private boolean user3LoggedIn = false;
    private boolean user4LoggedIn = false;

    private String user1Name = "John";
    private String passwordUser1 = "123456";

    private String user2Name = "Mark";
    private String passwordUser2 = "123456";

    private String user3Name = "Jane";
    private String passwordUser3 = "123456";

    private String user4Name = "Elizabeth";
    private String passwordUser4 = "123456";

    private static Controller remote;

    private static Email email;

    final static Logger logger = LoggerFactory.getLogger(PerformanceTest.class);
    static int iterationSignUpTest = 0;
    static int iterationSendMailTest = 0;
    static int iterationGetMailTest = 0;

    @Rule public ContiPerfRule rule = new ContiPerfRule();

    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(PerformanceTest.class);
    }


    @BeforeClass
    public static void setUp() throws RemoteException {
        logger.info("Entering setUp: {}");
        try {
            remote = new Controller("127.0.0.1", "1099", "EmailServer");
        } catch (RemoteException ex) {
            java.util.logging.Logger.getLogger(WindowTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        logger.info("Leaving setUp");
    }

    @Test
    @PerfTest(invocations = 1000, threads = 10)
    @Required(max = 1000, average = 500)
    public void testSignUp() throws Exception {
        logger.info("Starting SignUp PerformanceTest", iterationSignUpTest++);

        remote.signUp(user1Name, passwordUser1);
        user1LoggedIn = remote.signIn(user1Name, passwordUser1);
        assertTrue(user1LoggedIn);
        remote.signUp(user2Name, passwordUser2);
        user2LoggedIn = remote.signIn(user2Name, passwordUser2);
        assertTrue(user2LoggedIn);
        remote.signUp(user3Name, passwordUser3);
        user3LoggedIn = remote.signIn(user3Name, passwordUser3);
        assertTrue(user3LoggedIn);
        remote.signUp(user4Name, passwordUser4);
        user4LoggedIn = remote.signIn(user4Name, passwordUser4);
        assertTrue(user4LoggedIn);

        Thread.sleep(200);

        logger.debug("Finishing SignUp PerformanceTest");
    }

    @Test
    @PerfTest(invocations = 1000, threads = 10)
    @Required(max = 800, average = 400)
    public void testSendEmails() throws Exception {
        logger.info("Starting SendMailPerformaceTest", iterationSendMailTest++);

        email = new Email(
                user1Name,
                user2Name,
                "Test " + iterationSendMailTest,
                "Test message " + iterationSendMailTest,
                System.currentTimeMillis()
        );

        remote.sendEmail(email);

        Thread.sleep(200);

        logger.debug("Finishing SendMailPerformaceTest");
    }

    @Test
    @PerfTest(invocations = 1000, threads = 10)
    @Required(max = 1000, average = 600)
    public void testGetEmails() throws Exception {
        logger.info("Starting testGetEmails", iterationGetMailTest++);

        remote.getEmails(user1Name);

        Thread.sleep(200);

        logger.debug("Finishing testGetEmails");
    }
}
