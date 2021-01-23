package main ;
import java.util.* ;
public class Main {

    public static void khat(){
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
    }
    public static Scanner scanner = new Scanner(System.in);
    public static java.util.Date myclock = new java.util.Date();

    public static void main(String[] agrs){

        System.out.println("Welcome");
        khat();

        while (true) {

            System.out.print("type your number: | login = 0   registeer = 1   exit = 2| :");
            int start = scanner.nextInt();

            if(start==0){
                while (true){
                    // get username & password
                    // search in users
                    // open panel(with method) or print error or go back to start(with break)
                }
            }
            else if(start==1){
                while (true){
                    // for client: get f/n name & number & address & username & password(2x) | generate dates(regis & lastlog)

                }
            }
            else if(start==2){
                break;
            }
            else{
                System.out.println("error");
            }

        }
        khat();
        System.out.println("Have a nice day");
        khat();
    }
    void clientpanel(/*User client*/){
        /*
        show menu
        can make & revoke order ==> action result
        check my orders
         */
    }
    void cashierpanel(/*User cashier*/){
        /*
        check orders and change order state
         */
    }
    void chefpanel(/*User chef*/){
        /*
        add food(Food food)
        edit food(int id)
        remove food(int id)
        change food status(int id ,boolean isAvailble)
        cook (int id) (check order if state=confirm)
         */
    }
    void deliverymanpanel(/*User deliveryman*/){
        /*
        deliver (int id) (if state==cooked change to delivered)
         */
    }
    void managerpanel(/*User manager*/){
        /*
        register ()(rank except client)(add to files and reload array)
        edit (user name and password)
        remove
         */
    }
}
