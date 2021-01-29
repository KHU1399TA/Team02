package main.ranks;

import main.enums.AccessLevel;
import main.enums.ActionResult;
import main.enums.OrderState;
import main.others.Food;
import main.others.Order;

import java.util.ArrayList;
import java.util.Date;

public class Chef extends User{
    public Chef(String firstName, String lastName, String phonenumber, String username, String password, Date registrationDate, Date lastLoginDate, AccessLevel accessLevel) {
        super(firstName, lastName, phonenumber, username, password, registrationDate, lastLoginDate, accessLevel);
    }
    public static ActionResult addFood(Food food, ArrayList<Food> menu){
        for(Food i:menu){
            if(i.id==food.id){
                return ActionResult.FOOD_ALREADY_EXIST;
            }
            menu.add(food);
            return ActionResult.SUCCESS;
        }
        return ActionResult.UNKNOWN_ERROR;
    }
    public static ActionResult editFood(int id,ArrayList<String> newIngredients, ArrayList<Food> menu){
        for(Food i : menu){
            if(id==i.id){
                i = new Food(i.id,i.name,newIngredients,i.isAvailable);
            }
        }
        return ActionResult.FOOD_NOT_FOUND;
    }
    public static ActionResult removeFood(int id,ArrayList<Food> menu){
        for(Food i :menu){
            if(id==i.id){
                menu.remove(menu.indexOf(i));
                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.UNKNOWN_ERROR;
    }
    public static ActionResult changeFoodStatus(int id,boolean bool, ArrayList<Food> menu){
        for(Food i : menu){
            if(id==i.id){
                i.isAvailable=bool;
            }
        }
        return ActionResult.UNKNOWN_ERROR;
    }
    public static ActionResult cook(int id, ArrayList<Order> orders){
        for(Order i : orders){
            if(i.id==id && i.state== OrderState.CONFIRMED){
                i.state= OrderState.COOKED;
                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.UNKNOWN_ERROR;
    }
}
