package main ;
import main.enums.AccessLevel;
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
        for(User i : restaurant.users){
            System.out.println(i.toString());
        }
        line();
        save();
        for(User i : restaurant.users){
            System.out.println(i.toString_show());
        }
        System.out.println("Welcome");
        line();

        while (true) { //start page

            System.out.print("type your number: | login = 0   registeer = 1   back to start = 2| :");
            int start = scanner.nextInt();

            if (start == 0) {
                while (true) {

                    // search in users
                    // open panel(with method) or print error or go back to start
                }
            } else if (start == 1) {
                while (true) {
                    // (for client) get f/n name & number & address & username & password(2x) | generate dates(regis & lastlog)

                }
            } else if (start == 2) {
                break;
            } else {
                System.out.print("please enter correct number : ");
            }

        }
        line();
        System.out.println("Have a nice day");
        line();
    }

    void clientpanel(/*User client*/) {
        /*
        show menu
        refresh menu
        Action result make order(Order order ,Restaurant restaurant)
        Action result revoke order(revoke order ,Restaurant restaurant)
        check my orders
         */
    }

    void cashierpanel(/*User cashier*/) {
        /*
        Action result confirmOrder(int id ,Restaurant restaurant)
         */
    }

    void chefpanel(/*User chef*/) {
        /*
        ActionResult add food(Food food ,Restaurant restaurant)
        ActionResult edit food(int id ,Restaurant restaurant)
        ActionResult remove food(int id ,Restaurant restaurant)
        ActionResult change food status(int id ,boolean isAvailble ,Restaurant restaurant)
        ActionResult cook (int id ,Restaurant restaurant) (check order if state=confirm)
         */
    }

    void deliverymanpanel(/*User deliveryman*/) {
        /*
        ActionResult deliver (int id ,Restaurant restaurant) (if state==cooked change to delivered)
         */
    }

    void managerpanel(/*User manager*/) {
        /*
        ActionResult register (User member ,Restaurant restaurant)(ranks except client)(add to files and reload array)
        ActionResult edit (String username ,String password ,Restaurant restaurant)
        ActionResult remove (String username,Restaurant restaurant)
         */
    }
}
