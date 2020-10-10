package com.example.SpringBoot_Demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long user_id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String dateOfBirth;
    private String gender;

    public User() {
        super();
        this.firstName = null;
        this.lastName = null;
        this.email = null;
        this.password = null;
        this.dateOfBirth = null;
        this.gender = null;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getFirstName() {
        return firstName;
    }

    public Boolean setFirstName(String first) {
        if(first != null && first.length() > 0) {
            this.firstName = first;
            return true;
        }
        else {
            System.out.println("Enter a valid name.");
            return false;
        }
    }

    public String getLastName() {
        return lastName;
    }

    public Boolean setLastName(String last) {
        if(last != null && last.length() > 0) {
            this.lastName = last;
            return true;
        }
        else {
            System.out.println("Enter a valid name.");
            return false;
        }
    }

    public String getEmail() {
        return email;
    }

    public Boolean setEmail(String email) {
        String email_pattern = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        if(email.matches(email_pattern)){
            this.email = email;
            return true;
        } else {
            System.out.println("Enter a valid email address.");
            return false;
        }
    }

    public String getPassword() {
        return password;
    }

    public Boolean setPassword(String password) {
        if(password.length() >= 6) {
            this.password = password;
            return true;
        } else {
            System.out.println("Enter a valid Password {Must be six or more characters.}");
            return false;
        }
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public Boolean setDateOfBirth(String dob) {
        String regex = "^(1[0-2]|0[1-9])/(3[01]" + "|[12][0-9]|0[1-9])/[0-9]{4}$";
        Pattern pat = Pattern.compile(regex);
        Matcher mat = pat.matcher((CharSequence)dob);

        if(mat.matches()) {
            this.dateOfBirth = dob;
            return true;
        } else {
            System.out.println("Enter a valid date of birth. {mm/dd/yyyy}");
            return false;
        }
    }

    public String getGender() {
        return gender;
    }

    public Boolean setGender(String gender) {
        if(gender.equals("m") || gender.equals("M") || gender.equals("male") || gender.equals("Male")) {
            this.gender = "Male";
            return true;
        } else if(gender.equals("f") || gender.equals("F") || gender.equals("female") || gender.equals("Female")) {
            this.gender = "Female";
            return true;
        }
        else {
            System.out.println("Enter a valid gender");
            return false;
        }
    }
}
