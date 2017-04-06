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

    // Temporal variables for user and password
    private String user = "inigo22";
    private String password = "nuevo7768675gff";

    private static String newUser = "vitiok222";
    private static String newPassword = "123456789";

    // Create instance of Database object
    private static MongoDB db = new MongoDB();

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

        try {
            Boolean weCan = db.sign_in(newUser, newPassword);
            System.out.println("Value after db.sign_in: " + weCan);
        } catch (Exception e) {
            System.err.println("Server exception when trying to sign up with Victor name: " + e.toString());
        }

    }

    public String helloTo(String name) throws RemoteException {
        return null;
    }

    public boolean signIn(String user, String password) throws RemoteException {
        try {
            return db.sign_in(user, password);
        } catch(Exception exception) {
            System.err.println("Server when trying to sign in. Exception: " + exception.toString());
            return false;
        }
    }

    public boolean signUp(String user, String password) throws RemoteException {
        try {
            return db.sign_up(user, password);
        } catch(Exception exception) {
            System.err.println("Server when trying to sign up. Exception: " + exception.toString());
            return false;
        }
    }

    public boolean sendEmail(Email email) throws RemoteException {
        try {
            db.save_emails(email);
            System.out.println("Email have been send.");
            return true;
        } catch(Exception exception) {
            System.err.println("Server exception when trying to send Email. Exception: " + exception.toString());
            return false;
        }
    }

    public ArrayList<Email> getEmails(String user) throws RemoteException {
        System.out.println("Retrive all the emails for the user: " + user);
        return db.getEmails(user);
    }
}
