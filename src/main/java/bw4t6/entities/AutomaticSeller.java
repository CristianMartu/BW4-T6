package bw4t6.entities;


import bw4t6.entities.abstracts.Shop;
import bw4t6.enums.AutomaticSellerState;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
@DiscriminatorValue("automatic_sellers")
public class AutomaticSeller extends Shop {

    @Enumerated(EnumType.STRING)
    private AutomaticSellerState state;

    public AutomaticSeller() {
    }

    public AutomaticSeller(String emission_point, AutomaticSellerState state) {
        super(emission_point);
        this.state = state;
    }

    public AutomaticSeller(String seller_id, String emission_point, AutomaticSellerState state) {
        super(seller_id, emission_point);
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
