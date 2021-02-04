package main.ranks;
import main.enums.*;
import main.others.Order;
import java.util.ArrayList;
import java.util.Date;
import main.Main;

public class Cashier extends User {

    public Cashier(String firstName, String lastName, String phonenumber, String username, String password, Date registrationDate, Date lastLoginDate, AccessLevel accessLevel) {
        super(firstName, lastName, phonenumber, username, password, registrationDate, lastLoginDate, accessLevel);
    }

    public static ActionResult confirmOrder(int id, ArrayList<Order> orders) {
        for (Order i : orders) {
            if (i.id == id && i.state == OrderState.COOKED) {
                return ActionResult.FOOD_ALREADY_COOKED;
            }
            else if (i.id == id && i.state == OrderState.DELIVERED) {
                return ActionResult.FOOD_ALREADY_DELIVERED;
            }
            else if (i.id == id && i.state == OrderState.MADE) {
                i.state = OrderState.CONFIRMED;
                Main.save();

                return ActionResult.SUCCESS;
            }
            else {
                System.out.println(i.id + "" + id + "&&" + i.state + "==" + OrderState.MADE);
            }
        }
        return ActionResult.UNKNOWN_ERROR;
    }
}
