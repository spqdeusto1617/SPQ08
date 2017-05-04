package db;

import entities.User;

/**
 * Created by inigo on 17/01/17.
 */
public interface IMySQL {
    public void storeUser(User u);
    public void deleteUser(User u);
    public User getUser(String email);
}
