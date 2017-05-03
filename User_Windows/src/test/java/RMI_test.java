
import junit.framework.JUnit4TestAdapter;
import org.junit.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.logging.Level;
import static org.junit.Assert.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;




/**
 * RMI Unit test for Simple Client / Server RMI Testing.
 * Testing the only the Remoteness
 */
//@Ignore

public class RMI_test {
    // Properties are hard-coded because we want the test to be executed without external interaction

    private static String cwd = RMI_test.class.getProtectionDomain().getCodeSource().getLocation().getFile();
    private static Thread rmiRegistryThread = null;
    private static Thread rmiServerThread = null;
  
    final static Logger logger = LoggerFactory.getLogger(RMI_test.class);
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(RMI_test.class);
    }
    @Mock
    private RMIInterface remote;
    @Rule public MockitoRule mockitoRule = MockitoJUnit.rule(); 


    @BeforeClass static public void setUp() {
        // Launch the RMI registry
        class RMIRegistryRunnable implements Runnable {

            public void run() {
                try {
                    java.rmi.registry.LocateRegistry.createRegistry(1099);
                    logger.info("BeforeClass: RMI registry ready.");
                } catch (Exception e) {
                    logger.error("Exception starting RMI registry:");
                    logger.trace(e.getMessage());
                    e.printStackTrace();
                }
            }
        }

        rmiRegistryThread = new Thread(new RMIRegistryRunnable());
        rmiRegistryThread.start();
        try {
            Thread.sleep(4000);
        } catch (InterruptedException ie) {
            logger.error("Interruption Exception");
            logger.trace(ie.getMessage());
            ie.printStackTrace();
        }

        class RMIServerRunnable implements Runnable {

            public void run() {
                logger.info("This is a test to check how mvn test executes this test without external interaction; JVM properties by program");
                logger.info("**************: " + cwd);
                System.setProperty("java.rmi.server.codebase", "file:" + cwd);
                System.setProperty("java.security.policy", "target\\test-classes\\security\\java.policy");

                if (System.getSecurityManager() == null) {
                    System.setSecurityManager(new SecurityManager());
                }

                String name = "//127.0.0.1:1099/MessengerRMIDAO";
                logger.info("BeforeClass - Setting the server ready TestServer name: " + name);

                try {

                    RMIInterface remote = new Controller("//127.0.0.1","1099", "EmailServer");
                    Naming.rebind(name, remote);
                } catch (RemoteException re) {
                    logger.error(" # Messenger RemoteException: ");
                    logger.trace(re.getMessage());
                    re.printStackTrace();
                    System.exit(-1);
                } catch (MalformedURLException murle) {
                    logger.error(" # Messenger MalformedURLException: ");
                    logger.trace(murle.getMessage());
                    murle.printStackTrace();
                    System.exit(-1);
                }
            }
        }
        rmiServerThread = new Thread(new RMIServerRunnable());
        rmiServerThread.start();
        try {
            Thread.sleep(4000);
        } catch (InterruptedException ie) {
            logger.error("Interruption Exception");
            logger.trace(ie.getMessage());
            ie.printStackTrace();

        }

    }


    @Before public void setUpClient() {
        try {
            System.setProperty("java.security.policy", "target\\test-classes\\security\\java.policy");

            if (System.getSecurityManager() == null) {
                System.setSecurityManager(new SecurityManager());
            }

            String name = "//127.0.0.1:1099/MessengerRMIDAO";
            logger.info("BeforeTest - Setting the client ready for calling TestServer name: " + name);
            remote = (RMIInterface) java.rmi.Naming.lookup(name);
        }
        catch (Exception re) {
            logger.error(" # Messenger RemoteException: ");
            logger.trace(re.getMessage());
            re.printStackTrace();
            System.exit(-1);
        }

    }

    @Test public void registerNewUserTest() {
        RMIInterface remote  = Mockito.mock(Controller.class);
        boolean t=false;
        try{
            logger.info("Test 1 - Register new user");
            t=remote.signUp("victor", "victor");
        }
        catch (Exception re) {
            logger.error(" RemoteException: " );
            logger.trace(re.getMessage());
            re.printStackTrace();
        }
		/*
		 * Very simple test, inserting a valid new user
		 */
        assertTrue( t );
        try {
            verify(remote).signUp("victor","victor");
        } catch (RemoteException ex) {
            java.util.logging.Logger.getLogger(RMI_test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }




    @Test public void loginTest() {
        RMIInterface remote  = Mockito.mock(Controller.class);
        boolean t=false;
        try{
            logger.info("Test 2 - Register existing user.");
            t=remote.signIn("gotzon", "gotzon");
        }
        catch (Exception re) {
            logger.error(" RemoteException: " + re.getMessage());
            logger.trace(re.getMessage());
            re.printStackTrace();
        }
		/*
		 * Very simple test
		 */
        assertTrue( t );
        try {
            verify(remote).signIn("gotzon","gotzon");
        } catch (RemoteException ex) {
            java.util.logging.Logger.getLogger(RMI_test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    //@Test public void sayMessageValidUser() {

    @Test public void emailRetrieveTest() {
        logger.info("Test 3 - email retrieve test ");

        RMIInterface remote  = Mockito.mock(Controller.class);
        ArrayList<Email> emails = null;
        boolean t=true;
        try{
             emails= remote.getEmails("gotzon");

        } catch (RemoteException e){
            logger.error(" # RemoteException: " + e.getMessage());
            logger.trace(e.getMessage());
            e.printStackTrace();


        }
        if(emails.isEmpty())t=false;
        assertTrue( t );
        try {
            verify(remote).getEmails("gotzon");
        } catch (RemoteException ex) {
            java.util.logging.Logger.getLogger(RMI_test.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
/*
    @Test public void showBooksInStoreTest() {
        try{
            logger.info("Test 4 - showBooksInStore");
            Book b1 =new Book(2,"HL2","maria",0.2);
            remote.addBook(b1);
            remote.showBooksInStore();

        }
        catch (Exception re){
            logger.error(" RemoteException: " );
            logger.trace(re.getMessage());
            re.printStackTrace();

        }

        assertTrue( true );



    }

    @Test public void showUsersTest() {
        try{
            logger.info("Test 5 - showUsers");

            remote.registerUser("HL2","maria",false);
            remote.getAllUsers();

        }
        catch (Exception re){
            logger.error(" RemoteException: " );
            logger.trace(re.getMessage());
            re.printStackTrace();

        }

        assertTrue( true );



    }
    @Test(expected=RemoteException.class)
    public void showUsersFailTest() throws RemoteException{
        IDAO dao = new DAO();
        logger.info("Test 6 - showUsers");

        //dao.deleteDatabase();
        remote.getAllUsers();




    }

    @Test public void reviewsTestValidation() {
        logger.info("Test 7 - License Test");
        Review reviewTest = null;
        IDB db = new DB();

        Book b = new Book(10,"BF 1942", "jaja",19.90);
        Review r = new Review ("GGGG",7.5);
        User u = new User("JunitUser","Junit Pass",false);


        r.setBook(b);
        r.setUser(u);
        try{
            remote.registerUser("JunitUser","Junit Pass",false);
            remote.addBook(b);
            db.buyBook(u.getEmail(), b.getTitle());
            db.addReview(b, r, u);
            db.showUser(u.getEmail());
            reviewTest =	db.showReview(1);


        } catch (RemoteException e){
            logger.error("Remote Exception");
            logger.trace(e.getMessage());
            e.printStackTrace();


        }
        assertEquals(r.toString(), reviewTest.toString());
        assertEquals(r.getBook().getTitle(), reviewTest.getBook().getTitle());
        assertEquals(r.getUser().getEmail(), reviewTest.getUser().getEmail());
    }
*/
    @AfterClass static public void tearDown() {
        try	{
            rmiServerThread.join();
            rmiRegistryThread.join();
        } catch (InterruptedException ie) {
            logger.error("Interruption Exception");
            logger.trace(ie.getMessage());
            ie.printStackTrace();
        }


    }
}
