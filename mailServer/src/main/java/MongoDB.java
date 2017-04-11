import com.mongodb.*;
import org.joda.time.LocalDateTime;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/*
System.out.println(System.currentTimeMillis());
        LocalDateTime a = new LocalDateTime(System.currentTimeMillis());
        LocalDateTime b = new LocalDateTime();
        System.out.println(a.getDayOfMonth());
        System.out.println(a.compareTo(b));
        System.out.println(b.compareTo(a));
        System.out.println(a.compareTo(a));
 */

/**
 * Created by inigo on 30/03/17.
 * http://www.mkyong.com/mongodb/java-mongodb-hello-world-example/+
 * http://howtodoinjava.com/nosql/mongodb/java-mongodb-insert-documents-in-collection-examples/
 */
/*
Crear 1 collección por usuario -> los documentos serán los correos
 */
public class MongoDB {

    final String databaseName = "emails";
    final String databasePassword = "password";
    DB db = null;
    DBCollection dbPasswordCollection = null;

    final String userCollection = "user";


    public MongoDB(){
        MongoClient mongo = new MongoClient( "localhost" , 27017 );
        //Get database. If the database doesn’t exist, MongoDB will create it for you.
        this.db = mongo.getDB(this.databaseName);
        this.dbPasswordCollection = mongo.getDB(this.databasePassword).getCollection(this.userCollection);
    }

    /**
     *
     * @param user
     * @param password
     * @return if user exists -> false, else -> true
     */
    public boolean sign_up(String user, String password){
        BasicDBObject fichero = new BasicDBObject();
        fichero.put("_id", user);
//        System.out.println(this.dbPasswordCollection.findOne(fichero));
        if(this.dbPasswordCollection.findOne(fichero) != null){
            return false;
        } else {
            BasicDBObject ficheroIntroducir = new BasicDBObject();
            ficheroIntroducir.put("_id", user);
            ficheroIntroducir.put("password", password);
            this.dbPasswordCollection.insert(ficheroIntroducir);
            db.getCollection(user);
            return true;
        }
    }

    /**
     *
     * @param user
     * @param password
     * @return if user & password OK then true, else false
     */
    public boolean sign_in(String user, String password){
        BasicDBObject document = new BasicDBObject();
        document.put("_id", user);
        document.put("password", password);
        return this.dbPasswordCollection.findOne(document) != null;
    }

    /**
     *
     * @param email
     * @throws Exception If target doesn't exist, throws an exception
     */
    public void save_emails(Email email) throws Exception {
        BasicDBObject document = new BasicDBObject();
        document.put("_id", email.target);
        System.out.println(email.target);
        if(this.dbPasswordCollection.findOne(document) == null ) {
            System.out.println("IM CRASHING HERE");
            throw new Exception();
        } else {
            DBCollection usuario = this.db.getCollection(email.target);
            BasicDBObject documento = new BasicDBObject();
            documento.put("date", email.time);
            documento.put("source", email.source);
            documento.put("header", email.header);
            documento.put("message", email.message);
            usuario.insert(documento);
        }
    }

    public ArrayList<Email> getEmails(String user){
        DBCollection usuario = this.db.getCollection(user);
        BasicDBObject sortby = new BasicDBObject();
        sortby.put("date",-1);
        DBCursor cursor=usuario.find().sort( sortby );
        ArrayList<Email> listaCorreo = new ArrayList<Email>();
        while(cursor.hasNext()) {
            DBObject object=cursor.next();
            //String source, String header,String message, Long time
            String source=(String)object.get("source");
            String header=(String)object.get("header");
            String message=(String)object.get("message");
            Long time=(Long)object.get("date");
            listaCorreo.add(new Email(source, header, message,  time));
        }
        return listaCorreo;
    }
}
