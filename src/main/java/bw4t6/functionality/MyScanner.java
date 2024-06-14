package bw4t6.functionality;

import bw4t6.dao.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Scanner;

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

        Scanner scanner = new Scanner(System.in);

        System.out.println("Benvenuto! Sei un utente o un admin?");
        System.out.println("1. Utente");
        System.out.println("2. Admin");
        System.out.println("0. Esci");
        System.out.print("Scelta: ");
        int choice = scanner.nextInt();
        boolean exit = false;
        while (!exit) {
            switch (choice) {
                case 1:
                    userMenu(scanner);
                    System.out.print("\n\n1. Utente\n2. Admin\n0. Esci\nScelta:");
                    choice = scanner.nextInt();
                    break;
                case 2:
                    adminMenu(scanner);
                    System.out.print("\n\n1. Utente\n2. Admin\n0. Esci\nScelta:");
                    choice = scanner.nextInt();
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.print("\nScelta non valida.\n\nScelta: ");
                    choice = scanner.nextInt();
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
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Hai selezionato: Compra una card");

                case 2:
                    System.out.println("Hai selezionato: Acquista un abbonamento");

                case 3:
                    System.out.println("Hai selezionato: Compra un ticket");
                    break;
                case 4:
                    System.out.println("Hai selezionato: Controlla la validità dell'abbonamento");
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
            System.out.println("1.");
            System.out.println("2.");
            System.out.println("3.");
            System.out.println("4.");
            System.out.println("5.");
            System.out.println("6.");
            System.out.println("7. ");
            System.out.println("8.");
            System.out.println("9.");
            System.out.println("10.");
            System.out.println("0. Esci");

            System.out.print("Scelta: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Hai selezionato: Opzione 1");

                    break;
                case 2:
                    System.out.println("Hai selezionato: Opzione 2");

                    break;
                case 3:
                    System.out.println("Hai selezionato: Opzione 3");

                    break;
                case 4:
                    System.out.println("Hai selezionato: Opzione 4");

                    break;
                case 5:
                    System.out.println("Hai selezionato: Opzione 5");

                    break;
                case 6:
                    System.out.println("Hai selezionato: Opzione 6");

                    break;
                case 7:
                    System.out.println("Hai selezionato: Opzione 7");

                    break;
                case 8:
                    System.out.println("Hai selezionato: Opzione 8");

                    break;
                case 9:
                    System.out.println("Hai selezionato: Opzione 9");

                    break;
                case 10:
                    System.out.println("Hai selezionato: Opzione 10");

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