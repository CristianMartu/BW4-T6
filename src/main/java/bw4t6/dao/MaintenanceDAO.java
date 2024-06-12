package bw4t6.dao;

import bw4t6.entities.Maintenance;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.UUID;

public class MaintenanceDAO {
    private final EntityManager em;

    public MaintenanceDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Maintenance maintenance) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(maintenance);
        tx.commit();
        System.out.println(maintenance + " salvato");
    }

    public Maintenance findById(String id) {
        Maintenance maintenance = em.find(Maintenance.class, UUID.fromString(id));
        if (maintenance == null) throw new RuntimeException("Maintenance with id: " + id + " not found");
        return maintenance;
    }

    public void delete(String id) {
        Maintenance maintenance = this.findById(id);
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.remove(maintenance);
        tx.commit();

    }
}
