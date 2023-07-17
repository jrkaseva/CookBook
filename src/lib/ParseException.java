/**
 * 
 */
package lib;

/**
 * @author tietokone
 * @version 12.6.2023
 *
 */
public class ParseException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public ParseException() {
        super("Error when parsing");
    }

    /**
     * @param message of exception
     */
    public ParseException(String message) {
        super(message);
    }

    /**
     * @param cause of exception
     */
    public ParseException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message of exception
     * @param cause of exception
     */
    public ParseException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message of exception
     * @param cause of exception
     * @param enableSuppression true if ignore exception
     * @param writableStackTrace true if writable
     */
    public ParseException(String message, Throwable cause,
            boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
