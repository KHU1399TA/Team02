package main ;
import main.enums.*;
import main.others.*;
import main.ranks.*;
import utils.*;
import java.text.SimpleDateFormat;
import java.util.* ;

public class Main {

    public static Scanner scanner = new Scanner(System.in);
    public static Restaurant restaurant = new Restaurant();
    private static final String Data_users = "src/resources/users.txt";
    private static final String Data_foods = "src/resources/foods.txt";
    private static final String Data_orders = "src/resources/orders.txt";

    public static void load() throws Exception{
        restaurant.users.clear();
        restaurant.menu.clear();
        restaurant.orders.clear();

        FileManager users_file= new FileManager(Data_users);
        FileManager foods_file= new FileManager(Data_foods);
        FileManager orders_file= new FileManager(Data_orders);

        for(String i : users_file.readAll().split("\n")) {       //  load users
            if (i.length() == 0) break;

            String[] line = i.split("_");

            switch (Integer.parseInt(line[7])) {
                case 0:
                    restaurant.users.add(new Client(line[0], line[1], line[2], line[3], line[4], new SimpleDateFormat("dd/MM/yyyy").parse(line[5]), new SimpleDateFormat("dd/MM/yyyy").parse(line[6]), AccessLevel.CLIENT, line[8]));
                    break;

                case 1:
                    restaurant.users.add(new Cashier(line[0], line[1], line[2], line[3], line[4], new SimpleDateFormat("dd/MM/yyyy").parse(line[5]), new SimpleDateFormat("dd/MM/yyyy").parse(line[6]), AccessLevel.CASHIER));
                    break;

                case 2:
                    restaurant.users.add(new Chef(line[0], line[1], line[2], line[3], line[4], new SimpleDateFormat("dd/MM/yyyy").parse(line[5]), new SimpleDateFormat("dd/MM/yyyy").parse(line[6]), AccessLevel.CHEF));
                    break;

                case 3:
                    restaurant.users.add(new Deliveryman(line[0], line[1], line[2], line[3], line[4], new SimpleDateFormat("dd/MM/yyyy").parse(line[5]), new SimpleDateFormat("dd/MM/yyyy").parse(line[6]), AccessLevel.DELIVERYMAN));
                    break;

                case 4:
                    restaurant.users.add(new Manager(line[0], line[1], line[2], line[3], line[4], new SimpleDateFormat("dd/MM/yyyy").parse(line[5]), new SimpleDateFormat("dd/MM/yyyy").parse(line[6]), AccessLevel.MANAGER));
                    break;
            }
        }

        for(String i : foods_file.readAll().split("\n")) {       //  load foods
            if (i.length() == 0) break;

            String[] line = i.split("_");

            boolean bool = true;
            if (line[3].equals("false")) bool = false;

            ArrayList<String> ing = new ArrayList<String>();
            for (String j : line[2].split(",")) {
                ing.add(j);
            }

            restaurant.menu.add(new Food(Integer.parseInt(line[0]), line[1], ing, bool));
        }

        for(String i : orders_file.readAll().split("\n")) {       //  load orders
            if (i.length() == 0) break;

            String[] line = i.split("_");

            OrderState state = OrderState.MADE;
            switch (Integer.parseInt(line[3])) {
                case 0:
                    state = OrderState.MADE;
                    break;

                case 1:
                    state = OrderState.CONFIRMED;
                    break;

                case 2:
                    state = OrderState.COOKED;
                    break;

                case 3:
                    state = OrderState.DELIVERED;
                    break;
            }

            restaurant.orders.add(new Order(Integer.parseInt(line[0]), line[1], Integer.parseInt(line[2]), state, new SimpleDateFormat("dd/MM/yyyy").parse(line[4])));
        }
    }
    public static void save() {

        FileManager users_file = new FileManager(Data_users);
        FileManager foods_file = new FileManager(Data_foods);
        FileManager orders_file = new FileManager(Data_orders);

        users_file.write("", false);
        foods_file.write("", false);
        orders_file.write("", false);

        for (User i : restaurant.users) {
            users_file.writeLine(i.toString(), true);
        }

        for (Food i : restaurant.menu) {
            foods_file.writeLine(i.toString(), true);
        }

        for (Order i : restaurant.orders) {
            orders_file.writeLine(i.toString(), true);
        }
    }

