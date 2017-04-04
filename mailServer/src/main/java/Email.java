/**
 * Created by inigo on 4/04/17.
 */
public class Email {

    String source=null;
    String target=null;
    String header=null;
    String message=null;
    Long time = 0l;

    /**
     * Use on user
     * @param source
     * @param target
     * @param header
     * @param message
     */
    public Email(String source, String target, String header,String message){
        this.source=source;
        this.target=target;
        this.header=header;
        this.message=message;
        this.time= System.currentTimeMillis();
    }

    /**
     * Use on server when queried
     * @param source
     * @param target
     * @param header
     * @param message
     */
    public Email(String source, String header,String message, Long time){
        this.source=source;
        this.header=header;
        this.message=message;
        this.time= time;
    }
}
