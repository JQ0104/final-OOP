import java.util.Scanner;

public class User {
    private String name;
    private String cardNumber;
    private double balance;

    public User(String name, double balance) {
        this.name = name;
        this.balance = balance;
        this.cardNumber = getCardNumberFromUser();
    }

    public String getName() {
        return name;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void deductBalance(double amount) {
        if (amount <= balance) {
            balance -= amount;
        }
    }

    public boolean canAfford(double amount) {
        return amount <= balance;
    }

    // Get card number from user input
    private String getCardNumberFromUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter credit card number: ");
        return scanner.nextLine();
    }
}
