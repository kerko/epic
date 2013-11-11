package nl.fontys.epic.util;

/**
 * Handles {@see Command} objects and delegates commands to them.
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.0
 * @version 1.0
 */
public interface CommandHandler extends Command {
    
    /**
     * Registers a new command
     * 
     * @param identifier command identifier
     * @param command command to add
     */
    void register(String identifier, Command command);    
}
