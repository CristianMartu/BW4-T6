package bw4t6;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("team_database");

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}
