package bw4t6.entities;

import bw4t6.entities.abstracts.DocumentOfTravel;
import bw4t6.enums.SubscriptionState;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Locale;
import java.util.UUID;

@Entity
@DiscriminatorValue("subscriptions")
public class Subscription extends DocumentOfTravel {

    @Id
    @GeneratedValue
    private UUID id;
    @Enumerated(EnumType.STRING)
    private SubscriptionState state;
    @ManyToOne
    @JoinColumn(name = "card_id", nullable = false)
    private User user;
    private LocalDate expired_date;

    public Subscription() {
    }

    public Subscription(LocalDate emission_date, String emission_point, double price, SubscriptionState state, User user, LocalDate expired_date) {
        super(emission_date, emission_point, price);
        this.state = state;
        this.user = user;
        this.expired_date = expired_date;
    }

    public UUID getId() {
        return id;
    }

    public SubscriptionState getState() {
        return state;
    }

    public void setState(SubscriptionState state) {
        this.state = state;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getExpired_date() {
        return expired_date;
    }

    public void setExpired_date(LocalDate expired_date) {
        this.expired_date = expired_date;
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "id=" + id +
                ", state=" + state +
                ", user=" + user +
                ", expired_date=" + expired_date +
                ", id=" + id +
                ", emission_date=" + emission_date +
                ", emission_point='" + emission_point + '\'' +
                ", price=" + price +
                '}';
    }
}
