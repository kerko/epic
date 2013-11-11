package nl.fontys.epic.util;

import java.util.logging.Level;
import java.util.logging.Logger;
import nl.fontys.epic.TextAdventure;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*; 

/**
 * Test case for {@see SimpleCommandHandler}
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.0
 * @version 1.0
 */
public class SimpleCommandHandlerTest {
    
    SimpleCommandHandler handler;
    
    String command = "Hello World";
    String subcommand = "World";
    String key = "Hello";
    
    @Before
    public void setUp() {
        handler = new SimpleCommandHandler();
        handler.register(key, new CommandMock());
    }
    
    @Test
    public void testHandle() {
        try {
            handler.handle(command, null);
        } catch (CommandException ex) {
            fail(ex.getMessage());
        }
    }
    
    @Test
    public void testIsEmpty() {
        assertFalse("Handler should not be empty", handler.isEmpty());
    }
    
    @Test
    public void testGetSize() {
        assertTrue("Handler should have a size of 1", handler.size() == 1);
    }
    
    
    class CommandMock implements Command {

        @Override
        public void handle(String[] args, TextAdventure adventure) {
            assertTrue("Command needs to be " + subcommand, subcommand.equals(args[0]));
        }
        
    }
   
}
