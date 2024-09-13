
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Admin extends User {
    private String adminID;
    private String adminPW;

    // Constructors
    public Admin() {}

    public Admin(String name, String telNo, String email, String adminID, String adminPW) {
        super(name, telNo, email);
        this.adminID = adminID;
        this.adminPW = adminPW;
    }

    // Getters and Setters
    public String getAdminID() {
        return adminID;
    }

    public void setAdminID(String adminID) {
        this.adminID = adminID;
    }

    public String getAdminPW() {
        return adminPW;
    }

    public void setAdminPW(String adminPW) {
        this.adminPW = adminPW;
    }

    // toString method
    @Override
    public String toString() {
        return "Admin [adminID=" + adminID + ", adminPW=" + adminPW + ", " + super.toString() + "]";
    }

    // equals method
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Admin admin = (Admin) obj;
        return Objects.equals(adminID, admin.adminID) &&
               Objects.equals(adminPW, admin.adminPW);
    }

    // Override login validation method for Admins
    @Override
    public boolean loginValidation(List<? extends User> users, String id, String pw) {
        for (User user : users) {
            if (user instanceof Admin) {
                Admin admin = (Admin) user;
                if (admin.getAdminID().equals(id) && admin.getAdminPW().equals(pw)) {
                    return true;
                }
            }
        }
        return false;
    }

    // Static method to load Admins from a file
    public static List<Admin> loadAdminsFromFile(String filePath) throws IOException {
        List<Admin> admins = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] details = line.split(",");
                if (details.length == 5) {
                    Admin admin = new Admin(details[0], details[1], details[2], details[3], details[4]);
                    admins.add(admin);
                }
            }
        }
        return admins;
    }

    // Method to save Admin details back to the file
    public void saveToFile(String filePath) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
            bw.write(super.getName() + "," + super.getTelNo() + "," + super.getEmail() + "," + adminID + "," + adminPW);
            bw.newLine();
        }
    }
}
