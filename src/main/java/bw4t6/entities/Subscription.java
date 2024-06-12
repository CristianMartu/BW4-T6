package bw4t6.entities;

import bw4t6.entities.abstracts.DocumentOfTravel;
import bw4t6.enums.SubscriptionState;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@DiscriminatorValue("subscriptions")
public class Subscription extends DocumentOfTravel {


    @Enumerated(EnumType.STRING)
    private SubscriptionState state;
    @ManyToOne
    @JoinColumn(name = "card_id", nullable = false)
    private Card card;
    private LocalDate expired_date;

    public Subscription() {
    }

    public Subscription(LocalDate emission_date, double price, Seller seller, SubscriptionState state, Card card, LocalDate expired_date) {
        super(emission_date, price, seller);
        this.state = state;
        this.card = card;
        this.expired_date = expired_date;
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
                "state=" + state +
                ", card=" + card +
                ", expired_date=" + expired_date +
                ", document_id=" + document_id +
                ", emission_date=" + emission_date +
                ", price=" + price +
                '}';
    }
}
