package posSystem;

import java.util.ArrayList;

public class Users {
    public static ArrayList<Users> accts = new ArrayList<>();
    private static boolean Admin;
    private String username;
    private String pass;
    private String empName;

    public Users(String username, String pass, String empName) {
        this.username = username;
        this.pass = pass;
        this.empName = empName;
    }
    public static boolean isAdmin(){return Admin;}
    public String getUsername() { return username; }
    public String getPassword() { return pass; }
    public String getEmployeeName() { return empName; }
    public static void loadUser(ArrayList<String> lines) {
        accts.clear();
        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts.length == 3) {
                accts.add(new Users(parts[0], parts[1], parts[2]));
            }
        }
    }
    public static void setAdmin(boolean isAdmin){
        Users.Admin = isAdmin;
    }
    public static Users findUser(String username, String password) {
        for (Users u : accts) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                return u;
            }
        }
        return null;
    }
}