package by.dorozhuk.rwmvc.exception;

public class IntersectionException extends Exception {

    public IntersectionException() {
        super();
    }

    public IntersectionException(String message) {
        super(message);
    }

    public IntersectionException(String message, Throwable cause) {
        super(message, cause);
    }

    public IntersectionException(Throwable cause) {
        super(cause);
    }

    protected IntersectionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
