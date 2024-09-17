import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;
import java.util.UUID;

public class PurchaseOrder extends Product {
    private String POID;
    private Date orderDate;
    private double orderPrice;
    private String orderLocation;
    private int orderQty;
    private Product[] products;

    public PurchaseOrder() {
        this.POID = generateOrderID(); 
        orderDate = new Date();
    }

    public PurchaseOrder(String productID, String productName, double productPrice, String productDescription, int productQty,
                         String POID, double orderPrice, String orderLocation, int orderQty) {
        super(productID, productName, productPrice, productDescription, productQty);
        this.POID = generateOrderID();
        this.orderPrice = orderPrice;
        this.orderLocation = orderLocation;
        this.orderQty = orderQty;
        this.orderDate = new Date();
    }

    // Generate a random 4-digit order ID
    public String generateOrderID() {
        int randomOrderID = (int)(Math.random() * 9000) + 1000;  // 4-digit random number
        return String.format("ORD%04d", randomOrderID);  // Ensure it's 4 digits
    }

    public void setPOID(String POID) {
        this.POID = generateOrderID();
    }

    public double getOrderPrice() {
        return this.orderPrice;
    }

    public void setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getOrderLocation() {
        return this.orderLocation;
    }

    public void setOrderLocation(String orderLocation) {
        this.orderLocation = orderLocation;
    }

    public int getOrderQty() {
        return this.orderQty;
    }

    public void setOrderQty(int orderQty) {
        this.orderQty = orderQty;
    }

    // Add purchase order and other methods...


    
    // Add order
    public void addPurchaseOrder() {
        Scanner sc = new Scanner(System.in);
        PurchaseOrder po = new PurchaseOrder();
        PurchaseItem item = new PurchaseItem();
        boolean prodFound = false;
        String generatedOrderID = generateOrderID();
        String choice;
        
        File orderFile = new File("PurchaseOrder.txt");
        
        do {
            try (Scanner productReader = new Scanner(new File("Product.txt"))) {
                // Display the product sold 
                System.out.println("+-------------------+-----------------------+---------------------------+---------------------------+-------------------------------+");
                System.out.println("|=== Product ID ====|==== Product Name =====|=== Product Price (RM) ====|==== Product Quantity =====|==== Product Description ======|");
                System.out.println("+-------------------+-----------------------+---------------------------+---------------------------+-------------------------------+");
            
                while (productReader.hasNextLine()) {
                    String data = productReader.nextLine();
                    System.out.println(data);
                
                    System.out.println("+-------------------+-----------------------+---------------------------+---------------------------+-------------------------------+");
                }
                
                Scanner fileReader = new Scanner(new File("Product.txt"));
                
                System.out.println(" ====================================================");
                System.out.println("|                  Purchase Order                    | ");
                System.out.println(" ====================================================");
                
                System.out.printf("Order id: %s\n", generatedOrderID);
                
                System.out.print("Enter the product name you want to purchase: ");
                String purchasedProdName = sc.nextLine();
                System.out.print("Please enter the quantity: ");
                po.setOrderQty(sc.nextInt());
                sc.nextLine();
                
                try (FileWriter myWriter = new FileWriter(orderFile, true)) {
                    while (fileReader.hasNextLine()) {
                        String info = fileReader.nextLine();
                        if (info.contains("| " + purchasedProdName + " ")) {
                            prodFound = true;
                            
                            String[] parts = info.split("\\|");
                            String productID = parts[1].trim();
                            String productName = parts[2].trim();
                            double productPrice = Double.parseDouble(parts[3].trim());
                            int productQty = Integer.parseInt(parts[4].trim());
                            
                            if (po.getOrderQty() > productQty) {
                                System.out.println("The quantity of product sold is not enough.");
                            }
                             else {
                             	String currentID = generatedOrderID;
                                
                                System.out.println("+------------------------------+");
                                System.out.printf("| Order ID : %s           |\n",currentID);
                                System.out.println("+------------------------------+---------------------------+------------------------+------------------------------------+");
                                System.out.println("| Product Name                 | Order Price (RM)          |     Order Quantity     |              OrderDate             |");
                                System.out.println("+------------------------------+---------------------------+------------------------+------------------------------------+");


                                System.out.printf("| %-28s | %-25.2f | %-22d | %-34s |\n", productName, productPrice, po.getOrderQty(), orderDate);
                                System.out.println("+------------------------------+---------------------------+------------------------+------------------------------------+");
                                
                                myWriter.write(String.format("%s | %s | %.2f | %d | %s |\n", currentID, productName, productPrice, po.getOrderQty(), orderDate));
                                System.out.println("Order added successfully.");
                                
                                
                            }
                            break;
                        }
                    }
                    
                    if (!prodFound) {
                        System.out.println("There is no such product available. Please purchase the product that is available.");
                        addPurchaseOrder();
                    }
                }
            } catch (IOException e) {
                System.out.println("Error reading the file.");
                e.printStackTrace();
            }
            
            System.out.print("Are there any orders that you want to add? (Y for Yes, enter any key for exiting the Purchase Order function): ");
            choice = sc.nextLine();
            
            System.out.print("\n");
            
        } while (choice.equalsIgnoreCase("Y"));
        
        	System.out.println("+-----------------------------+");
            System.out.println("|Order management Menu:       |");
            System.out.println("+-----------------------------+");
            System.out.println("|1. Edit Order                |");
            System.out.println("|2. Delete Order              |");
            System.out.println("|3. View total price   	      |");
            System.out.println("+-----------------------------+");
            
            System.out.println("Please select your choice: ");
            String selection = sc.nextLine();
            
            switch(selection){
            
            	case "1":
            		editPurchaseOrder();
            		break;
            		
                case "2":
                	deletePurchasedOrder();
                	break;
                	
                case "3":
                    item.displayTotalPrice();
                    break;
                   
                default:
                	System.out.println("Invalid option, please try again.");
            }
    }
    
