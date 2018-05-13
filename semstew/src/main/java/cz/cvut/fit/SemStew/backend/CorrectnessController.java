package cz.cvut.fit.SemStew.backend;

public class CorrectnessController {
    // String contains only numbers check
    public static boolean OnlyNumbers(String input){
        if(input.matches("^[0-9]*$") && input.length() != 0)
            return true;
        return false;
    }

    public static boolean ValidEmail(String email){
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }
}
