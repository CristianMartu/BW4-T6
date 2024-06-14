package bw4t6.dao;

import bw4t6.entities.AutomaticSeller;
import bw4t6.entities.abstracts.DocumentOfTravel;
import bw4t6.entities.abstracts.Shop;
import bw4t6.enums.AutomaticSellerState;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class ShopDAO {
    private final EntityManager em;

    public ShopDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Shop shop) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(shop);
        tx.commit();
        System.out.println(shop);
    }

    public Shop findById(String id) {

        Shop shop = em.find(Shop.class, UUID.fromString(id));
        if (shop == null) throw new RuntimeException("Shop with id: " + id + " not found");
        return shop;
    }

    public void delete(String id) {
        Shop shop = this.findById(id);

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.remove(shop);
        tx.commit();

    }

    public List<AutomaticSeller> findActiveAutomaticSeller() {
        TypedQuery<AutomaticSeller> query = em.createQuery("SELECT s FROM AutomaticSeller s WHERE s.state = :state", AutomaticSeller.class);
        query.setParameter("state", AutomaticSellerState.IN_SERVICE);
        return query.getResultList();
    }

    public List<Shop> findSoldByShop(String name) {
        TypedQuery<Shop> query = em.createQuery("SELECT s FROM Shop s WHERE s.emission_point ILIKE :shop", Shop.class);
        query.setParameter("shop", name);
        return query.getResultList();
    }

    public List<DocumentOfTravel> findSoldByTime(LocalDateTime date1, LocalDateTime date2, String name) {
        List<Shop> result = findSoldByShop(name);
        UUID sellerId = result.get(0).getSeller_id();
        TypedQuery<DocumentOfTravel> query = em.createQuery("SELECT d FROM DocumentOfTravel d WHERE d.emission_date BETWEEN :date1 AND :date2", DocumentOfTravel.class);
        query.setParameter("date1", date1);
        query.setParameter("date2", date2);
        return query.getResultList().stream().filter(document -> document.getShop().getSeller_id() == sellerId).toList();
    }

    public List<DocumentOfTravel> findSubByTime(LocalDateTime date1, LocalDateTime date2, String name) {
        List<Shop> result = findSoldByShop(name);
        UUID sellerId = result.get(0).getSeller_id();
        TypedQuery<DocumentOfTravel> query = em.createQuery("SELECT d FROM DocumentOfTravel d WHERE d.emission_date BETWEEN :date1 AND :date2", DocumentOfTravel.class);
        query.setParameter("date1", date1);
        query.setParameter("date2", date2);
        return query.getResultList().stream().filter(document -> document.getShop().getSeller_id() == sellerId).toList();
    }

}
