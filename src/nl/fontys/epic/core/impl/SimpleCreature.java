package nl.fontys.epic.core.impl;

import nl.fontys.epic.TextAdventure;
import nl.fontys.epic.core.Creature;
import nl.fontys.epic.core.GameObject;
import nl.fontys.epic.core.Inventory;
import nl.fontys.epic.core.Item;
import nl.fontys.epic.core.Room;
import nl.fontys.epic.core.RoomException;
import nl.fontys.epic.core.Stats;
import nl.fontys.epic.util.Direction;
import nl.fontys.epic.util.Position;

/**
 *
 * @author Jan Kerkenhoff <jan.kerkenhoff@gmail.com>
 */
public class SimpleCreature implements Creature {

    private int life;
    private final String name;
    private final String ID;
    private final Inventory inv;
    private Position posi;
    private boolean dead;
    private final Stats base;
    private final TextAdventure adventure;
    private String currentRoomID;

    public SimpleCreature(int life, String name, Inventory inv, Stats base, TextAdventure adventure, String currentRoomID, int posix, int posiy, String ID) {
        this.life = life;
        this.name = name;
        this.inv = inv;
        this.base = base;
        this.adventure = adventure;
        this.currentRoomID = currentRoomID;
        this.posi.x = posix;
        this.posi.y = posiy;
        this.ID = ID;
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
        life = life - damage;
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
        Stats current = base;
        for (Item item : inv.getItems()) {
            base.addStats(item.getStats());
        }
        return current;
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
    public Position getPosition() {
        return this.posi;
    }

    @Override
    public Room getRoom() {
        return adventure.getRoom(currentRoomID);
    }

    @Override
    public void setPosition(int x, int y) {
        this.posi.x = x;
        this.posi.y = y;
    }

    @Override
    public boolean isInfrontOf(GameObject object) {
        Room otherRoom = object.getRoom();
        if(otherRoom == this.getRoom()){
            if(Math.abs(object.getPosition().y-this.posi.y) == 1 
                    && Math.abs(object.getPosition().x-this.posi.x) == 1){
                return true;
            }
                
        }
        return false;
    }

    @Override
    public String getID() {
        return this.ID;
    }

}
