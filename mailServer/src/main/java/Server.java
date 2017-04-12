import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * Created by inigo on 6/04/17.
 */
public class Server extends UnicastRemoteObject implements RMIInterface {

    private MongoDB db = null;

    protected Server() throws RemoteException {
        this.db = new MongoDB();
    }

    public String helloTo(String name) throws RemoteException {
        return null;
    }

    public boolean signIn(String user, String password) throws RemoteException {
        try {
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
            this.db.save_emails(email);
            System.out.println("Email have been send.");
            return true;
        } catch(Exception exception) {
            System.err.println("Server exception when trying to send Email. Exception: " + exception.toString());
            return false;
        }
    }

    public ArrayList<Email> getEmails(String user) throws RemoteException {
        System.out.println("Retrive all the emails for the user: " + user);
        return this.db.getEmails(user);
    }

    public static void runServer(String ip, String port, String serviceName){
        String name = "//" + ip + ":" + port + "/" + serviceName;
        try {
            RMIInterface objServer = new Server();
//			objServer.registerUser("client","pass");
            Naming.rebind(name, objServer);
            System.out.println("* Server '" + name + "' active and waiting...");
        } catch (Exception e) {
            System.err.println("- Exception running the server: " + e.getMessage());
            e.printStackTrace();
        }


    }
}
