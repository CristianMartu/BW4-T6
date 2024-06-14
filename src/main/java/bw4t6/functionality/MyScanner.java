package bw4t6.functionality;

import bw4t6.dao.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class MyScanner {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("team_database");

    public static void startScanner() {
        System.out.println("Hello World!");
        EntityManager em = emf.createEntityManager();
        CardDAO cardDAO = new CardDAO(em);
        DistanceDAO distanceDAO = new DistanceDAO(em);
        DocumentOfTravelDAO docDAO = new DocumentOfTravelDAO(em);
        MaintenanceDAO maintenanceDAO = new MaintenanceDAO(em);
        ShopDAO shopDAO = new ShopDAO(em);
        TripDAO tripDAO = new TripDAO(em);
        UserDAO userDAO = new UserDAO(em);
        VehicleDAO vehicleDAO = new VehicleDAO(em);

    }
}
