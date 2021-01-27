package main.others;

import main.enums.OrderState;

import java.util.Date;

public class Order {
    public int id;
    public String username;
    public int foodId;
    public OrderState state;
    public Date orderedAt;
    public Order(int id, String username, int foodId, OrderState state) {
        this.id = id;
        this.username = username;
        this.foodId = foodId;
        this.state = state;
        this.orderedAt = new Date();
    }

    @Override
    public String toString() {
        return "order [foodId=" + foodId + ", id=" + id + ", orderedAt=" + orderedAt + ", state=" + state
                + ", username=" + username + "]";
    }
    
}