package test;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import static org.junit.Assert.*;
import junit.framework.JUnit4TestAdapter;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.AfterClass;

/**
 * Created by inigo on 3/05/17.
 */
public class MongoDBTest {
    final String databaseName = "emails";
    final String databasePassword = "password";
    DB db = null;
    DBCollection dbPasswordCollection = null;
    String usr="gotzon",pass="gotzon";
    final String userCollection = "user";
    
    @org.junit.Before
    public void setUp() throws Exception {
         MongoClient mongo = new MongoClient( "localhost" , 27017 );
        //Get database. If the database doesn’t exist, MongoDB will create it for you.
        this.db = mongo.getDB(this.databaseName);
        this.dbPasswordCollection = mongo.getDB(this.databasePassword).getCollection(this.userCollection);
    }

    @org.junit.After
    public void tearDown() throws Exception {

    }

    @org.junit.Test
    public void sign_up() throws Exception {
          BasicDBObject fichero = new BasicDBObject();
        fichero.put("_id", usr);
//        System.out.println(this.dbPasswordCollection.findOne(fichero));
        if(this.dbPasswordCollection.findOne(fichero) != null){
            return false;
        } else {
            BasicDBObject ficheroIntroducir = new BasicDBObject();
            ficheroIntroducir.put("_id", usr);
            ficheroIntroducir.put("password", pass);
            this.dbPasswordCollection.insert(ficheroIntroducir);
            db.getCollection(usr);
            return true;
        }
    }

    @org.junit.Test
    public void sign_in() throws Exception {

    }

    @org.junit.Test
    public void save_emails() throws Exception {

    }

    @org.junit.Test
    public void getEmails() throws Exception {

    }

    @org.junit.Test
    public void delete_message() throws Exception {

    }

}