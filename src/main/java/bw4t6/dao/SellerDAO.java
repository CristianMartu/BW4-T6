package bw4t6.dao;

import bw4t6.entities.Seller;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.UUID;

public class SellerDAO {
    private final EntityManager em;

    public SellerDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Seller seller) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(seller);
        tx.commit();
        System.out.println(seller + " salvato");
    }

    public Seller findById(String id) {

        Seller seller = em.find(Seller.class, UUID.fromString(id));
        if (seller == null) throw new RuntimeException("Seller with id: " + id + " not found");
        return seller;
    }

    public void delete(String id) {
        Seller seller = this.findById(id);

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.remove(seller);
        tx.commit();

    }
}
