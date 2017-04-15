import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by inigo on 6/04/17.
 */
public class Controller implements RMIInterface {

    private RMIServiceLocator rsl;

    public Controller(String ip, String port, String serviceName) throws RemoteException {
        rsl = new RMIServiceLocator();
        rsl.setService(ip, port, serviceName);
    }

    public String helloTo(String name) throws RemoteException {
        return null;
    }

    public boolean signIn(String user, String password) throws RemoteException {
        return rsl.getService().signIn(user, password);
    }

    public boolean signUp(String user, String password) throws RemoteException {
        return rsl.getService().signUp(user,password);
    }

    public boolean sendEmail(Email email) throws RemoteException {
        return rsl.getService().sendEmail(email);
    }

    public ArrayList<Email> getEmails(String user) throws RemoteException {
        return rsl.getService().getEmails(user);
    }
}
