package bw4t6.entities;


import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "trips")
public class Trip {
    @Id
    @GeneratedValue
    private UUID trip_id;
    @ManyToOne
    @JoinColumn(name = "distance_id",nullable = false)
    private Distance distance_trip;

    private double average_time;
    private String starting_zone;
    private String finish_zone;

    public Trip() {}

    public Trip(Distance distance_trip, double average_time, String finish_zone, String starting_zone) {
        this.distance_trip = distance_trip;
        this.average_time = average_time;
        this.finish_zone = finish_zone;
        this.starting_zone = starting_zone;
    }

    public UUID getTrip_id() {
        return trip_id;
    }

    public Distance getDistance_trip() {
        return distance_trip;
    }

    public double getAverage_time() {
        return average_time;
    }

    public void setAverage_time(double average_time) {
        this.average_time = average_time;
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
                "trip_id=" + trip_id +
                ", distance_trip=" + distance_trip +
                ", average_time=" + average_time +
                ", starting_zone='" + starting_zone + '\'' +
                ", finish_zone='" + finish_zone + '\'' +
                '}';
    }
}
