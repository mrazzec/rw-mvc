package by.dorozhuk.rwmvc.exception;

public class UserAuthorizationServiceException extends Exception {

    public UserAuthorizationServiceException() {
        super();
    }

    public UserAuthorizationServiceException(String message) {
        super(message);
    }

    public UserAuthorizationServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserAuthorizationServiceException(Throwable cause) {
        super(cause);
    }

    protected UserAuthorizationServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
