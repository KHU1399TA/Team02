package main.ranks;

import main.enums.AccessLevel;

import java.util.Date;

public class Cashier extends User{

    public Cashier(String firstName, String lastName, String phonenumber, String username, String password, Date registrationDate, Date lastLoginDate, AccessLevel accessLevel) {
        super(firstName, lastName, phonenumber, username, password, registrationDate, lastLoginDate, accessLevel);
    }
}