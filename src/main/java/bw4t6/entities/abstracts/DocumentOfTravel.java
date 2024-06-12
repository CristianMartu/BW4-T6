package bw4t6.entities.abstracts;

import bw4t6.entities.Seller;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "documents_of_travel")
@Inheritance(strategy = InheritanceType.JOINED)

public abstract class DocumentOfTravel {
    @Id
    @GeneratedValue
    protected UUID document_id;
    protected LocalDate emission_date;
    protected double price;

    @ManyToOne
    @JoinColumn(name = "emission_points", nullable = false)
    private Seller seller;

    public DocumentOfTravel() {
    }

    public DocumentOfTravel(LocalDate emission_date, double price, Seller seller) {
        this.emission_date = emission_date;
        this.price = price;
        this.seller = seller;
    }

    public UUID getDocument_id() {
        return document_id;
    }

    public LocalDate getEmission_date() {
        return emission_date;
    }
}
