/**
 * @(#)User.java
 *
 *
 * @author 
 * @version 1.00 2024/9/8
 */
import java.util.Objects;
import java.util.List;

public abstract class User {
	private String name;
	private String telNo;
	private String email;
	
	//constructors
    public User() {
    	this("","","");
    }
    
    public User(String name, String telNo, String email){
    	this.name = name;
    	this.telNo = telNo;
    	this.email = email;
    }
    
    //Getter
    public String getName(){
    	return name;
    }
    
    public String getTelNo(){
    	return telNo;
    }
    
    public String getEmail(){
    	return email;
    }
    
    //setter
    public void setName(String name){
    	this.name = name;
    }
    
    public void setTelNo(String telNo){
    	this.telNo = telNo;
    }
    
    public void setEmail(String email){
    	this.email = email;
    }
    
    //ToString
    @Override
    public String toString(){
    	return "Name : " + name + ", TelNo : " + telNo + ", Email: " + email;	
    }
    
    //equals method
     public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        User user = (User) obj;
        return Objects.equals(name, user.name) &&
               Objects.equals(telNo, user.telNo) &&
               Objects.equals(email, user.email);
    }
    
    // Abstract method for login validation
    public abstract boolean loginValidation(List<? extends User> users, String id, String pw);
}