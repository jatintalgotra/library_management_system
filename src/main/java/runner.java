import java.util.List;
import java.util.Scanner;

import library_management_system.database.db;
import library_management_system.models.admin;
import library_management_system.models.books;
import library_management_system.models.student;
import library_management_system.services.Management;

class Menu {
    private static final Scanner scanner = new Scanner(System.in);

    public void mainMenu(Management lms) {
        System.out.println("Input 1 for Login.\n" + "Input 2 for Register.\n" + "Input 3 to Exit\n");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                System.out.println("You chose to Login.");
                lms.login();
                loggedInMenu(lms.getUser(), lms);
                break;

            case 2:
                System.out.println("You chose to Register.");
                lms.Register();
                System.out.println("\n");
                mainMenu(lms);
                break;

            case 3:
                System.out.println("Exiting the system. Goodbye!");
                scanner.close();
                System.exit(0);
                break;

            default:
                System.out.println("Invalid choice. Please input 1 for Login, 2 for Register, or 3 to Exit.");
                mainMenu(lms);
                break;
        }
    }

    public void loggedInMenu(Object user, Management lms) {
        if (user instanceof admin) {
            System.out.println("\nAdmin Menu:");
            System.out.println("1. Add Book");
            System.out.println("2. Add e-Book");
            System.out.println("3. Delete Book");
            System.out.println("4. Delete User");
            System.out.println("5. Logout");
            System.out.print("Enter your choice: ");

            int adminChoice = scanner.nextInt();
            switch (adminChoice) {
                case 1:
                    lms.enterBook();
                    loggedInMenu(user, lms);
                    break;
                
                case 2:
                    

                    break;


                case 3:
                    System.out.print("Enter book ID to delete: ");
                    int bookId = scanner.nextInt();
                    db database = lms.getDatabase();
                    try {
                        books book = database.searchBook(bookId);
                        database.deleteBook(book, user);
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    loggedInMenu(user, lms);
                    break;

                case 4:
                    System.out.print("Enter user type (admin/student): ");
                    String userType = scanner.next();
                    System.out.print("Enter user ID to delete: ");
                    int userId = scanner.nextInt();
                    if (userType.equalsIgnoreCase("admin")) {
                        try {
                            admin a = lms.getDatabase().searchAdmin(userId);
                            lms.getDatabase().deleteUser(a);
                            System.out.println("Delete successful!\n");
                        } catch (Exception e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                    } else if (userType.equalsIgnoreCase("student")) {
                        try {
                            student b = lms.getDatabase().searchStudent(userId);
                            lms.getDatabase().deleteUser(b);
                            System.out.println("Delete successful!\n");
                        
                        } catch (Exception e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                    } else {
                        System.out.println("Invalid user type!");
                    }
                    loggedInMenu(user, lms);
                    break;

                case 5:
                    System.out.println("Logging out...");
                    lms.setUser(null);
                    System.out.println("\n");
                    mainMenu(lms);

                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
        else {
            System.out.println("\nStudent Menu:");
            System.out.println("1. View Available Books");
            System.out.println("2. Borrow Book");
            System.out.println("3. Return Book");
            System.out.println("4. Logout");
            System.out.print("Enter your choice: ");

            int studentChoice = scanner.nextInt();
            switch (studentChoice) {
                case 1:
                    List<books> l1 = lms.getDatabase().displayBooks();
                    for (books b : l1) {
                        if (!b.isDelete()) {
                            System.out.println("BookID" + "\t" + "Book Name" + "\t" + "Author Name" + "\t" + "Category");
                            System.out.println(b.getBookID()+ "\t" +b.getName() + "\t" + b.getAuthor()+ "\t" + b.getCategory());
                        }
                        System.out.println("\n");
                    }
                    System.out.println("\n");
                    loggedInMenu(user, lms);
                    break;
                case 2:
                    System.out.print("Enter book ID to borrow: ");
                    int bookId = scanner.nextInt();
                    db database = lms.getDatabase();
                    try {
                        books book = database.searchBook(bookId);
                        database.borrowBook(book, (student)user);
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    System.out.println("\n");

                    loggedInMenu(user, lms);
                    break;
                case 3:
                    System.out.print("Enter book ID to return: ");
                    bookId = scanner.nextInt();
                    try {
                        books book = lms.getDatabase().searchBook(bookId);
                        lms.getDatabase().borrowBook(book, (student)user);
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    System.out.println("\n");
                    loggedInMenu(user, lms);
                    break;
                case 4:
                    System.out.println("Logging out...");
                    lms.setUser(null);
                    System.out.println("\n");
                    mainMenu(lms);
                    break;
                default:
                    System.out.println("Invalid choice!");
                    loggedInMenu(user, lms);
            }
        }
    }
}

public class runner {
    private static final Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        Management lms = new Management();
        Menu m = new Menu();
        
        System.out.println("Welcome to My Library Management System\n");
        m.mainMenu(lms);
        
        scanner.close();
    }
}
