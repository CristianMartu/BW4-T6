package bw4t6;

import bw4t6.dao.*;
import bw4t6.entities.*;
import bw4t6.enums.AutomaticSellerState;
import bw4t6.enums.VehicleType;
import com.github.javafaker.Faker;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Main {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("team_database");

    public static User createRandomUser() {
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        Date birthdayDate = faker.date().birthday();
        LocalDate birthday = birthdayDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        return new User(firstName, lastName, birthday);
    }

    public static Vehicle createRandomVehicle(Boolean vehicle_type) {
        Faker faker = new Faker();
        if (vehicle_type) {
            return new Vehicle(new Random().nextInt(40, 65), new Random().nextBoolean(), VehicleType.AUTOBUS);

        } else {
            return new Vehicle(new Random().nextInt(150, 200), new Random().nextBoolean(), VehicleType.TRAM);

        }
    }

    public static Seller createRandomSeller(Boolean sellerType) {
        AutomaticSellerState[] automaticSellerStates = {AutomaticSellerState.IN_SERVICE, AutomaticSellerState.OUT_OF_SERVICE};
        Faker faker = new Faker();
        String company_name = faker.company().name();
        String emission_point = faker.address().cityName();
        if (sellerType) return new Seller(emission_point, company_name);
        else return new AutomaticSeller(emission_point, company_name, automaticSellerStates[new Random().nextInt(2)]);
    }

    public static List<User> createRandomListUser(int quantity) {
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            userList.add(createRandomUser());
        }
        return userList;
    }

    public static List<Vehicle> createRandomListVehicle(int quantity) {
        List<Vehicle> vehicleList = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            vehicleList.add(createRandomVehicle(new Random().nextBoolean()));
        }
        return vehicleList;
    }

    public static List<Seller> createRandomListSeller(int quantity) {
        List<Seller> sellerList = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            sellerList.add(createRandomSeller(new Random().nextBoolean()));
        }
        return sellerList;
    }

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

        List<User> userList = createRandomListUser(10);
        userList.forEach(userDAO::save);

        List<Vehicle> vehicleList = createRandomListVehicle(10);
        vehicleList.forEach(vehicleDAO::save);

        List<Seller> sellerList = createRandomListSeller(10);
        sellerList.forEach(sellerDAO::save);


        try {
            Seller sellerRomeFromDb = sellerDAO.findById("494c15d6-ec2d-419d-873e-82283ace84c0");
            User tizianoFromDb = userDAO.findById("edeff83a-e7f9-4074-a453-3fa9e9ca0148");
            Vehicle busFromDb = vehicleDAO.findById("020088dd-2fff-473c-aa14-683df0a8c0db");
            Ticket ticket = new Ticket(LocalDate.of(2024, 9, 9), "Roma", 1.5, sellerRomeFromDb, true, tizianoFromDb, busFromDb);
            docDAO.save(ticket);
        } catch (RuntimeException error) {
            System.out.println(error.getMessage());
        }

    }
}
