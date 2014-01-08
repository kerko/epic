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
package nl.fontys.epic.core.impl;

import nl.fontys.epic.TextAdventure;
import nl.fontys.epic.impl.SimpleTextAdventure;
import nl.fontys.epic.core.Creature;
import nl.fontys.epic.core.Inventory;
import nl.fontys.epic.core.Room;
import nl.fontys.epic.core.RoomException;
import nl.fontys.epic.core.Stats;
import nl.fontys.epic.util.Direction;

/**
 *
 * @author Jan Kerkenhoff <jan.kerkenhoff@gmail.com>
 */
public class SimpleCreature extends SimpleGameObject implements Creature {

    private int life;
    private int maxLife;
    private final String name;

    private final Inventory inv;
    private boolean dead;
    private final Stats base;

    public SimpleCreature(int life, int maxLife, String name,  Inventory inv, Stats base, TextAdventure adventure, int posix,int posiy, String currentRoomID, String ID) {
        super(adventure, posix,posiy, ID);
        this.life = life;
        this.maxLife = maxLife;
        this.name = name;
        this.inv = new SimpleInventory(inv);
        this.base = base;
    }
    
    public SimpleCreature(Creature c) {
        this(c.getLife(), c.getMaxLife(), c.getName(), c.getInventory(), c.getStats(), c.getAdventure(), c.getX(), c.getY(), c.getRoom().getID(), c.getID());
    }

    @Override
    public void move(Direction direction) throws RoomException {
        Room room = this.getRoom();
        
        switch (direction) {
            case NORTH:
                room.moveObject(this, posi.x, posi.y++);
                break;
            case EAST:
                room.moveObject(this, posi.x++, posi.y);
                break;
            case SOUTH:
                room.moveObject(this, posi.x, posi.y--);
                break;
            case WEST:
                room.moveObject(this, posi.x--, posi.y);
                break;
        }
        

    }

    @Override
    public void damage(int damage) {
        life = life - (damage - getStats().getDefense()/3 );
        if (life <= 0) {
            this.kill();
        }
    }

    @Override
    public void kill() {
        Room room = this.getRoom();
        Inventory inventory = room.getItems(posi.x, posi.y);
        inventory.add(this.inv);
        room.removeObject(posi.x, posi.y);
        this.dead = true;

    }

    @Override
    public boolean isDead() {
        return this.dead;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Stats getStats() {      
        return base;
    }

    @Override
    public int getLife() {
        return life;
    }

    @Override
    public Inventory getInventory() {
        return this.inv;
    }


    @Override
    public int getMaxLife() {
       return this.maxLife;
    }

    @Override
    public void addlife(int ammount) {
        this.life = getLife()+ammount;
        if(life > getMaxLife()){
            life = getMaxLife();
        }
    }

   

}
