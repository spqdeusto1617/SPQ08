/**
 * Created by inigo on 4/05/17.
 */
import com.sun.prism.Texture;
import entities.User;
import db.*;

public class Main {
    public static void main(String[] args) {
        MySQL mySQL = new MySQL();
        User a=new User("a","a1",1,1);
        User b=new User("b","b1",2,2);
        mySQL.storeUser(a);
        mySQL.storeUser(b);
        mySQL.deleteUser(a);
        System.out.println(mySQL.getUser(b.getUsername()).toString());
        //System.out.println(mySQL.getUser(a.getUsername()).toString());
    }
}
