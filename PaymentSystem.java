import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PaymentSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create a user and let them input their card details
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        // Assign some default balance for the user, e.g., $500.00
        User user = new User(name, 500.00);

        // Prompt the user to enter the amount they want to pay
        System.out.print("Enter the amount to be paid: ");
        double amount = scanner.nextDouble();

        // Check if the user has sufficient balance for the payment
        if (!user.canAfford(amount)) {
            System.out.println("Insufficient balance. Your current balance is: $" + user.getBalance());
            return;  // Exit if the user cannot afford the payment
        }

        // Create the transaction object with the entered amount
        Transaction transaction = new Transaction(user, amount);

        // Process the payment through the PaymentGateway
        if (PaymentGateway.processPayment(user, amount)) {
            transaction.processTransaction();  // Deduct the amount from the user balance
        }

        // Display if the transaction was successful
        if (transaction.isTransactionProcessed()) {
            System.out.println("Payment of $" + amount + " complete for user: " + user.getName());
        } else {
            System.out.println("Payment failed.");
        }

        // Display the contents of the transactions file
        displayTransactionFileContents();
    }

    // Display transaction file contents on the console
    private static void displayTransactionFileContents() {
        try (BufferedReader reader = new BufferedReader(new FileReader("transactions.txt"))) {
            String line;
            System.out.println("\nTransaction Records:");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }
    }
}
