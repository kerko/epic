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
import nl.fontys.epic.commands.Command;
import nl.fontys.epic.core.Player;
import nl.fontys.epic.core.Room;

/**
 * Text adventure which provides game logic for a custom game.
 *
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.0
 * @version 1.0
 */
public interface TextAdventure {

    /**
     * Registers a new command to this text adventure.
     *
     * @param identifier identifier of the command
     * @param command command to register
     */
    void registerCommand(String identifier, Command command);

    /**
     * Returns the current player of this text adventure
     *
     * @return current player instance
     */
    Player getPlayer();

    /**
     * Returns the room where the actual player is currently in
     *
     * @return current room
     */
    Room getCurrentRoom();

    /**
     * Returns all rooms which are indicated by this game
     *
     * @return all rooms of this game
     */
    Collection<Room> getRooms();

    /**
     * Returns the room with the given id. Returns null when no room could not
     * be found.
     *
     * @param ID room ID
     * @return room instance with the given ID
     */
    Room getRoom(String ID);

    /**
     * Returns the name of this game
     *
     * @return name
     */
    String getName();

    /**
     * Executes a new command
     *
     * @param command command string
     */
    void command(String command);

}
