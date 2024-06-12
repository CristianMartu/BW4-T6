package bw4t6.dao;

import bw4t6.entities.Subscription;
import bw4t6.entities.abstracts.DocumentOfTravel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.UUID;

public class DocumentOfTravelDAO {
    private final EntityManager em;

    public DocumentOfTravelDAO(EntityManager em) {
        this.em = em;
    }

    public void save(DocumentOfTravel document) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(document);
        tx.commit();
        System.out.println(document + " salvato");
    }

    public DocumentOfTravelDAO findById(String id) {
        DocumentOfTravelDAO document = em.find(DocumentOfTravelDAO.class, UUID.fromString(id));
        if (document == null) throw new RuntimeException("Document with id: " + id + " not found");
        return document;
    }

    public Subscription findSubscriptionById(String id) {
        Subscription subscription = em.find(Subscription.class, UUID.fromString(id));
        if (subscription == null) throw new RuntimeException("Subscription with id: " + id + " not found");
        return subscription;
    }

    public void delete(String id) {
        DocumentOfTravelDAO document = this.findById(id);
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.remove(document);
        tx.commit();
    }
}
