import java.util.InputMismatchException;
import java.util.Scanner;

public class TrainTicketSystem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Train Ticket Purchasing System!");

        do {
            int startPoint;
            int endPoint;
            int numTickets;
            String ticketType;

            // Input validation for start point
            do {
                try {
                    System.out.println("Available Start Points:");
                    System.out.println("1. York");
                    System.out.println("2. Hull");
                    System.out.println("3. Sheffield");
                    System.out.print("Enter the start point (1, 2, or 3): ");
                    startPoint = scanner.nextInt();

                    if (startPoint < 1 || startPoint > 3) {
                        throw new InputMismatchException("Invalid start point. Please enter 1, 2, or 3.");
                    }

                    break; // Break out of the loop if input is valid

                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                    scanner.nextLine(); // Consume the invalid input
                }
            } while (true);

            // Display available endpoints based on the selected start point
            System.out.println("Available End Points:");

            switch (startPoint) {
                case 1:
                    System.out.println("1. Hull");
                    System.out.println("2. Sheffield");
                    break;
                case 2:
                    System.out.println("1. York");
                    System.out.println("2. Sheffield");
                    break;
                case 3:
                    System.out.println("1. York");
                    System.out.println("2. Hull");
                    break;
            }

            // Input validation for end point
            do {
                try {
                    System.out.print("Enter the end point (1 or 2): ");
                    endPoint = scanner.nextInt();

                    if (endPoint < 1 || endPoint > 2) {
                        throw new InputMismatchException("Invalid end point. Please enter 1 or 2.");
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

            // Input validation for ticket type
            do {
                try {
                    System.out.print("Enter the ticket type (one-way/return): ");
                    ticketType = scanner.next().toLowerCase();

                    if (!ticketType.equals("one-way") && !ticketType.equals("return")) {
                        throw new InputMismatchException("Invalid ticket type. Please enter 'one-way' or 'return'.");
                    }

                    break; // Break out of the loop if input is valid

                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter 'one-way' or 'return'.");
                    scanner.nextLine(); // Consume the invalid input
                }
            } while (true);

            // Calculate total cost based on start point, end point, number of tickets, and
            // ticket type
            double totalCost = calculateTotalCost(startPoint, endPoint, numTickets, ticketType);

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

    private static double calculateTotalCost(int startPoint, int endPoint, int numTickets, String ticketType) {
        // Define a pricing array based on start and end points
        double[][] pricingMatrix = {
                { 50.0, 60.0 }, // York to Hull and York to Sheffield
                { 55.0, 45.0 }, // Hull to York and Hull to Sheffield
                { 65.0, 55.0 } // Sheffield to York and Sheffield to Hull
        };

        // Assume a flat ticket price for simplicity
        double baseTicketPrice = pricingMatrix[startPoint - 1][endPoint - 1];

        // Apply a discount for return tickets
        if (ticketType.equals("return")) {
            baseTicketPrice *= 1.5; // Assume a 50% discount for return tickets
        }

        // Calculate total cost (simple calculation for illustration purposes)
        return baseTicketPrice * numTickets;
    }
}
