import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by inigo on 6/04/17.
 */
public class Controller implements RMIInterface{

    private RMIServiceLocator rsl;

    public Controller(String ip, String port, String serviceName) throws RemoteException {
        rsl = new RMIServiceLocator();
        rsl.setService(ip, port, serviceName);
    }

    public String helloTo(String name) throws RemoteException {
        return null;
    }

    public boolean signIn(String user, String password) throws RemoteException {
        System.out.println("Sign in user: " + user + " with passord: " + password);
        return rsl.getService().signIn(user, password);
    }

    public boolean signUp(String user, String password) throws RemoteException {
        System.out.println("Sign up: " + user + " password: " + password);
        return rsl.getService().signUp(user,password);
    }

    public boolean sendEmail(Email email) throws RemoteException {
        System.out.println("Send email: " + email.toString());
        return rsl.getService().sendEmail(email);
    }
    
    public boolean deleteEmail(Delete delete) throws RemoteException {
        System.out.println("Delete email: " + delete.toString());
        return rsl.getService().deleteEmail(delete);
    }

    public ArrayList<Email> getEmails(String user) throws RemoteException {
        System.out.println("Retrive emails for user: " + user);
        return rsl.getService().getEmails(user);
    }
}
