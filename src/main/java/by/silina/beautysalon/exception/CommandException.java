package by.silina.beautysalon.exception;

/**
 * The CommandException class.
 *
 * @author Silina Katsiaryna
 */
public class CommandException extends Exception {

    /**
     * Initializes a new CommandException.
     */
    public CommandException() {
        super();
    }

    /**
     * Initializes a new CommandException.
     *
     * @param message String
     */
    public CommandException(String message) {
        super(message);
    }

    /**
     * Initializes a new CommandException.
     *
     * @param message String
     * @param cause   Throwable
     */
    public CommandException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Initializes a new CommandException.
     *
     * @param cause Throwable
     */
    public CommandException(Throwable cause) {
        super(cause);
    }
}
