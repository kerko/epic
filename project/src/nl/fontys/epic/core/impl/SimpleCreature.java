package nl.fontys.epic.core.impl;

import nl.fontys.epic.SimpleTextAdventure;
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
public class SimpleCreature extends SimpleGameObject implements Creature {

    private int life;
    private int maxLife;
    private final String name;

    private final Inventory inv;
    private boolean dead;
    private final Stats base;

    public SimpleCreature(int life, int maxLife, String name,  Inventory inv, Stats base, SimpleTextAdventure adventure, int posix,int posiy, String currentRoomID, String ID) {
        super(adventure, posix,posiy, currentRoomID, ID);
        this.life = life;
        this.maxLife = maxLife;
        this.name = name;
        this.inv = inv;
        this.base = base;
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
