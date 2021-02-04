package main.others;
import main.enums.OrderState;
import java.text.SimpleDateFormat;
import java.util.Date;
public class Order {
    public int id;
    public String username;
    public int foodId;
    public OrderState state;
    public Date orderedAt;
    public SimpleDateFormat myformat = new SimpleDateFormat("dd/MM/yy");

    public Order(int id, String username, int foodId, OrderState state, Date orderedAt) {
        this.id = id;
        this.username = username;
        this.foodId = foodId;
        this.state = state;
        this.orderedAt = orderedAt;
    }

    @Override
    public String toString() {
        return id + "_" + username + "_" + foodId + "_" + state.toItring() + "_" + myformat.format(orderedAt) + "_";
    }

    public String toString_show() {
        return "Order id : " + id + " | Food id : " + foodId + " | state : " + state.toString() + " | Date : " + myformat.format(orderedAt);
    }
}