package bw4t6.dao;

import bw4t6.entities.AutomaticSeller;
import bw4t6.entities.Vehicle;
import bw4t6.enums.AutomaticSellerState;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.UUID;

public class VehicleDAO {
    private final EntityManager em;

    public VehicleDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Vehicle vehicle) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(vehicle);
        tx.commit();
        System.out.println(vehicle);
    }

    public Vehicle findById(String id) {
        Vehicle vehicle = em.find(Vehicle.class, UUID.fromString(id));
        if (vehicle == null) throw new RuntimeException("Veichle with id: " + id + " not found");
        return vehicle;
    }

    public void delete(String id) {
        Vehicle vehicle = this.findById(id);
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.remove(vehicle);
        tx.commit();

    }
    public List<Vehicle> serviceByVehicleId() {
        TypedQuery<Vehicle> query = em.createQuery("SELECT v FROM Vehicle v WHERE v.state = TRUE", Vehicle.class);
        return query.getResultList();
    }
}
