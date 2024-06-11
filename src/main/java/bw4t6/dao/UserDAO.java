package bw4t6.dao;

import bw4t6.entities.User;
import bw4t6.exceptions.MyCustomExeption;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.UUID;

public class UserDAO {
  private final EntityManager em;

    public UserDAO(EntityManager em) {
        this.em = em;
    }
    public void save(User user) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(user);
        tx.commit();
        System.out.println("elemento salvato");
    }
    public User findById(String id) {
     User user=   em.find(User.class, UUID.fromString(id));
        if (user == null) throw new RuntimeException("User with id: "+id+" not found" );
        return user;
    }
    public void delete(String id) {
        User user=   this.findById(id);
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.remove(user);
        tx.commit();

    }
}
