import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * Created by inigo on 30/03/17.
 */

//code to run on the terminal => rmiregistry -J-Djava.rmi.server.useCodebaseOnly=false
//introduce JVM argument on Edit Configurations (in the future it is going to be in the pom.xml) + path Of project should be modified
//      => -Djava.rmi.server.codebase=file:/home/inigo/workspace/Java/SPQ08/mailServer/target/classes/

public class Main extends UnicastRemoteObject implements RMIInterface{

    protected Main() throws RemoteException {
    }

    public static void main(String[] args) {
        //String source, String target, String header,String message
        System.out.println(new MongoDB().sign_up("inigo22","nuevo7768675gff"));
        System.out.println(new MongoDB().sign_in("inigo22","nuevo7768675gff"));
        try {
            new MongoDB().save_emails(new Email("inigo22","noExisto2","rtFav","message"));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("he petado");
        }
        System.out.println(new MongoDB().sign_up("noExisto2","nuevo"));
        try {
            new MongoDB().save_emails(new Email("inigo","noExisto2","rtFav","messageULTIMO MUA JA JA x2"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(new MongoDB().getEmails("noExisto2").get(0).message);

        try {

            Naming.rebind("//localhost/MyServer", new Main());
            System.err.println("Server is running...");

        } catch (Exception e) {

            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();

        }

    }

    public String helloTo(String name) throws RemoteException {
        return null;
    }

    public boolean signIn(String user, String password) throws RemoteException {
        return false;
    }

    public boolean signUp(String user, String password) throws RemoteException {
        return false;
    }

    public boolean sendEmail(Email email) throws RemoteException {
        return false;
    }

    public ArrayList<Email> getEmails() throws RemoteException {
        return null;
    }
}
