package cz.cvut.fit.SemStew.backend;

public class CorrectnessController {
    // String contains only numbers check
    public static boolean OnlyNumbers(String input){
        if(input.matches("^[0-9]*$") && input.length() != 0)
            return true;
        return false;
    }
}
