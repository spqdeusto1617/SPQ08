import java.rmi.RemoteException;
import java.util.ArrayList;
import org.slf4j.LoggerFactory;

/**
 * Created by inigo on 6/04/17.
 */
public class Controller implements RMIInterface{

    private RMIServiceLocator rsl;
    
    org.slf4j.Logger logger = LoggerFactory.getLogger(Window.class);
    public Controller(String ip, String port, String serviceName) throws RemoteException {
        rsl = new RMIServiceLocator();
        rsl.setService(ip, port, serviceName);
    }

    public String helloTo(String name) throws RemoteException {
        return "Hello";
    }

    public boolean deleteEmail(Delete delete) throws RemoteException{
        logger.info("delete = " + delete);
        return rsl.getService().deleteEmail(delete);
    }

    public boolean signIn(String user, String password) throws RemoteException {
        logger.info("Sign in user: " + user + " with passord: " + password);
        return rsl.getService().signIn(user, password);
    }

    public boolean signUp(String user, String password) throws RemoteException {
        logger.info("Sign up: " + user + " password: " + password);
        return rsl.getService().signUp(user,password);
    }

    public boolean sendEmail(Email email) throws RemoteException {
        logger.info("Send email: " + email.toString());
        return rsl.getService().sendEmail(email);
    }

    public ArrayList<Email> getEmails(String user) throws RemoteException {
        logger.info("Retrive emails for user: " + user);
        return rsl.getService().getEmails(user);
    }
}
