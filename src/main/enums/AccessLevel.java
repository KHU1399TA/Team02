package main.enums;

enum AccessLevel{
    MANAGER(0),CASHIER(1),CLIENT(2),CHEF(3),DELIVERYMAN(4);
    int number;

    AccessLevel(int number) {
        this.number = number;
    }


    public int getter() {
        return number;
    }
}

