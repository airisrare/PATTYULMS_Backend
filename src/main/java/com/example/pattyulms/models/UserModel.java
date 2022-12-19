package com.example.pattyulms.models;

public class UserModel 
{
    private int _id;
    private int UserID;
    private String Email;
    private String Username;
    private String Password;
    private int Role;
    private String Firstname;
    private String Lastname;

    //Default Constructor
    public UserModel() {

    }

    public UserModel(int _id, int UserID, String Email, String Username, String Password, int Role, String Firstname, String Lastname)
    {
        super();
        this._id = _id;
        this.UserID = UserID;
        this.Email = Email;
        this.Username = Username;
        this.Password = Password;
        this.Role = Role;
        this.Firstname = Firstname;
        this.Lastname = Lastname;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public int getRole() {
        return Role;
    }

    public void setRole(int role) {
        Role = role;
    }

    public String getFirstname() {
        return Firstname;
    }

    public void setFirstname(String firstname) {
        Firstname = firstname;
    }

    public String getLastname() {
        return Lastname;
    }

    public void setLastname(String lastname) {
        Lastname = lastname;
    }

    
    
}
