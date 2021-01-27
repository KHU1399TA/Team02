package main.ranks;

import main.enums.AccessLevel;

import java.util.Date;

public class Client extends User{
    String address;
    Client(String firstName, String lastName, String phonenumber, String username, String password, AccessLevel accessLevel,String address){
        super(firstName,lastName,phonenumber,username,password,accessLevel);
        this.address=address;
        super.registrationDate=new Date();
        super.lastLoginDate=new Date();
    }
}
