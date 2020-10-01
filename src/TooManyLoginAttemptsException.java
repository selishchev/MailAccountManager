public class TooManyLoginAttemptsException extends Exception {
    public String toString()
    {
        return "Error. Too many login attempts.";
    }
}
