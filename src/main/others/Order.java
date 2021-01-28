package main.others;

import main.enums.OrderState;

import java.util.Date;

public class Order {
    public int id;
    public String username;
    public int foodId;
    public OrderState state;
    public Date orderedAt;
    public Order(int id, String username, int foodId, OrderState state,Date orderedAt) {
        this.id = id;
        this.username = username;
        this.foodId = foodId;
        this.state = state;
        this.orderedAt = orderedAt;
    }
    @Override
    public String toString() {
        return id + "_" + username + "_" + foodId + "_" + state + "_" + orderedAt + "_";
    }
}