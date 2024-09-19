
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Assignment 
{
    
    private static final String ADMIN_FILE = "admins.txt";
    private static final String STAFF_FILE = "staff.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exitProgram = false;

        while (!exitProgram) {
            System.out.println("Welcome to the System");
            System.out.println("1. Admin Login");
            System.out.println("2. Staff Login");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    loginAsAdmin(scanner);
                    break;
                case 2:
                    loginAsStaff(scanner);
                    break;
                case 3:
                    System.out.println("Exiting the system...");
                    System.out.println("\t            _______ _                 _     __     __         ");
                System.out.println("\t           |__   __| |               | |    \\ \\   / /       ");
                System.out.println("\t              | |  | |__   __ _ _ __ | | __  \\ \\_/ /__  _   _ ");
                System.out.println("\t              | |  | '_ \\ / _` | '_ \\| |/ /   \\   / _ \\| | | |");
                System.out.println("\t              | |  | | | | (_| | | | |   <     | | (_) | |_| |");
                System.out.println("\t              |_|  |_| |_|\\__,_|_| |_|_|\\_\\    |_|\\___/ \\__,_|\n");
                System.out.println("\t          -----------THANK YOU FOR USING OUR SYSTEM-----------\n\n");
                System.out.println("\t          Press any key to exit the system . . . ");
                    exitProgram = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }

        scanner.close();
    }

    // Admin login process
    private static void loginAsAdmin(Scanner scanner) {
        try {
            // Load Admins from file
            List<Admin> admins = Admin.loadAdminsFromFile(ADMIN_FILE);

            System.out.print("Enter Admin ID: ");
            String adminID = scanner.nextLine();
            System.out.print("Enter Admin Password: ");
            String adminPW = scanner.nextLine();

            // Perform login validation
            Admin admin = new Admin();
            if (admin.loginValidation(admins, adminID, adminPW)) {
                System.out.println("Admin login successful!");
                adminActions(scanner);
            } else {
                System.out.println("Admin login failed. Invalid ID or password.");
            }
        } catch (IOException e) {
            System.out.println("Error loading admin file: " + e.getMessage());
        }
    }

    // Staff login process
    private static void loginAsStaff(Scanner scanner) {
        try {
            // Load Staff from file
            List<Staff> staffList = Staff.loadStaffFromFile(STAFF_FILE);

            System.out.print("Enter Staff ID: ");
            String staffID = scanner.nextLine();
            System.out.print("Enter Staff Password: ");
            String staffPW = scanner.nextLine();

            // Perform login validation
            Staff staff = new Staff();
            if (staff.loginValidation(staffList, staffID, staffPW)) {
                System.out.println("Staff login successful!");
                staffActions(scanner);
            } else {
                System.out.println("Staff login failed. Invalid ID or password.");
            }
        } catch (IOException e) {
            System.out.println("Error loading staff file: " + e.getMessage());
        }
    }

    // Actions for Admin after successful login
    private static void adminActions(Scanner scanner) {
        boolean backToMainMenu = false;

        while (!backToMainMenu) {
            System.out.println("Admin Menu:");
            System.out.println("1. View Staff ");
            System.out.println("2. View  ");
            System.out.println("3. Logout");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
			Admin ad = new Admin();
			
            switch (choice) {
            	
            	
                case 1:
                    ad.checkStaff();
                    System.out.println("Viewing staff...");
                    break;
                case 2:
          
                    System.out.println("Viewing admin...");
                    break;
                case 3:
                    System.out.println("Logging out...");
                    System.out.println("\t            _______ _                 _     __     __         ");
                System.out.println("\t           |__   __| |               | |    \\ \\   / /       ");
                System.out.println("\t              | |  | |__   __ _ _ __ | | __  \\ \\_/ /__  _   _ ");
                System.out.println("\t              | |  | '_ \\ / _` | '_ \\| |/ /   \\   / _ \\| | | |");
                System.out.println("\t              | |  | | | | (_| | | | |   <     | | (_) | |_| |");
                System.out.println("\t              |_|  |_| |_|\\__,_|_| |_|_|\\_\\    |_|\\___/ \\__,_|\n");
                System.out.println("\t          -----------THANK YOU FOR USING OUR SYSTEM-----------\n\n");
                System.out.println("\t          Press any key to exit the system . . . ");
                    backToMainMenu = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    // Actions for Staff after successful login
    private static void staffActions(Scanner scanner) {
        boolean backToMainMenu = false;

        while (!backToMainMenu) {
            System.out.println("Staff Menu:");
            System.out.println("1. View Admin");
            System.out.println("2. Perform Staff Actions");
            System.out.println("3. Logout");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
			Staff st = new Staff();
			
            switch (choice) {
                case 1:
                    st.checkAdmin();
                    System.out.println("Viewing staff profile...");
                    break;
                case 2:
                    // Add additional staff actions here
                    System.out.println("Performing staff actions...");
                    break;
                case 3:
                    System.out.println("Logging out...");
                    System.out.println("\t            _______ _                 _     __     __         ");
                System.out.println("\t           |__   __| |               | |    \\ \\   / /       ");
                System.out.println("\t              | |  | |__   __ _ _ __ | | __  \\ \\_/ /__  _   _ ");
                System.out.println("\t              | |  | '_ \\ / _` | '_ \\| |/ /   \\   / _ \\| | | |");
                System.out.println("\t              | |  | | | | (_| | | | |   <     | | (_) | |_| |");
                System.out.println("\t              |_|  |_| |_|\\__,_|_| |_|_|\\_\\    |_|\\___/ \\__,_|\n");
                System.out.println("\t          -----------THANK YOU FOR USING OUR SYSTEM-----------\n\n");
                System.out.println("\t          Press any key to exit the system . . . ");
                    backToMainMenu = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}

