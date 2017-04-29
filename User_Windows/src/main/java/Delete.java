/**
 * Created by Maria Blaja on 4/28/2017.
 */
public class Delete {
    private String user;
    private String source;
    private Long date;
   public Delete(String user, String source, Long date){
       this.user = user;
       this.source = source;
       this.date = date;
   }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }
}
