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

    private static Server server;
    private static Email email;

    final static Logger logger = LoggerFactory.getLogger(PerformanceTest.class);
    static int iterationSignUpTest = 0;
    static int iterationSendMailTest = 0;

    @Rule public ContiPerfRule rule = new ContiPerfRule();

    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(PerformanceTest.class);
    }


    @BeforeClass
    public static void setUp() throws RemoteException {
        //logger.info("Entering setUp");
        logger.info("Entering setUp: {}");
        server = new Server();
        server.runServer("127.0.0.1", "1099", "EmailServer");
        logger.info("Leaving setUp");
    }

    @Test
    @PerfTest(invocations = 1000, threads = 10)
    @Required(max = 1000, average = 500)
    public void testSignUp() throws Exception {
        logger.info("Starting SignUp PerformanceTest", iterationSignUpTest++);

        server.signUp(user1Name, passwordUser1);
        user1LoggedIn = server.signIn(user1Name, passwordUser1);
        assertTrue(user1LoggedIn);
        server.signUp(user2Name, passwordUser2);
        user2LoggedIn = server.signIn(user2Name, passwordUser2);
        assertTrue(user2LoggedIn);
        server.signUp(user3Name, passwordUser3);
        user3LoggedIn = server.signIn(user3Name, passwordUser3);
        assertTrue(user3LoggedIn);
        server.signUp(user4Name, passwordUser4);
        user4LoggedIn = server.signIn(user4Name, passwordUser4);
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

        server.sendEmail(email);

        Thread.sleep(200);

        logger.debug("Finishing SendMailPerformaceTest");
    }
}
