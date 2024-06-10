package bw4t6.entities.abstracts;

import bw4t6.entities.Seller;
import bw4t6.entities.User;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@Inheritance(strategy = InheritanceType.JOINED)
public abstract class DocumentOfTravel {

    @Id
    @GeneratedValue
    protected UUID id;
    protected LocalDate emission_date;
    protected String emission_point;
    protected double price;
@ManyToOne
@JoinColumn(name = "emission_points", nullable = false)
private Seller seller;
    public DocumentOfTravel() {}

    public DocumentOfTravel(LocalDate emission_date, String emission_point, double price) {
        this.emission_date = emission_date;
        this.emission_point = emission_point;
        this.price = price;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    @Override
    public String toString() {
        return "DocumentOfTravel{" +
                "seller=" + seller +
                '}';
    }
}
