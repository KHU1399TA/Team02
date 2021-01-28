package main ;
import main.enums.AccessLevel;
import main.enums.ActionResult;
import main.enums.OrderState;
import main.others.Food;
import main.others.Order;
import main.others.Restaurant;
import main.ranks.*;
import utils.*;
import java.util.* ;
public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static Date myClock = new Date();
    public static Restaurant restaurant = new Restaurant();
    private static final String Data_users = "src/resources/users.txt";
    private static final String Data_foods = "src/resources/foods.txt";
    private static final String Data_orders = "src/resources/orders.txt";
    public static void load() {
        restaurant.users.clear();
        restaurant.menu.clear();
        restaurant.orders.clear();
        FileManager users_file= new FileManager(Data_users);
        FileManager foods_file= new FileManager(Data_foods);
        FileManager orders_file= new FileManager(Data_orders);
        for(String i : users_file.readAll().split("\n")){       //  load users
            if(i.length()==0) break;        // debug for empty file
            String[] line = i.split("_");
            switch (Integer.parseInt(line[7])){
                case 0:
                    restaurant.users.add(new Client(line[0],line[1],line[2],line[3],line[4],myClock,myClock,AccessLevel.CLIENT,line[8]));
                    break;
                case 1:
                    restaurant.users.add(new Cashier(line[0],line[1],line[2],line[3],line[4],myClock,myClock,AccessLevel.CASHIER));
                    break;
                case 2:
                    restaurant.users.add(new Chef(line[0],line[1],line[2],line[3],line[4],myClock,myClock,AccessLevel.CHEF));
                    break;
                case 3:
                    restaurant.users.add(new Deliveryman(line[0],line[1],line[2],line[3],line[4],myClock,myClock,AccessLevel.DELIVERYMAN));
                    break;
                case 4:
                    restaurant.users.add(new Manager(line[0],line[1],line[2],line[3],line[4],myClock,myClock,AccessLevel.MANAGER));
                    break;

            }
        }
        for(String i : foods_file.readAll().split("\n")) {       //  load foods
            if(i.length()==0) break;        // debug for empty file
            String[] line = i.split("_");
            boolean bool=true;
            if (line[3].equals("false")) bool = false;
            ArrayList<String> ing = new ArrayList<String>();
            for(String j:line[3].split(",")){
                ing.add(j);
            }
            restaurant.menu.add(new Food(Integer.parseInt(line[0]),line[1],ing,bool));
        }
        for(String i : orders_file.readAll().split("\n")){       //  load orders
            if(i.length()==0) break;        // debug for empty file
            String[] line = i.split("_");
            OrderState state=OrderState.MADE;
            switch (Integer.parseInt(line[3])){
                case 0:
                    state=OrderState.MADE;
                    break;
                case 1:
                    state=OrderState.CONFIRMED;
                    break;
                case 2:
                    state=OrderState.COOKED;
                    break;
                case 3:
                    state=OrderState.DELIVERED;
                    break;
            }
            restaurant.orders.add(new Order(Integer.parseInt(line[0]),line[1],Integer.parseInt(line[2]),state,myClock));
        }
    }
    public static void save() {
        FileManager users_file= new FileManager(Data_users);
        FileManager foods_file= new FileManager(Data_foods);
        FileManager orders_file= new FileManager(Data_orders);
        users_file.write("",false);
        foods_file.write("",false);
        orders_file.write("",false);
        for(User i:restaurant.users){
            users_file.writeLine(i.toString(),true);
        }
        for(Food i:restaurant.menu){
            foods_file.writeLine(i.toString(),true);
        }
        for(Order i:restaurant.orders){
            orders_file.writeLine(i.toString(),true);
        }
    }

    public static void line() {
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
    }

    public static void main(String[] agrs) {
        load();
        System.out.println("Welcome");
        String firstName="";
        String lasrName="";
        String phoneNumber="";
        String username="";
        String password="";
        String address="";
        boolean enter=false;
        ActionResult saveAccResult;
        line();
        while (true) { //start page
            System.out.print("type your number: | login = 0   registeer = 1   exit = 2| :");
            int start = scanner.nextInt();
            if (start == 0) {           //login
                saveAccResult=ActionResult.USERNAME_NOT_FOUND;
                System.out.print("enter your username: ");
                username=scanner.next();
                System.out.print("enter your password: ");
                password=scanner.next();
                for(User i:restaurant.users){
                    saveAccResult=i.login(username,password);
                    if(saveAccResult==ActionResult.WRONG_PASSWORD){
                        System.out.println(ActionResult.WRONG_PASSWORD);
                        break;
                    }
                    if(saveAccResult==ActionResult.SUCCESS){
                        enter=true;
                        switch (i.accessLevel){
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
                if(saveAccResult==ActionResult.USERNAME_NOT_FOUND){
                    System.out.println(ActionResult.USERNAME_NOT_FOUND);
                }
            } else if (start == 1) {           //register
                while (true) {
                    saveAccResult=ActionResult.SUCCESS;
                    System.out.println(restaurant.users);
                    System.out.println("..|enter: firstName,lastName,phoneNumber,userName,password,address|..");
                    firstName=scanner.next();
                    lasrName=scanner.next();
                    phoneNumber=scanner.next();
                    username=scanner.next();
                    password=scanner.next();
                    address=scanner.next();
                    if(StringSyn.checkusername(username)==ActionResult.INVALID_USERNAME){
                        System.out.println(ActionResult.INVALID_USERNAME);
                        saveAccResult=ActionResult.INVALID_USERNAME;
                        break;
                    }
                    if(StringSyn.checkpassword(password)==ActionResult.INVALID_PASSWORD){
                        System.out.println(ActionResult.INVALID_PASSWORD);
                        saveAccResult=ActionResult.INVALID_PASSWORD;
                        break;
                    }
                    for(User i : restaurant.users){
                        if(i.username.equals(username)){
                            System.out.println(ActionResult.USERNAME_ALREADY_EXIST);
                            saveAccResult=ActionResult.USERNAME_ALREADY_EXIST;
                            break;
                        }
                    }
                    if(saveAccResult.equals(ActionResult.SUCCESS)) {
                        restaurant.users.add(new Client(firstName, lasrName, phoneNumber, username, password, myClock, myClock, AccessLevel.CLIENT, address));
                        save();
                        load();
                    }
                    break;
                }
            } else if (start == 2) {                //back to start
                break;
            } else {
                System.out.print("please enter correct number : ");
            }
        }
        line();
        System.out.println("Have a nice day");
        line();
    }

    static void clientpanel(User client) {
        System.out.println("---=== clientpanel ===---");
        while (true){
            int start;
            System.out.println("type your number: | show menu = 0    make order = 1    revoke order = 2    check my orders| :");
            start=scanner.nextInt();
            if(start==0){
                for(Food i : restaurant.menu){

                }
            }
        }
        /*
        show menu
        refresh menu
        Action result make order(Order order ,Restaurant restaurant)
        Action result revoke order(revoke order ,Restaurant restaurant)
        check my orders
         */
    }

    static void cashierpanel(User cashier) {
        System.out.println("---=== cashierpanel ===---");
        /*
        Action result confirmOrder(int id ,Restaurant restaurant)
         */
    }

    static void chefpanel(User chef) {
        System.out.println("---=== chefpanel ===---");
        /*
        ActionResult add food(Food food ,Restaurant restaurant)
        ActionResult edit food(int id ,Restaurant restaurant)
        ActionResult remove food(int id ,Restaurant restaurant)
        ActionResult change food status(int id ,boolean isAvailble ,Restaurant restaurant)
        ActionResult cook (int id ,Restaurant restaurant) (check order if state=confirm)
         */
    }

    static void deliverymanpanel(User deliveryman) {
        System.out.println("---=== deliverymanpanel ===---");
        /*
        ActionResult deliver (int id ,Restaurant restaurant) (if state==cooked change to delivered)
         */
    }

    static void managerpanel(User manager) {
        System.out.println("---=== managerpanel ===---");
        /*
        ActionResult register (User member ,Restaurant restaurant)(ranks except client)(add to files and reload array)
        ActionResult edit (String username ,String password ,Restaurant restaurant)
        ActionResult remove (String username,Restaurant restaurant)
         */
    }
}
