package library_management_system.database;

import library_management_system.models.admin;
import library_management_system.models.books;
import library_management_system.models.student;

import java.io.File;
// import java.io.File;
import java.util.ArrayList;
// import java.io.EOFException;
// import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;

public class db {
    private List<admin> adminList;
    private List<student> studentList;
    private List<books> bookList;
    private List<PDDocument> eBooks; 

    public void upload(File file){
        try {
            PDDocument obj = PDDocument.load(file);
            eBooks.add(obj);
        } catch (Exception e) {
            throw new RuntimeException("Error loading PDF file: " + e.getMessage());
        }
    }

    public List<books> displayBooks() {
        List<books> availableBooks = new ArrayList<>();
        for (books book : bookList) {
            if (!book.isDelete()) {
                availableBooks.add(book);
            }
        }
        return availableBooks;
    }
    
    public db() {
        adminList = new ArrayList<>();
        studentList = new ArrayList<>();
        bookList = new ArrayList<>();
    }

    

    public books searchBook(int bookID) throws Exception{
        for(books e: bookList){
            if(e.getBookID() == bookID){
                return e;
            }
        }
        throw new Exception("Book not found");
    }
    public admin searchAdmin(int userID) throws Exception {
        for(admin a: adminList){
            if(a.getUID() == userID){
                return a;
            }
        }
        throw new Exception("Admin not found");
    }
    public student searchStudent(int userID) throws Exception {
        for(student s: studentList){
            if(s.getUID() == userID){
                return s;
            }
        }
        throw new Exception("Student not found");
    }
    public void borrowBook(books book, student student) throws Exception {
        if (book.isDelete() || book.getBorrowedBy() != null) {
            throw new Exception("Book is not available for borrowing");
        }
        book.setBorrowedBy(student);
    }
    public void returnBook(books book) throws Exception {
        if (book.getBorrowedBy() == null) {
            throw new Exception("Book is not borrowed");
        }
        book.setBorrowedBy(null);
    }
    public void addUser(Object u) throws Exception{
        if(u instanceof admin){
            adminList.add((admin)u);
        }
        if(u instanceof student){
            studentList.add((student)u);
        }
        else{
            throw new Exception("Not a valid user");
        }
    }
    public void deleteUser(Object u) throws Exception{
        if(u instanceof admin){
            ((admin)u).setDelete(true);
        }
        if(u instanceof student){
            ((student)u).setDelete(true);
        }
        else    throw  new Exception("Not a valid user");
    }
    
    public void addBook(books B, Object user) throws Exception{
        if(user instanceof admin){
            bookList.add(B);
        }
        else{
            throw new Exception("User is not an admin");
        }
    }

    public void deleteBook(books B, Object user) throws Exception{
        if(user instanceof admin){
            B.setDelete(true);
        }
        else{
            throw new Exception("User is not an admin");
        }
    }
}
