package nl.fontys.epic.util;

/**
 * Is thrown when a command is corrupt or an error occurred
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.0
 * @version 1.0
 */
public class CommandException extends Exception {

    public CommandException(String message) {
        super(message);
    }

    public CommandException(Throwable cause) {
        super(cause);
    }
    
    
}
