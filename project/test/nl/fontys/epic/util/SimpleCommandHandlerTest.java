/* The MIT License (MIT)
 * 
 * Copyright (c) 2013 Jan Kerkenhoff, Miguel Gonzalez
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package nl.fontys.epic.util;

import nl.fontys.epic.commands.impl.SimpleCommandHandler;
import nl.fontys.epic.commands.CommandException;
import nl.fontys.epic.commands.Command;
import nl.fontys.epic.commands.CommandResponse;
import nl.fontys.epic.impl.SimpleTextAdventure;
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
        handler.handle(command, null);
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
        public CommandResponse handle(String[] args, SimpleTextAdventure adventure) throws CommandException {            
            assertTrue("Command needs to be " + subcommand, subcommand.equals(args[0]));
            return null;
        }

        
    }
   
}
