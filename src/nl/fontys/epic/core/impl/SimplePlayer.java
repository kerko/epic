
package nl.fontys.epic.core.impl;

import nl.fontys.epic.TextAdventure;
import nl.fontys.epic.core.Equip;
import nl.fontys.epic.core.GameObject;
import nl.fontys.epic.core.Inventory;
import nl.fontys.epic.core.Player;
import nl.fontys.epic.core.Room;
import nl.fontys.epic.core.RoomException;
import nl.fontys.epic.core.Stats;
import nl.fontys.epic.util.Direction;
import nl.fontys.epic.util.Position;

/**
 *
 * @author Jan Kerkenhoff <jan.kerkenhoff@gmail.com>
 */
public class SimplePlayer extends SimpleCreature implements Player{
    private Equip equip;

    public SimplePlayer(Equip equip, int life, int maxLife, String name, Inventory inv, Stats base, TextAdventure adventure, int posix, int posiy, String currentRoomID, String ID) {
        super(life, maxLife, name, inv, base, adventure, posix, posiy, currentRoomID, ID);
        this.equip = equip;
    }
  
   

    @Override
    public Equip getEquip() {
        return this.equip;
    }

   

    @Override
    public Stats getStats() {
       Stats stats =super.getStats();
       stats.addStats(equip.getStats());
       return stats;
    }

    @Override
    public void damage(int damage) {
        super.damage(damage); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getMaxLife() {
       return getMaxLife()+ equip.getLifeBonus();
    }

    
    

    

    
    
}
