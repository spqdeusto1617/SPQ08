package db;

import entities.User;

import javax.jdo.*;

/**
 * Created by inigo on 17/01/17.
 */
public class MySQL implements IMySQL {

    PersistenceManagerFactory pmf;

    public MySQL(){
        pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
    }

    @Override
    public void storeUser(User u) {
        System.out.println("Store User "+u.getUsername());
        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        try {
            tx.begin();
            pm.makePersistent(u);
            System.out.println("Inserting contents into the database ....");
            tx.commit();
        } catch (Exception ex) {
            System.out.println("# Error storing objects: " + ex.getMessage());
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            pm.close();
        }
    }

    @Override
    public void deleteUser(User u) {
        System.out.println("Delete User "+u.getUsername());
        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        try {
            tx.begin();
            Extent<User> extentP = pm.getExtent(User.class);
            pm.deletePersistent(u);
            pm.flush();
            tx.commit();
        } catch (Exception ex) {
            System.out.println("# Error getting Extent: " + ex.getMessage());
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            pm.close();
        }
    }

    @Override
    public User getUser(String username) {
        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        pm.getFetchPlan().setMaxFetchDepth(1);
        User user = null;
        try {
            tx.begin();
            user = pm.getObjectById(User.class, username);
            user = pm.detachCopy(user);
            tx.commit();
        } catch (Exception ex) {
            System.out.println("# Error getting Extent: " + ex.getMessage());
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            pm.close();
        }
        return user;
    }
}
