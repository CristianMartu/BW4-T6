package bw4t6.entities;


import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "sellers")
public class Seller {

    @Id
    @GeneratedValue
    private UUID seller_id;

    private String company_name;
    private String emission_point;


    public Seller() {}

    public Seller(String emission_point, String company_name) {
        this.emission_point = emission_point;
        this.company_name = company_name;
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
                '}';
    }
}
