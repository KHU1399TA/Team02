package main.ranks;
import main.enums.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class User {
    public String firstName;
    public String lastName;
    public String phonenumber;
    public String username;
    public String password;
    public Date registrationDate;
    public Date lastLoginDate;
    public AccessLevel accessLevel;
    public SimpleDateFormat myformat = new SimpleDateFormat("dd/MM/yy");

    public User(String firstName, String lastName, String phonenumber, String username, String password, Date registrationDate, Date lastLoginDate, AccessLevel accessLevel) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phonenumber = phonenumber;
        this.username = username;
        this.password = password;
        this.registrationDate = registrationDate;
        this.lastLoginDate = lastLoginDate;
        this.accessLevel = accessLevel;
    }

    @Override
    public String toString() {
        return firstName + "_" + lastName + "_" + phonenumber + "_" + username + "_" + password + "_" + myformat.format(registrationDate) + "_" + myformat.format(lastLoginDate) + "_" + accessLevel.get_num() + "_";
    }

    public String toString_show() {
        return accessLevel+":  |  F name : " + firstName + "  |  L name : " + lastName + "  |  phone number : " + phonenumber + "  |  username : " + username + "  |  password : " + password + "  |  reg Date : " + myformat.format(registrationDate) + "  |  last login Date : " + myformat.format(lastLoginDate);
    }

    public ActionResult login(String usernameInput,String passwordInput){
        if(!this.username.equals(usernameInput)) return ActionResult.USERNAME_NOT_FOUND;
        if(!this.password.equals(passwordInput)) return ActionResult.WRONG_PASSWORD;
        return ActionResult.SUCCESS;
    }
}
