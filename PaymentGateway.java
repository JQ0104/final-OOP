public class PaymentGateway {
    public static boolean processPayment(User user, double amount) {
        // Simulate validating the credit card number
        if (CreditCardValidator.validateCreditCard(user.getCardNumber())) {
            System.out.println("Processing payment for: " + user.getName());
            return true;  // Payment processed successfully
        } else {
            System.out.println("Invalid credit card number.");
            return false;  // Payment failed due to invalid card
        }
    }
}
