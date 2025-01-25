package library_management_system.models;

public class student extends user {
    String printRole(){
        return "student";
    }

    public student(String name, int UID, String email, String pswd){
        this.setName(name);
        this.setUID(UID);
        this.setPswd(pswd);
        this.setDelete(isDelete());
        this.setEmail(email);
    }

}
