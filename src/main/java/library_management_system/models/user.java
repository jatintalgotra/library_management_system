package library_management_system.models;

public abstract class user {
    //data members
    private String name;
    private int UID;
    private String email;
    private String pswd;
    private boolean isDelete;

    abstract String printRole();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUID() {
        return UID;
    }

    public void setUID(int UID) {
        this.UID = UID;
    }

    public String getPswd() {
        return pswd;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }
    public void setDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }
    public boolean isDelete() {
        return isDelete;
    }
}

