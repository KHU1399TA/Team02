package main.ranks;

import main.enums.AccessLevel;

public class Cashier extends User{

    Cashier(String firstName, String lastName, String phonenumber, String username, String password, AccessLevel accessLevel) {
        super(firstName, lastName, phonenumber, username, password, accessLevel);
    }
}
