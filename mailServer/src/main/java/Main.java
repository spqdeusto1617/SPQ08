/**
 * Created by inigo on 30/03/17.
 */

//code to run on the terminal => rmiregistry -J-Djava.rmi.server.useCodebaseOnly=false
//introduce JVM argument on Edit Configurations (in the future it is going to be in the pom.xml) + path Of project should be modified
//      => -Djava.rmi.server.codebase=file:/home/inigo/workspace/Java/SPQ08/mailServer/target/classes/

public class Main {

    public static void main(String[] args) {
        //String source, String target, String header,String message
        Server.runServer("127.0.0.1", "1099", "EmailServer");
    }
}
