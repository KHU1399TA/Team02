package main.ranks;

import main.enums.AccessLevel;

import java.util.Date;

public class Deliveryman extends User{
    public Deliveryman(String firstName, String lastName, String phonenumber, String username, String password, Date registrationDate, Date lastLoginDate, AccessLevel accessLevel) {
        super(firstName, lastName, phonenumber, username, password, registrationDate, lastLoginDate, accessLevel);
    }
}