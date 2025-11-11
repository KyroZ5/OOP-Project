package posSystem;
import java.io.*;
import java.util.*;
class Users {
	ArrayList <Users> accts = new ArrayList<>();
	private String username;
	private String password; 
	private String employeeName;
	Users(String username, String password, String employeeName){
		this.username = username;
		this.password = password;
		this.employeeName = employeeName;
	}

	public void loadUsersFromFile() throws FileNotFoundException, IOException {
		
		try(BufferedReader br = new BufferedReader(new FileReader("users.txt"))){
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) { 
                    accts.add("hahaha").;
                    username.add(parts[1]);
                    password.add(parts[2]);
                }
            }
            int size = username.size();
		}
	}
}
