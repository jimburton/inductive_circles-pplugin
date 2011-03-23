package icircles.util;

public class CannotDrawException extends Exception {

    private static final long serialVersionUID = 0L;
    public String message;

    public CannotDrawException(String message) {
        this.message = message;
    }
}
