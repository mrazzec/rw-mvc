package by.dorozhuk.rwmvc.exception;

public class EqualsException extends Exception {

    public EqualsException() {
        super();
    }

    public EqualsException(String message) {
        super(message);
    }

    public EqualsException(String message, Throwable cause) {
        super(message, cause);
    }

    public EqualsException(Throwable cause) {
        super(cause);
    }

    protected EqualsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
