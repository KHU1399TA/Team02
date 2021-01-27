package main.ranks;

import main.enums.AccessLevel;

public class Manager extends User{
    Manager(String firstName, String lastName, String phonenumber, String username, String password, AccessLevel accessLevel) {
        super(firstName, lastName, phonenumber, username, password, accessLevel);
    }
}
