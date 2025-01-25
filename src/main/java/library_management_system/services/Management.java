package library_management_system.services;

import library_management_system.database.db;
// import library_management_system.models.books;
import library_management_system.models.student;
import library_management_system.models.admin;
import library_management_system.models.books;

import java.io.File;
// import java.util.ArrayList;
// import java.util.List;
import java.util.Scanner;

public class Management {
    private db database;

    public db getDatabase() {
        return database;
    }

    private Scanner scanner;
    private int bookID = 2000;
    private int ID = 1000;

    private Object user;

    public void setUser(Object user) {
        this.user = user;
    }

    public Object getUser() {
        return user;
    }

    public Management(){
        database = new db();
        scanner = new Scanner(System.in);  // Initialize scanner in constructor
    }

    public void addEBook(String fileName){
        File file = new File("D:\\Projects\\LibraryManagSys\\library_management_system\\src\\test\\"+fileName+".pdf");
        database.upload(file);
    }

    public void Register() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        
        // System.out.print("Enter UID: ");
        int UID = ID+1;
        ID++;
        // scanner.nextLine();
        
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        
        System.out.print("Enter password: ");
        String pswd = scanner.nextLine();
        
        System.out.print("Enter role (admin/student): ");
        String role = scanner.nextLine();
        
        if(role.equalsIgnoreCase("student")) {
            student S = new student(name, UID, email, pswd);
            try {
                database.addUser(S);
            } catch (Exception e) {
                System.out.println("Error registering user: " + e.getMessage());
            }
        } else if(role.equalsIgnoreCase("admin")) {
            admin A = new admin(name, UID, email, pswd);
            try {
                database.addUser(A);
            } catch (Exception e) {
                System.out.println("Error registering user: " + e.getMessage());
            }
        }
        System.out.println("User registered successfully!");
        System.out.println("Your User ID is: "+ UID);

    }
    
    public void login(){
        System.out.print("Enter UID: ");
        int UID = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Enter password: ");
        String pswd = scanner.nextLine();

        try {
            admin A = database.searchAdmin(UID);
            if(A != null && A.getPswd().equals(pswd)){
                System.out.println("Successful Admin Login");
                user = A;
                return;
            }

            student S = database.searchStudent(UID);
            if (S != null && S.getPswd().equals(pswd)) {
                System.out.println("Successful Admin Login");
                user = S;
                return;
            }
            
            System.out.println("Invalid UID or password.");
        } catch (Exception e) {
            System.out.println("Error during login: " + e.getMessage());
        }
    }

    // Rest of the methods remain unchanged as they don't use Scanner

    public void enterBook() {
        System.out.print("Enter book name: ");
        String name = scanner.nextLine();
        
        int newBookID = bookID + 1;
        bookID++;
        
        System.out.print("Enter author name: ");
        String author = scanner.nextLine();
        
        System.out.print("Enter category: ");
        String category = scanner.nextLine();
        
        books newBook = new books(name, newBookID, author, category);
        
        try {
            database.addBook(newBook, user);
            System.out.println("Book added successfully!");
            System.out.println("Book ID is: " + newBookID);
        } catch (Exception e) {
            System.out.println("Error adding book: " + e.getMessage());
        }
    }
    // Add a cleanup method to close the scanner when done
    public void cleanup() {
        if (scanner != null) {
            scanner.close();
        }
    }
}
