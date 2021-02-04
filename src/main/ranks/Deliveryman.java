package main.ranks;
import main.enums.*;
import main.others.Order;
import java.util.ArrayList;
import java.util.Date;
import main.Main;

public class Deliveryman extends User {
    public Deliveryman(String firstName, String lastName, String phonenumber, String username, String password, Date registrationDate, Date lastLoginDate, AccessLevel accessLevel) {
        super(firstName, lastName, phonenumber, username, password, registrationDate, lastLoginDate, accessLevel);
    }

    public static ActionResult deliverOrder(int id, ArrayList<Order> orders) {
        for (Order i : orders) {
            if (i.id == id && i.state == OrderState.COOKED) {
                i.state = OrderState.DELIVERED;
                Main.save();
                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.UNKNOWN_ERROR;
    }
}
