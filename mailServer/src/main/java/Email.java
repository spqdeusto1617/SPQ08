
import java.io.Serializable;

/**
 * Created by inigo on 4/04/17.
 */
public class Email implements Serializable{

    String source=null;
    String target=null;
    String header=null;
    String message=null;
    Long time = 0l;

    /**
     * Use on user
     * @param source sender
     * @param target receiver
     * @param header the title
     * @param message the text
     */
    public Email(String source, String target, String header,String message){
        this.source=source;
        this.target=target;
        this.header=header;
        this.message=message;
        this.time= System.currentTimeMillis();
    }

    /**
     *  Use on server when queried
     * @param target receiver
     * @param source sender
     * @param header the title
     * @param message the text
     * @param time the moment
     */
    public Email(String target, String source, String header,String message, Long time){
        this.target=target;
        this.source=source;
        this.header=header;
        this.message=message;
        this.time= time;
    }

    @Override
    public String toString() {
        return "Email{" +
                "source='" + source + '\'' +
                ", target='" + target + '\'' +
                ", header='" + header + '\'' +
                ", message='" + message + '\'' +
                ", time=" + time +
                '}';
    }
}
