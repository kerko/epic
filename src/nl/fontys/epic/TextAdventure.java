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

package nl.fontys.epic;

import nl.fontys.epic.core.AdventureEvent;
import nl.fontys.epic.core.AdventureListener;
import nl.fontys.epic.core.Player;
import nl.fontys.epic.core.Room;
import nl.fontys.epic.util.AdventureEventAdapter;
import nl.fontys.epic.util.Command;
import nl.fontys.epic.util.CommandHandler;
import nl.fontys.epic.util.CommandResponse;
import nl.fontys.epic.util.Observer;
import nl.fontys.epic.util.SimpleCommandHandler;
import nl.fontys.epic.util.SimpleObserver;

/**
 *
 * @author Jan
 */
public class TextAdventure extends SimpleObserver<AdventureListener> implements Observer<AdventureListener> {
    
    private final CommandHandler commandHandler;
    
    public TextAdventure() {
        commandHandler = new SimpleCommandHandler();
    }

    public void registerCommand(String identifier, Command command) {
        commandHandler.register(identifier, command);
    }
    
    public Player getPlayer() {
        // TODO
        return null;
    }
    
    public Room getCurrentRoom() {
        // TODO
        return null;
    }
    
    public void command(String command) {
        
         CommandResponse response = commandHandler.handle(command, this);
         
         for (AdventureListener l : getListeners()) {
             AdventureEvent event = new AdventureEventAdapter(response);
             l.onAction(event);
         }
    }
}
