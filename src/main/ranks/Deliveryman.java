package main.ranks;

import main.enums.AccessLevel;

public class Deliveryman extends User{

    Deliveryman(String firstName, String lastName, String phonenumber, String username, String password, AccessLevel accessLevel) {
        super(firstName, lastName, phonenumber, username, password, accessLevel);
    }
}
