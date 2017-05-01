/**
 * Created by inigo on 6/04/17.
 */





public class RMIServiceLocator {

    public RMIInterface service;

    public void setService(String ip, String port, String serviceName) {
        String name = "//" + ip + ":" + port + "/" + serviceName;
        try {
            service = (RMIInterface) java.rmi.Naming.lookup(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public RMIInterface getService(){
        return service;
    }
}
