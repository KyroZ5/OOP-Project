	
package posSystem;

import java.util.*;

public class Users {
    public static ArrayList<Users> accts = new ArrayList<>();
	String username;
	String password; 
        String employeeName;
	Users(String username, String password, String employeeName){
		this.username = username;
		this.password = password;
		this.employeeName = employeeName;
	}
	public String getUsername() { 
		return username; }
        public String getPassword() { 
    	return password; }
        public String getEmployeeName() { 
    	return employeeName; }
   

	public void Users() {
            String line = null;
            while ((line != null)) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    Users user = new Users(parts[1], parts[2], parts[0]);
                    accts.add(user);
         }
      } 
   }
}