package bw4t6.functionality;

import bw4t6.dao.*;
import bw4t6.entities.*;
import bw4t6.entities.abstracts.Shop;
import bw4t6.enums.SubscriptionState;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.UUID;

public class MyScanner {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("team_database");

    private static final EntityManager em = emf.createEntityManager();
    private static final CardDAO cardDAO = new CardDAO(em);
    private static final DistanceDAO distanceDAO = new DistanceDAO(em);
    private static final DocumentOfTravelDAO docDAO = new DocumentOfTravelDAO(em);
    private static final MaintenanceDAO maintenanceDAO = new MaintenanceDAO(em);
    private static final ShopDAO shopDAO = new ShopDAO(em);
    private static final TripDAO tripDAO = new TripDAO(em);
    private static final UserDAO userDAO = new UserDAO(em);
    private static final VehicleDAO vehicleDAO = new VehicleDAO(em);

    public static void startScanner() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Benvenuto! Sei un utente o un admin?");
        System.out.println("1. Utente");
        System.out.println("2. Admin");
        System.out.println("0. Esci");
        System.out.print("Scelta: ");
        int choice = Integer.parseInt(scanner.nextLine());
        boolean exit = false;
        while (!exit) {
            switch (choice) {
                case 1:
                    userMenu(scanner);
                    System.out.print("\n\n1. Utente\n2. Admin\n0. Esci\nScelta:");
                    choice = Integer.parseInt(scanner.nextLine());
                    break;
                case 2:
                    adminMenu(scanner);
                    System.out.print("\n\n1. Utente\n2. Admin\n0. Esci\nScelta:");
                    choice = Integer.parseInt(scanner.nextLine());
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.print("\nScelta non valida.\n\nScelta: ");
                    choice = Integer.parseInt(scanner.nextLine());
                    break;
            }
        }
        scanner.close();
    }

    private static void userMenu(Scanner scanner) {
        boolean exit = false;
        while (!exit) {
            System.out.println("\nMenu Utente:");
            System.out.println("1. Compra una card");
            System.out.println("2. Acquista un abbonamento");
            System.out.println("3. Compra un ticket");
            System.out.println("4. Controlla la validità dell'abbonamento");
            System.out.println("0. Esci");

            System.out.print("Scelta: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    System.out.println("Hai selezionato: Compra una card\nInserisci il nome: ");
                    String name = scanner.nextLine();
                    System.out.println("Inserisci il cognome: ");
                    String surname = scanner.nextLine();
                    System.out.println("Inserisci anno di nascita: ");
                    LocalDate dateOfBirth = LocalDate.parse(scanner.nextLine());

                    User user = new User(name, surname, dateOfBirth);
                    userDAO.save(user);
                    Card card = new Card(user, LocalDate.now().plusYears(1));
                    cardDAO.save(card);
                    break;
                case 2:
                    System.out.println("Venditori disponibili: ");
                    shopDAO.getAllShop().forEach(shop -> System.out.println(shop.getEmission_point()));

                    System.out.print("\nScegli nome punto di emissione: ");
                    String nameEmissionPoint2 = scanner.nextLine();
                    Shop shop2 = shopDAO.findByName(nameEmissionPoint2);

                    System.out.println("Scegli durata abbonamento: \n1 - Settimanale\n2 - Mensile");
                    int number = Integer.parseInt(scanner.nextLine());
                    SubscriptionState state;
                    if (number == 1) {
                        state = SubscriptionState.WEEKLY;
                    } else if (number == 2) {
                        state = SubscriptionState.MONTHLY;
                    } else {
                        System.out.println("Hai sbagliato\nInserito default 1");
                        state = SubscriptionState.WEEKLY;
                    }
                    double price;
                    if (state == SubscriptionState.WEEKLY) {
                        price = 50;
                    } else price = 150;
                    System.out.println("Scrivi ID tessera utente");
                    String card_id = scanner.nextLine();   // 37d98088-3f68-47ec-81e1-0beeea7a3bcb
                    Card giovanni = cardDAO.findById(card_id);
                    Subscription subscription = new Subscription(LocalDateTime.now(), price, shop2, state, giovanni);
                    docDAO.save(subscription);
                    break;
                case 3:
                    System.out.println("Venditori disponibili: ");
                    shopDAO.getAllShop().forEach(shop -> System.out.println(shop.getEmission_point()));

                    System.out.print("\nScegli nome punto di emissione: ");
                    String nameEmissionPoint = scanner.nextLine();
                    Shop shop = shopDAO.findByName(nameEmissionPoint);

                    System.out.println("Scegli come comprare ticket: \n1. Abbonamento\n2. Non hai abbonamento ");
                    int number2 = Integer.parseInt(scanner.nextLine());
//                    Shop roma2 = shopDAO.findById("6ff3e06f-4a78-45f1-b69d-43bb59adf6e9");
                    Vehicle trattaRomaBologa = vehicleDAO.findById("aeba9b8e-fc5e-43fd-910c-a8f739f0593d");

                    if (number2 == 1) {
                        System.out.println("Hai selezionato: Compra un ticket con abbonamento");

                        System.out.println("Inserisci id abbonamento: ");
                        String subscriptionId = scanner.nextLine();  // cdfe79cd-abff-446f-a77c-496397e5c7a1
                        Subscription subscriptionGiacomo = docDAO.findSubscriptionById(subscriptionId);

                        Ticket ticketBySub = new Ticket(LocalDateTime.now(), shop, trattaRomaBologa, subscriptionGiacomo, 400);
                        docDAO.save(ticketBySub);

                    } else if (number2 == 2) {
                        System.out.println("Hai selezionato: Compra un ticket senza abbonamento");
                        User userGiovanni = userDAO.findById("37d98088-3f68-47ec-81e1-0beeea7a3bcb");
                        Ticket ticket = new Ticket(LocalDateTime.now(), 2.50, shop, userGiovanni, trattaRomaBologa, 400);
                        docDAO.save(ticket);
                    }

                    break;
                case 4:
                    System.out.println("Hai selezionato: Controlla la validità dell'abbonamento\nInserisci id: ");
                    String subscriptionId = scanner.nextLine(); //cdfe79cd-abff-446f-a77c-496397e5c7a1 Giovanni
                    docDAO.validitySubscription(subscriptionId);
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("Scelta non valida. Riprova.");
                    break;
            }
        }
    }

    private static void adminMenu(Scanner scanner) {
        boolean exit = false;
        while (!exit) {
            System.out.println("\nMenu Admin:");
            System.out.println("1. Visualizza biglietti venduti per punto di emissione e data");
            System.out.println("2. Visualizza abbonamenti venduti per punto di emissione e data");
            System.out.println("3. Visualizza validità tessera utente tramite id utente");
            System.out.println("4. Visualizza tutti veicoli");
            System.out.println("5. Visualizza manutenzione per id veicolo");
            System.out.println("6. Visualizza numero biglietti totali timbrati su un mezzo");
            System.out.println("7. Visualizza numero biglietti totali per una specifica tratta");
            System.out.println("8. Visualizza numero biglietti totali in un determinato periodo di tempo per id mezzo");
            System.out.println("9. Visualizza distributori automatici attivi");
            System.out.println("10. Visualizza veicoli in servizio");
            System.out.println("0. Esci");

            System.out.print("Scelta: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    System.out.print("\nInserisci periodo di tempo \ndal giorno: ");
                    String data1 = scanner.nextLine(); // 2024-06-08
                    LocalDate formatDate1 = LocalDate.parse(data1, DateTimeFormatter.ISO_LOCAL_DATE);
                    LocalDateTime dateTime1 = formatDate1.atStartOfDay();


                    System.out.print("al giorno: ");
                    String data2 = scanner.nextLine(); // 2024-06-20
                    LocalDate formatDate2 = LocalDate.parse(data2, DateTimeFormatter.ISO_LOCAL_DATE);
                    LocalDateTime dateTime2 = formatDate2.atStartOfDay();

                    shopDAO.getAllShop().forEach(shop -> System.out.println(shop.getEmission_point()));

                    System.out.print("\nScegli nome punto di emissione: ");
                    String emissionPoint = scanner.nextLine();
                    shopDAO.findSoldByTime(dateTime1, dateTime2, emissionPoint).forEach(System.out::println);
                    break;
                case 2:
                    System.out.print("\nInserisci periodo di tempo\ndal giorno: ");
                    String dataSub1 = scanner.nextLine(); // 2024-06-08
                    LocalDate formatDateSub1 = LocalDate.parse(dataSub1, DateTimeFormatter.ISO_LOCAL_DATE);
                    LocalDateTime dateTimeSub1 = formatDateSub1.atStartOfDay();


                    System.out.print("al giorno: ");
                    String dataSub2 = scanner.nextLine(); // 2024-06-20
                    LocalDate formatDateSub2 = LocalDate.parse(dataSub2, DateTimeFormatter.ISO_LOCAL_DATE);
                    LocalDateTime dateTimeSub2 = formatDateSub2.atStartOfDay();

                    shopDAO.getAllShop().forEach(shop -> System.out.println(shop.getEmission_point()));

                    System.out.print("\nScegli nome punto di emissione: ");
                    String emissionPointForSub = scanner.nextLine();
                    shopDAO.findSubByTime(dateTimeSub1, dateTimeSub2, emissionPointForSub).forEach(System.out::println);
                    break;
                case 3:
                    System.out.println("Inserire id utente: ");
                    String subscriptionId = scanner.nextLine(); // 10c5a836-9c63-4738-8592-f2252cdfc9a8
                    docDAO.validitySubscription(subscriptionId);
                    break;
                case 4:
                    vehicleDAO.getVehicle().forEach(System.out::println);
                    break;
                case 5:
                    System.out.println("Inserisci id corrispondente al veicolo: ");
                    String vehicleID = scanner.nextLine(); // 1c150e5b-ab0d-4827-b7d2-2fe111399c65
                    maintenanceDAO.maintenanceByVehicleId(vehicleID).forEach(System.out::println);
                    break;
                case 6:
                    System.out.println("Inserisci id corrispondente al veicolo: ");
                    String vehicle = scanner.nextLine(); // aeba9b8e-fc5e-43fd-910c-a8f739f0593d
                    docDAO.countValidatedTicketsByVehicle(UUID.fromString(vehicle));
                    break;
                case 7:
                    System.out.println("Inserisci id corrispondente al veicolo: ");
                    String vehicleAllTicket = scanner.nextLine(); // 1c150e5b-ab0d-4827-b7d2-2fe111399c65
                    docDAO.countTicketsByVehicle(UUID.fromString(vehicleAllTicket));
                    break;
                case 8:
                    System.out.print("\nInserisci periodo di tempo \ndal giorno: ");
                    String dataVehicle1 = scanner.nextLine(); // 2024-06-08
                    LocalDate formatDateVehicle1 = LocalDate.parse(dataVehicle1, DateTimeFormatter.ISO_LOCAL_DATE);
                    LocalDateTime dateTimeVehicle1 = formatDateVehicle1.atStartOfDay();


                    System.out.print("al giorno: ");
                    String dataVehicle2 = scanner.nextLine(); // 2024-06-20
                    LocalDate formatDateVehicle2 = LocalDate.parse(dataVehicle2, DateTimeFormatter.ISO_LOCAL_DATE);
                    LocalDateTime dateTimeVehicle2 = formatDateVehicle2.atStartOfDay();

                    System.out.println("Inserisci id corrispondente al veicolo: ");
                    String vehicleTicket = scanner.nextLine(); // 1c150e5b-ab0d-4827-b7d2-2fe111399c65

                    docDAO.countTicketsByTimeANDVehicle(dateTimeVehicle1, dateTimeVehicle2, UUID.fromString(vehicleTicket));
                    break;
                case 9:
                    System.out.println("Distributori automatici in servizio: ");
                    shopDAO.findActiveAutomaticSeller().forEach(automaticSeller -> System.out.println(automaticSeller.getEmission_point()));
                    break;
                case 10:
                    System.out.println("Veicoli in servizio: ");
                    vehicleDAO.serviceByVehicleId().forEach(System.out::println);
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("Scelta non valida. Riprova.");
                    break;
            }
        }

    }
}