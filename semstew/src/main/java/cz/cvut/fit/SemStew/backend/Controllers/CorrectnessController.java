package cz.cvut.fit.SemStew.backend.Controllers;

/**
 * @author DreamTeam SemStew
 * @version 1.0
 * @since 0.5
 */
public class CorrectnessController {
    /**
     * Method for checking string for numbers only
     *
     * Use {@link #OnlyNumbers(String input)} to determine if input string contains only numbers
     *
     * @param input String to be checked
     * @return true if input contains only numbers, else false
     */
    public static boolean OnlyNumbers(String input){
        if(input.matches("^[0-9]*$") && input.length() != 0)
            return true;
        return false;
    }

    /**
     * Method to check valid Email format
     *
     * Use {@link #ValidEmail(String email)} to determine if input string has valid Email format
     *
     * @param email String to be checked
     * @return true if input has correct Email format, false otherwise
     */
    public static boolean ValidEmail(String email){
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

    /**
     * Method to check valid Time format HH:MM
     *
     * Use {@link #ValidTime(String time)} to determine if input string has valid Time format
     *
     * @param time String to be checked
     * @return true if input has valid Time format, else false
     */
    public static boolean ValidTime(String time){
        String tPattern = "([01]?[0-9]|2[0-3]):[0-5][0-9]";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(tPattern);
        java.util.regex.Matcher m = p.matcher(time);
        return m.matches();
    }
}
