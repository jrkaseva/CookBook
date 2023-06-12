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
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message of exception
     */
    public ParseException(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param cause of exception
     */
    public ParseException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message of exception
     * @param cause of exception
     */
    public ParseException(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
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
        // TODO Auto-generated constructor stub
    }

}