    public static void line() {
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
    }

    public static void res_show() {
        System.out.println("users: " + restaurant.users);
        System.out.println("menu: " + restaurant.menu);
        System.out.println("orders: " + restaurant.orders);
    }

    public static void main(String[] agrs) throws Exception {
        System.out.println("                             ___/Welcome\\___");
        line();

        String firstName;
        String lasrName;
        String phoneNumber;
        String username;
        String password;
        String address;
        ActionResult saveAccResult;

        while (true) { //start page
            load();
            res_show();             /********/

            System.out.print("type your number: | login = 0   registeer = 1   exit = 9| :");
            int start = scanner.nextInt();
            boolean enter = false;

            if (start == 0) {           //login
                saveAccResult = ActionResult.USERNAME_NOT_FOUND;

                System.out.print("enter your username: ");
                username = scanner.next();

                System.out.print("enter your password: ");
                password = scanner.next();

                for (User i : restaurant.users) {

                    saveAccResult = i.login(username, password);

                    if (saveAccResult == ActionResult.WRONG_PASSWORD) {
                        System.out.println(ActionResult.WRONG_PASSWORD);
                        break;

                    }
                    else if (saveAccResult == ActionResult.SUCCESS) {
                        enter = true;

                        switch (i.accessLevel) {
                            case CLIENT:
                                clientpanel(i);
                                break;

                            case CASHIER:
                                cashierpanel(i);
                                break;

                            case CHEF:
                                chefpanel(i);
                                break;

                            case DELIVERYMAN:
                                deliverymanpanel(i);
                                break;

                            case MANAGER:
                                managerpanel(i);
                                break;
                        }

                        break;
                    }

                }
                if (saveAccResult == ActionResult.USERNAME_NOT_FOUND) {
                    System.out.println(ActionResult.USERNAME_NOT_FOUND);
                }

            }
            else if (start == 1) {           //register
                while (true) {

                    saveAccResult = ActionResult.SUCCESS;
                    System.out.println(restaurant.users);
                    System.out.println("..|enter: firstName,lastName,phoneNumber,userName,password,address|..");

                    firstName = scanner.next();
                    lasrName = scanner.next();
                    phoneNumber = scanner.next();
                    username = scanner.next();
                    password = scanner.next();
                    address = scanner.next();

                    if (StringSyn.checkusername(username) == ActionResult.INVALID_USERNAME) {
                        System.out.println(ActionResult.INVALID_USERNAME);
                        saveAccResult = ActionResult.INVALID_USERNAME;
                        break;
                    }

                    if (StringSyn.checkpassword(password) == ActionResult.INVALID_PASSWORD) {
                        System.out.println(ActionResult.INVALID_PASSWORD);
                        saveAccResult = ActionResult.INVALID_PASSWORD;
                        break;
                    }
                    for (User i : restaurant.users) {
                        if (i.username.equals(username)) {
                            System.out.println(ActionResult.USERNAME_ALREADY_EXIST);
                            saveAccResult = ActionResult.USERNAME_ALREADY_EXIST;
                            break;
                        }
                    }
                    if (saveAccResult.equals(ActionResult.SUCCESS)) {
                        restaurant.users.add(new Client(firstName, lasrName, phoneNumber, username, password, new Date(), new Date(), AccessLevel.CLIENT, address));

                        save();
                        load();
                    }
                    break;
                }
            }
            else if (start == 9) {                //back to start
                break;
            }
            else {
                System.out.print("please enter correct number : ");
            }
        }

        line();
        System.out.println("Have a nice day");
        line();
    }


