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
package nl.fontys.epic.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import nl.fontys.epic.TextAdventure;
import nl.fontys.epic.commands.impl.AttackCommand;
import nl.fontys.epic.commands.impl.DropCommand;
import nl.fontys.epic.commands.impl.GoCommand;
import nl.fontys.epic.commands.impl.UseCommand;
import nl.fontys.epic.core.AdventureListener;
import nl.fontys.epic.core.Player;
import nl.fontys.epic.core.Room;
import nl.fontys.epic.commands.Command;
import nl.fontys.epic.commands.CommandHandler;
import nl.fontys.epic.commands.Event;
import nl.fontys.epic.commands.Event.EventType;
import nl.fontys.epic.commands.impl.EquipCommand;
import nl.fontys.epic.commands.impl.OpenCommand;
import nl.fontys.epic.util.Observer;
import nl.fontys.epic.commands.impl.SimpleCommandHandler;
import nl.fontys.epic.commands.impl.SimpleEvent;
import nl.fontys.epic.util.SimpleObserver;

/**
 *
 * @author Jan
 */
public class SimpleTextAdventure extends SimpleObserver<AdventureListener> implements Observer<AdventureListener>, TextAdventure {

    private CommandHandler commandHandler;
    private Player player;
    private Map<String, Room> rooms;
    private String name;
    private String story;
    private boolean running;
    
    public SimpleTextAdventure(String name, Collection<Room> rooms, Player player) {
        this(name, null, rooms, player);
    }
    
    public SimpleTextAdventure() {
        this(null, null, new ArrayList<Room>(), null);
    }

    public SimpleTextAdventure(String name, String story, Collection<Room> rooms, Player player) {

        this.story = story;
        commandHandler = new SimpleCommandHandler();
        this.name = name;
        this.rooms = new HashMap<>();
        this.player = player;
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
        return name;
    }

    @Override
    public void command(String command) {

        Event response = commandHandler.handle(command, this);

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

    @Override
    public String getID() {
        return getName();
    }

    @Override
    public void start() {

        if (!isRunning() && story != null) {

            Event response = new SimpleEvent(story, EventType.INFO);

            for (AdventureListener l : getListeners()) {
                l.onAction(response);
            }
        }

        running = true;
    }

    @Override
    public void close() {
        running = false;
    }

    @Override
    public boolean isRunning() {
        return running;
    }
    
    
    // Setters for convinience
    
    public SimpleTextAdventure setPlayer(Player player) {
        this.player = player;
        return this;
    }
    
    public SimpleTextAdventure setRooms(Collection<Room> rooms) {
        this.rooms.clear();
        for (Room room : rooms) {
            this.rooms.put(room.getID(), room);
        }
        return this;
    }
    
    public SimpleTextAdventure setStory(String story) {
        this.story = story;
        return this;
    }
    
    public SimpleTextAdventure setName(String name) {
        this.name = name;
        return this;
    } 
}
