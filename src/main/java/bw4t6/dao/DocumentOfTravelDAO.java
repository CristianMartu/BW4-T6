package bw4t6.dao;

import bw4t6.entities.Ticket;
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
        System.out.println("elemento salvato");
    }
    public DocumentOfTravelDAO findById(String id) {
        DocumentOfTravelDAO document=   em.find(DocumentOfTravelDAO.class, UUID.fromString(id));
        if (document== null) throw new RuntimeException("Document with id: "+id+" not found" );
        return document;
    }
    public void delete(String id) {
        DocumentOfTravelDAO document =   this.findById(id);
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.remove(document);
        tx.commit();
    }
}
