package nl.fontys.epic.util;

import nl.fontys.epic.TextAdventure;

/**
 * Handles {@see Command} objects and delegates commands to them.
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.0
 * @version 1.0
 */
public interface CommandHandler {
    
    /**
     * Registers a new command
     * 
     * @param identifier command identifier
     * @param command command to add
     */
    void register(String identifier, Command command);   
    
    /**
     * Handles all commands
     * 
     * @param commandString
     * @param adventure 
     */
    void handle(String commandString, TextAdventure adventure) throws CommandException;
    
    /**
     * Determines the amount of commands in the handler
     * 
     * @return 
     */
    int size();
    
    /**
     * Determines if the handler is currently empty
     * 
     * @return 
     */
    boolean isEmpty();
}
