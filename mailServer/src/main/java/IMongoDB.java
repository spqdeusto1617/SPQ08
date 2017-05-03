/**
 * Created by inigo on 4/04/17.
 */
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IMongoDB extends Remote {

    String helloTo(String name) throws RemoteException;
    boolean signIn(String user, String password) throws RemoteException;
    boolean signUp(String user, String password) throws RemoteException;
    boolean sendEmail(Email email) throws RemoteException;
    boolean deleteEmail(Delete delete) throws RemoteException;
    ArrayList<Email> getEmails(String user) throws RemoteException;
}