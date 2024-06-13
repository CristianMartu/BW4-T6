package bw4t6.entities.abstracts;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "shops")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Shop {
    @Id
    //@GeneratedValue // da riattivare dopo
    protected UUID seller_id;

    protected String emission_point;

    @OneToMany(mappedBy = "shop")
    protected List<DocumentOfTravel> documentOfTravels;

    public Shop() {
    }

    public Shop(String emission_point) {
        this.emission_point = emission_point;
    }

    public Shop(String seller_id, String emission_point) {
        this.seller_id = UUID.fromString(seller_id);
        this.emission_point = emission_point;
    }

    public UUID getSeller_id() {
        return seller_id;
    }


    public String getEmission_point() {
        return emission_point;
    }

    public void setEmission_point(String emission_point) {
        this.emission_point = emission_point;
    }

    public List<DocumentOfTravel> getDocumentOfTravels() {
        return documentOfTravels;
    }

    public void setDocumentOfTravels(List<DocumentOfTravel> documentOfTravels) {
        this.documentOfTravels = documentOfTravels;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "seller_id=" + seller_id +
                ", emission_point='" + emission_point + '\'' +
                ", documentOfTravels=" + documentOfTravels +
                '}';
    }
}
