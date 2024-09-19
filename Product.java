import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Product {
	
	private String productID;
	private String productName;
	private double productPrice;
	private String productDescription;
	private int productQty;

    public Product() {
    	this("","",0.00,"",0);
    }
    
    public Product(String productID, String productName, double productPrice, String productDescription, int productQty) {
        this.productID = productID;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productQty = productQty;
        this.productDescription = productDescription;
    }

    public void setID(String productID){
    	this.productID = productID;
    }
    
    public String getID(){
    	return this.productID;
    }
    
    public void setName(String productName){
    	this.productName = productName;
    }
    
    public String getName(){
    	return this.productName;
    }
    
    public void setPrice(double productPrice) {
        this.productPrice = productPrice;
    }
    
    public double getPrice() {
        return this.productPrice;
    }
    
    public void setDescription(String productDescription) {
        this.productDescription = productDescription;
    }
    
    public String getDescription() {
        return this.productDescription;
    }
    
    public void setQty(int productQty) {
        this.productQty = productQty;
    }
    
    public int getQty() {
        return this.productQty;
    }
    
    public void displayProduct() {
    	Scanner sc = new Scanner(System.in);
    	try {
        	Scanner fileReader = new Scanner(new File("Product.txt"));
        	
        		//Header
        			System.out.println("+-------------------+-----------------------+---------------------------+----------------------------------------------+");
        			System.out.println("| Product ID        | Product Name          | Product Price (RM)        | Product Description                          |");
        			System.out.println("+-------------------+-----------------------+---------------------------+----------------------------------------------+");

       			// Read each line from the file
       	 		while (fileReader.hasNextLine()) {
            		String data = fileReader.nextLine();
            		String[] parts = data.split("\\|");  

            		if (parts.length >= 4) {
                		String productID = parts[1].trim();
                		String productName = parts[2].trim();
                		String productPrice = parts[3].trim();
                		String productDescription = parts[5].trim();

                		// Display the product details in a formatted table
                		System.out.printf("| %-17s | %-21s | %-25s | %-44s |\n", productID, productName, productPrice, productDescription);
            		}
        		}

        		// Footer
        			System.out.println("+-------------------+-----------------------+---------------------------+----------------------------------------------+");
        
        	fileReader.close();
        	
    	}catch (IOException e) {
        	System.out.println("Error reading the file.");
        	e.printStackTrace();
    	}
	}
	
	//check Product
	public void checkProduct() {
		Scanner sc = new Scanner(System.in);
		
		try {
        	Scanner fileReader = new Scanner(new File("Product.txt"));
        	
        		//Header
        		System.out.println("\n\n");
        		System.out.println("							   Product Menu         												 ");
				System.out.println("=====================================================================================================");
        		System.out.println("+-------------------+-----------------------+---------------------------+----------------------------------------------+");
        		System.out.println("| Product ID        | Product Name          | Product Price (RM)        | Product Description                          |");
       			System.out.println("+-------------------+-----------------------+---------------------------+----------------------------------------------+");

       			// Read each line from the file
       	 		while (fileReader.hasNextLine()) {
            		String data = fileReader.nextLine();
            		String[] parts = data.split("\\|");  

            		if (parts.length >= 4) {
                		String productID = parts[1].trim();
                		String productName = parts[2].trim();
                		String productPrice = parts[3].trim();
                		String productDescription = parts[5].trim();

                		// Display the product details in a formatted table
                		System.out.printf("| %-17s | %-21s | %-25s | %-44s |\n", productID, productName, productPrice, productDescription);
            		}
        		}

        		// Footer
        		System.out.println("+-------------------+-----------------------+---------------------------+----------------------------------------------+");
        
        	fileReader.close();
        	
    	}catch (IOException e) {
        	System.out.println("Error reading the file.");
        	e.printStackTrace();
    	}
    	
    	
    	System.out.print("\n\n========Check Product Stock Balance========");
    	System.out.print("\n--------------------------------------------\n");
    	System.out.print("Follow the product menu above to enter ID:");
    	String checkID = sc.nextLine().trim();;
    	
    	boolean found = false;
    	
    	try{
    		Scanner fileReader = new Scanner(new File("Product.txt"));
    		
    		while (fileReader.hasNextLine()){
    			String data = fileReader.nextLine();
                String[] parts = data.split("\\|"); 
                	
                	if (parts.length >= 4) { 
                    String productID = parts[1].trim();
                    String productQty = parts[4].trim();  
                    
                    // if peoduct ID same check ID  will display details
                    if (productID.equalsIgnoreCase(checkID)) {
                        found = true;
                        System.out.println("\n" + productID + " has a stock quantity of: " + productQty  + "\n");
                        break;
                    }
                }
    		}
    		if(!found){
    			System.out.println("\nNo product found with the ID: " + checkID + "\n");
    		}
    		
    		fileReader.close();	
    	}catch(IOException e){
    		System.out.println("Error reading the file.");
            e.printStackTrace();
    	}
	}
	   
}