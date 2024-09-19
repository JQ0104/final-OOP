import java.util.Scanner;

public class TestproductDetails {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        productDetails product = new productDetails();
        Stockbalance stock = new Stockbalance();
      
        String choice;

        // Menu loop
        while (true) {
            System.out.println("\n+-----------------------------+");
            System.out.println("|Product Management Menu:     |");
            System.out.println("+-----------------------------+");
            System.out.println("|1. Add Product               |");
            System.out.println("|2. Edit Product              |");
            System.out.println("|3. Read Products             |");
            System.out.println("|4. Delete Product            |");
            System.out.println("|5. Stock Balance             |");
            System.out.println("|6. Exit                      |");
            System.out.println("+-----------------------------+");
            System.out.print("\n\nPlease select an option: ");
            choice = sc.nextLine();

            switch (choice) {
                case "1":
                	//add product
                	do{
                   		// Collect product details
                   		System.out.println("\n=========Product Details=======");
         				System.out.println("+-----------------------------+");
                   		
                    	System.out.print("Enter the Product ID: ");
                    	String productID = sc.nextLine();

                    	System.out.print("Enter the Product Name: ");
                    	String productName = sc.nextLine();

                    	System.out.print("Enter the Product Price (RM): ");
                    	double productPrice = sc.nextDouble();
                    	sc.nextLine();

                    	System.out.print("Enter the Product Quantity: ");
                    	int productQty = sc.nextInt();
                    	sc.nextLine();

                    	System.out.print("Enter the Product Description: ");
                    	String productDescription = sc.nextLine();
					
            		
                    	// Call addProduct with the collected details
                    	product.addProduct(productID, productName, productPrice, productQty, productDescription);
                    
                    	System.out.print("\nDo you continue add the products?(Y/N) :");
                    	choice = sc.nextLine();
                    
                    }while (choice.equalsIgnoreCase("Y"));
                    break;

                case "2":
                    // Edit product
  					System.out.println("\n=========Product Details=======");
   					System.out.println("+-----------------------------+");
    				System.out.print("Enter the Product ID to edit: ");
    				String productIDToEdit = sc.nextLine();
    
				    product.editProduct(productIDToEdit, sc); 
                    break;

                case "3":
                    // Read products
                 	product.readProduct();
                    break;

                case "4":
                    // Delete product
                 	// Ask for the product ID to delete
    				System.out.print("\n\n=========Delete Product======== ");
    				System.out.print("\n--------------------------------");
    				System.out.print("\nEnter the Product ID to delete: ");
    				String productIDToDelete = sc.nextLine();
    				
    				product.deleteProduct(productIDToDelete, sc);
                    break;

                case "5":
                    // Stock Balance
					stock.checkStockBalance();
                    break;

                case "6":
                    // Exit program
                    System.out.println("Exiting the program.");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
    }
    
}
	