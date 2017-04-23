import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

/**
 * Created by inigo on 6/04/17.
 */
public class Server extends UnicastRemoteObject implements RMIInterface {

    private MongoDB db = null;

    protected Server() throws RemoteException {
        this.db= new MongoDB();
    }

    public String helloTo(String name) throws RemoteException {
        return null;
    }

    public boolean signIn(String user, String password) throws RemoteException {
        try {
            System.out.println("=================================");
            System.out.println("get profile for user: " + user);
            System.out.println("=================================");
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
            System.out.println("=================================");
            this.db.save_emails(email);
            System.out.println("=================================");
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

    public boolean insertImage(String user) throws RemoteException {
        try {
            return this.db.insertProfileImage(user);
        } catch (Exception exception) {
            return false;
        }
    }

    public static void runServer(String ip, String port, String serviceName){
        String name = "//" + ip + ":" + port + "/" + serviceName;
        try {
            RMIInterface objServer = new Server();
            Naming.rebind(name, objServer);
            System.out.println("* Server '" + name + "' active and waiting...");
            java.io.InputStreamReader inputStreamReader = new java.io.InputStreamReader ( System.in );
            java.io.BufferedReader stdin = new java.io.BufferedReader ( inputStreamReader );
            String line  = stdin.readLine();

            System.out.println("Insert Image start ++++");
            objServer.insertImage("vitiok");
            System.out.println("Insert Image end ++++");
        } catch (Exception e) {
            System.err.println("- Exception running the server: " + e.getMessage());
            e.printStackTrace();
        }
    }



}
