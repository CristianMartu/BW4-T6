package bw4t6.functionality;

import bw4t6.dao.*;
import bw4t6.entities.*;
import bw4t6.entities.abstracts.Shop;
import bw4t6.enums.AutomaticSellerState;
import bw4t6.enums.SubscriptionState;
import bw4t6.enums.VehicleType;
import com.github.javafaker.Faker;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

public class DbCreation {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("team_database");

    private static final User aldo = new User("10c5a836-9c63-4738-8592-f2252cdfc9a8", "Aldo", "Baglio", LocalDate.parse("1978-05-13"));
    private static final User giovanni = new User("37d98088-3f68-47ec-81e1-0beeea7a3bcb", "Giovanni", "Storti", LocalDate.parse("1967-03-05"));
    private static final User giacomo = new User("93db0c71-32ba-4f18-8a3e-c45e721d5dea", "Giacomo", "Poretti", LocalDate.parse("1960-02-02"));

    private static final Shop milano = new Seller("53f0541e-5ca2-478e-9eb7-7efc19515718", "Milano", "La sigaretta d'oro");
    private static final Shop roma = new AutomaticSeller("6ff3e06f-4a78-45f1-b69d-43bb59adf6e9", "Roma", AutomaticSellerState.IN_SERVICE);
    private static final Shop genova = new Seller("96bf3515-6e82-4ecd-8a4a-9db2af182cf5", "Genova", "Tabacchi e sapori");
    private static final Shop cagliari = new AutomaticSeller("b717738a-5bf9-4971-adce-c705d3b59232", "Cagliari", AutomaticSellerState.OUT_OF_SERVICE);

    private static final Vehicle autobus = new Vehicle("1c150e5b-ab0d-4827-b7d2-2fe111399c65", 60, true, VehicleType.AUTOBUS);
    private static final Vehicle tram = new Vehicle("87fd6baf-3ce7-4dce-84f0-abcf93ff0316", 150, true, VehicleType.TRAM);
    private static final Vehicle autobus2 = new Vehicle("aeba9b8e-fc5e-43fd-910c-a8f739f0593d", 55, false, VehicleType.AUTOBUS);
    private static final Vehicle tram2 = new Vehicle("fc885652-f181-4c0a-97cb-b24c9035fd41", 160, false, VehicleType.TRAM);

    private static final EntityManager em = emf.createEntityManager();
    private static final CardDAO cardDAO = new CardDAO(em);
    private static final DistanceDAO distanceDAO = new DistanceDAO(em);
    private static final DocumentOfTravelDAO docDAO = new DocumentOfTravelDAO(em);
    private static final MaintenanceDAO maintenanceDAO = new MaintenanceDAO(em);
    private static final ShopDAO shopDAO = new ShopDAO(em);
    private static final TripDAO tripDAO = new TripDAO(em);
    private static final UserDAO userDAO = new UserDAO(em);
    private static final VehicleDAO vehicleDAO = new VehicleDAO(em);

