package gui.spq;

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

    @Override
    public String helloTo(String name) throws RemoteException {
        return rsl.getService().helloTo(name);
    }

    @Override
    public boolean signIn(String user, String password) throws RemoteException {
        return rsl.getService().signIn(user, password);
    }

    @Override
    public boolean signUp(String user, String password) throws RemoteException {
        return rsl.getService().signUp(user,password);
    }

    @Override
    public boolean sendEmail(Email email) throws RemoteException {
        return rsl.getService().sendEmail(email);
    }

    @Override
    public ArrayList<Email> getEmails(String user) throws RemoteException {
        return rsl.getService().getEmails(user);
    }
}
