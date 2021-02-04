package main.ranks;
import main.enums.*;
import main.others.*;
import main.Main;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Client extends User {
    String address;
    public SimpleDateFormat myformat = new SimpleDateFormat("dd/MM/yy");

    public Client(String firstName, String lastName, String phonenumber, String username, String password, Date registrationDate, Date lastLoginDate, AccessLevel accessLevel, String address) {
        super(firstName, lastName, phonenumber, username, password, registrationDate, lastLoginDate, accessLevel);
        this.address = address;
    }

    @Override
    public String toString() {
        return firstName + "_" + lastName + "_" + phonenumber + "_" + username + "_" + password + "_" + myformat.format(registrationDate) + "_" + myformat.format(lastLoginDate) + "_" + accessLevel.get_num() + "_" + address + "_";
    }

    @Override
    public String toString_show() {
        return accessLevel+":  |  F name : " + firstName + "  |  L name : " + lastName + "  |  phone number : " + phonenumber + "  |  username : " + username + "  |  password : " + password + "  |  reg Date : " + myformat.format(registrationDate) + "  |  last login Date : " + myformat.format(lastLoginDate) + "  |  address : "+address;
    }

    public static ActionResult makeOrder(Order order, ArrayList<Order> orders, ArrayList<Food> menu) {
        for (Order i : orders) {
            if (order.foodId == i.foodId && order.username == i.username) {
                return ActionResult.ORDER_ALREADY_EXIST;
            }
        }

        boolean check = false;

        for (Food i : menu) {
            if (order.foodId == i.id) {
                check = true;
                if (i.isAvailable == false) {
                    return ActionResult.FOOD_NOT_Available;
                }
                break;
            }
        }
        if (!check) return ActionResult.FOOD_NOT_FOUND;

        orders.add(order);  // ??????????????????
        Main.save();

        return ActionResult.SUCCESS;
    }

    public static ActionResult revokeOrder(int id, ArrayList<Order> orders, Client client) {
        for (Order i : orders) {
            if (id == i.id && i.username.equals(client.username)) {
                if(i.state==OrderState.COOKED){
                    return ActionResult.FOOD_ALREADY_COOKED;
                }
                else if(i.state==OrderState.DELIVERED){
                    return ActionResult.FOOD_ALREADY_DELIVERED;
                }
                orders.remove(orders.indexOf(i));
                Main.save();
                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.ORDER_NOT_FOUND;
    }
}
