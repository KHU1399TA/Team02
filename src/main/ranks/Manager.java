package main.ranks;

import main.enums.AccessLevel;
import main.enums.ActionResult;
import main.others.Order;

import java.util.ArrayList;
import java.util.Date;

public class Manager extends User{
    public Manager(String firstName, String lastName, String phonenumber, String username, String password, Date registrationDate, Date lastLoginDate, AccessLevel accessLevel) {
        super(firstName, lastName, phonenumber, username, password, registrationDate, lastLoginDate, accessLevel);
    }
    public static ActionResult register(User user, ArrayList<User> users){
        users.add(user);
        return ActionResult.SUCCESS;
    }
    public static ActionResult edit(String username,int change,String string,ArrayList<User> users){
        for(User i:users){
            if(i.username.equals(username)){
                switch (change){
                    case 0:{
                        i.firstName=string;
                        break;
                    }
                    case 1:{
                        i.lastName=string;
                        break;
                    }
                    case 2:{
                        i.phonenumber=string;
                        break;
                    }
                    case 3:{
                        i.password=string;
                        break;
                    }
                }
                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.USERNAME_NOT_FOUND;
    }
    public static ActionResult edit(String username,int accessLevelnum,ArrayList<User> users){
        for(User i:users){
            if(i.username.equals(username)){
                i.accessLevel=AccessLevel.get_enum(accessLevelnum);
            }
            return ActionResult.SUCCESS;
        }
        return ActionResult.USERNAME_NOT_FOUND;
    }
    public static ActionResult remove(String username, ArrayList<User> users){
        for(User i:users){
            if(i.username.equals(username)){
                users.remove(users.indexOf(i));
                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.USERNAME_NOT_FOUND;
    }
}
