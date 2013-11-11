package nl.fontys.epic.util;

import nl.fontys.epic.TextAdventure;

/**
 * Represents a single command which can be applied to a {@see CommandHandler}
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.0
 * @version 1.0
 */
public interface Command {
    
    /**
     * Handles the command in the {@see TextAdventure} context
     * 
     * @param commandString
     * @param adventure 
     */
    void handle(String[] args, TextAdventure adventure) throws CommandException;
}
