package main.enums;

public enum OrderState{
    MADE("Sefareshe shoma amade ast"),CONFIRMED("sefareshe shoma taiid shode ast"),
    COOKED("sefareshe shoma pokhte shode ast"),DELIVERED("sefareshe shoma ersal shode ast");
    String orderState;

    OrderState(String orderState) {
        this.orderState = orderState;
    }

    @Override
    public String toString() {
        return orderState;
    }
}
