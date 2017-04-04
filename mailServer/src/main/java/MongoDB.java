import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import org.joda.time.LocalDateTime;

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

        Map<String,String> document = new HashMap<String, String>();
        document.put("user", user);
        document.put("password", password);
        if(this.dbPasswordCollection.findOne(new BasicDBObject( "_id", new BasicDBObject(document))) != null ){
            return false;
        }else{
            DBObject groupFields = new BasicDBObject( "_id", new BasicDBObject(document));
            this.dbPasswordCollection.insert(groupFields);
            db.getCollection("user");
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
        Map<String,String> document = new HashMap<String, String>();
        document.put("user", user);
        document.put("password", password);
        return this.dbPasswordCollection.findOne(new BasicDBObject( "_id", new BasicDBObject(document))) != null;
    }

    public void save_emails(String source, String target, String header,String messsage){
        DBCollection usuario = this.db.getCollection(target);
        Long time = System.currentTimeMillis();
        BasicDBObject documento = new BasicDBObject();
        documento.put("date",time);
        documento.put("source",source);
        documento.put("header", header);
        documento.put("message",messsage);
        usuario.insert(documento);
    }



}
