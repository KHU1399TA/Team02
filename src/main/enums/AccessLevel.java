package main.enums;

public enum AccessLevel {
    CLIENT(0), CASHIER(1), CHEF(2), DELIVERYMAN(3), MANAGER(4);
    int number;

    AccessLevel(int number) {
        this.number = number;
    }

    public int get_num() {
        return number;
    }

    public static AccessLevel get_enum(int a) {
        switch (a) {
            case 0:
                return AccessLevel.CLIENT;

            case 1:
                return AccessLevel.CASHIER;

            case 2:
                return AccessLevel.CHEF;

            case 3:
                return AccessLevel.DELIVERYMAN;

            case 4:
                return AccessLevel.MANAGER;
        }
        System.out.println("bad code at AccessLevel");

        return AccessLevel.CLIENT;
    }
}

