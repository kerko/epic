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
import nl.fontys.epic.commands.CommandResponse;
import nl.fontys.epic.impl.SimpleTextAdventure;
import nl.fontys.epic.core.Inventory;
import nl.fontys.epic.core.Item;
import nl.fontys.epic.commands.CommandResponse.ResponseType;
import nl.fontys.epic.util.Consumable;

/**
 * Uses an item and throws it away
 *
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.0
 * @version 1.0
 */
public class UseCommand implements Command {

    @Override
    public CommandResponse handle(String[] args, SimpleTextAdventure adventure) throws CommandException {
        if (args.length == 0 || args[0].trim().isEmpty()) {
            throw new CommandException("You have to select something to use");
        } else {
            String itemId = args[0];
            Inventory inventory = adventure.getPlayer().getInventory();
            
            if (inventory.contains(itemId)) {
                Item item = inventory.get(itemId);
                
                if (item instanceof Consumable) {
                    ((Consumable)item).use(adventure);
                    inventory.remove(itemId);
                    return new SimpleCommandResponse("You successfully used " + itemId);
                } else {
                    return new SimpleCommandResponse("You can't use " + itemId, ResponseType.ERROR);
                }
            } else {
                return new SimpleCommandResponse("You don't have any item called " + itemId, ResponseType.ERROR);
            }            
        }
    }

}
