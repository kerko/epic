package nl.fontys.epic.util;

import java.util.HashMap;
import java.util.Map;
import nl.fontys.epic.TextAdventure;

/**
 * Simple implementation of {@see CommandHandler}
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.0
 * @version 1.0
 */
public class SimpleCommandHandler implements CommandHandler {
    
    private Map<String, Command> commands;
    
    public SimpleCommandHandler() {
        commands = new HashMap<>();
    }

    @Override
    public void register(String identifier, Command command) {
        commands.put(identifier, command);
    }

    @Override
    public void handle(String commandString, TextAdventure adventure) {
        
    }

    @Override
    public int size() {
        return commands.size();
    }

    @Override
    public boolean isEmpty() {
        return commands.isEmpty();
    }
    
}
