package library_management_system.models;
// import library_management_system.models*;



public class books {
    private String name;
    private int bookID;
    private String author;
    private String category;
    private boolean isDelete;
    private student borrowedBy = null;

    public books(String name,int bookID, String author, String category){
        this.name = name;
        this.bookID = bookID;
        this.author = author;
        this.category = category;
        isDelete = false;
    }

    public String getAuthor() {
        return author;
    }
    public int getBookID() {
        return bookID;
    }
    public String getCategory() {
        return category;
    }
    public String getName() {
        return name;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public void setBookID(int bookID) {
        this.bookID = bookID;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public void setDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }
    public void setName(String name) {
        this.name = name;
    }
    public student getBorrowedBy() {
        return borrowedBy;
    }
    public void setBorrowedBy(student borrowedBy) {
        this.borrowedBy = borrowedBy;
    }
    public boolean isDelete() {
        return isDelete;
    }

}
