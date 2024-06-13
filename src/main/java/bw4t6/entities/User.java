package bw4t6.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {

    @Id
    //@GeneratedValue  // da riattivare dopo
    private UUID card_id;

    @OneToMany(mappedBy = "user")
    private List<Card> cards;

    private String name;
    private String surname;
    private LocalDate date_of_birth;


    public User() {
    }

    public User(String name, String surname, LocalDate date_of_birth) {
        this.name = name;
        this.surname = surname;
        this.date_of_birth = date_of_birth;
    }

    public User(String card_id, String name, String surname, LocalDate date_of_birth) {
        this.card_id = UUID.fromString(card_id);
        this.name = name;
        this.surname = surname;
        this.date_of_birth = date_of_birth;
    }

    public UUID getCard_id() {
        return card_id;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getBirthday() {
        return date_of_birth;
    }

    public void setBirthday(LocalDate date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    @Override
    public String toString() {
        return "User{" +
                "card_id=" + card_id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", date_of_birth=" + date_of_birth +
                '}';
    }
}
