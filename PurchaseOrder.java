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
    public void addPurOrder(String purchaseOrderName, int purOrderQty) {
        Scanner sc = new Scanner(System.in);
        PurchaseOrder po = new PurchaseOrder();
        PurchaseItem item = new PurchaseItem();
        Product product = new Product();
        boolean prodFound = false;
        String generatedOrderID = generateOrderID();
        product.setName(purchaseOrderName);
        setOrderQty(purOrderQty);
        String choice;
        
        File orderFile = new File("PurchaseOrder.txt");
        
        try (Scanner productReader = new Scanner(new File("Product.txt"))) {
        	
        	Scanner fileReader = new Scanner(new File("Product.txt"));
                
                try (FileWriter myWriter = new FileWriter(orderFile, true)) {
                    while (fileReader.hasNextLine()) {
                        String info = fileReader.nextLine();
                        if (info.contains("| " + product.getName() + " ")) {
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


                                System.out.printf("| %-28s | %-25.2f | %-22d | %-34s |\n", product.getName(), productPrice, getOrderQty(), orderDate);
                                System.out.println("+------------------------------+---------------------------+------------------------+------------------------------------+");
                                
                                myWriter.write(String.format("%s | %s | %.2f | %d | %s |\n", currentID, product.getName(), productPrice, orderQty, orderDate));
                                System.out.println("Order added successfully.");
                                
                                
                            }
                            break;
                        }
                    }
                    
                    if (!prodFound) {
                        System.out.println("There is no such product available. Please purchase the product that is available.");
                        
                    }
                }
                
            } catch (IOException e) {
                System.out.println("Error reading the file.");
                e.printStackTrace();
            }
            
        
        	
    }
    
    // Edit Order
    public void editPurchaseOrder(String orderIDToEdit) {
        Scanner sc = new Scanner(System.in);
        PurchaseItem item = new PurchaseItem();

        File inputFile = new File("PurchaseOrder.txt");
        File tempFile = new File("PurOrTempFile.txt");

        boolean productFound = false;

        try (Scanner fileReader = new Scanner(inputFile);
             FileWriter myWriter = new FileWriter(tempFile, true)) {

            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();

                
                String[] parts = line.split("\\|");
                if (parts.length >= 5) {  
                    String orderID = parts[0].trim();
                    String productName = parts[1].trim();
                    double productPrice = Double.parseDouble(parts[2].trim());
                    int productQty = Integer.parseInt(parts[3].trim());

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

                        // Write updated data into temp file
                        String formattedLine = String.format("%s | %s | %.2f | %d | %s", orderID, productName, productPrice, updatedQty, parts[4].trim());
                        myWriter.write(formattedLine + "\n");
                    } else {
                        // Write the unmodified data into the temp file
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
        
            
    }

    // Delete Order
    public void deletePurchasedOrder(String orderIDToDel) {
        Scanner sc = new Scanner(System.in);
        PurchaseItem item = new PurchaseItem();
        
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
        
            
    }
    
            

}
