package main.ranks;
import main.enums.*;
import java.util.Date;
public abstract class User {
    String firstName;
    String lastName;
    String phonenumber;
    String username;
    String password;
    Date registrationDate;
    Date lastLoginDate;
    AccessLevel accessLevel;

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

    ActionResult login(String username, String password) {
        return ActionResult.SUCCESS;
    }

    @Override
    public String toString() {
        return firstName + "_" + lastName + "_" + phonenumber + "_" + username + "_" + password + "_" + registrationDate + "_" + lastLoginDate + "_" + accessLevel.get_num() + "_";
    }
    public String toString_show(){
        return firstName + "_" + lastName + "_" + phonenumber + "_" + username + "_" + password + "_" + registrationDate + "_" + lastLoginDate + "_" + accessLevel + "_";
    }

}
