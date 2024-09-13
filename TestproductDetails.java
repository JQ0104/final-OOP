import java.util.Scanner;

public class TestproductDetails {

    public static void main(String[] args) {
       	Scanner sc = new Scanner(System.in);
        productDetails product = new productDetails();
        Stockbalance stock = new Stockbalance();
        String choice;

        // Menu loop
        while (true) {
        	System.out.println("+-----------------------------+");
            System.out.println("|Product Management Menu:     |");
            System.out.println("+-----------------------------+");
            System.out.println("|1. Add Product               |");
            System.out.println("|2. Edit Product              |");
            System.out.println("|3. Read Products             |");
            System.out.println("|4. Delete Product  	      |");
            System.out.println("|5. Stock Balance             |");
            System.out.println("|6. Exit                      |");
            System.out.println("+-----------------------------+");
            System.out.print("\n\nPlease select an option: ");
            choice = sc.nextLine();

            switch (choice) {
                case "1":
                	//Add product
                	product.addProduct();
                    break;

                case "2":
                    // Edit product
                    product.editProduct();
                    break;
                    
                case "3":
                    // Read products
                    product.readProduct();
                    break;

                case "4":
                    // Cancel/Delete product
                    product.deleteProduct();
                    break;

                case "5":
                    //Stock Balance
                    stock.checkStockBalance();
                    break;
                    
                case "6":
                    // Exit to admin menu 
                    System.out.println("Exiting the program.");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid option, please try again.");
            }
    }
}
}
