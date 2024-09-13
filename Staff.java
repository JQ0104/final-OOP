import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Staff extends User {
    private String staffID;
    private String staffPW;

    // Constructors
    public Staff() {}

    public Staff(String name, String telNo, String email, String staffID, String staffPW) {
        super(name, telNo, email);
        this.staffID = staffID;
        this.staffPW = staffPW;
    }

    // Getters and Setters
    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public String getStaffPW() {
        return staffPW;
    }

    public void setStaffPW(String staffPW) {
        this.staffPW = staffPW;
    }

    // toString method
    @Override
    public String toString() {
        return "Staff [staffID=" + staffID + ", staffPW=" + staffPW + ", " + super.toString() + "]";
    }

    // equals method
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Staff staff = (Staff) obj;
        return Objects.equals(staffID, staff.staffID) &&
               Objects.equals(staffPW, staff.staffPW);
    }

    // Override login validation method for Staff
    @Override
    public boolean loginValidation(List<? extends User> users, String id, String pw) {
        for (User user : users) {
            if (user instanceof Staff) {
                Staff staff = (Staff) user;
                if (staff.getStaffID().equals(id) && staff.getStaffPW().equals(pw)) {
                    return true;
                }
            }
        }
        return false;
    }

    // Static method to load Staff from a file
    public static List<Staff> loadStaffFromFile(String filePath) throws IOException {
        List<Staff> staffList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] details = line.split(",");
                if (details.length == 5) {
                    Staff staff = new Staff(details[0], details[1], details[2], details[3], details[4]);
                    staffList.add(staff);
                }
            }
        }
        return staffList;
    }

    // Method to save Staff details back to the file
    public void saveToFile(String filePath) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
            bw.write(super.getName() + "," + super.getTelNo() + "," + super.getEmail() + "," + staffID + "," + staffPW);
            bw.newLine();
        }
    }
}