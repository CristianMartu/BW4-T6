package bw4t6.entities;


import bw4t6.entities.abstracts.DocumentOfTravel;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@DiscriminatorValue("tickets")
public class Ticket extends DocumentOfTravel {


    @Id
    @GeneratedValue
    private UUID id;

    @ManyToMany
    @JoinTable (name = "tickets_trips", joinColumns = @JoinColumn(name = "ticket_id", nullable = false), inverseJoinColumns = @JoinColumn(name = "trip_id", nullable = false))
    private List<Trip> trips;
    private Boolean state;//checked unchecked

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle_ticket;

    public Ticket () {}

    public Ticket(LocalDate emission_date, String emission_point, double price, Boolean state, User user,Vehicle vehicle ) { // price da modificare
        super(emission_date, emission_point, price);
        this.state = state;
        this.user = user;
        this.vehicle_ticket = vehicle;
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

    public UUID getId() {
        return id;
    }


    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", trips=" + trips +
                ", state=" + state +
                ", user=" + user +
                ", vehicle=" + vehicle_ticket +
                ", id=" + id +
                ", emission_date=" + emission_date +
                ", emission_point='" + emission_point + '\'' +
                ", price=" + price +
                '}';
    }
}
