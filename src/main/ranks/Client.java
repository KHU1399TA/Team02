package main.ranks;
import main.enums.*;
import main.others.*;
import java.util.ArrayList;
import java.util.Date;

public class Client extends User{
    String address;

    public Client(String firstName, String lastName, String phonenumber, String username, String password, Date registrationDate, Date lastLoginDate, AccessLevel accessLevel, String address) {
        super(firstName, lastName, phonenumber, username, password, registrationDate, lastLoginDate, accessLevel);
        this.address = address;
    }

    @Override
    public String toString() {
        return firstName + "_" + lastName + "_" + phonenumber + "_" + username + "_" + password + "_" + registrationDate + "_" + lastLoginDate + "_" + accessLevel.get_num() + "_" + address + "_";
    }
    public String toString_show() {
        return firstName + "_" + lastName + "_" + phonenumber + "_" + username + "_" + password + "_" + registrationDate + "_" + lastLoginDate + "_" + accessLevel + "_" + address + "_";
    }
    public static ActionResult makeOrder(Order order, ArrayList<Order> orders,ArrayList<Food> menu){
        for(Order i : orders){
            if(order.foodId==i.foodId && order.username==i.username){
                return ActionResult.FOOD_ALREADY_EXIST;
            }
        }
        boolean check=false;
        for(Food i : menu){
            if(order.foodId==i.id){
                check=true;
            }
        }
        if(!check) return ActionResult.FOOD_NOT_FOUND;
        orders.add(order);  // ??????????????????
        return ActionResult.SUCCESS;
    }
    public static ActionResult revokeOrder(int id, ArrayList<Order> orders){
        for(Order i : orders){
            if(id==i.id){
                orders.remove(orders.indexOf(i));
                return ActionResult.SUCCESS;
            }
            return ActionResult.ORDER_NOT_FOUND;
        }
        return ActionResult.ORDER_NOT_FOUND;
    }
}
