package bw4t6.entities.abstracts;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@Inheritance(strategy = InheritanceType.JOINED)
public abstract class DocumentOfTravel {

    @Id
    @GeneratedValue
    protected UUID id;
    protected LocalDate emission_date;
    protected String emission_point;
    protected double price;

    public DocumentOfTravel() {}

    public DocumentOfTravel(LocalDate emission_date, String emission_point, double price) {
        this.emission_date = emission_date;
        this.emission_point = emission_point;
        this.price = price;
    }
}
