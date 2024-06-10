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
    private Card card;
    private LocalDate expired_date;

    public Subscription() {
    }

    public Subscription(LocalDate emission_date, String emission_point, double price, SubscriptionState state, Card card, LocalDate expired_date) {
        super(emission_date, emission_point, price);
        this.state = state;
        this.card = card;
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

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
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
                ", card=" + card +
                ", expired_date=" + expired_date +
                ", emission_date=" + emission_date +
                ", emission_point='" + emission_point + '\'' +
                ", price=" + price +
                '}';
    }
}
