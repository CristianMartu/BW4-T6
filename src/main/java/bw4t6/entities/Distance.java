package bw4t6.entities;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Id;


@Entity
@Table(name = "distances")
public class Distance {

    @Id
    @GeneratedValue
    private UUID distance_id;

    private int number_of_trip;
    private double time_of_travel;

    @ManyToOne
    @JoinColumn (name = "vehicle_id", nullable = false)
    private Vehicle vehicle_distance;

   @OneToMany (mappedBy = "distance_trip")
    private List<Trip> trip_list;

   public Distance(){

   }

    public Distance(Double time_of_travel,Vehicle vehicle_distance) {
        this.number_of_trip = 0;
        this.time_of_travel = time_of_travel;
        this.vehicle_distance = vehicle_distance;
    }

    public UUID getDistance_id() {
        return distance_id;
    }

    public int getNumber_of_trip() {
        return number_of_trip;
    }

    public void setNumber_of_trip(int number_of_trip) {
        this.number_of_trip = number_of_trip;
    }

    public double getTime_of_travel() {
        return time_of_travel;
    }

    public void setTime_of_travel(double time_of_travel) {
        this.time_of_travel = time_of_travel;
    }

    public Vehicle getVehicle_distance() {
        return vehicle_distance;
    }

    public void setVehicle_distance(Vehicle vehicle_distance) {
        this.vehicle_distance = vehicle_distance;
    }

    public List<Trip> getTrip_list() {
        return trip_list;
    }

    public void setTrip_list(List<Trip> trip_list) {
        this.trip_list = trip_list;
    }

    @Override
    public String toString() {
        return "Distance{" +
                "distance_id=" + distance_id +
                ", number_of_trip=" + number_of_trip +
                ", time_of_travel=" + time_of_travel +
                ", vehicle_distance=" + vehicle_distance +
                '}';
    }
}
