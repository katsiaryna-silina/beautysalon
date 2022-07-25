package by.silina.beautysalon.exception;

/**
 * The ServiceException class.
 *
 * @author Silina Katsiaryna
 */
public class ServiceException extends Exception {

    /**
     * Initializes a new ServiceException.
     */
    public ServiceException() {
        super();
    }

    /**
     * Initializes a new ServiceException.
     *
     * @param message String
     */
    public ServiceException(String message) {
        super(message);
    }
    
    /**
     * Initializes a new ServiceException.
     *
     * @param message String
     * @param cause   Throwable
     */
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Initializes a new ServiceException.
     *
     * @param cause Throwable
     */
    public ServiceException(Throwable cause) {
        super(cause);
    }
}
