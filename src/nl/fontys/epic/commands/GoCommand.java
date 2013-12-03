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
package nl.fontys.epic.commands;

import java.util.logging.Level;
import java.util.logging.Logger;
import nl.fontys.epic.TextAdventure;
import nl.fontys.epic.core.Player;
import nl.fontys.epic.core.RoomException;
import nl.fontys.epic.util.Command;
import nl.fontys.epic.util.CommandException;
import nl.fontys.epic.util.CommandResponse;
import nl.fontys.epic.util.CommandResponse.ResponseType;
import nl.fontys.epic.util.Direction;
import nl.fontys.epic.util.Position;
import nl.fontys.epic.util.SimpleCommandResponse;

/**
 *
 * @author miguel
 */
public class GoCommand implements Command {

    @Override
    public CommandResponse handle(String[] args, TextAdventure adventure) throws CommandException {
        if (args.length < 1 || args[0].trim().isEmpty()) {
            throw new CommandException("You have to specify a direction where to go!");
        } else {            
            String direction = args[0];
            return movePlayer(direction, adventure.getPlayer());
        }
    }
    
    private CommandResponse movePlayer(String direction, Player player) {
        
        Position pos = player.getPosition();
        Direction dir = Direction.translate(direction);
        
        if (dir.equals(Direction.NONE)) {
            return new SimpleCommandResponse(direction + "? What is that? A direction?", ResponseType.ERROR);
        }
        try {
            player.move(dir);
        } catch (RoomException ex) {
            return new SimpleCommandResponse("Can't move " + direction, ResponseType.ERROR);
        }
         return new SimpleCommandResponse("Moved " + direction, ResponseType.INFO);
        
    }
    
 }
