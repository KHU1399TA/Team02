package main.others;
public class Order {
    public int id;
    public String username;
    public int foodId;
    public OrderState state;
    public Date orderedAt;
    public order(int id, String username, int foodId, OrderState state, Date orderedAt) {
        this.id = id;
        this.username = username;
        this.foodId = foodId;
        this.state = state;
        this.orderedAt = orderedAt;
    }

    @Override
    public String toString() {
        return "order [foodId=" + foodId + ", id=" + id + ", orderedAt=" + orderedAt + ", state=" + state
                + ", username=" + username + "]";
    }
    
}