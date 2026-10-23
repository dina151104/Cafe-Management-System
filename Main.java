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

        // Display information for every cafe
        for (int i = 0; i < cafes.length; i++) {
            cafes[i].displayInfo();
        }

        int choice = -1;

        while (choice != 0) {
            System.out.println("\n===== Cafe Management Menu =====");
            System.out.println("1. Check opening status");
            System.out.println("2. Seat customers");
            System.out.println("3. Free tables");
            System.out.println("4. Record a sale");
            System.out.println("5. Display cafe information");
            System.out.println("0. Exit");

            choice = readInt(input, "Enter your choice: ");

            switch (choice) {
                case 1:
                    int hour = readInt(input, "Enter hour (0-23): ");
                    if (hour < 0 || hour > 23) {
                        System.out.println("Invalid hour. Please enter a value from 0 to 23.");
                        break;
                    }

                    // Check the opening status of every cafe
                    for (int i = 0; i < cafes.length; i++) {
                        if (cafes[i].isOpen(hour)) {
                            System.out.println(cafes[i].getName() + " is open.");
                        } else {
                            System.out.println(cafes[i].getName() + " is closed.");
                        }
                    }
                    break;

                case 2: {
                    Cafe selectedCafe = selectCafe(input, cafes);
                    if (selectedCafe == null) {
                        break;
                    }

                    int needed = readInt(input, "How many tables are needed? ");
                    if (selectedCafe.seatCustomers(needed) != -1) {
                        System.out.println("Tables allocated successfully at " + selectedCafe.getName() + ".");
                        System.out.println("Available tables: " + selectedCafe.getAvailableTables());
                    } else {
                        System.out.println("Unable to seat customers. Enter a positive number and check table availability.");
                    }
                    break;
                }

                case 3: {
                    Cafe selectedCafe = selectCafe(input, cafes);
                    if (selectedCafe == null) {
                        break;
                    }

                    int freed = readInt(input, "How many tables should be freed? ");
                    if (selectedCafe.freeTables(freed) != -1) {
                        System.out.println("Tables freed successfully at " + selectedCafe.getName() + ".");
                        System.out.println("Available tables: " + selectedCafe.getAvailableTables());
                    } else {
                        System.out.println("Invalid number. Enter a positive number no greater than the occupied tables.");
                    }
                    break;
                }

                case 4: {
                    Cafe selectedCafe = selectCafe(input, cafes);
                    if (selectedCafe == null) {
                        break;
                    }

                    double amount = readDouble(input, "Enter sale amount (RM): ");
                    if (Double.isFinite(amount) && amount > 0) {
                        selectedCafe.recordSale(amount);
                        System.out.printf("Sale recorded at %s. New daily sales: RM %.2f%n",
                                selectedCafe.getName(), selectedCafe.getDailySales());
                    } else {
                        System.out.println("Sale amount must be a finite number greater than zero.");
                    }
                    break;
                }

                case 5:
                    for (int i = 0; i < cafes.length; i++) {
                        cafes[i].displayInfo();
                    }
                    break;

                case 0:
                    System.out.println("Exiting Cafe Management System.");
                    break;

                default:
                    System.out.println("Invalid choice. Please select from the menu.");
            }
        }

        input.close();
    }

    // Read a whole number without crashing if the user enters text or a decimal
    private static int readInt(Scanner input, String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = input.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a whole number.");
            }
        }
    }

    // Read a decimal number without crashing if the input is invalid
    private static double readDouble(Scanner input, String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = input.nextLine().trim();
            try {
                return Double.parseDouble(line);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number, for example 12.50.");
            }
        }
    }

    // Let the user choose which cafe an operation applies to
    private static Cafe selectCafe(Scanner input, Cafe[] cafes) {
        System.out.println("Choose a cafe:");
        for (int i = 0; i < cafes.length; i++) {
            System.out.println((i + 1) + ". " + cafes[i].getName());
        }
        System.out.println("0. Cancel");

        int selection = readInt(input, "Enter cafe number: ");
        if (selection == 0) {
            System.out.println("Operation cancelled.");
            return null;
        }
        if (selection < 1 || selection > cafes.length) {
            System.out.println("Invalid cafe selection. Operation cancelled.");
            return null;
        }
        return cafes[selection - 1];
    }
}
