package bw4t6.entities;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "trips")
public class Trip {
    @Id
    @ManyToOne
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle_trip;

    @ManyToMany(mappedBy = "trips")
    private List<Ticket> tickets;


    private double average_time;
    private String starting_zone;
    private String finish_zone;

    public Trip() {}

    public Trip(Vehicle vehicle,  double average_time, String starting_zone, String finish_zone) {
        this.vehicle_trip = vehicle;
        this.average_time = average_time;
        this.starting_zone = starting_zone;
        this.finish_zone = finish_zone;
    }

    public double getAverage_time() {
        return average_time;
    }

    public void setAverage_time(double average_time) {
        this.average_time = average_time;
    }

    public Vehicle getVehicle() {
        return vehicle_trip;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle_trip = vehicle;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public String getStarting_zone() {
        return starting_zone;
    }

    public void setStarting_zone(String starting_zone) {
        this.starting_zone = starting_zone;
    }

    public String getFinish_zone() {
        return finish_zone;
    }

    public void setFinish_zone(String finish_zone) {
        this.finish_zone = finish_zone;
    }


    @Override
    public String toString() {
        return "Trip{" +
                "vehicle=" + vehicle_trip +
                ", tickets=" + tickets +
                ", average_time=" + average_time +
                ", starting_zone='" + starting_zone + '\'' +
                ", finish_zone='" + finish_zone + '\'' +
                '}';
    }
}
