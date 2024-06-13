package bw4t6.entities;


import bw4t6.entities.abstracts.Shop;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("sellers")
public class Seller extends Shop {

    private String company_name;

    public Seller() {
    }

    public Seller(String emission_point, String company_name) {
        super(emission_point);
        this.company_name = company_name;
    }

    public Seller(String seller_id, String emission_point, String company_name) {
        super(seller_id, emission_point);
        this.company_name = company_name;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    @Override
    public String toString() {
        return "Seller{" +
                "company_name='" + company_name + '\'' +
                '}';
    }
}
