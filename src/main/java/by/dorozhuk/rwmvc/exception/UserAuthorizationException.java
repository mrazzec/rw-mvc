package by.dorozhuk.rwmvc.exception;

public class UserAuthorizationException extends Exception {

    public UserAuthorizationException() {
        super();
    }

    public UserAuthorizationException(String message) {
        super(message);
    }

    public UserAuthorizationException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserAuthorizationException(Throwable cause) {
        super(cause);
    }

    protected UserAuthorizationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
