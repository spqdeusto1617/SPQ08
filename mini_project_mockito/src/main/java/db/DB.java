package db;

import entities.User;

/**
 * Created by inigo on 4/05/17.
 */
public class DB {

    public IMySQL medium;

    public DB(IMySQL medium){
        this.medium=medium;
    }

    public void storeUser(User u) {
        this.medium.storeUser(u);
    }


    public void deleteUser(User u) {
        this.medium.deleteUser(u);
    }

    public User getUser(String username) {
        return this.getUser(username);
    }
}
