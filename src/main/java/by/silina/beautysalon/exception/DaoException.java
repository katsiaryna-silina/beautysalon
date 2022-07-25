package by.silina.beautysalon.exception;

/**
 * The DaoException class.
 *
 * @author Silina Katsiaryna
 */
public class DaoException extends Exception {

    /**
     * Initializes a new DaoException.
     */
    public DaoException() {
    }

    /**
     * Initializes a new DaoException.
     *
     * @param message String
     */
    public DaoException(String message) {
        super(message);
    }

    /**
     * Initializes a new DaoException.
     *
     * @param message String
     * @param cause   Throwable
     */
    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Initializes a new DaoException.
     *
     * @param cause Throwable
     */
    public DaoException(Throwable cause) {
        super(cause);
    }
}
