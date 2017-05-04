import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import org.slf4j.LoggerFactory;

/**
 * Created by inigo on 6/04/17.
 */
public class Server extends UnicastRemoteObject implements RMIInterface {

    private MongoDB db = null;

    static org.slf4j.Logger logger = LoggerFactory.getLogger(Server.class);
    protected Server() throws RemoteException {
        this.db= new MongoDB();
    }

    public String helloTo(String name) throws RemoteException {
        return null;
    }

    public boolean deleteEmail(Delete delete) throws RemoteException{
        try {
            logger.info("delete = " + delete);
            return this.db.delete_message(delete);
        } catch(Exception exception) {
            System.err.println("Server when trying to sign delete. Exception: " + exception.toString());
            return false;
        }
    }

    public boolean signIn(String user, String password) throws RemoteException {
        try {
            logger.info("=================================");
            logger.info("sign in for user: " + user);
            logger.info("=================================");
            return this.db.sign_in(user, password);
        } catch(Exception exception) {
            System.err.println("Server when trying to sign in. Exception: " + exception.toString());
            return false;
        }
    }

    public boolean signUp(String user, String password) throws RemoteException {
        try {
            return this.db.sign_up(user, password);
        } catch(Exception exception) {
            System.err.println("Server when trying to sign up. Exception: " + exception.toString());
            return false;
        }
    }

    public boolean sendEmail(Email email) throws RemoteException {
        try {
            logger.info("=================================");
            this.db.save_emails(email);
            logger.info("Email have been send: " + email.toString());
            logger.info("=================================");
            return true;
        } catch(Exception exception) {
            System.err.println("Server exception when trying to send Email. Exception: " + exception.toString());
            return false;
        }
    }

    public ArrayList<Email> getEmails(String user) throws RemoteException {
        logger.info("Retrive all the emails for the user: " + user);
        return this.db.getEmails(user);
    }

    public static void runServer(String ip, String port, String serviceName){
        String name = "//" + ip + ":" + port + "/" + serviceName;
        try {
            RMIInterface objServer = new Server();
//			objServer.registerUser("client","pass");
            Naming.rebind(name, objServer);
            logger.info("* Server '" + name + "' active and waiting...");
            java.io.InputStreamReader inputStreamReader = new java.io.InputStreamReader ( System.in );
            java.io.BufferedReader stdin = new java.io.BufferedReader ( inputStreamReader );
            String line  = stdin.readLine();
        } catch (Exception e) {
            System.err.println("- Exception running the server: " + e.getMessage());
            e.printStackTrace();
        }

    }
}