    static void clientpanel(User user) {
        user.lastLoginDate = new Date();
        Client client = new Client(user.firstName, user.lastName, user.phonenumber, user.username, user.password, user.registrationDate, user.lastLoginDate, user.accessLevel, "");

        System.out.println("---=== clientpanel ===---");
        int start;

        while (true) {
            System.out.print("type your number: | show menu = 0    make order = 1    revoke order = 2    check my orders = 3    Log out = 9 | :");
            start = scanner.nextInt();

            if (start == 0) {
                for (Food i : restaurant.menu) {
                    System.out.println(i.toString_show());
                }
            }
            else if (start == 1) {
                System.out.println("enter your food id:");
                int id = scanner.nextInt();

                int orderId = 0;
                boolean unique;

                for (int i = 0; i <= 100000000; i++) {
                    unique = true;
                    for (Order j : restaurant.orders) {
                        if (i == j.id) {
                            unique = false;
                        }
                    }
                    if (unique == true) {
                        orderId = i;
                        break;
                    }
                }

                System.out.println(client.makeOrder(new Order(orderId, client.username, id, OrderState.MADE, new Date()), restaurant.orders, restaurant.menu));
                res_show();             /********/
                save();

            }
            else if (start == 2) {
                System.out.println("enter your order id");
                int id = scanner.nextInt();

                System.out.println(client.revokeOrder(id, restaurant.orders, client));
                save();

            }
            else if (start == 3) {
                boolean isnull = true;
                for (Order i : restaurant.orders) {
                    if (i.username.equals(client.username)) {
                        System.out.println(i.toString_show());
                        isnull = false;
                    }
                }
                if (isnull) {
                    System.out.println("Clear");
                }
            }
            else if (start == 9) return;
            else System.out.println("please enter correct number : ");
        }
    }


    static void cashierpanel(User user) {
        user.lastLoginDate = new Date();
        Cashier cashier = new Cashier(user.firstName, user.lastName, user.phonenumber, user.username, user.password, user.registrationDate, user.lastLoginDate, user.accessLevel);

        System.out.println("---=== cashierpanel ===---");
        int start;

        while (true) {
            System.out.print("type your number: | show orders = 0    Confirm order = 1    Log out = 9 | :");
            start = scanner.nextInt();

            if (start == 0) {
                for (Order i : restaurant.orders) {
                    System.out.println(i.toString_show());
                }
            }
            else if (start == 1) {
                System.out.println("enter Order id : ");
                int id = scanner.nextInt();

                System.out.println(cashier.confirmOrder(id, restaurant.orders));
            }
            else if (start == 9) return;
            else System.out.println("please enter correct number : ");
        }
    }


    static void chefpanel(User user) {
        user.lastLoginDate = new Date();
        Chef chef = new Chef(user.firstName, user.lastName, user.phonenumber, user.username, user.password, user.registrationDate, user.lastLoginDate, user.accessLevel);

        System.out.println("---=== chefpanel ===---");
        int start;

        while (true) {
            System.out.print("type your number: | show menu = 0    show orders = 1    add food = 2    edit food = 3    remove food = 4    change food status = 5    cook food = 6    Log out = 9 | :");
            start = scanner.nextInt();

            if (start == 0) {
                for (Food i : restaurant.menu) {
                    System.out.println(i.toString_show());
                }
            }

            else if (start == 1) {
                for (Order i : restaurant.orders) {
                    System.out.println(i.toString_show());
                }
            }
            else if (start == 2) {
                System.out.println("..|enter: id,name,Ingredients number,List Ingredients,status(1=Available   0=not Available)|..");

                int id = scanner.nextInt();
                String name = scanner.next();
                int number = scanner.nextInt();
                ArrayList<String> Ingredientsedit = new ArrayList<String>();

                for (int i = 0; i < number; i++) {
                    Ingredientsedit.add(scanner.next());
                }

                int isnumber = scanner.nextInt();

                boolean isAvailable = false;
                if (isnumber == 0) {
                    isAvailable = false;
                }
                else if (isnumber == 1) {
                    isAvailable = true;
                }
                else {
                    System.out.println(ActionResult.UNKNOWN_ERROR);
                    break;
                }

                System.out.println(chef.addFood(new Food(id, name, Ingredientsedit, isAvailable), restaurant.menu));

            }
            else if (start == 3) {
                System.out.println("..|enter: id,Ingredients number,List Ingredients|..");

                int id = scanner.nextInt();
                int number = scanner.nextInt();
                ArrayList<String> Ingredients = new ArrayList<String>();

                for (int i = 0; i < number; i++) {
                    Ingredients.add(scanner.next());
                }

                System.out.println(chef.editFood(id, Ingredients, restaurant.menu));


            }
            else if (start == 4) {
                System.out.println("..|enter: id|..");
                int id = scanner.nextInt();

                System.out.println(chef.removeFood(id, restaurant.menu));


            }
            else if (start == 5) {
                System.out.println("..|enter: id,status(1=Available   0=not Available)|..");

                int id = scanner.nextInt();
                int statusid = scanner.nextInt();
                boolean bool = false;

                if (statusid == 1) {
                    bool = true;
                }
                else if (statusid == 0) {
                    bool = false;
                }

                System.out.println(chef.changeFoodStatus(id, bool, restaurant.menu));


            }
            else if (start == 6) {
                System.out.println("..|enter: Order id|..");
                int id = scanner.nextInt();

                System.out.println(chef.cook(id, restaurant.orders));


            }
            else if (start == 9) return;
            else System.out.println("please enter correct number : ");
        }
    }


