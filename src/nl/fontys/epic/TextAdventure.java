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

import java.util.Collection;
import nl.fontys.epic.commands.AttackCommand;
import nl.fontys.epic.commands.DropCommand;
import nl.fontys.epic.commands.GoCommand;
import nl.fontys.epic.commands.OpenCommand;
import nl.fontys.epic.commands.UseCommand;
import nl.fontys.epic.core.AdventureListener;
import nl.fontys.epic.core.Player;
import nl.fontys.epic.core.Room;
import nl.fontys.epic.io.DataSource;
import nl.fontys.epic.util.Command;
import nl.fontys.epic.util.CommandHandler;
import nl.fontys.epic.util.CommandResponse;
import nl.fontys.epic.util.Observer;
import nl.fontys.epic.util.SimpleCommandHandler;
import nl.fontys.epic.util.SimpleObserver;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Jan
 */
public class TextAdventure extends SimpleObserver<AdventureListener> implements Observer<AdventureListener> {
    
    private final CommandHandler commandHandler;
    
    private DataSource source;
    
    public TextAdventure(DataSource source) {
        commandHandler = new SimpleCommandHandler();
        this.source = source;
        initDefaults();
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
    
    public Collection<Room> getRooms() {
        // TODO
        return null;
    }
    
    public String getName() {
        return source.getPath();
    }
    
    public void start() {
        
        NodeList list = source.parse();
        
        for (int i = 0; i < list.getLength(); ++i) {
            
            Node node = list.item(i);
            
            // TODO
            // Check for content            
            // Create factories
        }
    }
    
    public void command(String command) {
        
         CommandResponse response = commandHandler.handle(command, this);
         
         for (AdventureListener l : getListeners()) {
             l.onAction(response);
         }
    }
    
    
    private void initDefaults() {
        registerCommand("attack", new AttackCommand());
        registerCommand("drop", new DropCommand());
        registerCommand("go", new GoCommand());
        registerCommand("open", new OpenCommand());
        registerCommand("use", new UseCommand());
    }

    public boolean isRunning() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
