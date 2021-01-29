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
import java.util.concurrent.ConcurrentLinkedDeque;

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
            for(String j:line[2].split(",")){
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
    public static void res_show(){
        System.out.println(restaurant.users);
        System.out.println(restaurant.menu);
        System.out.println(restaurant.orders);
    }
    public static void main(String[] agrs) {
        load();
        res_show();
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
            System.out.print("type your number: | login = 0   registeer = 1   exit = 9| :");
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
            } else if (start == 9) {                //back to start
                break;
            } else {
                System.out.print("please enter correct number : ");
            }
        }
        line();
        System.out.println("Have a nice day");
        line();
    }

    static void clientpanel(User user) {
        Client client = new Client(user.firstName,user.lastName,user.phonenumber,user.username,user.password,user.registrationDate,user.lastLoginDate,user.accessLevel,"");
        System.out.println("---=== clientpanel ===---");
        int start;
        while (true){
            System.out.print("type your number: | show menu = 0    make order = 1    revoke order = 2    check my orders(all for now) = 3    exit = 9 | :");
            start=scanner.nextInt();
            if(start==0){
                for(Food i : restaurant.menu){
                    System.out.println(i.toString_show());
                }
                System.out.println();
            }
            else if(start==1){
                 System.out.println("enter your food id:");
                 int id=scanner.nextInt();
                 int orderId=0;
                 boolean unique;
                 for(int i=0;i<=100000000;i++){
                     unique=true;
                     for(Order j : restaurant.orders){
                         if(i==j.id){
                             unique=false;
                         }
                     }
                     if(unique==true){
                         orderId=i;
                         break;
                     }
                 }
                 Order order=new Order(orderId,client.username,id,OrderState.MADE,myClock);
                 save();
            }
            else if(start==2){
                System.out.println("enter your food id");
                int id=scanner.nextInt();
                System.out.println(client.revokeOrder(id,restaurant.orders));
                save();
            }
            else if(start==3){
                System.out.println(restaurant.orders);
            }
            else if(start==9)return;
            else System.out.println("please enter correct number : ");
        }
    }

    static void cashierpanel(User user) {
        Cashier cashier = new Cashier(user.firstName,user.lastName,user.phonenumber,user.username,user.password,user.registrationDate,user.lastLoginDate,user.accessLevel);
        System.out.println("---=== cashierpanel ===---");
        int start;
        while (true){
            System.out.print("type your number: | Confirm order = 0    exit = 9 | :");
            start=scanner.nextInt();
            if(start==0){
                System.out.println("enter Order id : ");
                int id=scanner.nextInt();
                System.out.println(cashier.confirmOrder(id,restaurant.orders));
            }
            else if(start==9)return;
            else System.out.println("please enter correct number : ");
            System.out.println(restaurant.orders);
        }
    }

    static void chefpanel(User user) {
        Chef chef = new Chef(user.firstName,user.lastName,user.phonenumber,user.username,user.password,user.registrationDate,user.lastLoginDate,user.accessLevel);
        System.out.println("---=== chefpanel ===---");
        int start;
        while (true){
            System.out.print("type your number: | add food = 0    edit food = 1    remove food = 2    change food status = 3    cook food = 4    exit = 9 | :");
            start=scanner.nextInt();
            if(start==0){
                System.out.println("..|enter: id,name,Ingredients number,List Ingredients,status(1=Available / 0=not Available)|..");
                int id=scanner.nextInt();
                String name=scanner.next();
                int number=scanner.nextInt();
                ArrayList<String> Ingredients = new ArrayList<String>();
                for(int i=0;i<number;i++){
                    Ingredients.add(scanner.next());
                }
                int isnumber=scanner.nextInt();
                boolean isAvailable=false;
                if(isnumber==0){
                    isAvailable=false;
                }
                else if(isnumber==1){
                    isAvailable=true;
                }
                else{
                    System.out.println(ActionResult.UNKNOWN_ERROR);
                }
                System.out.println(chef.addFood(new Food(id,name,Ingredients,isAvailable),restaurant.menu));
                System.out.println(restaurant.menu);
            }else if(start==1){
                System.out.println("..|enter: id,Ingredients number,List Ingredients|..");
                int id=scanner.nextInt();
                int number=scanner.nextInt();
                ArrayList<String> Ingredients = new ArrayList<String>();
                for(int i=0;i<number;i++){
                    Ingredients.add(scanner.next());
                }
                System.out.println(chef.editFood(id,Ingredients,restaurant.menu));
                System.out.println(restaurant.menu);
            }else if(start==2){
                System.out.println("..|enter: id|..");
                int id=scanner.nextInt();
                System.out.println(chef.removeFood(id,restaurant.menu));
                System.out.println(restaurant.menu);
            }else if(start==3){
                System.out.println("..|enter: id,status(1=Available / 0=not Available)|..");
                int id=scanner.nextInt();
                int statusid=scanner.nextInt();
                boolean bool=false;
                if(statusid==1){
                    bool=true;
                }else if(statusid==0){
                    bool=false;
                }
                System.out.println(chef.changeFoodStatus(id,bool,restaurant.menu));
                System.out.println(restaurant.menu);
            }else if(start==4){
                System.out.println("..|enter: id|..");
                int id=scanner.nextInt();
                System.out.println(chef.cook(id,restaurant.orders));
                System.out.println(restaurant.menu);
            }
            else if(start==9)return;
            else System.out.println("please enter correct number : ");
        }
    }

    static void deliverymanpanel(User user) {
        Deliveryman deliveryman = new Deliveryman(user.firstName,user.lastName,user.phonenumber,user.username,user.password,user.registrationDate,user.lastLoginDate,user.accessLevel);

        System.out.println("---=== deliverymanpanel ===---");
        int start;
        while (true){
            System.out.print("type your number: | Deliver order = 0    exit = 9 | :");
            start=scanner.nextInt();
            if(start==0){
                System.out.println("enter Order id : ");
                int id=scanner.nextInt();
                System.out.println(deliveryman.deliverOrder(id,restaurant.orders));
            }
            else if(start==9)return;
            else System.out.println("please enter correct number : ");
            System.out.println(restaurant.orders);
        }
    }
    static void managerpanel(User user) {
        Manager manager = new Manager(user.firstName,user.lastName,user.phonenumber,user.username,user.password,user.registrationDate,user.lastLoginDate,user.accessLevel);

        System.out.println("---=== managerpanel ===---");
        int start;
        while (true){
            System.out.print("type your number: | register = 0    edit = 1    remove = 2    exit = 9 | :");
            start=scanner.nextInt();
            if(start==0){
                System.out.println("..|enter: firstName,lastName,phoneNumber,username,password,accessLevel(client=0,cashier=1,chef=2,delivery=3)|..");
                String firstName=scanner.next();
                String lastName=scanner.next();
                String phonenumber=scanner.next();
                String username=scanner.next();
                String password=scanner.next();
                int accessLevelnumber=scanner.nextInt();
                switch (accessLevelnumber){
                    case 0:{
                        System.out.println("enter address");
                        manager.register(new Client(firstName,lastName,phonenumber,username,password,myClock,myClock,AccessLevel.CASHIER,scanner.next()),restaurant.users);
                        break;
                    }
                    case 1:{
                        manager.register(new Cashier(firstName,lastName,phonenumber,username,password,myClock,myClock,AccessLevel.CASHIER),restaurant.users);
                        break;
                    }
                    case 2:{
                        manager.register(new Chef(firstName,lastName,phonenumber,username,password,myClock,myClock,AccessLevel.CHEF),restaurant.users);
                        break;
                    }
                    case 3:{
                        manager.register(new Deliveryman(firstName,lastName,phonenumber,username,password,myClock,myClock,AccessLevel.DELIVERYMAN),restaurant.users);
                        break;
                    }
                }
            }
            if(start==1){
                System.out.println("type username:");
                String username=scanner.next();
                System.out.println("type your number: | changepass firstName = 0    change lastName= 1    change phonenumber= 2    change password= 3    change AccessLevel= 4    | :");
                int id=scanner.nextInt();
                if(id==4){
                    System.out.println("type your number: | client = 0    cashier = 1    chef = 2    delivery = 3    | :");
                    int accessLevelnum=scanner.nextInt();
                    manager.edit(username,accessLevelnum,restaurant.users);
                }
                else{
                    System.out.println("replace to:");
                    String string=scanner.next();
                    manager.edit(username,id,string,restaurant.users);
                }
            }
            if(start==2){
                System.out.println("type username:");
                String username=scanner.next();
                manager.remove(username,restaurant.users);
            }
            else if(start==9)return;
            else System.out.println("please enter correct number : ");
        }
    }
}
