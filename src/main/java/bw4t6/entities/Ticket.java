package bw4t6.entities;

import bw4t6.entities.abstracts.DocumentOfTravel;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@DiscriminatorValue("tickets")
public class Ticket extends DocumentOfTravel {


    @ManyToMany
    @JoinTable(
            name = "tickets_trips",
            joinColumns = @JoinColumn(name = "ticket_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "trip_id", nullable = false)
    )
    private List<Trip> trips;

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

    public Ticket(LocalDate emission_date, double price, Seller seller, Vehicle vehicle_ticket, Subscription subscription_ticket) {
        super(emission_date, price, seller);
        this.vehicle_ticket = vehicle_ticket;
        this.subscription_ticket = subscription_ticket;
        this.setState();
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

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }

    public Boolean getState() {
        return state;
    }

    public void setState() {
        if (this.subscription_ticket.getExpired_date().compareTo(LocalDate.now()) > 0) { // da modificare, controllare anche orario e minuti
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
                "trips=" + trips +
                ", state=" + state +
//                ", user=" + user +
                ", vehicle_ticket=" + vehicle_ticket +
                ", document_id=" + document_id +
                ", emission_date=" + emission_date +
                ", price=" + price +
                '}';
    }
}