    public static void firstRun() {
        System.out.println("Hello World!");

        Ticket ticketAldo = new Ticket(LocalDateTime.now(), 1.5, milano, aldo, autobus, 90);
        Ticket ticketGiacomo = new Ticket(LocalDateTime.now().minusDays(5), 1.8, roma, giacomo, tram, 120);
        Ticket ticketGiovanni = new Ticket(LocalDateTime.now().minusMonths(1), 2, cagliari, giovanni, autobus, 45);

        Card cardAldo = new Card(aldo, LocalDate.now().plusYears(1));
        Card cardGiovanni = new Card(giovanni, LocalDate.parse("2023-02-23"));

        Subscription subscriptionAldo = new Subscription(LocalDateTime.now(), 270, milano, SubscriptionState.WEEKLY, cardAldo);
        Subscription subscriptionGiovanni = new Subscription(LocalDateTime.now().minusMonths(5), 270, milano, SubscriptionState.MONTHLY, cardGiovanni);

        // Extra aggiungere controllo scadenza card per creazione ticket
        Ticket ticketAldoByCard = new Ticket(LocalDateTime.now(), milano, autobus, subscriptionAldo, 90);
        Ticket ticketGiovanniByCard = new Ticket(LocalDateTime.now().minusDays(1), roma, autobus, subscriptionGiovanni, 90);

        Distance percorrenza1tram = new Distance(45.0, tram);
        Distance percorrenza2tram = new Distance(125.0, tram);
        Distance percorrenza3tram = new Distance(15.0, tram);
        Distance percorrenza1Autobus = new Distance(100.0, autobus);
        Distance percorrenza2Autobus = new Distance(190.0, autobus);
        Distance percorrenza3Autobus = new Distance(90.0, autobus);
        Distance percorrenza1Tram2 = new Distance(23.0, tram2);
        Distance percorrenza2Tram2 = new Distance(30.0, tram2);
        Distance percorrenza1Autobus2 = new Distance(405.0, autobus2);
        Distance percorrenza2Autobus2 = new Distance(65.0, autobus2);
        Distance percorrenza3Autobus2 = new Distance(245.0, autobus2);
        Distance percorrenza4Autobus2 = new Distance(165.0, autobus2);

        userDAO.save(giovanni);
        userDAO.save(giacomo);
        userDAO.save(aldo);
        shopDAO.save(milano);
        shopDAO.save(genova);
        shopDAO.save(cagliari);
        shopDAO.save(roma);
        vehicleDAO.save(autobus);
        vehicleDAO.save(autobus2);
        vehicleDAO.save(tram);
        vehicleDAO.save(tram2);

        docDAO.save(ticketGiacomo);
        docDAO.save(ticketAldo);
        docDAO.save(ticketGiovanni);

        cardDAO.save(cardAldo);
        cardDAO.save(cardGiovanni);

        docDAO.save(subscriptionAldo);
        docDAO.save(subscriptionGiovanni);

        docDAO.save(ticketAldoByCard);
        docDAO.save(ticketGiovanniByCard);

        distanceDAO.save(percorrenza1tram);
        distanceDAO.save(percorrenza2tram);
        distanceDAO.save(percorrenza3tram);
        distanceDAO.save(percorrenza1Autobus);
        distanceDAO.save(percorrenza2Autobus);
        distanceDAO.save(percorrenza3Autobus);
        distanceDAO.save(percorrenza1Tram2);
        distanceDAO.save(percorrenza2Tram2);
        distanceDAO.save(percorrenza1Autobus2);
        distanceDAO.save(percorrenza2Autobus2);
        distanceDAO.save(percorrenza3Autobus2);
        distanceDAO.save(percorrenza4Autobus2);


        maintenanceDAO.save(new Maintenance("davide ha rotto l'autobus (rotto le ruote)", autobus2, null, LocalDate.now().minusDays(18)));
        maintenanceDAO.save(new Maintenance("Marina ha dato fuoco agli specchietti", autobus2, LocalDate.now().minusDays(3), LocalDate.now().minusDays(10)));
        maintenanceDAO.save(new Maintenance("Luca come autista fa schifo", tram, LocalDate.now().minusDays(50), LocalDate.now().minusDays(60)));
        maintenanceDAO.save(new Maintenance("Cristian non sapeva la strada ed è finito in un fosso", tram2, LocalDate.now().minusDays(30), LocalDate.now().minusDays(15)));
        maintenanceDAO.save(new Maintenance("Federico ha investito Davide e ha danneggiato la carrozzeria", autobus, null, LocalDate.now().minusDays(10)));

        tripDAO.save(new Trip(percorrenza1tram, 60, "Latina", "Roma"));
        tripDAO.save(new Trip(percorrenza2tram, 40, "Latina", "Aprilia"));
        tripDAO.save(new Trip(percorrenza3tram, 50, "Latina", "Pomezia"));
        tripDAO.save(new Trip(percorrenza1Autobus, 120, "Cagliari", "Quartu"));
        tripDAO.save(new Trip(percorrenza2Autobus, 100, "Cagliari", "Oristano"));
        tripDAO.save(new Trip(percorrenza3Autobus, 160, "Cagliari", "Sassari"));
        tripDAO.save(new Trip(percorrenza1Tram2, 60, "Reggio", "Modena"));
        tripDAO.save(new Trip(percorrenza2Tram2, 30, "Modena", "Parma"));
        tripDAO.save(new Trip(percorrenza1Autobus2, 400, "Bologna", "Roma"));
        tripDAO.save(new Trip(percorrenza2Autobus2, 100, "Bologna", "Milano"));
        tripDAO.save(new Trip(percorrenza3Autobus2, 10, "Milano", "Napoli"));
        tripDAO.save(new Trip(percorrenza4Autobus2, 15, "Trieste", "Catania"));
    }

