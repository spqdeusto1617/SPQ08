/**
 * Created by inigo on 4/04/17.
 */
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface RMIInterface extends Remote {

    String helloTo(String name) throws RemoteException;

    /**
     * Check if the user + password exist in the system
     * @param user
     * @param password
     * @return if user & password OK then true, else false
     * * @throws RemoteException
     */
    boolean signIn(String user, String password) throws RemoteException;

    /**
     * Checks if the admin is correct and introduces the user in the system
     * @param createUserRoot
     * @return
     * @throws RemoteException
     */
    boolean signUp(CreateUserRoot createUserRoot) throws RemoteException;
    /**
     *Introduce the message in the system
     * @param email
     * @throws RemoteException
     */
    boolean sendEmail(Email email) throws RemoteException;
    /**
     * Delete the message from the system
     * @param delete
     * @return
     * @throws RemoteException
     */
    boolean deleteEmail(Delete delete) throws RemoteException;
    /**
     * Get all the emails from an user
     * @param user
     * @return
     * * @throws RemoteException
     */
    ArrayList<Email> getEmails(String user) throws RemoteException;

    /**
     * Update the user password from the DB
     * @param user
     * @param oldPass
     * @param pass
     * @return
     * @throws RemoteException
     */
    public boolean updatePassword(String user, String oldPass, String pass) throws RemoteException;
}