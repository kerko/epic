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

import nl.fontys.epic.TextAdventure;
import nl.fontys.epic.core.Creature;
import nl.fontys.epic.core.Inventory;
import nl.fontys.epic.core.Item;
import nl.fontys.epic.core.Player;
import nl.fontys.epic.core.Room;
import nl.fontys.epic.commands.CommandResponse.ResponseType;
import nl.fontys.epic.util.ResourceManager;
import nl.fontys.epic.util.SharedResourceManager;

/**
 *
 * @author miguel
 */
public class AttackCommand implements Command {

    @Override
    public CommandResponse handle(String[] args, TextAdventure adventure) throws CommandException {
        ResourceManager manager = SharedResourceManager.getInstance(adventure.getName());
        if (args.length == 0) {
            throw new CommandException("You have to select a Target");
        }
        Creature creature = manager.get(args[0], Creature.class);
        if (creature == null) {
            throw new CommandException("You have to select a Creature");
        }
        Player player = adventure.getPlayer();
        int creatureHealth = creature.getLife();
        int playerHealth = creature.getLife();

        if (attack(player, creature)) {
            return this.processDeath(creature, adventure);           
        }
        if (attack(creature, player)) {
            return this.processDeath(player, adventure);
        }

        return new SimpleCommandResponse("You hit " + creature.getName() + "for " + (creatureHealth - creature.getLife())
                + ". You took" + (playerHealth - player.getLife()) + 
                "Damage from" + creature.getName() + ".", ResponseType.INFO);

    }

    private boolean attack(Creature cr1, Creature cr2) {
        int damage = cr1.getStats().getPower();
        cr2.damage(damage);
        return cr2.isDead();
    }

    private SimpleCommandResponse processDeath(Creature cr, TextAdventure adventure) {
        SimpleCommandResponse response = new SimpleCommandResponse(cr.getName() + " died,", ResponseType.INFO);
        response = drop(cr, adventure, response);
        cr.kill();
        return response;
    }

    private SimpleCommandResponse drop(Creature cr, TextAdventure adventure, SimpleCommandResponse response) {
        Room room = adventure.getCurrentRoom();

        Inventory items = cr.getInventory();
        if (!items.isEmpty()) {

            Inventory roomInventory = room.getItems(cr.getPosition().x, cr.getPosition().y);
            response.addEntry(cr.getName() + "dropped:");
            for (Item item : items.getItems()) {
                roomInventory.add(item);
                response.addEntry(item.getName());

            }

        }
        return response;

    }

}
