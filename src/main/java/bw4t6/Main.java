package bw4t6;

import bw4t6.dao.*;
import bw4t6.entities.Seller;
import bw4t6.entities.Ticket;
import bw4t6.entities.User;
import bw4t6.entities.Vehicle;
import bw4t6.enums.VehicleType;
import com.github.javafaker.Faker;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Main {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("team_database");

    public static void main(String[] args) {
        System.out.println("Hello World!");
        EntityManager em = emf.createEntityManager();
        CardDAO cardDAO = new CardDAO(em);
        DocumentOfTravelDAO docDAO = new DocumentOfTravelDAO(em);
        MaintenanceDAO maintenanceDAO = new MaintenanceDAO(em);
        SellerDAO sellerDAO = new SellerDAO(em);
        TripDAO tripDAO = new TripDAO(em);
        UserDAO userDAO = new UserDAO(em);
        VehicleDAO vehicleDAO = new VehicleDAO(em);

        Seller sellerRome = new Seller("Roma","tabacchi roma srl");

        Seller sellerRomeFromDb = sellerDAO.findById("494c15d6-ec2d-419d-873e-82283ace84c0");
        User tizianoFromDb = userDAO.findById("edeff83a-e7f9-4074-a453-3fa9e9ca0148");
        Vehicle busFromDb = vehicleDAO.findById("020088dd-2fff-473c-aa14-683df0a8c0db");
       Ticket ticket = new Ticket(LocalDate.of(2024,9,9),"Roma",1.5,sellerRomeFromDb,true,tizianoFromDb,busFromDb);
        docDAO.save(ticket);
       //        sellerDAO.save(sellerRome);


        Vehicle bus1 = new Vehicle(30,true, VehicleType.AUTOBUS);
//        vehicleDAO.save(bus1);








        User tiziano = new User("Tiziano","Sanse", LocalDate.of(2000,10,7));
//        userDAO.save(tiziano);
        Faker faker = new Faker();




        // Generare dati falsi
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        Date birthdayDate = faker.date().birthday();
        LocalDate birthday = birthdayDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();


        User user = new User(firstName, lastName, birthday);
//        userDAO.save(user);

    }
}
