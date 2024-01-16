import java.util.InputMismatchException;
import java.util.Scanner;

public class TrainTicketSystem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Train Ticket Purchasing System!");

        do {
            int destination;
            int numTickets;

            // Input validation for destination
            do {
                try {
                    System.out.println("Available Destinations:");
                    System.out.println("1. York");
                    System.out.println("2. Newcastle");
                    System.out.println("3. Sheffield");
                    System.out.print("Enter the destination (1, 2, or 3): ");
                    destination = scanner.nextInt();

                    if (destination < 1 || destination > 3) {
                        throw new InputMismatchException("Invalid destination. Please enter 1, 2, or 3.");
                    }

                    break; // Break out of the loop if input is valid

                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                    scanner.nextLine(); // Consume the invalid input
                }
            } while (true);

            // Input validation for number of tickets
            do {
                try {
                    System.out.print("Enter the number of tickets: ");
                    numTickets = scanner.nextInt();

                    if (numTickets <= 0) {
                        throw new InputMismatchException("Number of tickets must be a positive integer.");
                    }

                    break; // Break out of the loop if input is valid

                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid positive integer.");
                    scanner.nextLine(); // Consume the invalid input
                }
            } while (true);

            // Calculate total cost based on destination and number of tickets
            double totalCost = calculateTotalCost(destination, numTickets);

            // Display the total cost
            System.out.println("Total Cost: $" + totalCost);

            // Ask if the user wants to buy more tickets
            System.out.print("Do you want to buy more tickets? (yes/no): ");
            String buyMore = scanner.next().toLowerCase();

            if (!buyMore.equals("yes")) {
                System.out.println("Thank you for using the Train Ticket Purchasing System. Have a great day!");
                break; // Exit the loop if the user does not want to buy more tickets
            }

        } while (true);

        // Close the scanner
        scanner.close();
    }

    private static double calculateTotalCost(int destination, int numTickets) {
        double baseTicketPrice;

        // Set base ticket price based on destination
        switch (destination) {
            case 1:
                baseTicketPrice = 50.0;
                break;
            case 2:
                baseTicketPrice = 75.0;
                break;
            case 3:
                baseTicketPrice = 100.0;
                break;
            default:
                System.out.println("Invalid destination. Defaulting to City A.");
                baseTicketPrice = 50.0;
        }

        // Calculate total cost
        return baseTicketPrice * numTickets;
    }
}
