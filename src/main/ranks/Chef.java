package main.ranks;

import main.enums.AccessLevel;

public class Chef extends User{
    Chef(String firstName, String lastName, String phonenumber, String username, String password, AccessLevel accessLevel) {
        super(firstName, lastName, phonenumber, username, password, accessLevel);
    }
}
