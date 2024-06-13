package bw4t6.entities.abstracts;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "documents_of_travel")
@Inheritance(strategy = InheritanceType.JOINED)

public abstract class DocumentOfTravel {
    @Id
    @GeneratedValue
    protected UUID document_id;
    protected LocalDateTime emission_date;
    protected double price;

    @ManyToOne
    @JoinColumn(name = "emission_points", nullable = false)
    private Shop shop;

    public DocumentOfTravel() {
    }

    public DocumentOfTravel(LocalDateTime emission_date, double price, Shop shop) {
        this.emission_date = emission_date;
        this.price = price;
        this.shop = shop;
    }

    public DocumentOfTravel(LocalDateTime emission_date, Shop shop) {
        this.emission_date = emission_date;
        this.price = 0;
        this.shop = shop;
    }

    public DocumentOfTravel(double price, Shop shop) {
        this.emission_date = LocalDateTime.now();
        this.price = price;
        this.shop = shop;
    }

    public UUID getDocument_id() {
        return document_id;
    }

    public LocalDateTime getEmission_date() {
        return emission_date;
    }


}
