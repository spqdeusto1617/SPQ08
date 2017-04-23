import com.mongodb.*;
import org.joda.time.LocalDateTime;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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

    final String databaseEmails = "emails";
    final String databaseUsers = "users";
    DB dbUsers = null;
    DB dbEmails = null;
    DBCollection dbUsersCollection = null;
    DBCollection dbProfilesCollection = null;
    GridFS images = null;

    final String userCollection = "user";
    final String profileCollection = "profile";


    public MongoDB(){
        MongoClient mongo = new MongoClient( "localhost" , 27017 );
        //Get database. If the database doesn’t exist, MongoDB will create it for you.
        this.dbEmails = mongo.getDB(this.databaseEmails);
        this.dbUsers = mongo.getDB(this.databaseUsers);
        this.dbUsersCollection = mongo.getDB(this.databaseUsers).getCollection(this.userCollection);
        // initialize gridFs for storing images
        this.images = new GridFS(dbUsers);
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
//        System.out.println(this.dbUsersCollection.findOne(fichero));
        if(this.dbUsersCollection.findOne(fichero) != null){
            return false;
        } else {
            BasicDBObject ficheroIntroducir = new BasicDBObject();
            ficheroIntroducir.put("_id", user);
            ficheroIntroducir.put("password", password);
            this.dbUsersCollection.insert(ficheroIntroducir);
            dbEmails.getCollection(user);
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
        return this.dbUsersCollection.findOne(document) != null;
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
        if(this.dbUsersCollection.findOne(document) == null ) {
            System.out.println("IM CRASHING HERE");
            throw new Exception();
        } else {
            DBCollection usuario = this.dbEmails.getCollection(email.target);
            BasicDBObject documento = new BasicDBObject();
            documento.put("date", email.time);
            documento.put("source", email.source);
            documento.put("header", email.header);
            documento.put("message", email.message);
            usuario.insert(documento);
        }
    }

    public ArrayList<Email> getEmails(String user){
        DBCollection usuario = this.dbEmails.getCollection(user);
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

    public String getProfile(String user) {
        DBCollection userCollection = this.dbEmails.getCollection("user");
        BasicDBObject query = new BasicDBObject();
        query.put("_id", user);
        DBObject profile = this.dbUsersCollection.findOne(query);
        System.out.println("+++++++++++++++++++++++++");
        System.out.println("Fetched object: " + profile);
        System.out.println("+++++++++++++++++++++++++");
        return (String)profile.get("_id");
    }

    public static byte[] LoadImage(String filePath) throws Exception {
        File file = new File(filePath);
        int size = (int) file.length();
        byte[] buffer = new byte[size];
        try {
            FileInputStream in = new FileInputStream(file);
            in.read(buffer);
            in.close();
            return buffer;
        } catch (Exception e) {
            System.out.println("Failed in function loadImage: " + e);
            return buffer;
        }
    }

    public boolean insertProfileImage(String user) throws Exception {
        try {

            byte[] imageBytes = LoadImage("/Users/vitiok/Desktop/view.jpg");
            GridFSInputFile in = images.createFile(imageBytes);
            DBCollection profileCollection = this.dbUsers.getCollection(this.profileCollection);
            BasicDBObject profile = new BasicDBObject();
            profile.put("user", user);
            profile.put("image", images.createFile(imageBytes));
            profileCollection.insert(profile);

            System.out.println("++++++++++++++++++++++++");
            BasicDBObject query = new BasicDBObject("user", user);
            System.out.println(this.dbProfilesCollection.find(query));
            System.out.println("++++++++++++++++++++++++");

            GridFSDBFile out = images.findOne(new BasicDBObject("_id", in.getId()));

            FileOutputStream outputImage = new FileOutputStream("/Users/vitiok/Desktop/newView.jpg");
            out.writeTo(outputImage);
            outputImage.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
