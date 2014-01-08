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
package nl.fontys.epic.commands.impl;

import nl.fontys.epic.commands.Command;
import nl.fontys.epic.commands.CommandException;
import nl.fontys.epic.commands.Event;
import nl.fontys.epic.commands.impl.SimpleEvent;
import nl.fontys.epic.impl.SimpleTextAdventure;
import nl.fontys.epic.core.Player;
import nl.fontys.epic.core.RoomException;
import nl.fontys.epic.commands.Event.EventType;
import nl.fontys.epic.util.Direction;
import nl.fontys.epic.util.Position;

/**
 * Provides character movement with commands
 *
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.0
 * @version 1.0
 */
public class GoCommand implements Command {

    @Override
    public Event handle(String[] args, SimpleTextAdventure adventure) throws CommandException {
        if (args.length < 1 || args[0].trim().isEmpty()) {
            throw new CommandException("You have to specify a direction where to go!");
        } else {            
            String direction = args[0];
            return movePlayer(direction, adventure.getPlayer());
        }
    }
    
    private Event movePlayer(String direction, Player player) {
        
        Position pos = player.getPosition();
        Direction dir = Direction.translate(direction);
        
        if (dir.equals(Direction.NONE)) {
            return new SimpleEvent(direction + "? What is that? A direction?", EventType.ERROR);
        }
        try {
            player.move(dir);
        } catch (RoomException ex) {
            return new SimpleEvent("Can't move " + direction, EventType.ERROR);
        }
         return new SimpleEvent("Moved " + direction, EventType.INFO);
        
    }
    
 }