
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        try (Scanner sc = new Scanner(System.in)) {
            ParkingManager pm = new ParkingManager();

            while (true) {

                System.out.println("\n======================================");
                System.out.println("   PARKING MANAGEMENT SYSTEM");
                System.out.println("======================================");

                System.out.println("1. Park Vehicle");
                System.out.println("2. Remove Vehicle");
                System.out.println("3. View All Parked Vehicles");
                System.out.println("4. Search Vehicle");
                System.out.println("5. Show Total Parked Vehicles");
                System.out.println("6. View Available Slots");
                System.out.println("7. Show Parking Summary");
                System.out.println("8. Remove All Vehicles");
                System.out.println("9. Exit");

                System.out.print("\nEnter your choice: ");

                int choice;

                // Input validation for menu
                if (!sc.hasNextInt()) {
                    System.out.println("Invalid input! Please enter a number.");
                    sc.nextLine();
                    continue;
                }

                choice = sc.nextInt();
                sc.nextLine();

                try {

                    switch (choice) {

                        case 1 -> {
                            System.out.println("\n--- Park Vehicle ---");

                            System.out.print("Enter Vehicle Type (1 = Two-Wheeler, 2 = Four-Wheeler): ");
                            int type = sc.nextInt();
                            sc.nextLine();

                            System.out.print("Enter Vehicle Number: ");
                            String number = sc.nextLine();

                            System.out.print("Enter Owner Name: ");
                            String owner = sc.nextLine();

                            pm.parkVehicle(type, number, owner);
                            System.out.println("Vehicle parked successfully!");
                        }

                        case 2 -> {
                            System.out.println("\n--- Remove Vehicle ---");

                            System.out.print("Enter Vehicle Number: ");
                            String removeNumber = sc.nextLine();

                            pm.removeVehicle(removeNumber);
                            System.out.println("Vehicle removed successfully!");
                        }

                        case 3 -> {
                            System.out.println("\n--- Parked Vehicles ---");
                            pm.viewVehicles();
                        }

                        case 4 -> {
                            System.out.println("\n--- Search Vehicle ---");

                            System.out.print("Enter Vehicle Number to Search: ");
                            String searchNumber = sc.nextLine();

                            pm.searchVehicle(searchNumber);
                        }

                        case 5 -> {
                            System.out.println("\n--- Total Parked Vehicles ---");
                            pm.showCount();
                        }

                        case 6 -> {
                            System.out.println("\n--- Available Parking Slots ---");
                            pm.showSlots();
                        }

                        case 7 -> {
                            System.out.println("\n--- Parking Summary ---");
                            pm.showSummary();
                        }

                        case 8 -> {
                            System.out.println("\n--- Remove All Vehicles ---");

                            System.out.print("Are you sure? (yes/no): ");
                            String confirm = sc.nextLine();

                            if (confirm.equalsIgnoreCase("yes")) {
                                pm.removeAll();
                            } else {
                                System.out.println("Operation cancelled.");
                            }
                        }

                        case 9 -> {
                            System.out.println("\nThank you for using Parking Management System!");
                            System.out.println("Goodbye ");
                            return;
                        }

                        default ->
                            System.out.println("Invalid choice! Please select from 1 to 9.");
                    }

                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }
    }
}
