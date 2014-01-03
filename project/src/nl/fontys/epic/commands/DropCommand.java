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

import nl.fontys.epic.SimpleTextAdventure;
import nl.fontys.epic.core.Inventory;
import nl.fontys.epic.core.Item;
import nl.fontys.epic.core.Player;
import nl.fontys.epic.core.Room;
import nl.fontys.epic.commands.CommandResponse.ResponseType;

/**
 * {@see Command} implementation for dropping items.
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.0
 * @version 1.0
 */
public class DropCommand implements Command {

    @Override
    public CommandResponse handle(String[] args, SimpleTextAdventure adventure) throws CommandException {
        if (args.length < 1 || args[0].trim().isEmpty()) {
            throw new CommandException("You have to specify something to drop");
        } else {
            String itemId = args[0];
            Player player = adventure.getPlayer();
            Inventory inventory = player.getInventory();
            Item item = inventory.fetch(itemId);
            if (item != null) {                
                Room room = adventure.getCurrentRoom();
                Inventory items = room.getItems(player.getPosition().x, player.getPosition().y);
                items.add(item);
                return new SimpleCommandResponse("You dropped " + itemId);
            } else {
                return new SimpleCommandResponse("Unable to drop " + itemId, ResponseType.ERROR);
            }
        }
    }

}
