package Assignment1;

import java.util.Scanner;

// Cafe class
class Cafe {

    // Attributes
    private String name;
    private String location;
    private int openHour;
    private int closeHour;
    private int totalTables;
    private int occupiedTables;
    private double dailySales;

    // Default constructor
    public Cafe() {
        name = "Unknown Cafe";
        location = "Unknown";
        openHour = 8;
        closeHour = 22;
        totalTables = 10;
        occupiedTables = 0;
        dailySales = 0.0;
    }

    // Parameterized constructor
    public Cafe(String name, String location, int openHour,
                int closeHour, int totalTables,
                int occupiedTables, double dailySales) {
        setName(name);
        setLocation(location);
        setOpenHour(openHour);
        setCloseHour(closeHour);
        setTotalTables(totalTables);
        setOccupiedTables(occupiedTables);
        setDailySales(dailySales);
    }

    // Getters
    public String getName() { return name; }
    public String getLocation() { return location; }
    public int getOpenHour() { return openHour; }
    public int getCloseHour() { return closeHour; }
    public int getTotalTables() { return totalTables; }
    public int getOccupiedTables() { return occupiedTables; }
    public double getDailySales() { return dailySales; }

    // Setters
    public void setName(String name) {
        if (name != null && !name.trim().isEmpty())
            this.name = name;
    }

    public void setLocation(String location) {
        if (location != null && !location.trim().isEmpty())
            this.location = location;
    }

    public void setOpenHour(int openHour) {
        if (openHour >= 0 && openHour <= 23)
            this.openHour = openHour;
    }

    public void setCloseHour(int closeHour) {
        if (closeHour >= 0 && closeHour <= 23)
            this.closeHour = closeHour;
    }

    public void setTotalTables(int totalTables) {
        if (totalTables > 0 && totalTables >= occupiedTables)
            this.totalTables = totalTables;
    }

    public void setOccupiedTables(int occupiedTables) {
        if (occupiedTables >= 0 && occupiedTables <= totalTables)
            this.occupiedTables = occupiedTables;
    }

    public void setDailySales(double dailySales) {
        if (dailySales >= 0)
            this.dailySales = dailySales;
    }

    // Check whether the cafe is open
    public boolean isOpen(int hour) {
        if (hour < 0 || hour > 23)
            return false;

        if (openHour < closeHour)
            return hour >= openHour && hour < closeHour;
        else if (openHour > closeHour)
            return hour >= openHour || hour < closeHour;

        return false;
    }

    // Seat customers
    public int seatCustomers(int tables) {
        if (tables > 0 && occupiedTables + tables <= totalTables) {
            occupiedTables += tables;
            return occupiedTables;
        }
        return -1;
    }

    // Free occupied tables
    public int freeTables(int tables) {
        if (tables > 0 && tables <= occupiedTables) {
            occupiedTables -= tables;
            return occupiedTables;
        }
        return -1;
    }

    // Calculate available tables
    public int getAvailableTables() {
        return totalTables - occupiedTables;
    }

    // Record sales
    public void recordSale(double amount) {
        if (amount > 0)
            dailySales += amount;
    }

    // Get occupancy status
    public String getOccupancyStatus() {
        if (occupiedTables == 0)
            return "Empty";
        else if (occupiedTables == totalTables)
            return "Full";
        else
            return "Partially Occupied";
    }

    // Display cafe information
    public void displayInfo() {
        System.out.println("\n===== Cafe Information =====");
        System.out.println("Name: " + getName());
        System.out.println("Location: " + getLocation());
        System.out.println("Opening Hour: " + getOpenHour() + ":00");
        System.out.println("Closing Hour: " + getCloseHour() + ":00");
        System.out.println("Total Tables: " + getTotalTables());
        System.out.println("Occupied Tables: " + getOccupiedTables());
        System.out.println("Available Tables: " + getAvailableTables());
        System.out.printf("Daily Sales: RM %.2f%n", getDailySales());
        System.out.println("Occupancy Status: " + getOccupancyStatus());
    }
}

// Main class
public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // Create the first cafe using the default constructor
        Cafe cafe1 = new Cafe();

        cafe1.setName("Daily Brew");
        cafe1.setLocation("Shah Alam");
        cafe1.setOpenHour(8);
        cafe1.setCloseHour(22);
        cafe1.setTotalTables(10);
        cafe1.setOccupiedTables(3);
        cafe1.setDailySales(150.00);

        // Create the second cafe using the parameterized constructor
        Cafe cafe2 = new Cafe(
            "Coffee Corner", "Kuala Lumpur",
            9, 23, 15, 5, 250.00
        );

        // Store both cafes in an array
        Cafe[] cafes = {cafe1, cafe2};

        // For loop
        for (int i = 0; i < cafes.length; i++) {
            cafes[i].displayInfo();
        }

        int choice = -1;

        // While loop
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

            // Switch statement
            switch (choice) {
                case 1:
                    System.out.print("Enter hour (0-23): ");

                    if (!input.hasNextInt()) {
                        System.out.println("Invalid hour.");
                        input.nextLine();
                        break;
                    }

                    int hour = input.nextInt();

                    if (cafe1.isOpen(hour))
                        System.out.println(cafe1.getName() + " is open.");
                    else
                        System.out.println(cafe1.getName() + " is closed.");
                    break;

                case 2:
                    System.out.print("How many tables are needed? ");

                    if (!input.hasNextInt()) {
                        System.out.println("Please enter a valid number.");
                        input.nextLine();
                        break;
                    }

                    int needed = input.nextInt();

                    if (cafe1.seatCustomers(needed) != -1)
                        System.out.println("Tables allocated successfully.");
                    else
                        System.out.println("Unable to allocate tables.");
                    break;

                case 3:
                    System.out.print("How many tables should be freed? ");

                    if (!input.hasNextInt()) {
                        System.out.println("Please enter a valid number.");
                        input.nextLine();
                        break;
                    }

                    int freed = input.nextInt();

                    if (cafe1.freeTables(freed) != -1)
                        System.out.println("Tables freed successfully.");
                    else
                        System.out.println("Invalid number of tables.");
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
                        System.out.println("Amount must be positive.");
                    }
                    break;

                case 5:
                    cafe1.displayInfo();
                    break;

                case 0:
                    System.out.println("Exiting Cafe Management System.");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }

        input.close();
    }
}

