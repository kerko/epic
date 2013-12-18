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
import java.util.Map;
import nl.fontys.epic.commands.AttackCommand;
import nl.fontys.epic.commands.DropCommand;
import nl.fontys.epic.commands.GoCommand;
import nl.fontys.epic.commands.OpenCommand;
import nl.fontys.epic.commands.UseCommand;
import nl.fontys.epic.core.AdventureListener;
import nl.fontys.epic.core.Player;
import nl.fontys.epic.core.Room;
import nl.fontys.epic.commands.Command;
import nl.fontys.epic.commands.CommandHandler;
import nl.fontys.epic.commands.CommandResponse;
import nl.fontys.epic.commands.EquipCommand;
import nl.fontys.epic.util.Observer;
import nl.fontys.epic.commands.SimpleCommandHandler;
import nl.fontys.epic.util.SimpleObserver;

/**
 *
 * @author Jan
 */
public class SimpleTextAdventure extends SimpleObserver<AdventureListener> implements Observer<AdventureListener>, TextAdventure {

    private final CommandHandler commandHandler;
    private boolean started;
    private Player player;
    private Map<String, Room> rooms;

    public SimpleTextAdventure(Collection<Room> rooms, Player player) {
        commandHandler = new SimpleCommandHandler();
        this.rooms = new HashMap< >();
        for (Room room : rooms) {
            this.rooms.put(room.getID(), room);
        }
        
        initDefaults();
    }

    @Override
    public void registerCommand(String identifier, Command command) {
        commandHandler.register(identifier, command);
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public Room getCurrentRoom() {
        return player.getRoom();
    }

    @Override
    public Collection<Room> getRooms() {
        return rooms.values();
    }

    @Override
    public Room getRoom(String ID) {
        return rooms.get(ID);
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public void start() {
    }

    @Override
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

    @Override
    public void close() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
