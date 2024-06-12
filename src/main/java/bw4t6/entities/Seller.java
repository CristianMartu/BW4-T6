package bw4t6.entities;


import bw4t6.entities.abstracts.DocumentOfTravel;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "sellers")
@Inheritance(strategy = InheritanceType.JOINED)
public class Seller {

    @Id
    //@GeneratedValue // da riattivare dopo
    private UUID seller_id;

    private String company_name;
    private String emission_point;

    @OneToMany(mappedBy = "seller")
    private List<DocumentOfTravel> documentOfTravels;


    public Seller() {
    }

    public Seller(String emission_point, String company_name) {
        this.emission_point = emission_point;
        this.company_name = company_name;
    }

    public Seller(String seller_id, String emission_point, String company_name) {
        this.seller_id = UUID.fromString(seller_id);
        this.emission_point = emission_point;
        this.company_name = company_name;
    }

    public List<DocumentOfTravel> getDocumentOfTravels() {
        return documentOfTravels;
    }

    public void setDocumentOfTravels(List<DocumentOfTravel> documentOfTravels) {
        this.documentOfTravels = documentOfTravels;
    }

    public UUID getSeller_id() {
        return seller_id;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getEmission_point() {
        return emission_point;
    }

    public void setEmission_point(String emission_point) {
        this.emission_point = emission_point;
    }

    @Override
    public String toString() {
        return "Seller{" +
                "seller_id=" + seller_id +
                ", company_name='" + company_name + '\'' +
                ", emission_point='" + emission_point + '\'' +
                ", documentOfTravels=" + documentOfTravels +
                '}';
    }
}
