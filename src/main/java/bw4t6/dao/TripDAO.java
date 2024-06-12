package bw4t6.dao;

import bw4t6.entities.Trip;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.UUID;

public class TripDAO {
    private final EntityManager em;

    public TripDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Trip trip) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(trip);
        tx.commit();
        System.out.println(trip + " salvato");
    }

    public Trip findById(String id) {
        Trip trip = em.find(Trip.class, UUID.fromString(id));
        if (trip == null) throw new RuntimeException("Trip with id: " + id + " not found");
        return trip;
    }

    public void delete(String id) {
        Trip trip = this.findById(id);
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.remove(trip);
        tx.commit();

    }
}
