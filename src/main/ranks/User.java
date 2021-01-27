package main.ranks;
import main.enums.*;
import java.util.Date;
public abstract class User{
    String firstName;
    String lastName;
    String phonenumber;
    String username;
    String password;
    AccessLevel accessLevel;
    Date registrationDate;
    Date lastLoginDate;
    User(String firstName,String lastName,String phonenumber,String username,String password,AccessLevel accessLevel){
        this.firstName=firstName;
        this.lastName=lastName;
        this.phonenumber=phonenumber;
        this.username=username;
        this.password=password;
        this.accessLevel=accessLevel;
        this.registrationDate=new Date();
        this.lastLoginDate=new Date();
    }
    ActionResult login(String username,String password){
        return ActionResult.SUCCESS;
    }
}
