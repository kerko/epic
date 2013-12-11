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
import java.util.HashMap;
import nl.fontys.epic.commands.AttackCommand;
import nl.fontys.epic.commands.DropCommand;
import nl.fontys.epic.commands.GoCommand;
import nl.fontys.epic.commands.OpenCommand;
import nl.fontys.epic.commands.UseCommand;
import nl.fontys.epic.core.AdventureListener;
import nl.fontys.epic.core.Player;
import nl.fontys.epic.core.Room;
import nl.fontys.epic.io.DataSource;
import nl.fontys.epic.io.DataSourceException;
import nl.fontys.epic.commands.Command;
import nl.fontys.epic.commands.CommandHandler;
import nl.fontys.epic.commands.CommandResponse;
import nl.fontys.epic.commands.EquipCommand;
import nl.fontys.epic.util.DeferredEntityLoader;
import nl.fontys.epic.util.DeferredStorage;
import nl.fontys.epic.util.Observer;
import nl.fontys.epic.util.ResourceManager;
import nl.fontys.epic.util.SharedResourceManager;
import nl.fontys.epic.commands.SimpleCommandHandler;
import nl.fontys.epic.util.SimpleObserver;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Jan
 */
public class TextAdventure extends SimpleObserver<AdventureListener> implements Observer<AdventureListener> {

    private final CommandHandler commandHandler;
    private boolean started;
    private DataSource source;
    private Player player;
    private HashMap<String, Room> rooms;

    public TextAdventure(DataSource source) {
        commandHandler = new SimpleCommandHandler();
        this.source = source;
        initDefaults();
    }

    public void registerCommand(String identifier, Command command) {
        commandHandler.register(identifier, command);
    }

    public Player getPlayer() {
        return player;
    }

    public Room getCurrentRoom() {
        return player.getRoom();
    }

    public Collection<Room> getRooms() {
        return rooms.values();
    }

    public Room getRoom(String ID) {
        return rooms.get(ID);
    }

    public String getName() {
        return source.getPath();
    }

    public void start() throws DataSourceException {
        
        started = true;
        ResourceManager resourceManager = SharedResourceManager.getInstance(getName());
        DeferredStorage storage = DeferredStorage.getInstance();
        DeferredEntityLoader entityLoader = new DeferredEntityLoader(resourceManager, storage);

        if (source == null) {
            throw new DataSourceException("No data source defined.");
        }

        NodeList list = source.parse();

        for (int i = 0; i < list.getLength(); ++i) {

            Node node = list.item(i);

            // TODO
            // Check for content            
            // Create factories
        }
        
        // Fill all entities with data
        try {
            entityLoader.load(this);
        } catch (DeferredEntityLoader.LoadingException ex) {
            throw new DataSourceException(ex);
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
        registerCommand("equip", new EquipCommand());
    }

    public boolean isRunning() {
       return started;
    }
}
