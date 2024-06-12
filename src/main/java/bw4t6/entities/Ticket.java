package bw4t6.entities;

import bw4t6.entities.abstracts.DocumentOfTravel;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;

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

    public Ticket() {
    }

    public Ticket(LocalDate emission_date, double price, Seller seller, Boolean state, User user, Vehicle vehicle_ticket) {
        super(emission_date, price, seller);
        this.state = state;
        this.user = user;
        this.vehicle_ticket = vehicle_ticket;
    }

    public Ticket(LocalDate emission_date, Seller seller, Vehicle vehicle_ticket, Subscription subscription_ticket, Boolean state) {
        super(emission_date, seller);
        this.vehicle_ticket = vehicle_ticket;
        this.subscription_ticket = subscription_ticket;
        this.state = state;
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

    public void setState(Boolean state) {
        this.state = state;
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
