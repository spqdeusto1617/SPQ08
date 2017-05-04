/**
 * Created by inigo on 30/03/17.
 */


public class Main {

    public static void main(String[] args) {
        //Create user
        MongoDB db = new MongoDB();
        db.sign_up("inigo","inigo");
        db.sign_up("gotzon","gotzon");
        Email em3 = new Email("inigo2","gotzon","Hi","There");
        try {
            db.save_emails(em3);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Server.runServer(args[0], args[1], args[2]);

    }
}
