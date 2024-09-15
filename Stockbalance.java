import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Stockbalance {
	
	private String productID;
	private String productName;
	private double productPrice;
	private int productQty;

    public Stockbalance() {
    	this("","",0.00,0);
    }
    
    public Stockbalance(String productID, String productName, double productPrice, int productQty){
    	this.productID = productID;
    	this.productName = productName;
    	this.productPrice = productPrice;
    	this.productQty = productQty;
    }
    
    public void setID(String productID){
    	this.productID = productID;
    }
    
    public void setName(String Name){
    	this.productName = productName;
    }
    
    public void setPrice(double productPrice){
    	this.productPrice = productPrice;
    }
    
    public void setQty(int productQty){
    	this.productQty = productQty;
    }
    
    public String getID(){
        return this.productID;
    }
    
    public String getName(){
        return this.productName;
    }
    
    public double getPrice(){
    	return this.productPrice;
    }
    public int getQty(){
    	return this.productQty;
    }
    
    public void checkStockBalance() {
    try {
        Scanner fileReader = new Scanner(new File("Product.txt"));
		
		System.out.println("\n\n");
		System.out.println("							   Checking	Stock Balance												 ");
		System.out.println("=====================================================================================================");
        System.out.println("+-------------------+-----------------------+---------------------------+---------------------------+");
        System.out.println("|=== Product ID ====|==== Product Name =====|=== Product Price (RM) ====|==== Product Quantity =====|");
        System.out.println("+-------------------+-----------------------+---------------------------+---------------------------+");
		
		
        while (fileReader.hasNextLine()) {
            String data = fileReader.nextLine();
            
            // Split the line by '|' to get individual columns
            String[] parts = data.split("\\|");

            if (parts.length >= 5) {
                // Extract productID, productName, productPrice, and productQty
                String productID = parts[1].trim();
                String productName = parts[2].trim();
                String productPrice = parts[3].trim();
                String productQty = parts[4].trim();

                // Display the extracted information
                System.out.printf("| %-17s | %-21s | %-25s | %-25s |\n", productID, productName, productPrice, productQty);
            }
        }

        System.out.println("+-------------------+-----------------------+---------------------------+---------------------------+");
		System.out.println("\n\n");
		
        fileReader.close();
    } catch (IOException e) {
        System.out.println("Error reading the file.");
        e.printStackTrace();
    }
}

}