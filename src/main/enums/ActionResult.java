package main.enums;

public enum ActionResult {
    SUCCESS("Ba movafaghiat anjam shod"),
    INVALID_USERNAME("Baraye username faghat az a-z va A-Z va adad mitavanid estefade konid"),
    INVALID_PASSWORD("Baraye password faghat az a-z va A-Z va adad mitavanid estefade konid"),
    USERNAME_NOT_FOUND("Username peyda nashod"), WRONG_PASSWORD("password e username eshtebah ast"),
    USERNAME_ALREADY_EXIST("Username tekrari ast"), FOOD_NOT_FOUND("Hamchin ghazayi nadarim"),
    FOOD_ALREADY_EXIST("In ghaza ghablan ezafe shode"), ORDER_NOT_FOUND("Sefaresh peyda nashod"),
    ORDER_ALREADY_EXIST("Sefaresh tekrari ast"), UNKNOWN_ERROR("ERROR"),
    FOOD_NOT_Available("qaza tamoom shode bezoodi mojood mishavad"),
    FOOD_ALREADY_COOKED("qaza pokhte shode!"), FOOD_ALREADY_DELIVERED("qaza ersal shode!");

    String actionResult;

    ActionResult(String actionResult) {
        this.actionResult = actionResult;
    }

    @Override
    public String toString() {
        return actionResult;
    }
}


