package main.ranks;
import main.enums.*;
import main.others.*;
import java.util.ArrayList;
import java.util.Date;
import main.Main;

public class Chef extends User {
    public Chef(String firstName, String lastName, String phonenumber, String username, String password, Date registrationDate, Date lastLoginDate, AccessLevel accessLevel) {
        super(firstName, lastName, phonenumber, username, password, registrationDate, lastLoginDate, accessLevel);
    }

    public static ActionResult addFood(Food food, ArrayList<Food> menu) {
        for (Food i : menu) {
            if (i.id == food.id) {
                return ActionResult.FOOD_ALREADY_EXIST;
            }
            menu.add(food);
            Main.save();

            return ActionResult.SUCCESS;
        }
        return ActionResult.UNKNOWN_ERROR;
    }

    public static ActionResult editFood(int id, ArrayList<String> newIngredients, ArrayList<Food> menu) {
        for (Food i : menu) {
            if (id == i.id) {
                menu.set(menu.indexOf(i), (new Food(i.id, i.name, newIngredients, i.isAvailable)));
                Main.save();

                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.FOOD_NOT_FOUND;
    }

    public static ActionResult removeFood(int id, ArrayList<Food> menu) {
        for (Food i : menu) {
            if (id == i.id) {
                menu.remove(menu.indexOf(i));
                Main.save();

                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.UNKNOWN_ERROR;
    }

    public static ActionResult changeFoodStatus(int id, boolean bool, ArrayList<Food> menu) {
        for (Food i : menu) {
            if (id == i.id) {
                i.isAvailable = bool;
                Main.save();

                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.UNKNOWN_ERROR;
    }

    public static ActionResult cook(int id, ArrayList<Order> orders) {
        for (Order i : orders) {
            if (i.id == id && i.state == OrderState.CONFIRMED) {
                i.state = OrderState.COOKED;
                Main.save();

                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.UNKNOWN_ERROR;
    }
}
