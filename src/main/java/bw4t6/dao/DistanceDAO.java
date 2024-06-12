package bw4t6.dao;

import bw4t6.entities.Distance;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

import java.util.UUID;

public class DistanceDAO {
    private final EntityManager em;
    public DistanceDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Distance distance) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(distance);
        tx.commit();
        System.out.println(distance + " salvato");
    }

    public Distance findById(String id) {
        Distance distance = em.find(Distance.class, UUID.fromString(id));
        if (distance == null) throw new RuntimeException("Distance with id: " + id + " not found");
        return distance;
    }

    public void update(UUID id){
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Distance distance = em.find(Distance.class,id);
        distance.setNumber_of_trip(distance.getNumber_of_trip() + 1);
        tx.commit();
        System.out.println(distance);
    }
}