    static void deliverymanpanel(User user) {
        user.lastLoginDate = new Date();
        Deliveryman deliveryman = new Deliveryman(user.firstName, user.lastName, user.phonenumber, user.username, user.password, user.registrationDate, user.lastLoginDate, user.accessLevel);

        System.out.println("---=== deliverymanpanel ===---");
        int start;

        while (true) {
            System.out.print("type your number: | show orders = 0    Deliver order = 1    Log out = 9 | :");

            start = scanner.nextInt();

            if (start == 0) {
                for (Order i : restaurant.orders) {
                    System.out.println(i.toString_show());
                }
            }
            else if (start == 1) {
                System.out.println("enter Order id : ");
                int id = scanner.nextInt();
                System.out.println(deliveryman.deliverOrder(id, restaurant.orders));
            }
            else if (start == 9) return;
            else System.out.println("please enter correct number : ");

        }
    }

    static void managerpanel(User user) {
        user.lastLoginDate = new Date();
        Manager manager = new Manager(user.firstName, user.lastName, user.phonenumber, user.username, user.password, user.registrationDate, user.lastLoginDate, user.accessLevel);

        System.out.println("---=== managerpanel ===---");

        int start;

        while (true) {
            System.out.print("type your number: | show users = 0    register = 1    edit = 2    remove = 3    Log out = 9 | :");
            start = scanner.nextInt();

            if (start == 0) {
                for (User i : restaurant.users) {
                    System.out.println(i.toString_show());
                }
            }
            else if (start == 1) {
                System.out.println("..|enter: firstName,lastName,phoneNumber,username,password,accessLevel(client=0,cashier=1,chef=2,delivery=3)|..");

                String firstName = scanner.next();
                String lastName = scanner.next();
                String phonenumber = scanner.next();
                String username = scanner.next();
                String password = scanner.next();
                int accessLevelnumber = scanner.nextInt();

                switch (accessLevelnumber) {
                    case 0: {
                        System.out.println("enter address");
                        System.out.println(manager.register(new Client(firstName, lastName, phonenumber, username, password, new Date(), new Date(), AccessLevel.CLIENT, scanner.next()), restaurant.users));
                        break;
                    }

                    case 1: {
                        System.out.println(manager.register(new Cashier(firstName, lastName, phonenumber, username, password, new Date(), new Date(), AccessLevel.CASHIER), restaurant.users));
                        break;
                    }

                    case 2: {
                        System.out.println(manager.register(new Chef(firstName, lastName, phonenumber, username, password, new Date(), new Date(), AccessLevel.CHEF), restaurant.users));
                        break;
                    }

                    case 3: {
                        System.out.println(manager.register(new Deliveryman(firstName, lastName, phonenumber, username, password, new Date(), new Date(), AccessLevel.DELIVERYMAN), restaurant.users));
                        break;
                    }
                }
            }
            else if (start == 2) {
                System.out.println("type username:");
                String username = scanner.next();

                System.out.println("type your number: | change firstName = 0    change lastName= 1    change phonenumber= 2    change password= 3    change AccessLevel= 4    | :");
                int id = scanner.nextInt();

                if (id == 4) {
                    System.out.println("type your number: | client = 0    cashier = 1    chef = 2    delivery = 3    | :");
                    int accessLevelnum = scanner.nextInt();

                    manager.edit(username, accessLevelnum, restaurant.users);
                }
                else {
                    System.out.println("replace to:");
                    String string = scanner.next();

                    manager.edit(username, id, string, restaurant.users);
                }
            }
            else if (start == 3) {
                System.out.println("type username:");
                String username = scanner.next();

                manager.remove(username, restaurant.users);
            }
            else if (start == 9) return;
            else System.out.println("please enter correct number : ");
        }
    }
}
