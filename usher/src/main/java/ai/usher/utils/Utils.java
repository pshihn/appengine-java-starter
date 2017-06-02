package ai.usher.utils;

import java.util.Calendar;

public class Utils {
    public static long now() {
        return Calendar.getInstance().getTime().getTime();
    }
}
