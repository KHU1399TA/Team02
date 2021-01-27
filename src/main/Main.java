package main ;
import main.others.Restaurant;
import utils.*;
import java.util.* ;
public class Main {
    Restaurant restaurant = new Restaurant();
    final static private String Data_cashier = "src/resources/cashier";
    final static private String Data_chef = "src/resources/chef";
    final static private String Data_client = "src/resources/client";
    final static private String Data_deliveryman = "src/resources/deliveryman";
    final static private String Data_foods = "src/resources/foods";
    final static private String Data_manager = "src/resources/manager";
    final static private String Data_orders = "src/resources/orders";

    public void load() {

    }
    public void save() {

    }

    public static void line() {
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
    }

    public static Scanner scanner = new Scanner(System.in);
    public static java.util.Date myClock = new java.util.Date();

    //public static Restaurant restaurant = new Restaurant();
    public static void main(String[] agrs) {
        System.out.println("Welcome");
        line();

        while (true) { //start page

            System.out.print("type your number: | login = 0   registeer = 1   back to start = 2| :");
            int start = scanner.nextInt();

            if (start == 0) {
                while (true) {
                    // get username & password
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
