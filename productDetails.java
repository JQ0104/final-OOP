import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class productDetails {

    private String productID;
    private String productName;
    private double productPrice;
    private String productDescription;
    private int productQty;

    public productDetails() {
        this("", "", 0.00, 0, "");
    }

    public productDetails(String productID, String productName, double productPrice, int productQty, String productDescription) {
        this.productID = productID;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productQty = productQty;
        this.productDescription = productDescription;
    }

    public void setID(String productID) {
        this.productID = productID;
    }

    public void setName(String productName) {
        this.productName = productName;
    }

    public void setPrice(double productPrice) {
        this.productPrice = productPrice;
    }
    
    public void setQty(int productQty) {
        this.productQty = productQty;
    }

    public void setDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getID() {
        return this.productID;
    }

    public String getName() {
        return this.productName;
    }

    public double getPrice() {
        return this.productPrice;
    }
    
    public int getQty() {
        return this.productQty;
    }

    public String getDescription() {
        return this.productDescription;
    }
    
    
    ///Add Product
    public void addProduct(){
    	 Scanner sc = new Scanner(System.in);
    	 productDetails product = new productDetails();
    	 String choice;
    	 
    	 do{
    	 System.out.println("\n=========Product Details=======");
         System.out.println("+-----------------------------+");
         System.out.print("Enter the Product ID:");
         product.setID(sc.nextLine());
         
         System.out.print("Enter the Product Name: ");
         product.setName(sc.nextLine());
         
         System.out.print("Enter the Product Price(RM): ");
         product.setPrice(sc.nextDouble());
         sc.nextLine();
         
         System.out.print("Enter the Product Quantity: ");
         product.setQty(sc.nextInt());
         sc.nextLine();

         System.out.print("Enter the Product Description: ");
         product.setDescription(sc.nextLine());

		System.out.println("+-------------------+-----------------------+---------------------------+---------------------------+-------------------------------+");
        System.out.println("| Product ID        | Product Name          | Product Price (RM)        | Product Quantity			| Product Description           |");
        System.out.println("+-------------------+-----------------------+---------------------------+---------------------------+-------------------------------+");
        System.out.printf("| %-17s | %-21s | %-25.2f |%-27s| %-29s |\n", product.getID(), product.getName(), product.getPrice(), product.getQty(), product.getDescription());
        System.out.println("+-------------------+-----------------------+---------------------------+---------------------------+-------------------------------+");

		// Write the product details to the txt file
        try {
            FileWriter myWriter = new FileWriter("Product.txt", true);
                        
            int idWidth = 17;
            int nameWidth = 21;
            int priceWidth = 25;
            int qtyWidth = 25;
            int descWidth = 29;

            // Write product details in a formatted manner
            String formattedLine = String.format(
                "| %-"+idWidth+"s | %-"+nameWidth+"s | %-"+priceWidth+".2f | %-"+qtyWidth+"d | %-"+descWidth+"s |\n", 
                product.getID(), 
                product.getName(), 
                product.getPrice(), 
                product.getQty(),
                product.getDescription()
            );

            myWriter.write(formattedLine);

           
			myWriter.close();
                        System.out.println("Product added successfully!\n");
                    } catch (IOException e) {
                        System.out.println("Error, please try again!");
                        e.printStackTrace();
                    }
                    
                    System.out.print("\nDo you continue add the products?(Y/N) :");
                    choice = sc.nextLine();
                    }while (choice.equalsIgnoreCase("Y"));
    }

         
    ///Edit Product
   public void editProduct() {
    	Scanner sc = new Scanner(System.in);

    	// Ask for the product ID to edit
    	//Header
        System.out.print("\n\n=========Edit Product======== ");
    	System.out.print("\n--------------------------------");
    	System.out.print("\nEnter the Product ID to edit: ");
    	String productIDToEdit = sc.nextLine();

    	File inputFile = new File("Product.txt");
   		File tempFile = new File("tempProduct.txt");

    	boolean productFound = false;

    	try (Scanner fileReader = new Scanner(inputFile);
        FileWriter myWriter = new FileWriter(tempFile, true)) {

        // Reading the file line by line
        while (fileReader.hasNextLine()) {
            String line = fileReader.nextLine();

            if (line.contains("| " + productIDToEdit + " ")) {
                productFound = true;
                

                // Print current product details
                System.out.println("+-------------------+-----------------------+---------------------------+---------------------------+-------------------------------+");
        		System.out.println("| Product ID        | Product Name          | Product Price (RM)        | Product Quantity			| Product Description           |");
        		System.out.println("+-------------------+-----------------------+---------------------------+---------------------------+-------------------------------+");
                System.out.println(line);
                System.out.println("+-------------------+-----------------------+---------------------------+---------------------------+-------------------------------+");


                // Prompt the user for new details
                System.out.print("\nEnter new Product Name (leave empty to keep current): ");
                String newName = sc.nextLine();
                System.out.print("Enter new Product Price (leave empty to keep current): ");
                String newPriceStr = sc.nextLine();
                System.out.print("Enter new Product Quantity (leave empty to keep current): ");
                String newQtyStr = sc.nextLine();
                System.out.print("Enter new Product Description (leave empty to keep current): ");
                String newDescription = sc.nextLine();

                // Split the current line to extract existing details
                String[] parts = line.split("\\|");

                // Assign new details if provided, otherwise keep current
                String updatedName = newName.isEmpty() ? parts[2].trim() : newName;
                double updatedPrice = newPriceStr.isEmpty() ? Double.parseDouble(parts[3].trim()) : Double.parseDouble(newPriceStr);
                int updatedQty = newQtyStr.isEmpty() ? Integer.parseInt(parts[4].trim()) : Integer.parseInt(newQtyStr);
                String updatedDescription = newDescription.isEmpty() ? parts[5].trim() : newDescription;

                // Format the updated product details
                String formattedLine = String.format(
                    "| %-17s | %-21s | %-25.2f | %-25d | %-29s |",
                    productIDToEdit,
                    updatedName,
                    updatedPrice,
                    updatedQty,
                    updatedDescription
                );

                // Write the updated product details to the temporary file
                myWriter.write(formattedLine + "\n");
            } else {
                // Write the unchanged line to the temporary file
                myWriter.write(line + "\n");
            }
        }
    	} catch (IOException e) {
        	System.out.println("\nError occurred while editing the product.");
        	e.printStackTrace();
    	}

    	// If product was found, replace the original file with the updated temp file
    	if (productFound) {
        	if (inputFile.delete()) {
            	tempFile.renameTo(inputFile);
            	System.out.println("\nProduct updated successfully.\n");
        	} else {
            	System.out.println("\nError deleting the original file.");
        	}
    	} else {
        	System.out.println("\nProduct ID not found.");
        	tempFile.delete();
    	}
	}
	
	// Read Product
	public void readProduct(){
		String select;
		try{
			Scanner fileReader = new Scanner(new File("Product.txt"));
			
			System.out.println("+-------------------+-----------------------+---------------------------+---------------------------+-------------------------------+");
        	System.out.println("|=== Product ID ====|==== Product Name =====|=== Product Price (RM) ====|==== Product Quantity =====|==== Product Description ======|");
        	System.out.println("+-------------------+-----------------------+---------------------------+---------------------------+-------------------------------+");
			
			while(fileReader.hasNextLine()){
				String data = fileReader.nextLine();
				System.out.println(data);
			}
			System.out.println("+-------------------+-----------------------+---------------------------+---------------------------+-------------------------------+");
			
			
			fileReader.close();
		}catch(IOException e){
			System.out.println("Error reading the file.");
            e.printStackTrace();
		}
	}
	
	//Delete/Cancel Product
	public void deleteProduct() {
    Scanner sc = new Scanner(System.in);

    // Ask for the product ID to delete
    System.out.print("\n\n=========Delete Product======== ");
    System.out.print("\n--------------------------------");
    System.out.print("\nEnter the Product ID to delete: ");
    String productIDToDelete = sc.nextLine();

    File inputFile = new File("Product.txt");
    File tempFile = new File("tempProduct.txt");

    boolean productFound = false;

    try (Scanner fileReader = new Scanner(inputFile);
         FileWriter myWriter = new FileWriter(tempFile, true)) {

        // Reading the file line by line
        while (fileReader.hasNextLine()) {
            String line = fileReader.nextLine();

            // If the line contains the product ID, skip writing it to the temp file
            if (line.contains("| " + productIDToDelete + " ")) {
                productFound = true;
            } else {
                // Write all other lines to the temporary file
                myWriter.write(line + "\n");
            }
        }

    } catch (IOException e) {
        System.out.println("Error occurred while deleting the product.");
        e.printStackTrace();
    }

    // If the product was found, replace the original file with the updated temp file
    if (productFound) {
        if (inputFile.delete()) {
            tempFile.renameTo(inputFile);
            System.out.println("\nProduct deleted successfully.\n");
        } else {
            System.out.println("Error deleting the original file.");
        }
    } else {
        System.out.println("\nProduct ID not found.\n");
        tempFile.delete();
    }
}
        
    
}
