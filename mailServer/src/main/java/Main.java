import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

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
//        System.out.println(new MongoDB().sign_up("inigo","nuevo"));
//        System.out.println(new MongoDB().sign_in("inigo","nuevo"));
//        new MongoDB().save_emails("","","","");

        try {

            Naming.rebind("//localhost/MyServer", new Main());
            System.err.println("Server ready");

        } catch (Exception e) {

            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();

        }

    }

    public String helloTo(String name) throws RemoteException {
        return null;
    }
}
