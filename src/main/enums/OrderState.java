package main.enums;

public enum OrderState{
    MADE("Sefareshe shoma sabt shode",0),CONFIRMED("sefareshe shoma taiid shode ast",1),
    COOKED("sefareshe shoma pokhte shode ast",2),DELIVERED("sefareshe shoma ersal shode ast",3);
    String orderState;
    int orderStateNumber;
    OrderState(String orderState,int orderStateNumber) {
        this.orderState = orderState;
        this.orderStateNumber = orderStateNumber;
    }

    public String toString() {
        return String.valueOf(orderStateNumber);
    }
}
