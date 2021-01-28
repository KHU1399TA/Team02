package main.ranks;

import main.enums.AccessLevel;
import main.enums.ActionResult;
import main.others.Order;

import java.util.Date;

public class Client extends User{
    String address;

    public Client(String firstName, String lastName, String phonenumber, String username, String password, Date registrationDate, Date lastLoginDate, AccessLevel accessLevel, String address) {
        super(firstName, lastName, phonenumber, username, password, registrationDate, lastLoginDate, accessLevel);
        this.address = address;
    }

    @Override
    public String toString() {
        return firstName + "_" + lastName + "_" + phonenumber + "_" + username + "_" + password + "_" + registrationDate + "_" + lastLoginDate + "_" + accessLevel.get_num() + "_" + address + "_";
    }
    public String toString_show() {
        return firstName + "_" + lastName + "_" + phonenumber + "_" + username + "_" + password + "_" + registrationDate + "_" + lastLoginDate + "_" + accessLevel + "_" + address + "_";
    }
//    ActionResult makeOrder(Order order){
//
//    }
//    ActionResult revokeOrder(int id){
//
//    }
}
