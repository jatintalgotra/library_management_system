package library_management_system.models;

public class admin extends user {
    String printRole(){
        return "admin";
    }

    public admin(String name, int UID, String email, String pswd){
        this.setName(name);
        this.setUID(UID);
        this.setPswd(pswd);
        this.setDelete(isDelete());
        this.setEmail(email);
    }
}
