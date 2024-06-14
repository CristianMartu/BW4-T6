package bw4t6.dao;

import bw4t6.entities.Subscription;
import bw4t6.entities.abstracts.DocumentOfTravel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.time.LocalDateTime;
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
        System.out.println(document);
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

    public void validitySubscription(String id) {
        TypedQuery<Subscription> query = em.createQuery("SELECT c FROM Subscription c  WHERE c.card.user.card_id = :id", Subscription.class);
        query.setParameter("id", UUID.fromString(id));
        query.getResultList().forEach(subscription -> {
            if (subscription.getExpired_date().isBefore(LocalDateTime.now())) {
                System.out.println("tessera scaduta!");
            } else {
                System.out.println("tessera valida");
            }
        });
    }

    public void countValidatedTicketsByTime(LocalDateTime startDate, LocalDateTime endDate) {
        TypedQuery<Long> query = em.createQuery(
                "SELECT COUNT(t) FROM Ticket t WHERE t.emission_date BETWEEN :startDate AND :endDate AND t.state = false",
                Long.class);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        long count = query.getSingleResult();
        System.out.println("Totale biglietti validati tra " + startDate + " e " +endDate+ " : " + count);

    }

    public void countValidatedTicketsByVehicle(UUID vehicle_id) {
        TypedQuery<Long> query = em.createQuery(
                "SELECT COUNT(t) FROM Ticket t WHERE t.vehicle_ticket.vehicle_id = :vehicle_id AND t.state = false",
                Long.class);
        query.setParameter("vehicle_id", vehicle_id);
        long count =  query.getSingleResult();
        System.out.println("Totale biglietti validati nel veicolo " + vehicle_id + " : " + count);
    }
}

