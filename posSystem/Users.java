	
package posSystem;

import java.util.*;


class Users {
	public static ArrayList <Users> accts = new ArrayList<>();
	String username, pass, empName;
	private double balance;
	Users (String username,String pass, String empName){
		this.username = username;
		this.pass = pass;
		this.empName = empName;
	}
	public String getUsername() { 
		return username; }
        public String getPassword() { 
    	return pass; }
        public String getEmployeeName() { 
    	return empName; }
   

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
	public String[] split(String string) {
		// TODO Auto-generated method stub
		return null;
	}
}