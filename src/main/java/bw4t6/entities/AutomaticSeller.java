package bw4t6.entities;


import bw4t6.enums.AutomaticSellerState;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue("automatic_sellers")
public class AutomaticSeller extends Seller{

    //private Boolean active;

    @Enumerated(EnumType.STRING)
    private AutomaticSellerState state;

    public AutomaticSeller () {}

    public AutomaticSeller(String emission_point, String company_name, AutomaticSellerState state) {
        super(emission_point, company_name);
        this.state = state;
    }

    public AutomaticSellerState getState() {
        return state;
    }

    public void setState(AutomaticSellerState state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "AutomaticSeller{" +
                "state=" + state +
                '}';
    }

}
