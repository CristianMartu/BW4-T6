package bw4t6.entities;

import bw4t6.enums.VehicleType;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "vehicles")
public class Vehicle {

    @Id
    @GeneratedValue
    private UUID vehicle_id;

    private int capacity;
    private boolean state;
    @OneToMany(mappedBy = "vehicle_maintenance")
    private List<Maintenance> vehicle_maintenances;

    @OneToMany(mappedBy = "vehicle_trip")
    private List<Trip> vehicle_trips;

    @OneToMany(mappedBy = "vehicle_ticket")
    private List<Ticket> vehicle_tickets;

    @Enumerated(EnumType.STRING)
    private VehicleType vehicle_type;

    public Vehicle () {}

    public Vehicle(int capacity, boolean state, VehicleType vehicle_type) {
        this.capacity = capacity;
        this.state = state;
        this.vehicle_type = vehicle_type;
    }

    public UUID getVehicle_id() {
        return vehicle_id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public List<Maintenance> getVehicle_maintenances() {
        return vehicle_maintenances;
    }

    public void setVehicle_maintenances(List<Maintenance> vehicle_maintenances) {
        this.vehicle_maintenances = vehicle_maintenances;
    }

    public List<Trip> getVehicle_trips() {
        return vehicle_trips;
    }

    public void setVehicle_trips(List<Trip> vehicle_trips) {
        this.vehicle_trips = vehicle_trips;
    }

    public List<Ticket> getVehicle_tickets() {
        return vehicle_tickets;
    }

    public void setVehicle_tickets(List<Ticket> vehicle_tickets) {
        this.vehicle_tickets = vehicle_tickets;
    }

    public VehicleType getVehicle_type() {
        return vehicle_type;
    }

    public void setVehicle_type(VehicleType vehicle_type) {
        this.vehicle_type = vehicle_type;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicle_id=" + vehicle_id +
                ", capacity=" + capacity +
                ", state=" + state +
                ", vehicle_type=" + vehicle_type +
                '}';
    }
}
