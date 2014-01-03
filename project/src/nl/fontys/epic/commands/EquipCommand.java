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
import nl.fontys.epic.core.Equip;
import nl.fontys.epic.core.Inventory;
import nl.fontys.epic.core.Item;
import nl.fontys.epic.core.Player;
import nl.fontys.epic.core.Equipable;

/**
 * {@see Command} implementation for equipping items.
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.0
 * @version 1.0
 */
public class EquipCommand implements Command {

    @Override
    public CommandResponse handle(String[] args, SimpleTextAdventure adventure) throws CommandException {
        if (args.length < 1 || args[0].trim().isEmpty()) {
            throw new CommandException("You have to specify a direction where to go!");
        }
        
        // Initialization
        String itemId = args[0];
        Player player = adventure.getPlayer();
        Inventory inventory = player.getInventory();
        
        // Check if it's an equippable item
        Item item = inventory.get(itemId);
        
        if (item instanceof Equipable) {
            Equipable equipItem = (Equipable)item;
            Equip equip = player.getEquip();
            
            Equipable current = equip.attach(equipItem);
            
            if (current != null && current instanceof Item) {
                inventory.add((Item)current);
                return new SimpleCommandResponse("You switched " + itemId + " with " + ((Item)current).getID());
            } else {
                return new SimpleCommandResponse("You equipped " + itemId);
                
            }
        } else {
            throw new CommandException(itemId + " can not be equipped.");
        }
    }
    
}
