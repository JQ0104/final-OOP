public class CreditCardValidator {

    public static boolean validateCreditCard(String cardNumber) {
        int sum = 0;
        boolean alternate = false;

        // Process the card number using the Luhn algorithm
        for (int i = cardNumber.length() - 1; i >= 0; i--) {
            int n = Integer.parseInt(cardNumber.substring(i, i + 1));

            if (alternate) {
                n *= 2;
                if (n > 9) {
                    n = (n % 10) + 1;
                }
            }
            sum += n;
            alternate = !alternate;
        }

        // If the sum is divisible by 10, it's a valid card
        return (sum % 10 == 0);
    }
}