    public static void queryPrint() {
        System.out.println("***********************maintenanceByVehicleId*************************");
        maintenanceDAO.maintenanceByVehicleId("aeba9b8e-fc5e-43fd-910c-a8f739f0593d").forEach(System.out::println);

        System.out.println("***********************findActiveAutomaticSeller*************************");
        shopDAO.findActiveAutomaticSeller().forEach(System.out::println);

        System.out.println("***********************serviceByVehicleId*************************");
        vehicleDAO.serviceByVehicleId().forEach(System.out::println);

        System.out.println("***********************validitySubscription*************************");
        docDAO.validitySubscription("37d98088-3f68-47ec-81e1-0beeea7a3bcb");

        System.out.println("***********************findSoldTicketByTime*************************");
        shopDAO.findSoldByTime(LocalDateTime.now().minusDays(5), LocalDateTime.now().plusDays(1), "Milano").forEach(System.out::println);

        System.out.println("***********************findSubByTime*************************");
        shopDAO.findSubByTime(LocalDateTime.now().minusDays(5), LocalDateTime.now().plusDays(1), "Milano").forEach(System.out::println);

        System.out.println("***********************countValidatedTicketsByTime*************************");
        docDAO.countValidatedTicketsByTime(LocalDateTime.now().minusDays(30), LocalDateTime.now().plusDays(1));

        System.out.println("***********************countValidatedTicketsByVehicle*************************");
        docDAO.countValidatedTicketsByVehicle(UUID.fromString("1c150e5b-ab0d-4827-b7d2-2fe111399c65"));

        System.out.println("***********************countTicketsByVehicle*************************");
        docDAO.countTicketsByVehicle(UUID.fromString("1c150e5b-ab0d-4827-b7d2-2fe111399c65"));

        System.out.println("***********************countTicketsByTimeANDVehicle*************************");
        docDAO.countTicketsByTimeANDVehicle(LocalDateTime.now().minusDays(5), LocalDateTime.now().plusDays(1), UUID.fromString("1c150e5b-ab0d-4827-b7d2-2fe111399c65"));
    }

    public static void addElement() {
        try {
            Subscription aldoFromDb = docDAO.findSubscriptionById("3e914e68-434d-4f50-9890-ed56dc30acd0");
            Ticket ticketAldoByCard2 = new Ticket(LocalDateTime.now(), roma, tram, aldoFromDb, 90);
            Ticket ticketAldoByCard3 = new Ticket(LocalDateTime.now().minusMonths(1), roma, tram, aldoFromDb, 90);
            docDAO.save(ticketAldoByCard2);
            docDAO.save(ticketAldoByCard3);
        } catch (RuntimeException error) {
            System.out.println(error.getMessage());
        }

        try {
            for (int i = 0; i < 5; i++) {
                Distance percorrenza1FromDb = distanceDAO.findById("881188e8-c58f-476c-82ec-b0390f6d078d");
                Trip newRepTrip = new Trip(percorrenza1FromDb, 60, "Latina", "Roma");
                tripDAO.save(newRepTrip);
            }
        } catch (RuntimeException error) {
            System.out.println(error.getMessage());
        }
    }


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

    public static Shop createRandomSeller() {
        boolean shopType = new Random().nextBoolean();
        AutomaticSellerState[] automaticSellerStates = {AutomaticSellerState.IN_SERVICE, AutomaticSellerState.OUT_OF_SERVICE};
        Faker faker = new Faker();
        String company_name = faker.company().name();
        String emission_point = faker.address().cityName();
        if (shopType) return new Seller(emission_point, company_name);
        else return new AutomaticSeller(emission_point, automaticSellerStates[new Random().nextInt(2)]);
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
    /*
    List<User> userList = createRandomList(10, "user");
        userList.forEach(userDAO::save);

    List<Vehicle> vehicleList = createRandomList(10, "vehicle");
        vehicleList.forEach(vehicleDAO::save);

    List<Seller> sellerList = createRandomList(10, "seller");
        sellerList.forEach(sellerDAO::save);

     */
}
