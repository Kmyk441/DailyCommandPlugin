package me.kmyk.dailycommand;

public class TimeUtils {

    public static int getMinutes(String string) {
        return Integer.parseInt(string.substring(string.indexOf(':') + 1));
    }

    public static int getHours(String string) {
        return Integer.parseInt(string.substring(0, string.indexOf(':')));
    }

    public static boolean isValidTime(String string) {
        int index = string.indexOf(':');
        if (index == -1) return false;
        String left = string.substring(0, index);
        String right = string.substring(index + 1);
        if (left.isEmpty() || left.length() > 2) return false;
        if (right.length() != 2) return false;

        int leftInt;
        int rightInt;

        try {
            leftInt = Integer.parseInt(left);
            rightInt = Integer.parseInt(right);
        } catch (NumberFormatException e) {
            return false;
        }

        if (leftInt < 0 || leftInt > 23) return false;
        if (rightInt < 0 || rightInt > 59) return false;

        return true;
    }

}
