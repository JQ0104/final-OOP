import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private User user;
    private double amount;
    private boolean isProcessed;
    private LocalDateTime timestamp;

    public Transaction(User user, double amount) {
        this.user = user;
        this.amount = amount;
        this.isProcessed = false;
        this.timestamp = LocalDateTime.now();  // Set the current date and time
    }

    public void processTransaction() {
        if (user.canAfford(amount)) {
            user.deductBalance(amount);
            isProcessed = true;
            System.out.println("Transaction Successful: $" + amount);
            saveTransactionToFile();  // Save transaction to the file
        } else {
            System.out.println("Insufficient Funds for User: " + user.getName());
        }
    }

    public boolean isTransactionProcessed() {
        return isProcessed;
    }

    private void saveTransactionToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("transactions.txt", true))) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedTimestamp = timestamp.format(formatter);
            writer.write("Timestamp: " + formattedTimestamp + ", User: " + user.getName() + ", Amount: $" + amount + ", Status: " + (isProcessed ? "Successful" : "Failed"));
            writer.newLine();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }
}
