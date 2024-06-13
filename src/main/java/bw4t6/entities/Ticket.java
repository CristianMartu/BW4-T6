package bw4t6.entities;

import bw4t6.entities.abstracts.DocumentOfTravel;
import bw4t6.entities.abstracts.Shop;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("tickets")
public class Ticket extends DocumentOfTravel {


    private Boolean state;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle_ticket;

    @ManyToOne
    @JoinColumn(name = "subscription_id")
    private Subscription subscription_ticket;

    private int duration_validity;
    private LocalDateTime expired_date;

    public Ticket() {
    }

    public Ticket(LocalDateTime emission_date, Shop shop, Vehicle vehicle_ticket, Subscription subscription_ticket, Integer duration_validity) {
        super(emission_date, shop);
        this.vehicle_ticket = vehicle_ticket;
        this.subscription_ticket = subscription_ticket;
        this.duration_validity = duration_validity;
        this.setExpired_date();
        this.setState();
    }

    public Ticket(LocalDateTime emission_date, double price, Shop shop, User user, Vehicle vehicle_ticket, int duration_validity) {
        super(price, shop);
        this.emission_date = emission_date;
        this.vehicle_ticket = vehicle_ticket;
        this.user = user;
        this.duration_validity = duration_validity;
        this.setExpired_date();
        this.setState();
    }


    public int getDuration_validity() {
        return duration_validity;
    }

    public void setDuration_validity(int duration_validity) {
        this.duration_validity = duration_validity;
    }

    public LocalDateTime getExpired_date() {
        return expired_date;
    }

    public void setExpired_date() {
        this.expired_date = this.emission_date.plusMinutes(this.duration_validity);
    }

    public Vehicle getVehicle_ticket() {
        return vehicle_ticket;
    }

    public void setVehicle_ticket(Vehicle vehicle_ticket) {
        this.vehicle_ticket = vehicle_ticket;
    }

    public Subscription getSubscription_ticket() {
        return subscription_ticket;
    }

    public void setSubscription_ticket(Subscription subscription_ticket) {
        this.subscription_ticket = subscription_ticket;
    }

    public Boolean getState() {
        return state;
    }

    public void setState() {
        if (LocalDateTime.now().isBefore(this.expired_date)) {
            this.state = true;
        } else this.state = false;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Vehicle getVehicle() {
        return vehicle_ticket;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle_ticket = vehicle;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                ", state=" + state +
//                ", user=" + user +
                ", vehicle_ticket=" + vehicle_ticket +
                ", document_id=" + document_id +
                ", emission_date=" + emission_date +
                ", price=" + price +
                '}';
    }
}
