package com.mycompany.main;

// Imports Scanner so we can get user input
import java.util.Scanner;

// Creates the Main class
public class Main {

// Creates Scanner for user input
    static Scanner input = new Scanner (System.in);
    // Stores the user's entered details
    static String username;
    static String password;
    static String cellPhone;
    
    // Stores the registered username
    static String registeredUsername;
    
    // Stores the registered password
    static String registeredPassword;
    
    //Stores the registered cellphone number
    static String registeredcellPhone;
    
    // Creates the username checking method
    public static boolean checkUserName(String username){
            // Username must have exactly 5 characters
            // and must contain an underscore
            if (username.length() == 5 && username.contains("_")) {
                return true;
            } else {
    }
        
        // Temporary return value
        return false;
    }
    // Creates the password checking method
    public static boolean checkPasswordComplexity(String password){
        // password must have at least 8 characters
        // and contain uppercase, lowercase, number and special character
        if (password.length() >= 8
            && password.matches(".*[A-Z].*")
            && password.matches(".*[a-z].*")
            && password.matches(".*[0-9].*")
            && password.matches(".*[^a-zA-Z0-9].*")) {
            return true;
        } else {
        }
        // Temporary return value
        return false;
    }
    
    // Create the cellphone  checking method
    public static boolean checkCellPhoneNumber(String number){
        // Number must start with +27
        // followed by exactly 9 digits 
        if (number.matches("^//+27[0-9]{9}$")) {
            return true;
        } else {
        }
        
        //Temporary return value
        return false;
    }
        
    // Creates the registration method
    public static void registerUser(){
        // Infinite loop for username
        while (true) {
            // Ask form username
            System.out.print("Enter your username: ");
            username = input.nextLine();
        // Check username
        boolean UsernameCorrect = checkUserName(username);
        // Check if username is correct
        if (UsernameCorrect) {
            break;
        } else{
            System.out.println("Username is incorrectly formatted. please try again");
          }
        }
        // Infinite loop for password
        while (true) {
            // Ask for password
            System.out.print("Enter your password: ");
            password = input.nextLine();
            // Check password
        boolean passwordCorrect = checkPasswordComplexity(password);
        // Check if password is correct
        if (passwordCorrect) {
            break;
        } else{
            System.out.println("Password is incorrectly formatted. please try again");
          }
        }
        // Infinite loop for cellphone number
        while (true){
            // Ask for cellphone number
            System.out.print("Enter your cellphone number (+27): ");
            cellPhone = input.nextLine();
            // Check cellphone number
            boolean phoneCorrect = checkCellPhoneNumber(cellPhone);
            // Check if cellphone number is correct
            if (phoneCorrect) {
                break;
            } else {
                System.out.println("Cellphone number is incorrectly formatted. please try again");
            }
        }
            // Save the valid username
            registeredUsername = username;
            // Save the valid password
            registeredPassword = password;
            // Save the valid cellphone number 
            registeredcellPhone = cellPhone;
            // Display success message
            System.out.println("User registered successfully.");
        }
    
    // Checks the login details
    public static boolean loginUser(String username, String password){
        // Infinite loop for login
        while (true) {
            // Compare entered details with registered details
        if (username.equals(registeredUsername)
                && password.equals(registeredPassword)){
            return true;
        } else{
         // Login details are incorrect
         System.out.println("Username or password incorrect, please try again");
           // Re-prompt the user for username
           System.out.print("Enter your username to login: ");
           username = input.nextLine();
           // Re-prompt the user for password
           System.out.print("Enter your password to login: ");
           password = input.nextLine();
        }
        }
    }
    
    // Creates the login status method
    public static String returnLoginStatus(boolean loginSuccessful){
        // Check if login was successul
        if (loginSuccessful){
            return "Welcome, it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
         }
        
        //  Temporary return message 
    }
    
    // Main method where the program starts 
    public static void main(String[] args){
        // Call registration method
        registerUser();
        // Call login method
        boolean loginSuccessful = loginUser(username, password);
        // Call login status method
        System.out.println(returnLoginStatus(loginSuccessful));
        
        // Calls the username method
        checkUserName("");
        
        // Calls the password method 
        checkPasswordComplexity("");
        
        // Calls the cellphone method
        checkCellPhoneNumber("");
        
        // Calls the registration method
        registerUser();
        
        // Calls the login method
        loginUser("", "");
        
        // Calls the login status method 
        returnLoginStatus(false);
            }
        }
