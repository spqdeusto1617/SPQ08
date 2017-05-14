/**
 * Created by inigo on 14/05/17.
 */
public class CreateUserRoot {
    private String userRoot;
    private String passRoot;
    private String userCreate;
    private String passUserCreate;
    private boolean userRightsRoot;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreateUserRoot)) return false;

        CreateUserRoot that = (CreateUserRoot) o;

        if (isUserRightsRoot() != that.isUserRightsRoot()) return false;
        if (!getUserRoot().equals(that.getUserRoot())) return false;
        if (!getPassRoot().equals(that.getPassRoot())) return false;
        if (!getUserCreate().equals(that.getUserCreate())) return false;
        return getPassUserCreate().equals(that.getPassUserCreate());

    }

    @Override
    public int hashCode() {
        int result = getUserRoot().hashCode();
        result = 31 * result + getPassRoot().hashCode();
        result = 31 * result + getUserCreate().hashCode();
        result = 31 * result + getPassUserCreate().hashCode();
        result = 31 * result + (isUserRightsRoot() ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CreateUserRoot{" +
                "userRoot='" + userRoot + '\'' +
                ", passRoot='" + passRoot + '\'' +
                ", userCreate='" + userCreate + '\'' +
                ", passUserCreate='" + passUserCreate + '\'' +
                ", userRightsRoot=" + userRightsRoot +
                '}';
    }

    public String getUserRoot() {
        return userRoot;
    }

    public void setUserRoot(String userRoot) {
        this.userRoot = userRoot;
    }

    public String getPassRoot() {
        return passRoot;
    }

    public void setPassRoot(String passRoot) {
        this.passRoot = passRoot;
    }

    public String getUserCreate() {
        return userCreate;
    }

    public void setUserCreate(String userCreate) {
        this.userCreate = userCreate;
    }

    public String getPassUserCreate() {
        return passUserCreate;
    }

    public void setPassUserCreate(String passUserCreate) {
        this.passUserCreate = passUserCreate;
    }

    public boolean isUserRightsRoot() {
        return userRightsRoot;
    }

    public void setUserRightsRoot(boolean userRightsRoot) {
        this.userRightsRoot = userRightsRoot;
    }

    public CreateUserRoot(String userRoot, String passRoot, String userCreate, String passUserCreate, boolean userRightsRoot) {

        this.userRoot = userRoot;
        this.passRoot = passRoot;
        this.userCreate = userCreate;
        this.passUserCreate = passUserCreate;
        this.userRightsRoot = userRightsRoot;
    }
}
