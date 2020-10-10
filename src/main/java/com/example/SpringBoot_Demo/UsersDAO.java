package com.example.SpringBoot_Demo;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

@Component
public class UsersDAO {
    Scanner sc = new Scanner(System.in);
    public static ArrayList<User> userArr = new ArrayList<>();


    public void addUser(User userObj) {
        try {
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "karan", "1234");
            Statement myStmt = myConn.createStatement();
            ResultSet rs = myStmt.executeQuery("SELECT user_id FROM users ORDER BY user_id DESC LIMIT 1");
            int id = 0;
            while(rs.next()) {
                id = rs.getInt("user_id") + 1;
            }
            String insertInput = id + ", '" + userObj.getFirstName() + "' , '" + userObj.getLastName() + "', '" + userObj.getEmail() + "', '" + userObj.getPassword() + "', '" + userObj.getDateOfBirth() + "', '" + userObj.getGender() + "'";
            System.out.println(insertInput);
            myStmt.executeUpdate("INSERT INTO users " + "values(" + insertInput + ")");
            System.out.println("Insert Complete!");
            myStmt.close();
        }catch(Exception exc) {
            exc.printStackTrace();
        }
    }

    public void updateUser(int id) {
        try {
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "karan", "1234");
            Statement myStmt = myConn.createStatement();
            String query = "select * from users where user_id = " + id;
            ResultSet rs = myStmt.executeQuery(query);
            String oldPass = null;
            while(rs.next()) {
                oldPass = rs.getString("password");
            }
            System.out.println("Enter existing password for checking:");
            String pass = sc.nextLine();
            if(pass.equals(oldPass)) {
                System.out.println("Enter a new first name:");
                String newfn = sc.nextLine();
                String queryInput = "UPDATE users set firstName = '" + newfn + "' WHERE user_id = " + id;
                myStmt.executeUpdate(queryInput);
            } else {
                System.out.println("Incorrect current password.");
            }
            myStmt.close();
        } catch (Exception exec) {
            System.out.println(exec.getMessage());
        }
    }

    public void removeUser(int id) {
        try {
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "karan", "1234");
            Statement myStmt = myConn.createStatement();
            String queryInput = "DELETE FROM users WHERE user_id = " + id;
            myStmt.executeUpdate(queryInput);
            System.out.println("User Removed!");
            myStmt.close();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    public User findUser(int user_id) {
        for(int i = 0; i < userArr.size(); i++) {
            if(userArr.get(i).getUser_id() == user_id) {
                return userArr.get(i);
            }
        }
        return null;
    }

    public ArrayList<User> printUsers(){
        try {
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "karan", "1234");
            Statement myStmt = myConn.createStatement();
            ResultSet rs = myStmt.executeQuery("select * from users");

            System.out.println("Users:");

            while(rs.next()) {
//				User u = new User();
                int userId = rs.getInt("user_id");
                String fn = rs.getString("firstName");
                String ln = rs.getString("lastName");
                String email = rs.getString("email");
                String dob = rs.getString("dateOfBirth");
                String gender = rs.getString("gender");
//				u.setUser_id(userId);
//				u.setFirstName(fn);
//				u.setLastName(ln);
//				u.setEmail(email);
//				u.setDateOfBirth(dob);
//				u.setGender(gender);
                if(findUser(userId) == null) {
//					userArr.add(temp);
                }
            }
            myStmt.close();
        } catch (Exception exec) {
            System.out.println(exec.getMessage());
        }
        return userArr;
    }
}