    // Edit Order
    public void editPurchaseOrder() {
        Scanner sc = new Scanner(System.in);
        PurchaseItem item = new PurchaseItem();

        // Ask for the order ID to edit
        System.out.println(" ====================================================================== ");
        System.out.println("|                            Edit Order                               |");
        System.out.println(" ======================================================================");
        System.out.print("\nEnter the Order ID to Edit: ");
        String orderIDToEdit = sc.nextLine().trim();  // Trim any leading/trailing spaces

        File inputFile = new File("PurchaseOrder.txt");
        File tempFile = new File("PurOrTempFile.txt");

        boolean productFound = false;

        try (Scanner fileReader = new Scanner(inputFile);
             FileWriter myWriter = new FileWriter(tempFile, true)) {

            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();

                // Splitting using '|' as delimiter and ensuring correct parts count
                String[] parts = line.split("\\|");
                if (parts.length >= 5) {  // Ensure the line has at least 5 parts (Order ID, Product Name, Product Price, Order Quantity, Order Date)
                    String orderID = parts[0].trim();
                    String productName = parts[1].trim();

                    // Add try-catch to handle any potential issues when parsing the product price
                    double productPrice;
                    try {
                        productPrice = Double.parseDouble(parts[2].trim());
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Invalid product price for order ID: " + orderID);
                        continue;  // Skip this line and move to the next one
                    }

                    int productQty;
                    try {
                        productQty = Integer.parseInt(parts[3].trim());
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Invalid product quantity for order ID: " + orderID);
                        continue;  // Skip this line and move to the next one
                    }

                    if (orderID.equalsIgnoreCase(orderIDToEdit)) {
                        productFound = true;

                        // Print current product details
                        System.out.println("+-------------------+-----------------------+---------------------------+---------------------------+-------------------------------+");
                        System.out.println("| Order ID          | Product Name          | Product Price (RM)        | Order Quantity            | Order Date                    |");
                        System.out.println("+-------------------+-----------------------+---------------------------+---------------------------+-------------------------------+");
                        System.out.printf("| %-17s | %-21s | %-25.2f | %-25d | %-21s |\n", orderID, productName, productPrice, productQty, parts[4].trim());
                        System.out.println("+-------------------+-----------------------+---------------------------+---------------------------+-------------------------------+");

                        // Ask for new quantity
                        System.out.print("Enter new order quantity (leave empty to keep current): ");
                        String newQty = sc.nextLine();

                        int updatedQty = newQty.isEmpty() ? productQty : Integer.parseInt(newQty);

                        // Write updated data to temp file
                        String formattedLine = String.format("%s | %s | %.2f | %d | %s", orderID, productName, productPrice, updatedQty, parts[4].trim());
                        myWriter.write(formattedLine + "\n");
                    } else {
                        // Write the unmodified data to the temp file
                        myWriter.write(line + "\n");
                    }
                } else {
                    // Log any lines that don't have the expected number of parts
                    System.out.println("Error: Invalid line format in order file: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("\nError occurred while editing the order.");
            e.printStackTrace();
        }

        if (productFound) {
            // Delete the original file and rename the temporary file to the original name
            if (inputFile.delete()) {
                tempFile.renameTo(inputFile);
                System.out.println("\nOrder updated successfully.\n");
            } else {
                System.out.println("\nError deleting the original file.");
            }
        } else {
            System.out.println("\nOrder ID not found.");
            tempFile.delete();  // Cleanup temporary file if no matching order was found
        }
        
            System.out.println("+-----------------------------+");
            System.out.println("|Order management Menu:       |");
            System.out.println("+-----------------------------+");
            System.out.println("|1. Edit again                |");
            System.out.println("|2. Delete Order              |");
            System.out.println("|3. View total price   	      |");
            System.out.println("+-----------------------------+");
            
            System.out.println("Please select your choice: ");
            String selection = sc.nextLine();
            
            switch(selection){
            
            	case "1":
            		editPurchaseOrder();
            		break;
            		
                case "2":
                	deletePurchasedOrder();
                	break;
                	
                case "3":
                    item.displayTotalPrice();
                    break;
                   
                default:
                	System.out.println("Invalid option, please try again.");
            }
    }

    // Delete Order
    public void deletePurchasedOrder() {
        Scanner sc = new Scanner(System.in);
        PurchaseItem item = new PurchaseItem();
        
        System.out.println(" =================================================");
        System.out.println("|             Delete Purchased Order              |");
        System.out.println(" =================================================");
        
        System.out.print("\nEnter the Order ID to delete: ");
        String orderIDToDel = sc.nextLine();
        
        File inputFile = new File("PurchaseOrder.txt");
        File tempFile = new File("PurOrTempFile.txt");
        
        boolean prodFound = false;
        
        try (Scanner fileReader = new Scanner(inputFile);
             FileWriter myWriter = new FileWriter(tempFile, true)) {
                
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                
                String[] parts = line.split("\\|");
                if (parts.length > 0) {
                    String orderID = parts[0].trim();
                    
                    if (orderID.equalsIgnoreCase(orderIDToDel)) {
                        prodFound = true;
                    } else {
                        myWriter.write(line + "\n");
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error occurred while deleting the order selected.");
            e.printStackTrace();
        }
        
        if (prodFound) {
            if (inputFile.delete()) {
                tempFile.renameTo(inputFile);
                System.out.println("\nThe order is successfully deleted.");
            } else {
                System.out.println("Error occurred when deleting the order in the original file.");
            }
        } else {
            System.out.println("\nOrder ID not found.");
            tempFile.delete();
        }
        
        System.out.println("+-----------------------------+");
            System.out.println("| Menu:                       |");
            System.out.println("+-----------------------------+");
            System.out.println("|1. Edit order                |");
            System.out.println("|2. View total price   	      |");
            System.out.println("+-----------------------------+");
            
            System.out.println("Please select your choice: ");
            String selection = sc.nextLine();
            
            switch(selection){
            
            	case "1":
            		editPurchaseOrder();
            		break;
                	
                case "2":
                    item.displayTotalPrice();
                    break;
                   
                default:
                	System.out.println("Invalid option, please try again.");
            }
    }
    
            

}
