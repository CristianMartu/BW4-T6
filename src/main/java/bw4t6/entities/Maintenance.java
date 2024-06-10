package bw4t6.entities;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "maintenances")
public class Maintenance {

    @Id
    @GeneratedValue
    @ManyToOne
    @JoinColumn(name = "vehicle_id", nullable = false)
    private UUID vehicle_maintenance;

    private LocalDate maintenance_start;
    private LocalDate maintenance_end;

    public Maintenance() {}

    public Maintenance(LocalDate maintenance_start, LocalDate maintenance_end) {
        this.maintenance_start = maintenance_start;
        this.maintenance_end = maintenance_end;
    }

    public UUID getVehicle_maintenance() {
        return vehicle_maintenance;
    }

    public LocalDate getMaintenance_start() {
        return maintenance_start;
    }

    public void setMaintenance_start(LocalDate maintenance_start) {
        this.maintenance_start = maintenance_start;
    }

    public LocalDate getMaintenance_end() {
        return maintenance_end;
    }

    public void setMaintenance_end(LocalDate maintenance_end) {
        this.maintenance_end = maintenance_end;
    }

    @Override
    public String toString() {
        return "Maintenance{" +
                "vehicle_maintenance=" + vehicle_maintenance +
                ", maintenance_start=" + maintenance_start +
                ", maintenance_end=" + maintenance_end +
                '}';
    }
}
