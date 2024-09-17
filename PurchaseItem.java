import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

public class PurchaseItem extends PurchaseOrder {
    private String productName;
    private double productPrice;
    private static int calTotalPrice;

    // Default constructor
    public PurchaseItem() {
        this.productName = "";
        this.productPrice = 0.0;
    }

    // Getter and Setter methods
    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return this.productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }
    
    //display the order
    public void displayOrderHistory(){
    	String select;
		try{
			Scanner fileReader = new Scanner(new File("PurchaseOrder.txt"));
			
			
			
			while(fileReader.hasNextLine()){
				String info = fileReader.nextLine();
				
				String[] parts = info.split("\\|");
                String currentID = parts[0].trim();
                String productName = parts[1].trim();
                double productPrice = Double.parseDouble(parts[2].trim());
                int productQty = Integer.parseInt(parts[3].trim());
                String orderDate = parts[4].trim();
			
	      		System.out.println("+------------------------------+");
                System.out.printf("| Order ID : %s           |\n",currentID);
                System.out.println("+------------------------------+---------------------------+------------------------+------------------------------------+");
                System.out.println("| Product Name                 | Order Price (RM)          |     Order Quantity     |              OrderDate             |");
                System.out.println("+------------------------------+---------------------------+------------------------+------------------------------------+");

				
				System.out.printf("| %-28s | %-25.2f | %-22d | %-34s |\n",productName,productPrice,productQty,orderDate);
				
			}
			System.out.println("+------------------------------+---------------------------+------------------------+------------------------------------+");
			
			
			fileReader.close();
		}catch(IOException e){
			System.out.println("Error reading the file.");
            e.printStackTrace();
		}
    }

    // Calculate the total
  public void displayTotalPrice() {
  	double totalPrice = 0.00;
  	String currentID = "";
  	boolean isFirstOrder = true; //to detect new order
  	
  	try{
			Scanner fileReader = new Scanner(new File("PurchaseOrder.txt"));
			
			
			while(fileReader.hasNextLine()){
				String info = fileReader.nextLine();
				
				String[] parts = info.split("\\|");
                String orderID = parts[0].trim();
                String productName = parts[1].trim();
                double productPrice = Double.parseDouble(parts[2].trim());
                int productQty = Integer.parseInt(parts[3].trim());
                String orderDate = parts[4].trim();
                
                if(isFirstOrder || !orderID.equals(currentID)){
                	if(!isFirstOrder){
                		System.out.printf("|Total Price for Order ID %s: RM %-25.2f\n", currentID, totalPrice);
                        System.out.println("+------------------------------+---------------------------+------------------------+------------------------------------+");
                	}
               
                
                currentID = orderID;
                totalPrice = 0.00; //reset total price for new order
                isFirstOrder = false;
			
	      		System.out.println("+------------------------------+");
                System.out.printf("| Order ID : %s           |\n",currentID);
                System.out.println("+------------------------------+---------------------------+------------------------+------------------------------------+");
                System.out.println("| Product Name                 | Order Price (RM)          |     Order Quantity     |              OrderDate             |");
                System.out.println("+------------------------------+---------------------------+------------------------+------------------------------------+");
                
                }
				
				System.out.printf("| %-28s | %-25.2f | %-22d | %-34s |\n",productName,productPrice,productQty,orderDate);
				System.out.println("+------------------------------+---------------------------+------------------------+------------------------------------+");
				
				totalPrice += productPrice * productQty;
				
			
		 }
			    
				System.out.printf("| Total Price                  | RM%-25.2f                                                             |\n",totalPrice);
				System.out.println("+------------------------------+---------------------------+------------------------+------------------------------------+");
			
			fileReader.close();
		}catch(IOException e){
			System.out.println("Error reading the file.");
            e.printStackTrace();
		}
}

}

