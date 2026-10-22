package Assignment1;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // Create an object using the default constructor
        Cafe cafe1 = new Cafe();

        // Use setters to update the cafe details
        cafe1.setName("Daily Brew");
        cafe1.setLocation("Shah Alam");
        cafe1.setOpenHour(8);
        cafe1.setCloseHour(22);
        cafe1.setTotalTables(10);
        cafe1.setOccupiedTables(3);
        cafe1.setDailySales(150.00);

        // Create an object using the parameterized constructor
        Cafe cafe2 = new Cafe(
            "Coffee Corner",
            "Kuala Lumpur",
            9,
            23,
            15,
            5,
            250.00
        );

        // Store both objects in an array
        Cafe[] cafes = {cafe1, cafe2};

        // For loop: display information for each cafe
        for (int i = 0; i < cafes.length; i++) {
            cafes[i].displayInfo();
        }

        // While loop: repeatedly show the menu
        int choice = -1;

        while (choice != 0) {
            System.out.println("\n===== Cafe Management Menu =====");
            System.out.println("1. Check opening status");
            System.out.println("2. Seat customers");
            System.out.println("3. Free tables");
            System.out.println("4. Record a sale");
            System.out.println("5. Display cafe information");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            if (!input.hasNextInt()) {
                System.out.println("Please enter a valid number.");
                input.nextLine();
                continue;
            }

            choice = input.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter hour (0-23): ");
                    int hour = input.nextInt();

                    if (cafe1.isOpen(hour)) {
                        System.out.println(cafe1.getName()
                                + " is open.");
                    } else {
                        System.out.println(cafe1.getName()
                                + " is closed.");
                    }
                    break;

                case 2:
                    System.out.print("How many tables are needed? ");
                    int needed = input.nextInt();

                    if (cafe1.seatCustomers(needed) != -1) {
                        System.out.println("Tables allocated successfully.");
                    } else {
                        System.out.println(
                            "Unable to seat customers. Check availability."
                        );
                    }
                    break;

                case 3:
                    System.out.print("How many tables should be freed? ");
                    int freed = input.nextInt();

                    if (cafe1.freeTables(freed) != -1) {
                        System.out.println("Tables freed successfully.");
                    } else {
                        System.out.println(
                            "Invalid number of tables to free."
                        );
                    }
                    break;

                case 4:
                    System.out.print("Enter sale amount (RM): ");

                    if (!input.hasNextDouble()) {
                        System.out.println("Please enter a valid amount.");
                        input.nextLine();
                        break;
                    }

                    double amount = input.nextDouble();

                    if (amount > 0) {
                        cafe1.recordSale(amount);
                        System.out.println("Sale recorded successfully.");
                    } else {
                        System.out.println(
                            "Sale amount must be greater than zero."
                        );
                    }
                    break;

                case 5:
                    cafe1.displayInfo();
                    break;

                case 0:
                    System.out.println("Exiting Cafe Management System.");
                    break;

                default:
                    System.out.println(
                        "Invalid choice. Please select from the menu."
                    );
            }
        }

        input.close();
    }
}