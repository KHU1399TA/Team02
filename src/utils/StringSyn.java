package utils;
import main.enums.*;

public class StringSyn {
    public static ActionResult checkusername(String s) {
        for (char i : s.toCharArray()) {
            int ascii = i;
            if ((48 <= ascii && ascii <= 57) || (65 <= ascii && ascii <= 90) || (97 <= ascii && ascii <= 122)) continue;
            else return ActionResult.INVALID_USERNAME;
        }
        return ActionResult.SUCCESS;
    }

    public static ActionResult checkpassword(String s) {
        for (char i : s.toCharArray()) {
            int ascii = i;
            if ((48 <= ascii && ascii <= 57) || (65 <= ascii && ascii <= 90) || (97 <= ascii && ascii <= 122)) continue;
            else return ActionResult.INVALID_PASSWORD;
        }
        return ActionResult.SUCCESS;
    }
}
