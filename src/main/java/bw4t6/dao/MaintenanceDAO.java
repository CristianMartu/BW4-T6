package bw4t6.dao;

import bw4t6.entities.Maintenance;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.util.List;
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
        System.out.println(maintenance);
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
    public List<Maintenance> maintenanceByVehicleId(String id){
        TypedQuery<Maintenance> query = em.createQuery("SELECT m FROM Maintenance m WHERE m.vehicle_maintenance.vehicle_id = :id", Maintenance.class);
        query.setParameter("id",UUID.fromString(id));
        return query.getResultList();
    }
}
