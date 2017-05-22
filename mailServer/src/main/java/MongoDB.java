import com.mongodb.*;
import org.joda.time.LocalDateTime;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.LoggerFactory;



/**
 * Created by inigo on 30/03/17.
 * http://www.mkyong.com/mongodb/java-mongodb-hello-world-example/
 * http://howtodoinjava.com/nosql/mongodb/java-mongodb-insert-documents-in-collection-examples/
 *Crear 1 collección por usuario -> los documentos serán los correos
 */
public class MongoDB {

    public String getDatabaseName() {
        return databaseName;
    }

    public String getDatabasePassword() {
        return databasePassword;
    }

    final String databaseName = "emails";
    final String databasePassword = "password";
    private DB db = null;
    private DBCollection dbPasswordCollection = null;

    final String userCollection = "user";
    
    org.slf4j.Logger logger = LoggerFactory.getLogger(MongoDB.class);

    MongoClient mongo;

    public String getUserCollection() {
        return userCollection;
    }

    public MongoDB(){

        this.mongo = new MongoClient( "localhost" , 27017 );
        //Get database. If the database doesn’t exist, MongoDB will create it for you.
        this.db = mongo.getDB(this.databaseName);
        this.dbPasswordCollection = mongo.getDB(this.databasePassword).getCollection(this.userCollection);
    }

    public DB getDb() {
        return db;
    }

    public void setDb(DB db) {
        this.db = db;
    }

    public DBCollection getDbPasswordCollection() {
        return dbPasswordCollection;
    }

    public void setDbPasswordCollection(DBCollection dbPasswordCollection) {
        this.dbPasswordCollection = dbPasswordCollection;
    }


    /**
     * Sign up  introduces the user in the system
     * @param user string that resembles the username of that new user
     * @param password
     * @param rootRights
     * @return if user exists -> false, else -> true
     */
    public boolean sign_up(String user, String password, Boolean rootRights){
        BasicDBObject fichero = new BasicDBObject();
        fichero.put("_id", user);
        if(this.dbPasswordCollection.findOne(fichero) != null){
            return false;
        } else {
            BasicDBObject ficheroIntroducir = new BasicDBObject();
            ficheroIntroducir.put("_id", user);
            ficheroIntroducir.put("password", password);
            ficheroIntroducir.put("rootRights", rootRights);
            this.dbPasswordCollection.insert(ficheroIntroducir);
            db.getCollection(user);
            return true;
        }
    }
    
    /**

     * Changes the password
     * @param user
     * @param pass
     * @param oldPass
     * @return if user exists does not exist -> false, else -> true and change password
     * if user and pass OK then change password
     */
    public boolean updatePassword(String user, String oldPass, String pass){
        BasicDBObject fichero = new BasicDBObject();
        fichero.put("_id", user);
        
        if(this.dbPasswordCollection.findOne(fichero) == null){
            return false;
        } else {
            BasicDBObject ficheroIntroducir = new BasicDBObject();
            BasicDBObject comprobar = new BasicDBObject();
            ficheroIntroducir.put("_id", user);
            ficheroIntroducir.put("password", pass);
            comprobar.put("_id", user);
            comprobar.put("password", oldPass);
            if (sign_in_as_root(user, oldPass)) {
                ficheroIntroducir.put("rootRights", true);
                comprobar.put("rootRights", true);
            }
            else{
                ficheroIntroducir.put("rootRights", false);
                comprobar.put("rootRights", false);
            }
            
            
            BasicDBObject searchQuery = new BasicDBObject().append("_id", user);
            
            
            if(this.dbPasswordCollection.findOne(comprobar) != null){
                
                this.dbPasswordCollection.update(searchQuery, ficheroIntroducir);
            }
            else{
                return false;
            }
            return true;
        }
    }

    /**
     * Check if the user + password exist in the system
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
     *Check if the user + password exist in the system  + check is it is an admin
     * @param user
     * @param password
     * @return if user & password & rootRights OK then true, else false
     */
    public boolean sign_in_as_root(String user, String password){
        BasicDBObject document = new BasicDBObject();
        document.put("_id", user);
        document.put("password", password);
        document.put("rootRights", true);
        return this.dbPasswordCollection.findOne(document) != null;
    }

    /**
     *Introduce the message in the system
     * @param email
     * @throws Exception If target doesn't exist, throws an exception
     */
    public void save_emails(Email email) throws Exception {
        BasicDBObject document = new BasicDBObject();
        logger.info(email.target);
        if(this.dbPasswordCollection.findOne(document) == null ) {
            logger.info("IM CRASHING HERE");
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

    /**
     * Get all the emails from an user
     * @param user
     * @return
     */
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
            listaCorreo.add(new Email(user,source, header, message,  time));
        }
        return listaCorreo;
    }

    /**
     * Delete the message from the system 
     * @param del
     * @return
     */
    public boolean delete_message(Delete del){
        DBCollection table = db.getCollection(del.getUser());
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("date", del.getDate() );
        searchQuery.put("source", del.getSource());
        table.remove(searchQuery);
        return true;
    }
}
