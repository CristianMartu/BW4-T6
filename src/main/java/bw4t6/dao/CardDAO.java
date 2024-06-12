package bw4t6.dao;

import bw4t6.entities.Card;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.UUID;

public class CardDAO {
    private final EntityManager em;

    public CardDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Card card) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(card);
        tx.commit();
        System.out.println(card + " salvato");
    }

    public Card findById(String id) {
        Card card = em.find(Card.class, UUID.fromString(id));
        if (card == null) throw new RuntimeException("Card with id: " + id + " not found");
        return card;
    }

    public void delete(String id) {
        Card card = this.findById(id);
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.remove(card);
        tx.commit();

    }
}
