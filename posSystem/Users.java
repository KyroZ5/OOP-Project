	
package posSystem;

import java.io.*;
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
   
    
	public void loadUsersFromFile() throws FileNotFoundException, IOException {
		
		try(BufferedReader br = new BufferedReader(new FileReader("users.txt"))){
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    Users user = new Users(parts[1], parts[2], parts[0]);
                    accts.add(user);
                }
            } 
               }
                System.out.println();
	}	
}