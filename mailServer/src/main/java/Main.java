/**
 * Created by inigo on 30/03/17.
 */


public class Main {

    public static void main(String[] args) {
//        //Create user
       MongoDB db = new MongoDB();
        db.sign_up("inigo","inigo",true);
        Server.runServer(args[0], args[1], args[2]);

    }
}
