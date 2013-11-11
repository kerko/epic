package nl.fontys.epic.util;

import java.util.Arrays;
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
    
    private final Map<String, Command> commands;
    
    public SimpleCommandHandler() {
        commands = new HashMap<>();
    }

    @Override
    public void register(String identifier, Command command) {
        commands.put(identifier, command);
    }

    @Override
    public void handle(String commandString, TextAdventure adventure) throws CommandException {
        
        String[] args = commandString.split(" ");
        
        if (args.length == 0) {
            throw new CommandException("Command should not be empty");
        }
        
        Command command = commands.get(args[0]);
        
        if (command != null) {
            command.handle(reduceArgs(args), adventure);
        } else {
            throw new CommandException("Command '" + args[0] + "' not found.");
        }
        
        
    }

    @Override
    public int size() {
        return commands.size();
    }

    @Override
    public boolean isEmpty() {
        return commands.isEmpty();
    }
    
    
    static String[] reduceArgs(String[] args) {
        if (args.length <= 1)
            return new String[0];
        
        return Arrays.copyOfRange(args, 1, args.length);
    }
    
}
