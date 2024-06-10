package bw4t6.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "cards")
public class Card {

    @Id
    @ManyToOne
    @JoinColumn(name = "card_id", nullable = false)
    private User user;


    @OneToMany(mappedBy = "user")  //da cambiare poi
    private List<Subscription> subscriptionList;
    private LocalDate expired_date;

    public Card(){}

    public Card(User user, LocalDate expired_date) {
        this.user = user;
        this.expired_date = expired_date;
    }

    public User getUser() {
        return user;
    }


    public List<Subscription> getSubscriptionList() {
        return subscriptionList;
    }

    public void setSubscriptionList(List<Subscription> subscriptionList) {
        this.subscriptionList = subscriptionList;
    }

    public LocalDate getExpired_date() {
        return expired_date;
    }

    public void setExpired_date(LocalDate expired_date) {
        this.expired_date = expired_date;
    }

    @Override
    public String toString() {
        return "Card{" +
                "user=" + user +
                ", subscriptionList=" + subscriptionList +
                ", expired_date=" + expired_date +
                '}';
    }
}