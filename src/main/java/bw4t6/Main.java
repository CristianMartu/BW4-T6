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
import java.util.*;

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

    public static Vehicle createRandomVehicle() {
        boolean vehicle_type = new Random().nextBoolean();
        Faker faker = new Faker();
        if (vehicle_type) {
            return new Vehicle(new Random().nextInt(40, 65), new Random().nextBoolean(), VehicleType.AUTOBUS);

        } else {
            return new Vehicle(new Random().nextInt(150, 200), new Random().nextBoolean(), VehicleType.TRAM);

        }
    }

    public static Seller createRandomSeller() {
        boolean sellerType = new Random().nextBoolean();
        AutomaticSellerState[] automaticSellerStates = {AutomaticSellerState.IN_SERVICE, AutomaticSellerState.OUT_OF_SERVICE};
        Faker faker = new Faker();
        String company_name = faker.company().name();
        String emission_point = faker.address().cityName();
        if (sellerType) return new Seller(emission_point, company_name);
        else return new AutomaticSeller(emission_point, company_name, automaticSellerStates[new Random().nextInt(2)]);
    }


    public static <T> List<T> createRandomList(int quantity, String type) {
        List<T> list = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            if (Objects.equals(type, "user")) list.add((T) createRandomUser());
            if (Objects.equals(type, "vehicle")) list.add((T) createRandomVehicle());
            if (Objects.equals(type, "seller")) list.add((T) createRandomSeller());
        }
        return list;
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

/*        List<User> userList = createRandomList(10, "user");
        userList.forEach(userDAO::save);

        List<Vehicle> vehicleList = createRandomList(10, "vehicle");
        vehicleList.forEach(vehicleDAO::save);

        List<Seller> sellerList = createRandomList(10, "seller");
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
 */

        User aldo = new User("10c5a836-9c63-4738-8592-f2252cdfc9a8", "Aldo", "Baglio", LocalDate.parse("1978-05-13"));
        User giovanni = new User("37d98088-3f68-47ec-81e1-0beeea7a3bcb", "Giovanni", "Storti", LocalDate.parse("1967-03-05"));
        User giacomo = new User("93db0c71-32ba-4f18-8a3e-c45e721d5dea", "Giacomo", "Poretti", LocalDate.parse("1960-02-02"));

        Seller milano = new Seller("53f0541e-5ca2-478e-9eb7-7efc19515718", "Milano", "Tabaccheria del corso");
        Seller roma = new AutomaticSeller("6ff3e06f-4a78-45f1-b69d-43bb59adf6e9", "Roma", "La sigaretta d'oro", AutomaticSellerState.IN_SERVICE);
        Seller genova = new Seller("96bf3515-6e82-4ecd-8a4a-9db2af182cf5", "Genova", "Angolo del fumo");
        Seller cagliari = new AutomaticSeller("b717738a-5bf9-4971-adce-c705d3b59232", "Cagliari", "Tabacchi e sapori", AutomaticSellerState.OUT_OF_SERVICE);

        Vehicle autobus = new Vehicle("1c150e5b-ab0d-4827-b7d2-2fe111399c65", 60, true, VehicleType.AUTOBUS);
        Vehicle tram = new Vehicle("87fd6baf-3ce7-4dce-84f0-abcf93ff0316", 150, true, VehicleType.TRAM);
        Vehicle autobus2 = new Vehicle("aeba9b8e-fc5e-43fd-910c-a8f739f0593d", 55, false, VehicleType.AUTOBUS);
        Vehicle tram2 = new Vehicle("fc885652-f181-4c0a-97cb-b24c9035fd41", 160, false, VehicleType.TRAM);

        Ticket ticketAldo = new Ticket(LocalDate.of(2024, 9, 9), 1.5, milano, true, aldo, autobus);
        Ticket ticketGiacomo = new Ticket(LocalDate.of(2024, 9, 9), 1.5, roma, true, giacomo, tram);
        Ticket ticketGiovanni = new Ticket(LocalDate.of(2024, 9, 9), 1.5, cagliari, true, giovanni, autobus);

        userDAO.save(giovanni);
        userDAO.save(giacomo);
        userDAO.save(aldo);
        sellerDAO.save(milano);
        sellerDAO.save(genova);
        sellerDAO.save(cagliari);
        sellerDAO.save(roma);
        vehicleDAO.save(autobus);
        vehicleDAO.save(autobus2);
        vehicleDAO.save(tram);
        vehicleDAO.save(tram2);
        docDAO.save(ticketGiacomo);
        docDAO.save(ticketAldo);
        docDAO.save(ticketGiovanni);
        
    }
}
